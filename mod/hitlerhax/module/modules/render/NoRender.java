// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.render;

import net.minecraft.init.MobEffects;
import net.minecraftforge.client.GuiIngameForge;
import mod.hitlerhax.setting.Setting;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.network.play.server.SPacketSpawnMob;
import mod.hitlerhax.event.HtlrEventCancellable;
import net.minecraft.network.play.server.SPacketEntityStatus;
import java.util.function.Predicate;
import mod.hitlerhax.module.Category;
import mod.hitlerhax.event.events.HtlrEventSpawnEffect;
import mod.hitlerhax.event.events.HtlrEventEntity;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import mod.hitlerhax.event.events.HtlrEventPacket;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventRain;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.module.Module;

public class NoRender extends Module
{
    public final BooleanSetting rain;
    public final BooleanSetting skylight;
    public final ModeSetting hurtCam;
    public final BooleanSetting fire;
    public final BooleanSetting portalEffect;
    public final BooleanSetting potionIndicators;
    public final BooleanSetting crystals;
    public final BooleanSetting totemAnimation;
    public final BooleanSetting enchantTables;
    public final BooleanSetting armor;
    public final BooleanSetting tnt;
    public final BooleanSetting items;
    public final BooleanSetting withers;
    public final BooleanSetting skulls;
    public final BooleanSetting fireworks;
    public BooleanSetting particles;
    public BooleanSetting signs;
    public BooleanSetting pistons;
    @EventHandler
    private final Listener<HtlrEventRain> onRain;
    @EventHandler
    private final Listener<HtlrEventPacket> PacketEvent;
    @EventHandler
    private final Listener<RenderBlockOverlayEvent> OnBlockOverlayEvent;
    @EventHandler
    private final Listener<HtlrEventPacket.ReceivePacket> onReceivePacket;
    @EventHandler
    private final Listener<HtlrEventEntity> onRenderEntity;
    @EventHandler
    private final Listener<HtlrEventSpawnEffect> onSpawnEffectParticle;
    @EventHandler
    private final Listener<HtlrEventEntity> onEntityAdd;
    
