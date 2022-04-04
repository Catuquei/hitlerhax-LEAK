// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.command.commands;

import net.minecraft.inventory.IInventory;
import net.minecraft.entity.passive.AbstractHorse;
import mod.hitlerhax.Client;
import mod.hitlerhax.command.Command;

public class Entity extends Command
{
    private net.minecraft.entity.Entity e;
    
    @Override
    public String getAlias() {
        return "entity";
    }
    
    @Override
    public String getDescription() {
        return "interact with a set entity";
    }
    
    @Override
    public String getSyntax() {
        return ".entity setpointedentity | .entity clear | .entity inventory | .entity ride | .entity dismount | .entity setridingentity";
    }
    
    @Override
    public void onCommand(final String command, final String[] args) {
        if (args[0].isEmpty()) {
            Client.addChatMessage("No arguments found");
            Client.addChatMessage(this.getSyntax());
        }
        else {
            final String s = args[0];
            switch (s) {
                case "setpointedentity": {
                    this.e = Entity.mc.field_147125_j;
                    break;
                }
                case "setridingentity": {
                    this.e = Entity.mc.field_71439_g.field_184239_as;
                    break;
                }
                case "clear": {
                    this.e = null;
                    break;
                }
                case "inventory": {
                    if (this.e != null) {
                        final boolean sneakState = Entity.mc.field_71439_g.func_70093_af();
                        Entity.mc.field_71439_g.func_70095_a(true);
                        Entity.mc.field_71439_g.func_184826_a((AbstractHorse)this.e, (IInventory)((AbstractHorse)this.e).field_110296_bG);
                        Entity.mc.field_71439_g.func_70095_a(sneakState);
                        break;
                    }
                    break;
                }
                case "ride": {
                    Entity.mc.field_71439_g.func_184220_m(this.e);
                    break;
                }
                case "dismount": {
                    Entity.mc.field_71439_g.func_184210_p();
                    break;
                }
                default: {
                    Client.addChatMessage("Invalid Argument(s)");
                    Client.addChatMessage(this.getSyntax());
                    break;
                }
            }
        }
    }
}
