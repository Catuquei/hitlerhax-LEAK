// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.player;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import java.util.UUID;
import com.mojang.authlib.GameProfile;
import mod.hitlerhax.setting.Setting;
import mod.hitlerhax.module.Category;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import mod.hitlerhax.setting.settings.StringSetting;
import mod.hitlerhax.module.Module;

public class FakePlayer extends Module
{
    final StringSetting name;
    public EntityOtherPlayerMP newPlayer;
    
    public FakePlayer() {
        super("FakePlayer", "Spawns in a fake player", Category.PLAYER);
        this.name = new StringSetting("FakePlayer name", this, "Jakethasnake52");
        this.addSetting(this.name);
    }
    
    public void onEnable() {
        if (FakePlayer.mc.field_71439_g != null && FakePlayer.mc.field_71441_e != null) {
            (this.newPlayer = new EntityOtherPlayerMP((World)FakePlayer.mc.field_71441_e, new GameProfile((UUID)null, this.name.value))).func_82149_j((Entity)FakePlayer.mc.field_71439_g);
            this.newPlayer.field_70759_as = FakePlayer.mc.field_71439_g.field_70759_as;
            FakePlayer.mc.field_71441_e.func_73027_a(-100, (Entity)this.newPlayer);
        }
        else {
            this.toggle();
        }
    }
    
    public void onDisable() {
        if (FakePlayer.mc.field_71439_g != null && FakePlayer.mc.field_71441_e != null) {
            FakePlayer.mc.field_71441_e.func_72900_e((Entity)this.newPlayer);
        }
    }
}
