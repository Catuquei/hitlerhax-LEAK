// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module;

import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.renderer.Tessellator;
import mod.hitlerhax.event.events.HtlrEventRender;
import mod.hitlerhax.util.render.RenderUtil;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import java.util.List;
import java.util.Iterator;
import mod.hitlerhax.module.modules.utilities.YawLock;
import mod.hitlerhax.module.modules.utilities.BookWriter;
import mod.hitlerhax.module.modules.utilities.AFKReply;
import mod.hitlerhax.module.modules.utilities.PacketCancel;
import mod.hitlerhax.module.modules.utilities.AntiAFK;
import mod.hitlerhax.module.modules.utilities.Spammer;
import mod.hitlerhax.module.modules.utilities.Reconnect;
import mod.hitlerhax.module.modules.utilities.AutoFish;
import mod.hitlerhax.module.modules.render.Search;
import mod.hitlerhax.module.modules.render.NoRender;
import mod.hitlerhax.module.modules.render.LSD;
import mod.hitlerhax.module.modules.render.FOV;
import mod.hitlerhax.module.modules.render.NewChunks;
import mod.hitlerhax.module.modules.render.ViewModel;
import mod.hitlerhax.module.modules.render.LowOffHand;
import mod.hitlerhax.module.modules.render.Esp2dHelper;
import mod.hitlerhax.module.modules.render.Esp;
import mod.hitlerhax.module.modules.render.HoleEsp;
import mod.hitlerhax.module.modules.render.ExtraTab;
import mod.hitlerhax.module.modules.render.Nametags;
import mod.hitlerhax.module.modules.render.Freecam;
import mod.hitlerhax.module.modules.render.FullBright;
import mod.hitlerhax.module.modules.render.EntityTracers;
import mod.hitlerhax.module.modules.player.AutoLog;
import mod.hitlerhax.module.modules.player.FastPlace;
import mod.hitlerhax.module.modules.player.NoEntityTrace;
import mod.hitlerhax.module.modules.player.XCarry;
import mod.hitlerhax.module.modules.player.FakePlayer;
import mod.hitlerhax.module.modules.player.Disabler;
import mod.hitlerhax.module.modules.utilities.NoHunger;
import mod.hitlerhax.module.modules.player.AutoEat;
import mod.hitlerhax.module.modules.player.Scaffold;
import mod.hitlerhax.module.modules.movement.LineLocker;
import mod.hitlerhax.module.modules.movement.EntityFly;
import mod.hitlerhax.module.modules.movement.Parkour;
import mod.hitlerhax.module.modules.movement.BoatFly;
import mod.hitlerhax.module.modules.movement.ElytraFlight;
import mod.hitlerhax.module.modules.movement.Velocity;
import mod.hitlerhax.module.modules.movement.Sprint;
import mod.hitlerhax.module.modules.movement.NoSlow;
import mod.hitlerhax.module.modules.movement.EntityRide;
import mod.hitlerhax.module.modules.movement.AutoWalk;
import mod.hitlerhax.module.modules.movement.Jesus;
import mod.hitlerhax.module.modules.movement.Flight;
import mod.hitlerhax.module.modules.movement.Speed;
import mod.hitlerhax.module.modules.combat.Offhand;
import mod.hitlerhax.module.modules.combat.AutoArmor;
import mod.hitlerhax.module.modules.combat.SpeedEXP;
import mod.hitlerhax.module.modules.combat.Criticals;
import mod.hitlerhax.module.modules.combat.Anchor;
import mod.hitlerhax.module.modules.combat.AutoTotem;
import mod.hitlerhax.module.modules.combat.Surround;
import mod.hitlerhax.module.modules.combat.CrystalAura;
import mod.hitlerhax.module.modules.combat.KillAura;
import mod.hitlerhax.module.modules.client.ClientFont;
import mod.hitlerhax.module.modules.client.DiscordRPC;
import mod.hitlerhax.module.modules.hud.Hud;
import mod.hitlerhax.module.modules.client.ClickGui;
import java.util.ArrayList;
import mod.hitlerhax.util.Globals;

public class ModuleManager implements Globals
{
    public final ArrayList<Module> modules;
    
