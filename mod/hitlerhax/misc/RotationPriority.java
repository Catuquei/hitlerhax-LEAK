// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.misc;

public enum RotationPriority
{
    Highest(200), 
    High(100), 
    Normal(0), 
    Low(-100), 
    Lowest(-200);
    
    final int priority;
    
    private RotationPriority(final int priority) {
        this.priority = priority;
    }
    
    public int getPriority() {
        return this.priority;
    }
}
