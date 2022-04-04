// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.misc;

import mod.hitlerhax.util.Timer;
import mod.hitlerhax.util.Globals;

public class Rotation implements Globals
{
    public float yaw;
    public float pitch;
    public RotationPriority rotationPriority;
    public RotationMode mode;
    public final Timer rotationStay;
    
    public Rotation(final float yaw, final float pitch, final RotationMode mode, final RotationPriority rotationPriority) {
        this.rotationStay = new Timer();
        this.yaw = yaw;
        this.pitch = pitch;
        this.mode = mode;
        this.rotationPriority = rotationPriority;
        this.rotationStay.reset();
    }
    
    public void updateRotations() {
        try {
            switch (this.mode) {
                case Packet: {
                    Rotation.mc.field_71439_g.field_70761_aq = this.yaw;
                    Rotation.mc.field_71439_g.field_70759_as = this.yaw;
                    break;
                }
                case Legit: {
                    Rotation.mc.field_71439_g.field_70177_z = this.yaw;
                    Rotation.mc.field_71439_g.field_70125_A = this.pitch;
                    break;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public enum RotationMode
    {
        Packet, 
        Legit, 
        None;
    }
}
