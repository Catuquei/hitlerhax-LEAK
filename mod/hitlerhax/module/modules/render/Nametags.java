// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.render;

import net.minecraft.util.ResourceLocation;
import java.util.Iterator;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.init.Enchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import mod.hitlerhax.util.font.FontUtils;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import mod.hitlerhax.util.render.RenderUtil;
import mod.hitlerhax.util.render.ColorUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import mod.hitlerhax.event.events.HtlrEventRender;
import mod.hitlerhax.setting.Setting;
import java.util.function.Predicate;
import mod.hitlerhax.module.Category;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventRenderEntityName;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.setting.settings.IntSetting;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.module.Module;

public class Nametags extends Module
{
    final BooleanSetting renderSelf;
    final IntSetting range;
    final BooleanSetting items;
    final BooleanSetting durability;
    final BooleanSetting protType;
    final BooleanSetting health;
    final BooleanSetting ping;
    @EventHandler
    private final Listener<HtlrEventRenderEntityName> player_nametag;
    
    public Nametags() {
        super("Nametags", "Adds More Features To Nametags", Category.RENDER);
        this.renderSelf = new BooleanSetting("self", this, true);
        this.range = new IntSetting("Range", this, 150);
        this.items = new BooleanSetting("items", this, true);
        this.durability = new BooleanSetting("durability", this, true);
        this.protType = new BooleanSetting("protType", this, true);
        this.health = new BooleanSetting("health", this, true);
        this.ping = new BooleanSetting("ping", this, true);
        this.player_nametag = new Listener<HtlrEventRenderEntityName>(event -> event.cancel(), (Predicate<HtlrEventRenderEntityName>[])new Predicate[0]);
        this.addSetting(this.renderSelf);
        this.addSetting(this.range);
        this.addSetting(this.items);
        this.addSetting(this.durability);
        this.addSetting(this.protType);
        this.addSetting(this.health);
        this.addSetting(this.ping);
    }
    
