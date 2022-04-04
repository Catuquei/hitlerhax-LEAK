// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.sound;

import net.minecraft.util.SoundCategory;
import net.minecraft.client.audio.Sound;
import javax.annotation.Nullable;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.audio.ISound;

public class Drippler
{
    public static final ISound sound;
    private static final String song = "drippler";
    private static final ResourceLocation loc;
    
    static {
        loc = new ResourceLocation("sounds/drippler.ogg");
        sound = (ISound)new ISound() {
            private final int pitch = 1;
            private final int volume = 1;
            
            public ResourceLocation func_147650_b() {
                return Drippler.loc;
            }
            
            @Nullable
            public SoundEventAccessor func_184366_a(final SoundHandler soundHandler) {
                return new SoundEventAccessor(Drippler.loc, "TheRicharch");
            }
            
            public Sound func_184364_b() {
                return new Sound("drippler", 1.0f, 1.0f, 1, Sound.Type.SOUND_EVENT, false);
            }
            
            public SoundCategory func_184365_d() {
                return SoundCategory.VOICE;
            }
            
            public boolean func_147657_c() {
                return true;
            }
            
            public int func_147652_d() {
                return 2;
            }
            
            public float func_147653_e() {
                return 1.0f;
            }
            
            public float func_147655_f() {
                return 1.0f;
            }
            
            public float func_147649_g() {
                return 1.0f;
            }
            
            public float func_147654_h() {
                return 0.0f;
            }
            
            public float func_147651_i() {
                return 0.0f;
            }
            
            public ISound.AttenuationType func_147656_j() {
                return ISound.AttenuationType.LINEAR;
            }
        };
    }
}
