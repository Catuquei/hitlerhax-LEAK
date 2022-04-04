// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.render;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.client.renderer.entity.RenderManager;
import mod.hitlerhax.util.render.RenderUtil;
import java.util.Objects;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.world.World;
import java.util.LinkedList;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.ChunkEvent;
import mod.hitlerhax.Main;
import me.zero.alpine.listener.Listenable;
import mod.hitlerhax.event.HtlrEventBus;
import net.minecraftforge.common.MinecraftForge;
import java.util.Iterator;
import java.awt.Color;
import net.minecraft.client.renderer.culling.Frustum;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import mod.hitlerhax.setting.Setting;
import java.util.concurrent.Executors;
import net.minecraft.network.play.server.SPacketMultiBlockChange;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.network.play.server.SPacketChunkData;
import java.util.function.Predicate;
import java.util.HashMap;
import java.util.ArrayList;
import mod.hitlerhax.module.Category;
import java.util.concurrent.ExecutorService;
import mod.hitlerhax.event.events.HtlrEventPacket;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventSettings;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.util.math.ChunkPos;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Block;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.setting.settings.SearchBlockSelectorSetting;
import mod.hitlerhax.module.Module;

public class Search extends Module
{
    final SearchBlockSelectorSetting blocks;
    BooleanSetting outline;
    final BooleanSetting tracer;
    private final ReadWriteLock search_lock;
    public final Map<Block, Integer> to_search;
    private ReadWriteLock targets_lock;
    private Map<BlockPos, Integer> targets;
    private ReadWriteLock new_chunks_lock;
    private List<ChunkPos> new_chunks;
    private final ICamera camera;
    @EventHandler
    private final Listener<HtlrEventSettings> SettingEvent;
    @EventHandler
    private final Listener<HtlrEventPacket.ReceivePacket> PacketRecieveEvent;
    final ExecutorService executor;
    
    public Search() {
        super("Search", "Highlights Blocks", Category.RENDER);
        this.blocks = new SearchBlockSelectorSetting("Select Blocks", this, true, new ArrayList<Block>(), new HashMap<Block, Integer>());
        this.outline = new BooleanSetting("Outline", this, true);
        this.tracer = new BooleanSetting("Tracer", this, true);
        this.SettingEvent = new Listener<HtlrEventSettings>(event -> {
            this.onDisable();
            this.onEnable();
            return;
        }, (Predicate<HtlrEventSettings>[])new Predicate[0]);
        SPacketChunkData chunk;
        SPacketUpdateTileEntity updt;
        Color color;
        SPacketBlockChange change;
        Color color2;
        SPacketMultiBlockChange change2;
        final SPacketMultiBlockChange.BlockUpdateData[] array;
        int length;
        int i = 0;
        SPacketMultiBlockChange.BlockUpdateData up;
        Color color3;
        this.PacketRecieveEvent = new Listener<HtlrEventPacket.ReceivePacket>(event -> {
            if (event.get_packet() instanceof SPacketChunkData) {
                chunk = (SPacketChunkData)event.get_packet();
                this.new_chunks_lock.writeLock().lock();
                this.new_chunks.add(new ChunkPos(chunk.func_149273_e(), chunk.func_149271_f()));
                this.new_chunks_lock.writeLock().unlock();
            }
            else if (event.get_packet() instanceof SPacketUpdateTileEntity) {
                updt = (SPacketUpdateTileEntity)event.get_packet();
                color = this.isTargeted(Search.mc.field_71441_e.func_180495_p(updt.func_179823_a()), updt.func_179823_a(), updt.func_148857_g());
                this.targets_lock.writeLock().lock();
                if (color != null) {
                    this.targets.put(updt.func_179823_a(), color.getRGB());
                }
                else {
                    this.targets.remove(updt.func_179823_a());
                }
                this.targets_lock.writeLock().unlock();
            }
            else if (event.get_packet() instanceof SPacketBlockChange) {
                change = (SPacketBlockChange)event.get_packet();
                color2 = this.isTargeted(change.func_180728_a(), change.func_179827_b(), null);
                this.targets_lock.writeLock().lock();
                if (color2 != null) {
                    this.targets.put(change.func_179827_b(), color2.getRGB());
                }
                else {
                    this.targets.remove(change.func_179827_b());
                }
                this.targets_lock.writeLock().unlock();
            }
            else if (event.get_packet() instanceof SPacketMultiBlockChange) {
                change2 = (SPacketMultiBlockChange)event.get_packet();
                change2.func_179844_a();
                for (length = array.length; i < length; ++i) {
                    up = array[i];
                    color3 = this.isTargeted(up.func_180088_c(), up.func_180090_a(), null);
                    this.targets_lock.writeLock().lock();
                    if (color3 != null) {
                        this.targets.put(up.func_180090_a(), color3.getRGB());
                    }
                    else {
                        this.targets.remove(up.func_180090_a());
                    }
                    this.targets_lock.writeLock().unlock();
                }
            }
            return;
        }, (Predicate<HtlrEventPacket.ReceivePacket>[])new Predicate[0]);
        this.executor = Executors.newSingleThreadExecutor();
        this.addSetting(this.blocks);
        this.addSetting(this.tracer);
        this.to_search = new HashMap<Block, Integer>();
        this.search_lock = new ReentrantReadWriteLock();
        this.targets = new HashMap<BlockPos, Integer>();
        this.targets_lock = new ReentrantReadWriteLock();
        this.new_chunks = new ArrayList<ChunkPos>();
        this.new_chunks_lock = new ReentrantReadWriteLock();
        this.camera = (ICamera)new Frustum();
    }
    
