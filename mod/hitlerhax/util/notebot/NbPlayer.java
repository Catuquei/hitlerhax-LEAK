// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util.notebot;

import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;
import mod.hitlerhax.module.modules.utilities.NoteBot;

public class NbPlayer implements Runnable
{
    public static volatile boolean Running;
    public static volatile boolean Playing;
    public static volatile boolean Started;
    public static volatile NoteBot.Music PlayingMusic;
    public static volatile String LastNote;
    public static volatile Color LastNoteColor;
    private static long starttime;
    
    @Override
    public void run() {
        while (NbPlayer.Running) {
            if (NbPlayer.Playing) {
                if (NbPlayer.PlayingMusic == null) {
                    continue;
                }
                if (!NbPlayer.Started) {
                    NbPlayer.starttime = System.currentTimeMillis();
                    NbPlayer.Started = true;
                }
                NbPlayer.PlayingMusic.CalculateProgress(NbPlayer.starttime, System.currentTimeMillis());
                boolean finished = true;
                for (final NoteBot.MusicChannel channel : NbPlayer.PlayingMusic.GetChannels()) {
                    if (channel.IsDone()) {
                        continue;
                    }
                    finished = false;
                    final ArrayList<NoteBot.MusicNote> notes = channel.GetNextNotes();
                    final long time = System.currentTimeMillis() - NbPlayer.starttime;
                    if (time < notes.get(0).GetTime()) {
                        continue;
                    }
                    for (final NoteBot.MusicNote note : notes) {
                        final int inst = channel.GetInstrument();
                        if (inst == 0) {
                            channel.RemoveNextNote();
                        }
                        else {
                            final NbInstrument instrument = NbMapper.GetInstrument(inst);
                            if (instrument == null) {
                                channel.RemoveNextNote();
                            }
                            else {
                                final int pitch = note.GetNote();
                                final BlockPos pos = instrument.GetNotePosition(pitch);
                                if (pos != null) {
                                    NbUtil.LeftClick(pos);
                                }
                                channel.RemoveNextNote();
                                NbPlayer.LastNote = NbUtil.GetRealNote(pitch);
                                NbPlayer.LastNoteColor = NbMapper.GetInstrumentColor(inst);
                            }
                        }
                    }
                }
                if (!finished) {
                    continue;
                }
                NbPlayer.Started = false;
                NbPlayer.Playing = false;
            }
        }
    }
}