    public ModuleManager() {
        this.modules = new ArrayList<Module>();
        this.addModule(new ClickGui());
        this.addModule(new Hud());
        this.addModule(new DiscordRPC());
        this.addModule(new ClientFont());
        this.addModule(new KillAura());
        this.addModule(new CrystalAura());
        this.addModule(new Surround());
        this.addModule(new AutoTotem());
        this.addModule(new Anchor());
        this.addModule(new Criticals());
        this.addModule(new SpeedEXP());
        this.addModule(new AutoArmor());
        this.addModule(new Offhand());
        this.addModule(new Speed());
        this.addModule(new Flight());
        this.addModule(new Jesus());
        this.addModule(new AutoWalk());
        this.addModule(new EntityRide());
        this.addModule(new NoSlow());
        this.addModule(new Sprint());
        this.addModule(new Velocity());
        this.addModule(new ElytraFlight());
        this.addModule(new BoatFly());
        this.addModule(new Parkour());
        this.addModule(new EntityFly());
        this.addModule(new LineLocker());
        this.addModule(new Scaffold());
        this.addModule(new AutoEat());
        this.addModule(new NoHunger());
        this.addModule(new Disabler());
        this.addModule(new FakePlayer());
        this.addModule(new XCarry());
        this.addModule(new NoEntityTrace());
        this.addModule(new FastPlace());
        this.addModule(new AutoLog());
        this.addModule(new EntityTracers());
        this.addModule(new FullBright());
        this.addModule(new Freecam());
        this.addModule(new Nametags());
        this.addModule(new ExtraTab());
        this.addModule(new HoleEsp());
        this.addModule(new Esp());
        this.addModule(new Esp2dHelper());
        this.addModule(new LowOffHand());
        this.addModule(new ViewModel());
        this.addModule(new NewChunks());
        this.addModule(new FOV());
        this.addModule(new LSD());
        this.addModule(new NoRender());
        this.addModule(new Search());
        this.addModule(new AutoFish());
        this.addModule(new Reconnect());
        this.addModule(new Spammer());
        this.addModule(new AntiAFK());
        this.addModule(new PacketCancel());
        this.addModule(new AFKReply());
        this.addModule(new BookWriter());
        this.addModule(new YawLock());
    }
    
    public void addModule(final Module m) {
        this.modules.add(m);
    }
    
    public Module getModule(final String name) {
        for (final Module m : this.modules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }
    
    public List<Module> getModuleList() {
        new ModuleManager();
        return this.modules;
    }
    
    public List<Module> getModulesByCategory(final Category c) {
        final List<Module> modules = new ArrayList<Module>();
        for (final Module m : this.modules) {
            if (m.getCategory() == c) {
                modules.add(m);
            }
        }
        return modules;
    }
    
    public void update() {
        for (final Module module : this.modules) {
            if (module.toggled) {
                module.onUpdate();
            }
        }
    }
    
    public void render(final RenderWorldLastEvent event) {
        ModuleManager.mc.field_71424_I.func_76320_a("HitlerHax");
        ModuleManager.mc.field_71424_I.func_76320_a("setup");
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_179120_a(770, 771, 1, 0);
        GlStateManager.func_179103_j(7425);
        GlStateManager.func_179097_i();
        GlStateManager.func_187441_d(1.0f);
        final Vec3d pos = this.get_interpolated_pos((Entity)ModuleManager.mc.field_71439_g, event.getPartialTicks());
        final HtlrEventRender event_render = new HtlrEventRender(RenderUtil.INSTANCE, pos, 0.0f);
        event_render.reset_translation();
        ModuleManager.mc.field_71424_I.func_76319_b();
        for (final Module m : this.getModuleList()) {
            if (m.toggled) {
                ModuleManager.mc.field_71424_I.func_76320_a(m.name);
                m.render(event_render);
                ModuleManager.mc.field_71424_I.func_76319_b();
            }
        }
        ModuleManager.mc.field_71424_I.func_76320_a("release");
        GlStateManager.func_187441_d(1.0f);
        GlStateManager.func_179103_j(7424);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
        GlStateManager.func_179089_o();
        RenderUtil.release_gl();
        ModuleManager.mc.field_71424_I.func_76319_b();
        ModuleManager.mc.field_71424_I.func_76319_b();
    }
    
    public void render() {
        for (final Module m : this.getModuleList()) {
            if (m.toggled) {
                m.render();
            }
        }
    }
    
    public Vec3d get_interpolated_pos(final Entity entity, final double ticks) {
        return new Vec3d(entity.field_70142_S, entity.field_70137_T, entity.field_70136_U).func_178787_e(this.process(entity, ticks, ticks, ticks));
    }
    
    public Vec3d process(final Entity entity, final double x, final double y, final double z) {
        return new Vec3d((entity.field_70165_t - entity.field_70142_S) * x, (entity.field_70163_u - entity.field_70137_T) * y, (entity.field_70161_v - entity.field_70136_U) * z);
    }
    
    public void onClientTick(final TickEvent.ClientTickEvent event) {
        for (final Module m : this.getModuleList()) {
            if (m.toggled) {
                m.onClientTick(event);
            }
        }
    }
}