    public NoRender() {
        super("NoRender", "Doesnt render stuff", Category.RENDER);
        this.rain = new BooleanSetting("rain", this, false);
        this.skylight = new BooleanSetting("skylightUpdates", this, false);
        this.hurtCam = new ModeSetting("hurtCam", this, "yesHurtCam", new String[] { "yesHurtCam", "noHurtCam", "penis" });
        this.fire = new BooleanSetting("fire", this, false);
        this.portalEffect = new BooleanSetting("portalEffect", this, false);
        this.potionIndicators = new BooleanSetting("potionIndicators", this, false);
        this.crystals = new BooleanSetting("crystals", this, false);
        this.totemAnimation = new BooleanSetting("totemAnimation", this, false);
        this.enchantTables = new BooleanSetting("encahtTables", this, false);
        this.armor = new BooleanSetting("armor", this, false);
        this.tnt = new BooleanSetting("tnt", this, false);
        this.items = new BooleanSetting("items", this, false);
        this.withers = new BooleanSetting("withers", this, false);
        this.skulls = new BooleanSetting("skulls", this, false);
        this.fireworks = new BooleanSetting("fireworks", this, false);
        this.particles = new BooleanSetting("particles", this, false);
        this.signs = new BooleanSetting("signs", this, false);
        this.pistons = new BooleanSetting("pistons", this, false);
        this.onRain = new Listener<HtlrEventRain>(event -> {
            if (this.rain.isEnabled()) {
                if (NoRender.mc.field_71441_e != null) {
                    event.cancel();
                }
            }
            return;
        }, (Predicate<HtlrEventRain>[])new Predicate[0]);
        SPacketEntityStatus packet;
        this.PacketEvent = new Listener<HtlrEventPacket>(event -> {
            if (NoRender.mc.field_71441_e == null || NoRender.mc.field_71439_g == null) {
                return;
            }
            else {
                if (event.get_packet() instanceof SPacketEntityStatus) {
                    packet = (SPacketEntityStatus)event.get_packet();
                    if (packet.func_149160_c() == 35 && this.totemAnimation.isEnabled()) {
                        event.cancel();
                    }
                }
                return;
            }
        }, (Predicate<HtlrEventPacket>[])new Predicate[0]);
        this.OnBlockOverlayEvent = new Listener<RenderBlockOverlayEvent>(event -> {
            if (this.fire.isEnabled() && event.getOverlayType() == RenderBlockOverlayEvent.OverlayType.FIRE) {
                event.setCanceled(true);
            }
            return;
        }, (Predicate<RenderBlockOverlayEvent>[])new Predicate[0]);
        SPacketSpawnMob packet2;
        this.onReceivePacket = new Listener<HtlrEventPacket.ReceivePacket>(event -> {
            if (event.get_era() == HtlrEventCancellable.Era.EVENT_PRE && event.get_packet() instanceof SPacketSpawnMob) {
                packet2 = (SPacketSpawnMob)event.get_packet();
                if (this.skulls.isEnabled() && packet2.func_149025_e() == 19) {
                    event.cancel();
                }
            }
            return;
        }, (Predicate<HtlrEventPacket.ReceivePacket>[])new Predicate[0]);
        this.onRenderEntity = new Listener<HtlrEventEntity>(event -> {
            if (this.crystals.isEnabled() && event.get_entity() instanceof EntityEnderCrystal) {
                event.cancel();
            }
            if (this.tnt.isEnabled() && event.get_entity() instanceof EntityTNTPrimed) {
                event.cancel();
            }
            if (this.items.isEnabled() && event.get_entity() instanceof EntityItem) {
                event.cancel();
            }
            if (this.withers.isEnabled() && event.get_entity() instanceof EntityWither) {
                event.cancel();
            }
            if (this.skulls.isEnabled() && event.get_entity() instanceof EntityWitherSkull) {
                event.cancel();
            }
            if (this.fireworks.isEnabled() && event.get_entity() instanceof EntityFireworkRocket) {
                event.cancel();
            }
            return;
        }, (Predicate<HtlrEventEntity>[])new Predicate[0]);
        this.onSpawnEffectParticle = new Listener<HtlrEventSpawnEffect>(event -> {
            if (this.fireworks.isEnabled() && (event.getParticleID() == EnumParticleTypes.FIREWORKS_SPARK.func_179348_c() || event.getParticleID() == EnumParticleTypes.EXPLOSION_HUGE.func_179348_c() || event.getParticleID() == EnumParticleTypes.EXPLOSION_LARGE.func_179348_c() || event.getParticleID() == EnumParticleTypes.EXPLOSION_NORMAL.func_179348_c())) {
                event.cancel();
            }
            return;
        }, (Predicate<HtlrEventSpawnEffect>[])new Predicate[0]);
        this.onEntityAdd = new Listener<HtlrEventEntity>(event -> {
            if (this.fireworks.isEnabled() && event.get_entity() instanceof EntityFireworkRocket) {
                event.cancel();
            }
            if (this.skulls.isEnabled() && event.get_entity() instanceof EntityWitherSkull) {
                event.cancel();
            }
            if (this.tnt.isEnabled() && event.get_entity() instanceof EntityTNTPrimed) {
                event.cancel();
            }
            return;
        }, (Predicate<HtlrEventEntity>[])new Predicate[0]);
        this.addSetting(this.rain);
        this.addSetting(this.skylight);
        this.addSetting(this.hurtCam);
        this.addSetting(this.fire);
        this.addSetting(this.portalEffect);
        this.addSetting(this.potionIndicators);
        this.addSetting(this.crystals);
        this.addSetting(this.totemAnimation);
        this.addSetting(this.enchantTables);
        this.addSetting(this.armor);
        this.addSetting(this.tnt);
        this.addSetting(this.items);
        this.addSetting(this.withers);
        this.addSetting(this.skulls);
        this.addSetting(this.fireworks);
    }
    
    public void onDisable() {
        GuiIngameForge.renderPortal = true;
    }
    
    @Override
    public void onUpdate() {
        if (this.hurtCam.is("penis")) {
            NoRender.mc.field_71439_g.func_70057_ab();
        }
        if (this.portalEffect.isEnabled()) {
            GuiIngameForge.renderPortal = false;
            NoRender.mc.field_71439_g.func_184596_c(MobEffects.field_76431_k);
        }
    }
}
