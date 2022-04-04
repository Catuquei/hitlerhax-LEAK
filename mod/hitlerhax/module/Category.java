// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module;

public enum Category
{
    COMBAT("Combat"), 
    RENDER("Render"), 
    MOVEMENT("Movement"), 
    PLAYER("Player"), 
    UTILITIES("Utilities"), 
    HUD("Hud"), 
    CLIENT("Client");
    
    public final String name;
    
    private Category(final String name) {
        this.name = name;
    }
}
