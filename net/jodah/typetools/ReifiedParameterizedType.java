// 
// Decompiled by Procyon v0.5.36
// 

package net.jodah.typetools;

import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;

class ReifiedParameterizedType implements ParameterizedType
{
    private final ParameterizedType original;
    private final Type[] reifiedTypeArguments;
    private final boolean[] loop;
    private int reified;
    
    ReifiedParameterizedType(final ParameterizedType original) {
        this.reified = 0;
        this.original = original;
        this.reifiedTypeArguments = new Type[original.getActualTypeArguments().length];
        this.loop = new boolean[original.getActualTypeArguments().length];
    }
    
    void addReifiedTypeArgument(final Type type) {
        if (this.reified >= this.reifiedTypeArguments.length) {
            return;
        }
        if (type == this) {
            this.loop[this.reified] = true;
        }
        this.reifiedTypeArguments[this.reified++] = type;
    }
    
    @Override
    public Type[] getActualTypeArguments() {
        return this.reifiedTypeArguments;
    }
    
    @Override
    public Type getRawType() {
        return this.original.getRawType();
    }
    
    @Override
    public Type getOwnerType() {
        return this.original.getOwnerType();
    }
    
    @Override
    public String toString() {
        final Type ownerType = this.getOwnerType();
        final Type rawType = this.getRawType();
        final Type[] actualTypeArguments = this.getActualTypeArguments();
        final StringBuilder sb = new StringBuilder();
        if (ownerType != null) {
            if (ownerType instanceof Class) {
                sb.append(((Class)ownerType).getName());
            }
            else {
                sb.append(ownerType.toString());
            }
            sb.append("$");
            if (ownerType instanceof ParameterizedType) {
                sb.append(rawType.getTypeName().replace(((ParameterizedType)ownerType).getRawType().getTypeName() + "$", ""));
            }
            else if (rawType instanceof Class) {
                sb.append(((Class)rawType).getSimpleName());
            }
            else {
                sb.append(rawType.getTypeName());
            }
        }
        else {
            sb.append(rawType.getTypeName());
        }
        if (actualTypeArguments != null && actualTypeArguments.length > 0) {
            sb.append("<");
            for (int i = 0; i < actualTypeArguments.length; ++i) {
                if (i != 0) {
                    sb.append(", ");
                }
                final Type t = actualTypeArguments[i];
                if (i >= this.reified) {
                    sb.append("?");
                }
                else if (t == null) {
                    sb.append("null");
                }
                else if (this.loop[i]) {
                    sb.append("...");
                }
                else {
                    sb.append(t.getTypeName());
                }
            }
            sb.append(">");
        }
        return sb.toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final ReifiedParameterizedType that = (ReifiedParameterizedType)o;
        if (!this.original.equals(that.original)) {
            return false;
        }
        if (this.reifiedTypeArguments.length != that.reifiedTypeArguments.length) {
            return false;
        }
        for (int i = 0; i < this.reifiedTypeArguments.length; ++i) {
            if (this.loop[i] != that.loop[i]) {
                return false;
            }
            if (!this.loop[i]) {
                if (this.reifiedTypeArguments[i] != that.reifiedTypeArguments[i]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int result = this.original.hashCode();
        for (int i = 0; i < this.reifiedTypeArguments.length; ++i) {
            if (!this.loop[i]) {
                if (this.reifiedTypeArguments[i] instanceof ReifiedParameterizedType) {
                    result = 31 * result + this.reifiedTypeArguments[i].hashCode();
                }
            }
        }
        return result;
    }
}
