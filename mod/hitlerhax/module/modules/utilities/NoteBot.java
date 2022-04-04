// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.utilities;

import com.google.gson.JsonParser;
import mod.hitlerhax.util.notebot.NbUtil;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.NoteBlockEvent;
import mod.hitlerhax.util.notebot.NbPlayer;
import javax.sound.midi.InvalidMidiDataException;
import com.google.gson.Gson;
import java.util.Iterator;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Track;
import com.google.gson.GsonBuilder;
import java.util.Map;
import com.google.gson.JsonArray;
import javax.sound.midi.ShortMessage;
import java.math.BigInteger;
import javax.sound.midi.MetaMessage;
import java.util.HashMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.sound.midi.MidiSystem;
import java.io.IOException;
import mod.hitlerhax.Client;
import java.nio.file.LinkOption;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.Paths;
import java.io.File;
import mod.hitlerhax.util.notebot.NbInstrument;
import mod.hitlerhax.util.notebot.NbMapper;
import mod.hitlerhax.Main;
import me.zero.alpine.listener.Listenable;
import mod.hitlerhax.event.HtlrEventBus;
import net.minecraftforge.common.MinecraftForge;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import java.awt.Color;
import javax.sound.midi.Sequence;
import mod.hitlerhax.util.notebot.NbNote;
import java.util.ArrayList;
import java.nio.file.Path;
import mod.hitlerhax.setting.settings.StringSetting;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.module.Module;

public class NoteBot extends Module
{
    final ModeSetting mode;
    final StringSetting file;
    public static Path musicFolder;
    public static ArrayList<NbNote> ToTune;
    static final int NOTE_ON = 144;
    static final int SET_TEMPO = 81;
    static final int TIME_SIGNATURE = 88;
    static int PATCH_CHANGE;
    static Sequence seq;
    static int[] channelprograms;
    static String noteBotJson;
    String name;
    long length;
    ArrayList<MusicChannel> channels;
    float progress;
    public static volatile boolean Running;
    public static volatile boolean Playing;
    public static volatile boolean Started;
    public static volatile Music PlayingMusic;
    public static volatile String LastNote;
    public static volatile Color LastNoteColor;
    long lsttime;
    long starttime;
    
    public NoteBot() {
        super("Note Bot", "Plays Note Blocks", Category.UTILITIES);
        this.mode = new ModeSetting("Mode", this, "Tune", new String[] { "Tune", "Play", "Listen" });
        this.file = new StringSetting("Filename", this, "example.mid");
        this.lsttime = 0L;
        this.starttime = 0L;
        this.addSetting(this.mode);
        this.addSetting(this.file);
    }
    