    @Override
    public void onUpdate() {
        if (this.to_search == null) {
            for (final Block b : this.blocks.blocks) {
                final int color = new Color(this.blocks.getColor(b)).getRGB();
                assert this.to_search != null;
                this.to_search.put(b, color);
            }
        }
    }
    
    @Override
    protected void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        HtlrEventBus.EVENT_BUS.subscribe(this);
        Main.config.Save();
        this.resetTargets();
        for (final Block b : this.blocks.getBlocks()) {
            final int color = new Color(this.blocks.getColor(b)).getRGB();
            this.to_search.put(b, color);
        }
    }
    
    @Override
    protected void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        HtlrEventBus.EVENT_BUS.unsubscribe(this);
        Main.config.Save();
        this.onDisconnect();
    }
    
    public void onDisconnect() {
        this.targets_lock.writeLock().lock();
        this.targets.clear();
        this.targets_lock.writeLock().unlock();
        this.new_chunks_lock.writeLock().lock();
        this.new_chunks.clear();
        this.new_chunks_lock.writeLock().unlock();
    }
    
    @SubscribeEvent
    public void onUnLoad(final ChunkEvent.Unload event) {
        final int x = event.getChunk().field_76635_g;
        final int z = event.getChunk().field_76647_h;
        this.targets_lock.writeLock().lock();
        final int n;
        final int n2;
        this.targets.keySet().removeIf(position -> position.func_177958_n() >> 4 == n && position.func_177952_p() >> 4 == n2);
        this.targets_lock.writeLock().unlock();
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (Search.mc.field_71441_e == null) {
            return;
        }
        this.new_chunks_lock.readLock().lock();
        final int size = this.new_chunks.size();
        this.new_chunks_lock.readLock().unlock();
        if (size == 0) {
            return;
        }
        if (Search.mc.field_71441_e == null) {
            return;
        }
        final IChunkProvider provider = (IChunkProvider)Search.mc.field_71441_e.func_72863_F();
        if (provider == null) {
            return;
        }
        this.new_chunks_lock.writeLock().lock();
        final Iterator<ChunkPos> iterator = this.new_chunks.iterator();
        while (iterator.hasNext()) {
            final ChunkPos position = iterator.next();
            final Chunk c = provider.func_186026_b(position.field_77276_a, position.field_77275_b);
            if (c != null) {
                this.searchChunk(c);
                iterator.remove();
            }
        }
        this.new_chunks_lock.writeLock().unlock();
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        final RenderManager renderManager = Search.mc.func_175598_ae();
        if (renderManager == null || renderManager.field_78733_k == null) {
            return;
        }
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_179120_a(770, 771, 1, 0);
        GlStateManager.func_179103_j(7425);
        GlStateManager.func_179097_i();
        GlStateManager.func_179094_E();
        GlStateManager.func_179132_a(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(1.5f);
        final List<Target> tracers = new LinkedList<Target>();
        this.targets_lock.readLock().lock();
        for (final BlockPos position : this.targets.keySet()) {
            final AxisAlignedBB bb = Search.mc.field_71441_e.func_180495_p(position).func_185918_c((World)Search.mc.field_71441_e, position).func_186662_g(0.0020000000949949026).func_72317_d(-renderManager.field_78730_l, -renderManager.field_78731_m, -renderManager.field_78728_n);
            final int color = this.targets.get(position);
            tracers.add(new Target(position, color));
            this.camera.func_78547_a(0.0, 0.0, 0.0);
            if (this.camera.func_78546_a(bb)) {
                final float red = (color >> 16 & 0xFF) / 255.0f;
                final float blue = (color >> 8 & 0xFF) / 255.0f;
                final float green = (color & 0xFF) / 255.0f;
                RenderGlobal.func_189694_a(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c, bb.field_72336_d, bb.field_72337_e, bb.field_72334_f, red, blue, green, 0.65f);
                RenderGlobal.func_189695_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c, bb.field_72336_d, bb.field_72337_e, bb.field_72334_f, red, blue, green, 0.25f);
            }
        }
        this.targets_lock.readLock().unlock();
        GL11.glDisable(2848);
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179121_F();
        final boolean bobbing = Search.mc.field_71474_y.field_74336_f;
        Search.mc.field_71474_y.field_74336_f = false;
        Search.mc.field_71460_t.func_78479_a(event.getPartialTicks(), 0);
        for (final Target t : tracers) {
            final Vec3d pos = new Vec3d((Vec3i)t.position).func_72441_c(0.5, 0.5, 0.5).func_178786_a(renderManager.field_78730_l, renderManager.field_78731_m, renderManager.field_78728_n);
            final Vec3d forward = new Vec3d(0.0, 0.0, 1.0).func_178789_a(-(float)Math.toRadians(Objects.requireNonNull(Search.mc.func_175606_aa()).field_70125_A)).func_178785_b(-(float)Math.toRadians(Search.mc.func_175606_aa().field_70177_z));
            if (this.tracer.isEnabled()) {
                RenderUtil.drawLine3D((float)forward.field_72450_a, (float)forward.field_72448_b + Search.mc.func_175606_aa().func_70047_e(), (float)forward.field_72449_c, (float)pos.field_72450_a, (float)pos.field_72448_b, (float)pos.field_72449_c, 0.85f, t.color);
            }
        }
        Search.mc.field_71474_y.field_74336_f = bobbing;
        Search.mc.field_71460_t.func_78479_a(event.getPartialTicks(), 0);
        GlStateManager.func_187441_d(1.0f);
        GlStateManager.func_179103_j(7424);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
        GlStateManager.func_179126_j();
        GlStateManager.func_179089_o();
    }
    
    public void resetTargets() {
        if (Search.mc.field_71441_e == null) {
            return;
        }
        this.targets_lock.writeLock().lock();
        this.targets.clear();
        this.targets_lock.writeLock().unlock();
        final int x = (int)Objects.requireNonNull(Search.mc.func_175606_aa()).field_70165_t >> 4;
        final int z = (int)Search.mc.func_175606_aa().field_70161_v >> 4;
        for (int i = x - Search.mc.field_71474_y.field_151451_c; i <= x + Search.mc.field_71474_y.field_151451_c; ++i) {
            for (int j = z - Search.mc.field_71474_y.field_151451_c; j <= z + Search.mc.field_71474_y.field_151451_c; ++j) {
                final Chunk c = Search.mc.field_71441_e.func_72863_F().func_186026_b(i, j);
                if (c != null) {
                    this.searchChunk(c);
                }
            }
        }
    }
    
    private void searchChunk(final Chunk chunk) {
        int m;
        final ExtendedBlockStorage[] array;
        int length;
        int l = 0;
        ExtendedBlockStorage storage;
        int i;
        int j;
        int k;
        BlockPos position;
        Color color;
        this.executor.execute(() -> {
            synchronized (this.blocks) {
                m = -1;
                chunk.func_76587_i();
                for (length = array.length; l < length; ++l) {
                    storage = array[l];
                    ++m;
                    if (storage != null) {
                        for (i = 0; i < 16; ++i) {
                            for (j = 0; j < 16; ++j) {
                                for (k = 0; k < 16; ++k) {
                                    position = new BlockPos((chunk.field_76635_g << 4) + i, (m << 4) + j, (chunk.field_76647_h << 4) + k);
                                    color = this.isTargeted(storage.func_177485_a(i, j, k), position, null);
                                    if (this.blocks.blocks.contains(Search.mc.field_71441_e.func_180495_p(position).func_177230_c()) && color != null) {
                                        this.targets_lock.writeLock().lock();
                                        this.targets.put(position, color.getRGB());
                                        this.targets_lock.writeLock().unlock();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
    
    public void ClearFromTargets(final int color) {
        this.targets_lock.readLock().lock();
        this.targets.keySet().removeIf(position -> this.targets.get(position) == color);
        this.targets_lock.readLock().unlock();
    }
    
    public Color isTargeted(final IBlockState state, final BlockPos position, final NBTTagCompound updt_tag) {
        this.search_lock.readLock().lock();
        Color color = null;
        if (state.func_177230_c() != null && this.to_search != null) {
            if (!this.blocks.getBlocks().contains(state.func_177230_c())) {
                this.search_lock.readLock().unlock();
                return null;
            }
            color = new Color(this.blocks.colors.get(state.func_177230_c()));
        }
        this.search_lock.readLock().unlock();
        return color;
    }
    
    public boolean containsTag(final NBTTagCompound tag, final NBTTagCompound sub) {
        for (final String key : sub.func_150296_c()) {
            if (tag.func_150299_b(key) != sub.func_150299_b(key)) {
                return false;
            }
            if (sub.func_150299_b(key) == 10) {
                if (!this.containsTag(tag.func_74775_l(key), sub.func_74775_l(key))) {
                    return false;
                }
                continue;
            }
            else {
                if (!tag.func_74781_a(key).equals((Object)sub.func_74781_a(key))) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    private static class Target
    {
        public int color;
        public BlockPos position;
        
        public Target(final BlockPos position, final int color) {
            this.position = position;
            this.color = color;
        }
    }
}
