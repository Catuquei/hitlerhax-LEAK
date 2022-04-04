// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import me.zero.alpine.listener.Listenable;
import mod.hitlerhax.event.HtlrEventBus;
import net.minecraftforge.common.MinecraftForge;
import mod.hitlerhax.event.HtlrEventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import mod.hitlerhax.sound.SongManager;
import mod.hitlerhax.event.HtlrEventManager;
import mod.hitlerhax.setting.SettingManager;
import mod.hitlerhax.command.CommandManager;
import mod.hitlerhax.module.modules.hud.HudRadar;
import mod.hitlerhax.module.modules.hud.HudWelcome;
import mod.hitlerhax.module.modules.hud.HudArmor;
import mod.hitlerhax.module.modules.hud.HudBPS;
import mod.hitlerhax.module.modules.hud.HudFPS;
import mod.hitlerhax.module.modules.hud.HudWatermark;
import mod.hitlerhax.module.modules.hud.HudArrayList;
import mod.hitlerhax.module.modules.hud.HudCoords;
import mod.hitlerhax.config.Config;
import mod.hitlerhax.module.ModuleManager;
import mod.hitlerhax.util.font.HtlrFontRenderer;
import mod.hitlerhax.util.render.ColorUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "htlr", name = "HitlerHax", version = "1.3.1", acceptedMinecraftVersions = "[1.12.2]")
@SideOnly(Side.CLIENT)
public class Main
{
    public static final String MODID = "htlr";
    public static final String NAME = "HitlerHax";
    public static final String VERSION = "1.3.1";
    public static final String DEV_VERSION = "1.3.1";
    public static final String MC_VERSIONS = "[1.12.2]";
    public static final ColorUtil COLOR;
    public static long startTimeStamp;
    public static HtlrFontRenderer customFontRenderer;
    public static ModuleManager moduleManager;
    public static Config config;
    public static final HudCoords hudCoords;
    public static final HudArrayList hudArrayList;
    public static final HudWatermark hudVersion;
    public static final HudFPS hudFps;
    public static final HudBPS hudBps;
    public static final HudArmor hudArmor;
    public static final HudWelcome hudWelcome;
    public static final HudRadar hudRadar;
    public static CommandManager cmdManager;
    public static SettingManager settingManager;
    public static HtlrEventManager eventManager;
    public static SongManager songManager;
    public static boolean configLoaded;
    @Mod.Instance
    public Main instance;
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        if (event.getSide() == Side.SERVER) {
            return;
        }
        HtlrEventHandler.INSTANCE = new HtlrEventHandler();
        MinecraftForge.EVENT_BUS.register((Object)this.instance);
        MinecraftForge.EVENT_BUS.register((Object)Main.hudCoords);
        MinecraftForge.EVENT_BUS.register((Object)Main.hudArrayList);
        MinecraftForge.EVENT_BUS.register((Object)Main.hudVersion);
        MinecraftForge.EVENT_BUS.register((Object)Main.hudFps);
        MinecraftForge.EVENT_BUS.register((Object)Main.hudBps);
        MinecraftForge.EVENT_BUS.register((Object)Main.hudArmor);
        MinecraftForge.EVENT_BUS.register((Object)Main.hudWelcome);
        MinecraftForge.EVENT_BUS.register((Object)Main.hudRadar);
        Main.moduleManager = new ModuleManager();
        Main.cmdManager = new CommandManager();
        Main.settingManager = new SettingManager();
        Main.eventManager = new HtlrEventManager();
        Main.songManager = new SongManager();
        (Main.config = new Config()).Load();
        Main.configLoaded = true;
        MinecraftForge.EVENT_BUS.register((Object)Main.eventManager);
        HtlrEventBus.EVENT_BUS.subscribe(Main.eventManager);
        Main.startTimeStamp = System.currentTimeMillis();
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        if (event.getSide() == Side.SERVER) {
            return;
        }
        Main.moduleManager.getModule("ClientFont").toggle();
        Main.moduleManager.getModule("ClientFont").toggle();
    }
    
    static {
        COLOR = new ColorUtil(121, 193, 255, 100);
        Main.startTimeStamp = 0L;
        hudCoords = new HudCoords();
        hudArrayList = new HudArrayList();
        hudVersion = new HudWatermark();
        hudFps = new HudFPS();
        hudBps = new HudBPS();
        hudArmor = new HudArmor();
        hudWelcome = new HudWelcome();
        hudRadar = new HudRadar();
        Main.configLoaded = false;
    }
}