    public void onEnable() {
        if (NoteBot.mc.field_71439_g == null || NoteBot.mc.field_71441_e == null) {
            this.toggle();
            return;
        }
        MinecraftForge.EVENT_BUS.register((Object)this);
        HtlrEventBus.EVENT_BUS.subscribe(this);
        Main.config.Save();
        NbMapper.MapInstruments();
        if (this.mode.is("Tune")) {
            NbMapper.GetInstrument(1);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(2);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(3);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(4);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(5);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(6);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(7);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(8);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(9);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(10);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(11);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(12);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(13);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(14);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(15);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(16);
            NbInstrument.DiscoverInstrument();
            NbMapper.GetInstrument(1);
            NbInstrument.TuneInstrument();
            NbMapper.GetInstrument(2);
            NbInstrument.TuneInstrument();
            NbMapper.GetInstrument(3);
            NbInstrument.TuneInstrument();
            NbMapper.GetInstrument(4);
            NbInstrument.TuneInstrument();
            NbMapper.GetInstrument(5);
            NbInstrument.TuneInstrument();
            NbMapper.GetInstrument(6);
            NbInstrument.TuneInstrument();
            NbMapper.GetInstrument(7);
            NbInstrument.TuneInstrument();
            NbMapper.GetInstrument(8);
            NbInstrument.TuneInstrument();
            NbMapper.GetInstrument(9);
            NbInstrument.TuneInstrument();
            NbMapper.GetInstrument(10);
            NbInstrument.TuneInstrument();
            NbMapper.GetInstrument(11);
            NbInstrument.TuneInstrument();
            NbMapper.GetInstrument(12);
            NbInstrument.TuneInstrument();
            NbMapper.GetInstrument(13);
            NbInstrument.TuneInstrument();
            NbMapper.GetInstrument(14);
            NbInstrument.TuneInstrument();
            NbMapper.GetInstrument(15);
            NbInstrument.TuneInstrument();
            NbMapper.GetInstrument(16);
            NbInstrument.TuneInstrument();
        }
        else {
            if (this.mode.is("Play")) {
                NoteBot.musicFolder = NoteBot.mc.field_71412_D.toPath().resolve("hitlerhax" + File.separator + "notebot");
                final Path musicFile = Paths.get(NoteBot.musicFolder + File.separator + this.file.value, new String[0]);
                try {
                    Files.createDirectories(NoteBot.musicFolder, (FileAttribute<?>[])new FileAttribute[0]);
                    if (!Files.exists(musicFile, new LinkOption[0])) {
                        Client.addChatMessage("could not find the specified file");
                        this.toggle();
                    }
                    else {
                        readMidi(musicFile.toFile());
                        this.playMusic();
                    }
                    return;
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            if (this.mode.is("Listen")) {
                NoteBot.musicFolder = NoteBot.mc.field_71412_D.toPath().resolve("hitlerhax" + File.separator + "notebot");
                final Path musicFile = Paths.get(NoteBot.musicFolder + File.separator + this.file.value, new String[0]);
                try {
                    Files.createDirectories(NoteBot.musicFolder, (FileAttribute<?>[])new FileAttribute[0]);
                    if (!Files.exists(musicFile, new LinkOption[0])) {
                        Client.addChatMessage("could not find the specified file");
                        this.toggle();
                    }
                    else {
                        readMidi(musicFile.toFile());
                        this.listenMusic();
                    }
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
    
    private static void readMidi(final File file) throws InvalidMidiDataException, IOException {
        NoteBot.seq = MidiSystem.getSequence(file);
        NoteBot.channelprograms = new int[30];
        final JsonObject main = new JsonObject();
        main.addProperty("Name", file.getName().substring(0, file.getName().length() - 4));
        final JsonObject data = new JsonObject();
        main.addProperty("Length", (Number)0);
        main.add("Data", (JsonElement)data);
        final HashMap<Integer, JsonObject> jsonchannels = new HashMap<Integer, JsonObject>();
        long maxstamp = 0L;
        int mspqn = 500000;
        final int res = NoteBot.seq.getResolution();
        int trackn = 0;
        for (final Track track : NoteBot.seq.getTracks()) {
            for (int eventidx = 0; eventidx < track.size(); ++eventidx) {
                final MidiEvent event = track.get(eventidx);
                if (IsTimeSignature(event)) {
                    final MetaMessage mmsg = (MetaMessage)event.getMessage();
                    final byte[] timesig = mmsg.getMessage();
                    final int timesignum = timesig[2];
                    final int timesigden = timesig[3];
                    System.out.println("TIME_SIGNATURE Track: " + trackn + " msg: num: " + timesignum + " den: " + timesigden + " Tick: " + event.getTick());
                }
                else if (IsTempoChange(event)) {
                    final MetaMessage mmsg = (MetaMessage)event.getMessage();
                    mspqn = new BigInteger(mmsg.getData()).intValue();
                    System.out.println("SET_TEMPO Track: " + trackn + " msg: " + mspqn + " Tick: " + event.getTick());
                }
                else if (IsNoteOn(event)) {
                    final ShortMessage smsg = (ShortMessage)event.getMessage();
                    int note = smsg.getData1();
                    final long tick = event.getTick();
                    final int channel = smsg.getChannel();
                    final long time = (long)(tick * (mspqn / res) / 1000.0 + 0.5);
                    note %= 24;
                    if (time >= maxstamp) {
                        maxstamp = time;
                    }
                    if (jsonchannels.get(channel) == null) {
                        final JsonObject jsonchannel = new JsonObject();
                        jsonchannels.put(channel, jsonchannel);
                        jsonchannel.addProperty("OriginalInstrument", (Number)NoteBot.channelprograms[channel]);
                        jsonchannel.addProperty("Instrument", "0");
                        final JsonArray jsonnotes = new JsonArray();
                        jsonchannel.add("Notes", (JsonElement)jsonnotes);
                    }
                    final JsonObject element = new JsonObject();
                    element.addProperty("Note", "" + note);
                    element.addProperty("Tick", "" + time);
                    jsonchannels.get(channel).getAsJsonArray("Notes").add((JsonElement)element);
                    System.out.println("NOTE_ON Track: " + trackn + " Note: " + note + " Instrument: " + NoteBot.channelprograms[channel] + " Channel: " + channel + " Tick: " + tick + " Time: " + time);
                }
                else if (IsPatchChange(event)) {
                    final ShortMessage smsg = (ShortMessage)event.getMessage();
                    final long tick2 = event.getTick();
                    final int channel2 = smsg.getChannel();
                    final int program = smsg.getData1();
                    NoteBot.channelprograms[channel2] = program + 1;
                    System.out.println("PATCH_CHANGE Track: " + trackn + " Program: " + program + " Channel: " + channel2 + " Tick: " + tick2);
                }
            }
            ++trackn;
        }
        int jsonchanneln = 1;
        for (final Map.Entry<Integer, JsonObject> entryset : jsonchannels.entrySet()) {
            data.add("" + jsonchanneln, (JsonElement)entryset.getValue());
            ++jsonchanneln;
        }
        main.addProperty("Length", (Number)maxstamp);
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        NoteBot.noteBotJson = gson.toJson((JsonElement)main);
    }
    
    private static boolean IsPatchChange(final MidiEvent event) {
        if (!(event.getMessage() instanceof ShortMessage)) {
            return false;
        }
        final ShortMessage smsg = (ShortMessage)event.getMessage();
        return smsg.getStatus() >= 192 && smsg.getStatus() <= 207;
    }
    
    private static boolean IsTempoChange(final MidiEvent event) {
        if (!(event.getMessage() instanceof MetaMessage)) {
            return false;
        }
        final MetaMessage mmsg = (MetaMessage)event.getMessage();
        return mmsg.getType() == 81;
    }
    
    private static boolean IsTimeSignature(final MidiEvent event) {
        if (!(event.getMessage() instanceof MetaMessage)) {
            return false;
        }
        final MetaMessage mmsg = (MetaMessage)event.getMessage();
        return mmsg.getType() == 88;
    }
    
    private static boolean IsNoteOn(final MidiEvent event) {
        if (!(event.getMessage() instanceof ShortMessage)) {
            return false;
        }
        final ShortMessage smsg = (ShortMessage)event.getMessage();
        return smsg.getCommand() == 144;
    }
    
    public void playMusic() {
        NbPlayer.PlayingMusic = new Music();
        NbPlayer.Started = false;
        NbPlayer.Playing = true;
    }
    
    public void listenMusic() {
    }
    
    @SubscribeEvent
    public void OnNotePlayed(final NoteBlockEvent event) {
        if (NbInstrument.Tunning) {
            if (NbInstrument.ToTune.size() == 0) {
                NbInstrument.CurrentTuneComplete = true;
                return;
            }
            if (event.getVanillaNoteId() == NbInstrument.ToTune.get(0).GetPitch()) {
                NbInstrument.CurrentTuneComplete = true;
            }
        }
        if (NbInstrument.Discovering) {
            final NbNote note = NbInstrument.ToDiscover.get(0);
            note.SetKnownPitch(event.getVanillaNoteId());
            if (event.getVanillaNoteId() == note.GetPitch()) {
                note.SetValidated(true);
            }
            NbInstrument.CurrentDiscoveryComplete = true;
        }
    }
    
    @SubscribeEvent
    public void OnTick(final TickEvent.ClientTickEvent event) {
        if (NbInstrument.Tunning) {
            if (NbInstrument.ToTune.size() == 0) {
                NbInstrument.Tunning = false;
                NbInstrument.CurrentInstrument = -1;
                return;
            }
            final NbNote note = NbInstrument.ToTune.get(0);
            if (!NbInstrument.CurrentTuneComplete) {
                if (System.currentTimeMillis() - this.lsttime >= 100L) {
                    NbUtil.RightClick(note.GetPosition());
                    this.lsttime = System.currentTimeMillis();
                }
            }
            else {
                NbInstrument.ToTune.remove(0);
                NbInstrument.CurrentTuneComplete = false;
            }
        }
        if (NbInstrument.Discovering) {
            if (NbInstrument.ToDiscover.size() == 0) {
                NbInstrument.Discovering = false;
                NbInstrument.CurrentInstrument = -1;
                return;
            }
            final NbNote note = NbInstrument.ToDiscover.get(0);
            if (!NbInstrument.CurrentDiscoveryComplete) {
                if (System.currentTimeMillis() - this.lsttime >= 100L) {
                    NbUtil.LeftClick(note.GetPosition());
                    this.lsttime = System.currentTimeMillis();
                }
            }
            else {
                NbInstrument.ToDiscover.remove(0);
                NbInstrument.CurrentDiscoveryComplete = false;
            }
        }
    }
    
    static {
        NoteBot.PATCH_CHANGE = 192;
    }
    
    public static class MusicChannel
    {
        final int id;
        final int instrument;
        final int originalinstrument;
        final ArrayList<MusicNote> notes;
        
        public MusicChannel(final int id, final int instrument, final int originalinstrument) {
            this.id = id;
            this.instrument = instrument;
            this.originalinstrument = originalinstrument;
            this.notes = new ArrayList<MusicNote>();
        }
        
        public int GetInstrument() {
            return this.instrument;
        }
        
        public void AddNote(final MusicNote note) {
            this.notes.add(note);
        }
        
        public void RemoveNextNote() {
            if (this.notes.size() > 0) {
                this.notes.remove(0);
            }
        }
        
        public boolean IsDone() {
            return this.notes.size() == 0;
        }
        
        public ArrayList<MusicNote> GetNextNotes() {
            final ArrayList<MusicNote> ret = new ArrayList<MusicNote>();
            if (this.notes.size() > 0) {
                int i = 0;
                MusicNote note = this.notes.get(0);
                int time;
                do {
                    ret.add(note);
                    time = note.GetTime();
                    if (++i >= this.notes.size()) {
                        break;
                    }
                    note = this.notes.get(i);
                } while (note.GetTime() == time);
            }
            return ret;
        }
        
        public ArrayList<MusicNote> GetNotes() {
            return this.notes;
        }
    }
    
    public static class MusicNote
    {
        final int note;
        final int time;
        
        public MusicNote(final int note, final int time) {
            this.note = note;
            this.time = time;
        }
        
        public int GetNote() {
            return this.note;
        }
        
        public int GetTime() {
            return this.time;
        }
    }
    
    public static class Music
    {
        final String name;
        final long length;
        final ArrayList<MusicChannel> channels;
        float progress;
        
        public Music() {
            this.channels = new ArrayList<MusicChannel>();
            this.progress = 0.0f;
            final String content = NoteBot.noteBotJson;
            final JsonParser jsonparser = new JsonParser();
            final JsonObject mainjson = (JsonObject)jsonparser.parse(content);
            this.name = mainjson.get("Name").getAsString();
            this.length = mainjson.get("Length").getAsLong();
            for (final Map.Entry<String, JsonElement> channelel : mainjson.getAsJsonObject("Data").entrySet()) {
                final JsonObject channeljson = channelel.getValue().getAsJsonObject();
                final int instrument = channeljson.get("Instrument").getAsInt();
                final int originalinstrument = channeljson.get("OriginalInstrument").getAsInt();
                final MusicChannel channel = new MusicChannel(Integer.parseInt(channelel.getKey()), instrument, originalinstrument);
                final JsonArray notesjson = channeljson.getAsJsonArray("Notes");
                for (int i = 0; i < notesjson.size(); ++i) {
                    final JsonElement noteel = notesjson.get(i);
                    final int note = noteel.getAsJsonObject().get("Note").getAsInt();
                    final int time = noteel.getAsJsonObject().get("Tick").getAsInt();
                    channel.AddNote(new MusicNote(note, time));
                }
                this.channels.add(channel);
            }
        }
        
        public float GetProgress() {
            return this.progress;
        }
        
        public void CalculateProgress(final long starttime, final long currenttime) {
            this.progress = NbUtil.LongInterpolate(currenttime, starttime, starttime + this.length, 0.0f, 1.0f);
        }
        
        public ArrayList<MusicChannel> GetChannels() {
            return this.channels;
        }
        
        public String GetName() {
            return this.name;
        }
        
        public long GetLength() {
            return this.length;
        }
    }
}