    @Override
    public void render(final HtlrEventRender event) {
        if (Nametags.mc.field_71439_g == null || Nametags.mc.field_71441_e == null) {
            return;
        }
        final Vec3d vec3d;
        Nametags.mc.field_71441_e.field_73010_i.stream().filter(this::shouldRender).forEach(entityPlayer -> {
            vec3d = this.findEntityVec3d(entityPlayer);
            this.renderNameTags(entityPlayer, vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
        });
    }
    
    private void renderNameTags(final EntityPlayer entityPlayer, final double posX, final double posY, final double posZ) {
        final double adjustedY = posY + (entityPlayer.func_70093_af() ? 1.9 : 2.1);
        final String[] name = { this.buildEntityNameString(entityPlayer) };
        RenderUtil.drawNametag(posX, adjustedY, posZ, name, new ColorUtil(255, 255, 255, 255), 2);
        this.renderItemsPos(entityPlayer, 0, 0);
        GlStateManager.func_179121_F();
    }
    
    private boolean shouldRender(final EntityPlayer entityPlayer) {
        return (entityPlayer != Nametags.mc.field_71439_g || this.renderSelf.isEnabled()) && !entityPlayer.field_70128_L && entityPlayer.func_110143_aJ() > 0.0f && entityPlayer.func_70032_d((Entity)Nametags.mc.field_71439_g) <= this.range.getValue();
    }
    
    private Vec3d findEntityVec3d(final EntityPlayer entityPlayer) {
        final double posX = this.balancePosition(entityPlayer.field_70165_t, entityPlayer.field_70142_S);
        final double posY = this.balancePosition(entityPlayer.field_70163_u, entityPlayer.field_70137_T);
        final double posZ = this.balancePosition(entityPlayer.field_70161_v, entityPlayer.field_70136_U);
        return new Vec3d(posX, posY, posZ);
    }
    
    private double balancePosition(final double newPosition, final double oldPosition) {
        return oldPosition + (newPosition - oldPosition) * Nametags.mc.field_71428_T.field_194147_b;
    }
    
    private TextFormatting healthColor(final int health) {
        if (health <= 0) {
            return TextFormatting.DARK_RED;
        }
        if (health <= 5) {
            return TextFormatting.RED;
        }
        if (health <= 10) {
            return TextFormatting.GOLD;
        }
        if (health <= 15) {
            return TextFormatting.YELLOW;
        }
        if (health <= 20) {
            return TextFormatting.DARK_GREEN;
        }
        return TextFormatting.GREEN;
    }
    
    private String buildEntityNameString(final EntityPlayer entityPlayer) {
        String name = entityPlayer.func_70005_c_();
        if (this.ping.isEnabled()) {
            int value = 0;
            if (Nametags.mc.func_147114_u() != null && Nametags.mc.func_147114_u().func_175102_a(entityPlayer.func_110124_au()) != null) {
                value = Nametags.mc.func_147114_u().func_175102_a(entityPlayer.func_110124_au()).func_178853_c();
            }
            name = name + " " + value + "ms";
        }
        if (this.health.isEnabled()) {
            final int health = (int)(entityPlayer.func_110143_aJ() + entityPlayer.func_110139_bj());
            final TextFormatting textFormatting = this.healthColor(health);
            name = name + " " + textFormatting + health;
        }
        return name;
    }
    
    private void renderItem(final ItemStack itemStack, final int posX, final int posY, final int posY2) {
        GlStateManager.func_179098_w();
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179086_m(256);
        GlStateManager.func_179126_j();
        GlStateManager.func_179118_c();
        final int posY3 = (posY2 > 4) ? ((posY2 - 4) * 8 / 2) : 0;
        Nametags.mc.func_175599_af().field_77023_b = -150.0f;
        RenderHelper.func_74519_b();
        Nametags.mc.func_175599_af().func_180450_b(itemStack, posX, posY + posY3);
        Nametags.mc.func_175599_af().func_175030_a(Nametags.mc.field_71466_p, itemStack, posX, posY + posY3);
        RenderHelper.func_74518_a();
        Nametags.mc.func_175599_af().field_77023_b = 0.0f;
        RenderUtil.prepare_new();
        GlStateManager.func_179094_E();
        GlStateManager.func_179139_a(0.5, 0.5, 0.5);
        this.renderEnchants(itemStack, posX, posY - 24);
        GlStateManager.func_179121_F();
    }
    
    private void renderItemDurability(final ItemStack itemStack, final int posX, final int posY) {
        float green;
        final float damagePercent = green = (itemStack.func_77958_k() - itemStack.func_77952_i()) / (float)itemStack.func_77958_k();
        if (green > 1.0f) {
            green = 1.0f;
        }
        else if (green < 0.0f) {
            green = 0.0f;
        }
        GlStateManager.func_179098_w();
        GlStateManager.func_179094_E();
        GlStateManager.func_179139_a(0.5, 0.5, 0.5);
        FontUtils.drawStringWithShadow((int)(damagePercent * 100.0f) + "%", posX * 2, posY, new ColorUtil(255, 255, 255, 255));
        GlStateManager.func_179121_F();
        GlStateManager.func_179090_x();
    }
    
    private void renderItemsPos(final EntityPlayer entityPlayer, int posX, int posY) {
        final ItemStack mainHandItem = entityPlayer.func_184614_ca();
        final ItemStack offHandItem = entityPlayer.func_184592_cb();
        int armorCount = 3;
        for (int i = 0; i <= 3; ++i) {
            final ItemStack itemStack = (ItemStack)entityPlayer.field_71071_by.field_70460_b.get(armorCount);
            if (!itemStack.func_190926_b()) {
                posX -= 8;
                final int size = EnchantmentHelper.func_82781_a(itemStack).size();
                if (this.items.isEnabled() && size > posY) {
                    posY = size;
                }
            }
            --armorCount;
        }
        if (!mainHandItem.func_190926_b() && (this.items.isEnabled() || (this.durability.isEnabled() && offHandItem.func_77984_f()))) {
            posX -= 8;
            final int enchantSize = EnchantmentHelper.func_82781_a(offHandItem).size();
            if (this.items.isEnabled() && enchantSize > posY) {
                posY = enchantSize;
            }
        }
        if (!mainHandItem.func_190926_b()) {
            final int enchantSize = EnchantmentHelper.func_82781_a(mainHandItem).size();
            if (this.items.isEnabled() && enchantSize > posY) {
                posY = enchantSize;
            }
            int armorY = this.findArmorY(posY);
            if (this.items.isEnabled() || (this.durability.isEnabled() && mainHandItem.func_77984_f())) {
                posX -= 8;
            }
            if (this.items.isEnabled()) {
                this.renderItem(mainHandItem, posX, armorY, posY);
                armorY -= 32;
            }
            if (this.durability.isEnabled() && mainHandItem.func_77984_f()) {
                this.renderItemDurability(mainHandItem, posX, armorY);
            }
            armorY -= Nametags.mc.field_71466_p.field_78288_b;
            if (this.items.isEnabled() || (this.durability.isEnabled() && mainHandItem.func_77984_f())) {
                posX += 16;
            }
        }
        int armorCount2 = 3;
        for (int j = 0; j <= 3; ++j) {
            final ItemStack itemStack2 = (ItemStack)entityPlayer.field_71071_by.field_70460_b.get(armorCount2);
            if (!itemStack2.func_190926_b()) {
                int armorY2 = this.findArmorY(posY);
                if (this.items.isEnabled()) {
                    this.renderItem(itemStack2, posX, armorY2, posY);
                    armorY2 -= 32;
                }
                if (this.durability.isEnabled() && itemStack2.func_77984_f()) {
                    this.renderItemDurability(itemStack2, posX, armorY2);
                }
                posX += 16;
            }
            --armorCount2;
        }
        if (!offHandItem.func_190926_b()) {
            int armorY = this.findArmorY(posY);
            if (this.items.isEnabled()) {
                this.renderItem(offHandItem, posX, armorY, posY);
                armorY -= 32;
            }
            if (this.durability.isEnabled() && offHandItem.func_77984_f()) {
                this.renderItemDurability(offHandItem, posX, armorY);
            }
        }
    }
    
    private int findArmorY(final int posY) {
        int posY2 = this.durability.isEnabled() ? -26 : -27;
        if (posY > 4) {
            posY2 -= (posY - 4) * 8;
        }
        return posY2;
    }
    
    private void renderEnchants(final ItemStack itemStack, final int posX, final int posY) {
        GlStateManager.func_179098_w();
        for (final Enchantment enchantment : EnchantmentHelper.func_82781_a(itemStack).keySet()) {
            if (enchantment == null) {
                continue;
            }
            if (!this.protType.isEnabled()) {
                continue;
            }
            final int level = EnchantmentHelper.func_77506_a(enchantment, itemStack);
            if (!enchantment.equals(Enchantments.field_185297_d) && !enchantment.equals(Enchantments.field_180310_c)) {
                continue;
            }
            FontUtils.drawStringWithShadow(ChatFormatting.BOLD + this.findStringForEnchants(enchantment, level), posX * 2 + 13, posY + 18, new ColorUtil(255, 255, 255, 255));
        }
        GlStateManager.func_179090_x();
    }
    
    private String findStringForEnchants(final Enchantment enchantment, final int level) {
        final ResourceLocation resourceLocation = (ResourceLocation)Enchantment.field_185264_b.func_177774_c((Object)enchantment);
        String string = (resourceLocation == null) ? enchantment.func_77320_a() : resourceLocation.toString();
        final int charCount = (level > 1) ? 12 : 13;
        if (string.length() > charCount) {
            string = string.substring(10, charCount);
        }
        return string.substring(0, 1).toUpperCase() + string.substring(1) + ((level > 1) ? Integer.valueOf(level) : "");
    }
}
