// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.combat;

import java.util.Objects;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.MathHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.util.CombatRules;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.init.Blocks;
import mod.hitlerhax.util.render.RenderUtil;
import java.awt.Color;
import mod.hitlerhax.util.render.ColorUtil;
import mod.hitlerhax.event.events.HtlrEventRender;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.RayTraceResult;
import java.util.List;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.EntityLivingBase;
import java.util.Collection;
import net.minecraft.util.EnumHand;
import net.minecraft.init.Items;
import net.minecraft.entity.player.EntityPlayer;
import java.util.Comparator;
import java.util.Iterator;
import mod.hitlerhax.setting.Setting;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.network.play.server.SPacketSoundEffect;
import java.util.function.Predicate;
import net.minecraft.network.play.client.CPacketPlayer;
import mod.hitlerhax.Main;
import mod.hitlerhax.module.Category;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventPacket;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.util.Timer;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import mod.hitlerhax.setting.settings.ColorSetting;
import mod.hitlerhax.setting.settings.FloatSetting;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.setting.settings.ModeSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.module.Module;

public class CrystalAura extends Module
{
    public final BooleanSetting breakCrystal;
    public final BooleanSetting placeCrystal;
    public final ModeSetting switchHand;
    public final ModeSetting logic;
    public final IntSetting breakSpeed;
    public final ModeSetting breakType;
    public final ModeSetting breakHand;
    public final ModeSetting breakMode;
    public final FloatSetting breakRange;
    public final FloatSetting placeRange;
    public final IntSetting facePlaceValue;
    public final BooleanSetting highPing;
    public final BooleanSetting antiGhost;
    public final BooleanSetting raytrace;
    public final BooleanSetting rotate;
    public final BooleanSetting spoofRotations;
    public final IntSetting minDmg;
    public final BooleanSetting multiplace;
    public final IntSetting multiplaceValue;
    public final BooleanSetting multiplacePlus;
    public final BooleanSetting antiSuicide;
    public final IntSetting maxSelfDmg;
    public final BooleanSetting antiSelfPop;
    public final FloatSetting enemyRange;
    public final FloatSetting wallsRange;
    public final BooleanSetting mode113;
    public final BooleanSetting strictInteract;
    public final BooleanSetting outline;
    public final BooleanSetting showBlock;
    public final BooleanSetting showDamage;
    final ColorSetting color;
    private boolean switchCooldown;
    private BlockPos renderBlock;
    private EnumFacing enumFacing;
    private Entity renderEnt;
    public static final ArrayList<BlockPos> PlacedCrystals;
    public static boolean ghosting;
    public boolean active;
    boolean offHand;
    private boolean togglePitch;
    int oldSlot;
    public static boolean placing;
    final Timer timer;
    @EventHandler
    private final Listener<HtlrEventPacket.SendPacket> packetSendListener;
    @EventHandler
    private final Listener<HtlrEventPacket.ReceivePacket> packetReceiveListener;
    @EventHandler
    private final Listener<HtlrEventPacket.ReceivePacket> packetReceiveListener2;
    private static boolean isSpoofingAngles;
    private static float yaw;
    private static float pitch;
    
