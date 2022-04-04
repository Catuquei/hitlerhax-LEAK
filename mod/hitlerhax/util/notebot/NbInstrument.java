// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util.notebot;

import net.minecraft.util.math.BlockPos;
import java.util.Iterator;
import java.util.ArrayList;

public class NbInstrument
{
    public static boolean Tunning;
    public static ArrayList<NbNote> ToTune;
    public static boolean CurrentTuneComplete;
    public static boolean Discovering;
    public static ArrayList<NbNote> ToDiscover;
    public static boolean CurrentDiscoveryComplete;
    public static int CurrentInstrument;
    private static ArrayList<NbNote> notes;
    private static int id;
    
    public NbInstrument(final int id) {
        NbInstrument.notes = new ArrayList<NbNote>();
        NbInstrument.id = id;
    }
    
    public static void TuneInstrument() {
        NbInstrument.ToTune = new ArrayList<NbNote>();
        for (final NbNote note : NbInstrument.notes) {
            if (note.GetKnownPitch() != note.GetPitch()) {
                NbInstrument.ToTune.add(note);
            }
        }
        NbInstrument.Tunning = true;
        NbInstrument.CurrentInstrument = NbInstrument.id;
    }
    
    public static void DiscoverInstrument() {
        NbInstrument.ToDiscover = new ArrayList<NbNote>();
        for (final NbNote note : NbInstrument.notes) {
            note.SetValidated(false);
            NbInstrument.ToDiscover.add(note);
        }
        NbInstrument.Discovering = true;
        NbInstrument.CurrentInstrument = NbInstrument.id;
    }
    
    public void AddNote(final int pitch, final BlockPos pos) {
        NbInstrument.notes.add(new NbNote(pitch, pos));
    }
    
    public BlockPos GetNotePosition(final int pitch) {
        for (final NbNote note : NbInstrument.notes) {
            if (note.GetPitch() == pitch) {
                return note.GetPosition();
            }
        }
        return null;
    }
    
    static {
        NbInstrument.Tunning = false;
        NbInstrument.CurrentTuneComplete = false;
        NbInstrument.Discovering = false;
        NbInstrument.CurrentDiscoveryComplete = false;
        NbInstrument.CurrentInstrument = -1;
    }
}
