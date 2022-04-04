// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraft.client.multiplayer.ServerData;
import mod.hitlerhax.module.modules.utilities.Reconnect;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.ClientChatEvent;
import mod.hitlerhax.ui.clickgui.ClickGuiController;
import mod.hitlerhax.Client;
import java.util.Iterator;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiChat;
import mod.hitlerhax.module.Module;
import org.lwjgl.input.Keyboard;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import mod.hitlerhax.event.events.HtlrEventGameOverlay;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import mod.hitlerhax.Main;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import mod.hitlerhax.util.Globals;
import me.zero.alpine.listener.Listenable;

public class HtlrEventManager implements Listenable, Globals
{
    @SubscribeEvent
    public void onUpdate(final LivingEvent.LivingUpdateEvent event) {
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (HtlrEventManager.mc.field_71439_g == null) {
            return;
        }
        Main.moduleManager.update();
    }
    
    @SubscribeEvent
    public void onWorldRender(final RenderWorldLastEvent event) {
        if (event.isCanceled()) {
            return;
        }
        Main.moduleManager.render(event);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Post event) {
        if (event.isCanceled()) {
            return;
        }
        HtlrEventBus.EVENT_BUS.post(new HtlrEventGameOverlay(event.getPartialTicks(), new ScaledResolution(HtlrEventManager.mc)));
    }
    
    @SubscribeEvent
    public void onInputUpdate(final InputUpdateEvent event) {
        HtlrEventBus.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void key(final InputEvent.KeyInputEvent k) {
        if (HtlrEventManager.mc.field_71441_e == null || HtlrEventManager.mc.field_71439_g == null) {
            return;
        }
        try {
            if (Keyboard.isCreated() && Keyboard.getEventKeyState()) {
                final int keyCode = Keyboard.getEventKey();
                if (keyCode <= 0) {
                    return;
                }
                for (final Module m : Main.moduleManager.modules) {
                    if (m.toggled) {
                        m.onKeyPress();
                    }
                    if (m.getKey() == keyCode && keyCode > 0) {
                        m.toggle();
                        return;
                    }
                }
                if (keyCode == 52) {
                    HtlrEventManager.mc.func_147108_a((GuiScreen)new GuiChat("."));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SubscribeEvent
    public void tickKeybind(final TickEvent event) {
        if (Client.getNextKeyPressForKeybinding) {
            for (int i = 0; i < Keyboard.getKeyCount(); ++i) {
                if (Keyboard.isKeyDown(i)) {
                    Client.getNextKeyPressForKeybinding = false;
                    Client.keybindModule.setKey(i);
                    Client.keybindModule = null;
                    ClickGuiController.INSTANCE.settingController.refresh(false);
                    Main.config.Save();
                    return;
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onChatMessage(final ClientChatEvent event) {
        final String message = event.getMessage();
        if (message.startsWith(Client.getCommandPrefix())) {
            final String command = message.substring(message.indexOf(Client.getCommandPrefix()) + 1);
            Client.addChatMessage("§c" + message, false);
            Main.cmdManager.callCommand(command);
            event.setCanceled(true);
            HtlrEventManager.mc.field_71456_v.func_146158_b().func_146239_a(message);
        }
    }
    
    @SubscribeEvent
    public void sendPacket(final GuiOpenEvent event) {
        if (event.getGui() instanceof GuiDisconnected) {
            final ServerData data = HtlrEventManager.mc.func_147104_D();
            if (data != null) {
                ((Reconnect)Main.moduleManager.getModule("Reconnect")).serverData = data;
            }
        }
    }
    
    @SubscribeEvent
    public void onWorldUnload(final WorldEvent.Unload event) {
        final ServerData data = HtlrEventManager.mc.func_147104_D();
        final Module freecam = Main.moduleManager.getModule("freecam");
        if (freecam.isToggled()) {
            freecam.setToggled(false);
        }
        if (data != null) {
            ((Reconnect)Main.moduleManager.getModule("Reconnect")).serverData = data;
        }
    }
}