    public CrystalAura() {
        super("CrystalAura", "places and breaks Crystals", Category.COMBAT);
        this.breakCrystal = new BooleanSetting("BreakCrystal", this, true);
        this.placeCrystal = new BooleanSetting("PlaceCrystal", this, true);
        this.switchHand = new ModeSetting("Switch", this, "Off", new String[] { "Off", "OnEnable", "Detect" });
        this.logic = new ModeSetting("Logic", this, "Break, Place", new String[] { "Break, Place", "Place, Break" });
        this.breakSpeed = new IntSetting("BreakSpeed", this, 20);
        this.breakType = new ModeSetting("BreakType", this, "Packet", new String[] { "Swing", "Packet" });
        this.breakHand = new ModeSetting("BreakHand", this, "Both", new String[] { "Main", "Offhand", "Both" });
        this.breakMode = new ModeSetting("BreakMode", this, "All", new String[] { "All", "Smart", "Own" });
        this.breakRange = new FloatSetting("BreakRange", this, 4.4f);
        this.placeRange = new FloatSetting("PlaceRange", this, 4.4f);
        this.facePlaceValue = new IntSetting("FacePlaceVal", this, 8);
        this.highPing = new BooleanSetting("HighPing", this, true);
        this.antiGhost = new BooleanSetting("AntiGhosting", this, true);
        this.raytrace = new BooleanSetting("Raytrace", this, true);
        this.rotate = new BooleanSetting("Rotate", this, true);
        this.spoofRotations = new BooleanSetting("SpoofRotations", this, true);
        this.minDmg = new IntSetting("MinDmg", this, 5);
        this.multiplace = new BooleanSetting("Multiplace", this, false);
        this.multiplaceValue = new IntSetting("MultiplaceValue", this, 2);
        this.multiplacePlus = new BooleanSetting("MultiplacePlus", this, true);
        this.antiSuicide = new BooleanSetting("AntiSuicide", this, false);
        this.maxSelfDmg = new IntSetting("AntiSuicideValue", this, 10);
        this.antiSelfPop = new BooleanSetting("AntiSelfPop", this, true);
        this.enemyRange = new FloatSetting("Range", this, 6.0f);
        this.wallsRange = new FloatSetting("WallsRange", this, 3.5f);
        this.mode113 = new BooleanSetting("1.13Place", this, false);
        this.strictInteract = new BooleanSetting("StrictInteract", this, true);
        this.outline = new BooleanSetting("Outline", this, false);
        this.showBlock = new BooleanSetting("ShowBlock", this, true);
        this.showDamage = new BooleanSetting("ShowDamage", this, true);
        this.color = new ColorSetting("Color", this, Main.COLOR);
        this.switchCooldown = false;
        this.active = false;
        this.offHand = false;
        this.togglePitch = false;
        this.timer = new Timer();
        final Packet<?> packet;
        this.packetSendListener = new Listener<HtlrEventPacket.SendPacket>(event -> {
            packet = event.get_packet();
            if (packet instanceof CPacketPlayer && this.spoofRotations.isEnabled() && CrystalAura.isSpoofingAngles) {
                ((CPacketPlayer)packet).func_186999_a(CrystalAura.yaw);
                ((CPacketPlayer)packet).func_186998_b(CrystalAura.pitch);
            }
            return;
        }, (Predicate<HtlrEventPacket.SendPacket>[])new Predicate[0]);
        SPacketSoundEffect packet2;
        final Iterator<Entity> iterator;
        Entity e;
        this.packetReceiveListener = new Listener<HtlrEventPacket.ReceivePacket>(event -> {
            if (event.get_packet() instanceof SPacketSoundEffect) {
                packet2 = (SPacketSoundEffect)event.get_packet();
                if (packet2.func_186977_b() == SoundCategory.BLOCKS && packet2.func_186978_a() == SoundEvents.field_187539_bB) {
                    CrystalAura.mc.field_71441_e.field_72996_f.iterator();
                    while (iterator.hasNext()) {
                        e = iterator.next();
                        if (e instanceof EntityEnderCrystal && e.func_70011_f(packet2.func_149207_d(), packet2.func_149211_e(), packet2.func_149210_f()) <= 6.0) {
                            e.func_70106_y();
                        }
                    }
                }
            }
            return;
        }, (Predicate<HtlrEventPacket.ReceivePacket>[])new Predicate[0]);
        SPacketSoundEffect packet3;
        final Iterator<BlockPos> iterator2;
        BlockPos blockPos;
        final CPacketUseEntity cPacketUseEntity2;
        CPacketUseEntity cPacketUseEntity;
        final Iterator<Entity> iterator3;
        Entity e2;
        this.packetReceiveListener2 = new Listener<HtlrEventPacket.ReceivePacket>(event -> {
            if (event.get_packet() instanceof SPacketSoundEffect) {
                packet3 = (SPacketSoundEffect)event.get_packet();
                if (packet3.func_186977_b() == SoundCategory.BLOCKS && packet3.func_186978_a() == SoundEvents.field_187539_bB) {
                    CrystalAura.PlacedCrystals.iterator();
                    while (iterator2.hasNext()) {
                        blockPos = iterator2.next();
                        if (blockPos.func_185332_f((int)packet3.func_149207_d(), (int)packet3.func_149211_e(), (int)packet3.func_149210_f()) <= 6.0) {
                            new CPacketUseEntity((Entity)new EntityEnderCrystal((World)CrystalAura.mc.field_71441_e, (double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p()));
                            cPacketUseEntity = cPacketUseEntity2;
                            CrystalAura.mc.field_71439_g.field_71174_a.func_147297_a((Packet)cPacketUseEntity);
                            CrystalAura.PlacedCrystals.remove(blockPos);
                            return;
                        }
                    }
                    CrystalAura.mc.field_71441_e.field_72996_f.iterator();
                    while (iterator3.hasNext()) {
                        e2 = iterator3.next();
                        if (e2 instanceof EntityEnderCrystal && e2.func_70011_f(packet3.func_149207_d(), packet3.func_149211_e(), packet3.func_149210_f()) <= 6.0) {
                            e2.func_70106_y();
                        }
                    }
                }
            }
            return;
        }, (Predicate<HtlrEventPacket.ReceivePacket>[])new Predicate[0]);
        this.addSetting(this.breakCrystal);
        this.addSetting(this.placeCrystal);
        this.addSetting(this.switchHand);
        this.addSetting(this.logic);
        this.addSetting(this.breakSpeed);
        this.addSetting(this.breakType);
        this.addSetting(this.breakHand);
        this.addSetting(this.breakMode);
        this.addSetting(this.breakRange);
        this.addSetting(this.placeRange);
        this.addSetting(this.facePlaceValue);
        this.addSetting(this.highPing);
        this.addSetting(this.antiGhost);
        this.addSetting(this.raytrace);
        this.addSetting(this.rotate);
        this.addSetting(this.spoofRotations);
        this.addSetting(this.minDmg);
        this.addSetting(this.multiplace);
        this.addSetting(this.multiplaceValue);
        this.addSetting(this.multiplacePlus);
        this.addSetting(this.antiSuicide);
        this.addSetting(this.maxSelfDmg);
        this.addSetting(this.antiSelfPop);
        this.addSetting(this.enemyRange);
        this.addSetting(this.wallsRange);
        this.addSetting(this.mode113);
        this.addSetting(this.strictInteract);
        this.addSetting(this.outline);
        this.addSetting(this.showBlock);
        this.addSetting(this.showDamage);
    }
    
    public void onEnable() {
        if (CrystalAura.mc.field_71439_g == null || CrystalAura.mc.field_71441_e == null) {
            return;
        }
        this.oldSlot = CrystalAura.mc.field_71439_g.field_71071_by.field_70461_c;
        CrystalAura.PlacedCrystals.clear();
        resetRotation();
        this.active = false;
        CrystalAura.placing = false;
        CrystalAura.ghosting = false;
    }
    
    public void onDisable() {
        super.onDisable();
        if (this.switchHand.is("OnEnable")) {
            CrystalAura.mc.field_71439_g.field_71071_by.field_70461_c = this.oldSlot;
            CrystalAura.mc.field_71442_b.func_78765_e();
        }
        this.renderBlock = null;
        this.renderEnt = null;
        CrystalAura.PlacedCrystals.clear();
        this.active = false;
        CrystalAura.placing = false;
        CrystalAura.ghosting = false;
    }
    
    @Override
    public void onUpdate() {
        if (CrystalAura.PlacedCrystals.size() > 3 && this.timer.getTimePassed() > 40L && CrystalAura.PlacedCrystals.size() > 3) {
            CrystalAura.ghosting = true;
        }
        if (CrystalAura.mc.field_71439_g == null || CrystalAura.mc.field_71441_e == null || Main.moduleManager.getModule("Surround").isToggled()) {
            return;
        }
        this.implementLogic();
    }
    
    private void implementLogic() {
        if (this.logic.is("Break, Place")) {
            this.breakLogic();
            this.placeLogic();
        }
        else if (this.logic.is("Place, Break")) {
            this.placeLogic();
            this.breakLogic();
        }
    }
    
    private void breakLogic() {
        final EntityEnderCrystal crystal = (EntityEnderCrystal)CrystalAura.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity instanceof EntityEnderCrystal).filter(e -> CrystalAura.mc.field_71439_g.func_70032_d(e) <= this.breakRange.getValue()).filter(this::crystalCheck).map(entity -> entity).min(Comparator.comparing(c -> CrystalAura.mc.field_71439_g.func_70032_d(c))).orElse(null);
        if (this.breakCrystal.isEnabled() && crystal != null) {
            if (!CrystalAura.mc.field_71439_g.func_70685_l((Entity)crystal) && CrystalAura.mc.field_71439_g.func_70032_d((Entity)crystal) > this.wallsRange.getValue()) {
                return;
            }
            if (this.timer.getTimePassed() / 50L >= 20 - this.breakSpeed.getValue()) {
                this.timer.resetCurrent();
                this.active = true;
                if (this.rotate.isEnabled()) {
                    this.lookAtPacket(crystal.field_70165_t, crystal.field_70163_u, crystal.field_70161_v, (EntityPlayer)CrystalAura.mc.field_71439_g);
                }
                if (this.breakType.is("Swing")) {
                    this.breakCrystal(crystal);
                }
                if (this.breakType.is("Packet")) {
                    CrystalAura.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketUseEntity((Entity)crystal));
                    this.swingArm();
                }
                if (this.highPing.isEnabled()) {
                    crystal.func_70106_y();
                    CrystalAura.mc.field_71441_e.func_73022_a();
                    CrystalAura.mc.field_71441_e.func_72910_y();
                }
                this.active = false;
            }
        }
        else {
            resetRotation();
            this.active = false;
        }
    }
    
    private void placeLogic() {
        int crystalSlot = (CrystalAura.mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_185158_cP) ? CrystalAura.mc.field_71439_g.field_71071_by.field_70461_c : -1;
        if (crystalSlot == -1) {
            for (int l = 0; l < 9; ++l) {
                if (CrystalAura.mc.field_71439_g.field_71071_by.func_70301_a(l).func_77973_b() == Items.field_185158_cP && CrystalAura.mc.field_71439_g.func_184586_b(EnumHand.OFF_HAND).func_77973_b() != Items.field_185158_cP) {
                    crystalSlot = l;
                    break;
                }
            }
        }
        this.offHand = (CrystalAura.mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_185158_cP);
        if (CrystalAura.mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_185158_cP) {
            this.offHand = true;
        }
        else if (crystalSlot == -1) {
            return;
        }
        final List<BlockPos> blocks = this.findCrystalBlocks();
        final List<Entity> entities = new ArrayList<Entity>(new ArrayList<Entity>(CrystalAura.mc.field_71441_e.field_73010_i));
        BlockPos blockPos1 = null;
        double damage = 0.5;
        if (!this.placeCrystal.isEnabled()) {
            return;
        }
        if (!this.offHand && CrystalAura.mc.field_71439_g.field_71071_by.field_70461_c != crystalSlot && this.switchHand.is("OnEnable")) {
            CrystalAura.mc.field_71439_g.field_71071_by.field_70461_c = crystalSlot;
            CrystalAura.mc.field_71442_b.func_78765_e();
            resetRotation();
            this.switchCooldown = true;
        }
        for (final Entity entity : entities) {
            if (entity != CrystalAura.mc.field_71439_g) {
                if (((EntityLivingBase)entity).func_110143_aJ() <= 0.0f) {
                    continue;
                }
                for (final BlockPos blockPos2 : blocks) {
                    final double b = entity.func_174818_b(blockPos2);
                    if (b >= Math.pow(this.enemyRange.getValue(), 2.0)) {
                        continue;
                    }
                    final double d = calculateDamage(blockPos2.func_177958_n() + 0.5, blockPos2.func_177956_o() + 1, blockPos2.func_177952_p() + 0.5, entity);
                    if (d <= this.minDmg.getValue() && ((EntityLivingBase)entity).func_110143_aJ() + ((EntityLivingBase)entity).func_110139_bj() > this.facePlaceValue.getValue()) {
                        continue;
                    }
                    if (d <= damage) {
                        continue;
                    }
                    final double self = calculateDamage(blockPos2.func_177958_n() + 0.5, blockPos2.func_177956_o() + 1, blockPos2.func_177952_p() + 0.5, (Entity)CrystalAura.mc.field_71439_g);
                    if (self > d && d >= ((EntityLivingBase)entity).func_110143_aJ()) {
                        continue;
                    }
                    if (self - 0.5 > CrystalAura.mc.field_71439_g.func_110143_aJ() && this.antiSelfPop.isEnabled()) {
                        continue;
                    }
                    if (this.antiSuicide.isEnabled() && self > this.maxSelfDmg.getValue()) {
                        continue;
                    }
                    damage = d;
                    blockPos1 = blockPos2;
                    this.renderEnt = entity;
                }
            }
        }
        if (damage == 0.5) {
            this.renderBlock = null;
            this.renderEnt = null;
            resetRotation();
            return;
        }
        this.renderBlock = blockPos1;
        if (this.timer.getTimePassed() / 50L >= 20 - this.breakSpeed.getValue()) {
            if (this.rotate.isEnabled()) {
                this.lookAtPacket(blockPos1.func_177958_n() + 0.5, blockPos1.func_177956_o() - 0.5, blockPos1.func_177952_p() + 0.5, (EntityPlayer)CrystalAura.mc.field_71439_g);
            }
            final RayTraceResult result = CrystalAura.mc.field_71441_e.func_72933_a(new Vec3d(CrystalAura.mc.field_71439_g.field_70165_t, CrystalAura.mc.field_71439_g.field_70163_u + CrystalAura.mc.field_71439_g.func_70047_e(), CrystalAura.mc.field_71439_g.field_70161_v), new Vec3d(blockPos1.func_177958_n() + 0.5, blockPos1.func_177956_o() - 0.5, blockPos1.func_177952_p() + 0.5));
            if (this.raytrace.isEnabled()) {
                if (result == null || result.field_178784_b == null) {
                    this.enumFacing = null;
                    this.renderBlock = null;
                    resetRotation();
                    return;
                }
                this.enumFacing = result.field_178784_b;
            }
            if (this.switchCooldown) {
                this.switchCooldown = false;
                return;
            }
            final Vec3d crystal = new Vec3d(blockPos1.func_177958_n() + 0.5, blockPos1.func_177956_o() - 0.5, blockPos1.func_177952_p() + 0.5);
            if (blockPos1 != null) {
                if (!this.offHand && CrystalAura.mc.field_71439_g.field_71071_by.field_70461_c != crystalSlot && this.switchHand.is("Detect")) {
                    CrystalAura.mc.field_71439_g.field_71071_by.field_70461_c = crystalSlot;
                    CrystalAura.mc.field_71442_b.func_78765_e();
                    resetRotation();
                    this.switchCooldown = true;
                }
                if (CrystalAura.mc.field_71439_g.func_184614_ca().func_77973_b() != Items.field_185158_cP && CrystalAura.mc.field_71439_g.func_184592_cb().func_77973_b() != Items.field_185158_cP) {
                    return;
                }
                if (this.raytrace.isEnabled() && this.enumFacing != null) {
                    CrystalAura.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos1, this.enumFacing, this.offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                    CrystalAura.placing = true;
                }
                else if (this.strictInteract.isEnabled() && blockPos1.func_177956_o() > CrystalAura.mc.field_71439_g.func_174813_aQ().field_72338_b + CrystalAura.mc.field_71439_g.func_70047_e()) {
                    final RayTraceResult lowestResult = CrystalAura.mc.field_71441_e.func_72933_a(CrystalAura.mc.field_71439_g.func_174824_e(1.0f), crystal);
                    final EnumFacing placeFace = (lowestResult == null || lowestResult.field_178784_b == null) ? EnumFacing.DOWN : lowestResult.field_178784_b;
                    CrystalAura.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos1, placeFace, this.offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                    CrystalAura.placing = true;
                }
                else if (blockPos1.func_177956_o() == 255) {
                    CrystalAura.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos1, EnumFacing.DOWN, this.offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                    CrystalAura.placing = true;
                }
                else {
                    CrystalAura.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos1, EnumFacing.UP, this.offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                    CrystalAura.placing = true;
                }
                CrystalAura.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
                CrystalAura.PlacedCrystals.add(blockPos1);
            }
            if (CrystalAura.isSpoofingAngles) {
                if (this.togglePitch) {
                    final EntityPlayerSP field_71439_g = CrystalAura.mc.field_71439_g;
                    field_71439_g.field_70125_A += (float)4.0E-4;
                    this.togglePitch = false;
                }
                else {
                    final EntityPlayerSP field_71439_g2 = CrystalAura.mc.field_71439_g;
                    field_71439_g2.field_70125_A -= (float)4.0E-4;
                    this.togglePitch = true;
                }
            }
            if (!this.placeCrystal.isEnabled()) {
                return;
            }
            this.timer.resetCurrent();
        }
    }
    
    @Override
    public void render(final HtlrEventRender event) {
        if (this.renderBlock != null && this.showBlock.isEnabled()) {
            RenderUtil.drawBox(this.renderBlock, 1.0, new ColorUtil(this.color.getColor()), 255);
        }
        if (this.outline.isEnabled() && this.renderBlock != null && this.renderEnt != null) {
            RenderUtil.drawBoundingBox(this.renderBlock, 1.0, 1.0f, new ColorUtil(this.color.getColor(), 255));
        }
        if (this.showDamage.isEnabled() && this.renderBlock != null && this.renderEnt != null) {
            final double d = calculateDamage(this.renderBlock.func_177958_n() + 0.5, this.renderBlock.func_177956_o() + 1, this.renderBlock.func_177952_p() + 0.5, this.renderEnt);
            final String[] damageText = { ((Math.floor(d) == d) ? Integer.valueOf((int)d) : String.format("%.1f", d)) + "" };
            RenderUtil.drawNametag(this.renderBlock.func_177958_n() + 0.5, this.renderBlock.func_177956_o() + 0.5, this.renderBlock.func_177952_p() + 0.5, damageText, new ColorUtil(255, 255, 255), 1);
        }
    }
    
    private void breakCrystal(final EntityEnderCrystal crystal) {
        CrystalAura.mc.field_71442_b.func_78764_a((EntityPlayer)CrystalAura.mc.field_71439_g, (Entity)crystal);
        this.swingArm();
    }
    
    private void swingArm() {
        if (this.breakHand.getMode().equalsIgnoreCase("Both") && CrystalAura.mc.field_71439_g.func_184592_cb() != null) {
            CrystalAura.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
            CrystalAura.mc.field_71439_g.func_184609_a(EnumHand.OFF_HAND);
        }
        else if (this.breakHand.getMode().equalsIgnoreCase("Offhand") && CrystalAura.mc.field_71439_g.func_184592_cb() != null) {
            CrystalAura.mc.field_71439_g.func_184609_a(EnumHand.OFF_HAND);
        }
        else {
            CrystalAura.mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
        }
    }
    
    public boolean canPlaceCrystal(final BlockPos blockPos) {
        final BlockPos airBlock1 = blockPos.func_177982_a(0, 1, 0);
        final BlockPos airBlock2 = blockPos.func_177982_a(0, 2, 0);
        final boolean crystal = CrystalAura.mc.field_71441_e.field_72996_f.stream().filter(entity -> entity instanceof EntityEnderCrystal).filter(e -> CrystalAura.mc.field_71439_g.func_70032_d(e) <= this.breakRange.getValue()).filter(this::crystalCheck).map(entity -> entity).min(Comparator.comparing(c -> CrystalAura.mc.field_71439_g.func_70032_d(c))).orElse(null) != null;
        if (this.mode113.isEnabled()) {
            return (CrystalAura.mc.field_71441_e.func_180495_p(blockPos).func_177230_c() == Blocks.field_150357_h || CrystalAura.mc.field_71441_e.func_180495_p(blockPos).func_177230_c() == Blocks.field_150343_Z) && CrystalAura.mc.field_71441_e.func_72872_a((Class)Entity.class, new AxisAlignedBB(airBlock1)).isEmpty() && CrystalAura.mc.field_71441_e.func_72872_a((Class)Entity.class, new AxisAlignedBB(airBlock2)).isEmpty();
        }
        if (!this.multiplace.isEnabled() && !this.highPing.isEnabled() && !crystal) {
            return (CrystalAura.mc.field_71441_e.func_180495_p(blockPos).func_177230_c() == Blocks.field_150357_h || CrystalAura.mc.field_71441_e.func_180495_p(blockPos).func_177230_c() == Blocks.field_150343_Z) && CrystalAura.mc.field_71441_e.func_180495_p(airBlock1).func_177230_c() == Blocks.field_150350_a && CrystalAura.mc.field_71441_e.func_180495_p(airBlock2).func_177230_c() == Blocks.field_150350_a && CrystalAura.mc.field_71441_e.func_72872_a((Class)Entity.class, new AxisAlignedBB(airBlock1)).isEmpty() && CrystalAura.mc.field_71441_e.func_72872_a((Class)Entity.class, new AxisAlignedBB(airBlock2)).isEmpty();
        }
        if (!this.multiplace.isEnabled() && !this.highPing.isEnabled() && crystal) {
            return false;
        }
        if (this.multiplace.isEnabled() && !this.multiplacePlus.isEnabled() && CrystalAura.PlacedCrystals.size() > this.multiplaceValue.getValue()) {
            return false;
        }
        if ((this.multiplace.isEnabled() && CrystalAura.PlacedCrystals.size() <= this.multiplaceValue.getValue()) || (this.multiplace.isEnabled() && this.multiplacePlus.isEnabled())) {
            return (CrystalAura.mc.field_71441_e.func_180495_p(blockPos).func_177230_c() == Blocks.field_150357_h || CrystalAura.mc.field_71441_e.func_180495_p(blockPos).func_177230_c() == Blocks.field_150343_Z) && CrystalAura.mc.field_71441_e.func_180495_p(airBlock1).func_177230_c() == Blocks.field_150350_a && CrystalAura.mc.field_71441_e.func_180495_p(airBlock2).func_177230_c() == Blocks.field_150350_a && CrystalAura.mc.field_71441_e.func_72872_a((Class)Entity.class, new AxisAlignedBB(airBlock1)).isEmpty() && CrystalAura.mc.field_71441_e.func_72872_a((Class)Entity.class, new AxisAlignedBB(airBlock2)).isEmpty();
        }
        return (CrystalAura.mc.field_71441_e.func_180495_p(blockPos).func_177230_c() == Blocks.field_150357_h || CrystalAura.mc.field_71441_e.func_180495_p(blockPos).func_177230_c() == Blocks.field_150343_Z) && CrystalAura.mc.field_71441_e.func_180495_p(airBlock1).func_177230_c() == Blocks.field_150350_a && CrystalAura.mc.field_71441_e.func_180495_p(airBlock2).func_177230_c() == Blocks.field_150350_a && CrystalAura.mc.field_71441_e.func_72872_a((Class)Entity.class, new AxisAlignedBB(airBlock1)).isEmpty() && CrystalAura.mc.field_71441_e.func_72872_a((Class)Entity.class, new AxisAlignedBB(airBlock2)).isEmpty();
    }
    
    private List<BlockPos> findCrystalBlocks() {
        final NonNullList<BlockPos> positions = (NonNullList<BlockPos>)NonNullList.func_191196_a();
        positions.addAll((Collection)this.getSphere(getPlayerPos(), this.placeRange.getValue(), (int)this.placeRange.getValue(), false, true, 0).stream().filter((Predicate<? super Object>)this::canPlaceCrystal).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)positions;
    }
    
    private boolean crystalCheck(final Entity crystal) {
        if (!(crystal instanceof EntityEnderCrystal)) {
            return false;
        }
        if (this.breakMode.getMode().equalsIgnoreCase("All")) {
            return true;
        }
        if (this.breakMode.getMode().equalsIgnoreCase("Own")) {
            for (final BlockPos pos : new ArrayList<BlockPos>(CrystalAura.PlacedCrystals)) {
                if (pos != null && pos.func_185332_f((int)crystal.field_70165_t, (int)crystal.field_70163_u, (int)crystal.field_70161_v) <= 3.0) {
                    return true;
                }
            }
        }
        else if (this.breakMode.getMode().equalsIgnoreCase("Smart")) {
            final EntityLivingBase target = (EntityLivingBase)((this.renderEnt != null) ? this.renderEnt : this.GetNearTarget(crystal));
            if (target == null || target == CrystalAura.mc.field_71439_g) {
                return false;
            }
            final float targetDmg = calculateDamage(crystal.field_70165_t + 0.5, crystal.field_70163_u + 1.0, crystal.field_70161_v + 0.5, (Entity)target);
            return targetDmg >= this.minDmg.getValue() || (targetDmg > this.minDmg.getValue() && target.func_110143_aJ() > this.facePlaceValue.getValue());
        }
        return false;
    }
    
    private boolean validTarget(final Entity entity) {
        return entity != null && entity instanceof EntityLivingBase && !entity.field_70128_L && ((EntityLivingBase)entity).func_110143_aJ() > 0.0f && entity instanceof EntityPlayer && entity != CrystalAura.mc.field_71439_g;
    }
    
    private EntityLivingBase GetNearTarget(final Entity distanceTarget) {
        return (EntityLivingBase)CrystalAura.mc.field_71441_e.field_72996_f.stream().filter(this::validTarget).map(entity -> entity).min(Comparator.comparing((Function<? super T, ? extends Comparable>)distanceTarget::func_70032_d)).orElse(null);
    }
    
    private static float getDamageMultiplied(final float damage) {
        final int diff = CrystalAura.mc.field_71441_e.func_175659_aa().func_151525_a();
        return damage * ((diff == 0) ? 0.0f : ((diff == 2) ? 1.0f : ((diff == 1) ? 0.5f : 1.5f)));
    }
    
    public static float calculateDamage(final double posX, final double posY, final double posZ, final Entity entity) {
        final float doubleExplosionSize = 12.0f;
        final double distancedsize = entity.func_70011_f(posX, posY, posZ) / doubleExplosionSize;
        final Vec3d vec3d = new Vec3d(posX, posY, posZ);
        final double blockDensity = entity.field_70170_p.func_72842_a(vec3d, entity.func_174813_aQ());
        final double v = (1.0 - distancedsize) * blockDensity;
        final float damage = (float)(int)((v * v + v) / 2.0 * 7.0 * doubleExplosionSize + 1.0);
        double finald = 1.0;
        if (entity instanceof EntityLivingBase) {
            finald = getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(damage), new Explosion((World)CrystalAura.mc.field_71441_e, (Entity)null, posX, posY, posZ, 6.0f, false, true));
        }
        return (float)finald;
    }
    
    public static float getBlastReduction(final EntityLivingBase entity, float damage, final Explosion explosion) {
        if (entity instanceof EntityPlayer) {
            final EntityPlayer ep = (EntityPlayer)entity;
            final DamageSource ds = DamageSource.func_94539_a(explosion);
            damage = CombatRules.func_189427_a(damage, (float)ep.func_70658_aO(), (float)ep.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111126_e());
            final int k = EnchantmentHelper.func_77508_a(ep.func_184193_aE(), ds);
            final float f = MathHelper.func_76131_a((float)k, 0.0f, 20.0f);
            damage *= 1.0f - f / 25.0f;
            if (entity.func_70644_a((Potion)Objects.requireNonNull(Potion.func_188412_a(11)))) {
                damage -= damage / 4.0f;
            }
            damage = Math.max(damage, 0.0f);
            return damage;
        }
        damage = CombatRules.func_189427_a(damage, (float)entity.func_70658_aO(), (float)entity.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111126_e());
        return damage;
    }
    
    public List<BlockPos> getSphere(final BlockPos loc, final float r, final int h, final boolean hollow, final boolean sphere, final int plus_y) {
        final List<BlockPos> circleblocks = new ArrayList<BlockPos>();
        final int cx = loc.func_177958_n();
        final int cy = loc.func_177956_o();
        final int cz = loc.func_177952_p();
        for (int x = cx - (int)r; x <= cx + r; ++x) {
            for (int z = cz - (int)r; z <= cz + r; ++z) {
                for (int y = sphere ? (cy - (int)r) : cy; y < (sphere ? (cy + r) : ((float)(cy + h))); ++y) {
                    final double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0);
                    if (dist < r * r && (!hollow || dist >= (r - 1.0f) * (r - 1.0f))) {
                        final BlockPos l = new BlockPos(x, y + plus_y, z);
                        circleblocks.add(l);
                    }
                }
            }
        }
        return circleblocks;
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(CrystalAura.mc.field_71439_g.field_70165_t), Math.floor(CrystalAura.mc.field_71439_g.field_70163_u), Math.floor(CrystalAura.mc.field_71439_g.field_70161_v));
    }
    
    private static void resetRotation() {
        if (CrystalAura.isSpoofingAngles) {
            CrystalAura.yaw = CrystalAura.mc.field_71439_g.field_70177_z;
            CrystalAura.pitch = CrystalAura.mc.field_71439_g.field_70125_A;
            CrystalAura.isSpoofingAngles = false;
        }
    }
    
    public static double[] calculateLookAt(final double px, final double py, final double pz, final EntityPlayer me) {
        double dirx = me.field_70165_t - px;
        double diry = me.field_70163_u - py;
        double dirz = me.field_70161_v - pz;
        final double len = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);
        dirx /= len;
        diry /= len;
        dirz /= len;
        double pitch = Math.asin(diry);
        double yaw = Math.atan2(dirz, dirx);
        pitch = pitch * 180.0 / 3.141592653589793;
        yaw = yaw * 180.0 / 3.141592653589793;
        yaw += 90.0;
        return new double[] { yaw, pitch };
    }
    
    private static void setYawAndPitch(final float yaw1, final float pitch1) {
        CrystalAura.yaw = yaw1;
        CrystalAura.pitch = pitch1;
        CrystalAura.isSpoofingAngles = true;
    }
    
    private void lookAtPacket(final double px, final double py, final double pz, final EntityPlayer me) {
        final double[] v = calculateLookAt(px, py, pz, me);
        setYawAndPitch((float)v[0], (float)v[1]);
    }
    
    static {
        PlacedCrystals = new ArrayList<BlockPos>();
        CrystalAura.ghosting = false;
        CrystalAura.placing = false;
    }
}
