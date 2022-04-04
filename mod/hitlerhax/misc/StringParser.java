// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.misc;

public class StringParser
{
    public static boolean isInteger(final String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public static boolean isFloat(final String s) {
        try {
            Float.parseFloat(s);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
