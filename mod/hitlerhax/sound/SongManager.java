// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.sound;

import java.util.Random;
import java.util.Collections;
import net.minecraft.client.audio.ISound;
import java.util.List;
import mod.hitlerhax.util.Globals;

public class SongManager implements Globals
{
    private final List<ISound> songs;
    private ISound menuSong;
    private final ISound currentSong;
    
    public SongManager() {
        this.songs = Collections.singletonList(Drippler.sound);
        this.menuSong = this.getRandomSong();
        this.currentSong = this.getRandomSong();
    }
    
    public ISound getMenuSong() {
        return this.menuSong = this.getRandomSong();
    }
    
    public void play() {
        if (!this.isCurrentSongPlaying()) {
            SongManager.mc.field_147127_av.func_147682_a(this.currentSong);
        }
    }
    
    private boolean isCurrentSongPlaying() {
        return SongManager.mc.field_147127_av.func_147692_c(this.currentSong);
    }
    
    private ISound getRandomSong() {
        final Random random = new Random();
        return this.songs.get(random.nextInt(this.songs.size()));
    }
}
