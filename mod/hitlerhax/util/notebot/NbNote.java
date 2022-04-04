// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util.notebot;

import net.minecraft.util.math.BlockPos;

public class NbNote
{
    private int knownpitch;
    private final int pitch;
    private boolean validated;
    private final BlockPos pos;
    
    public NbNote(final int pitch, final BlockPos pos) {
        this.knownpitch = -1;
        this.pitch = pitch;
        this.pos = pos;
    }
    
    public void SetKnownPitch(final int pitch) {
        this.knownpitch = pitch;
    }
    
    public int GetKnownPitch() {
        return this.knownpitch;
    }
    
    public int GetPitch() {
        return this.pitch;
    }
    
    public BlockPos GetPosition() {
        return this.pos;
    }
    
    public boolean IsValidated() {
        return this.validated;
    }
    
    public void SetValidated(final boolean validated) {
        this.validated = validated;
    }
}
