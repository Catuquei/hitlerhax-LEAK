// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.util;

import mod.hitlerhax.Main;
import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class HtlrDiscordRichPresence
{
    private static final DiscordRichPresence discordRichPresence;
    private static final DiscordRPC discordRPC;
    
    public static void start(final String mode) {
        final DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        eventHandlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2));
        if (mode.equalsIgnoreCase("HitlerHax")) {
            final String discordID = "870753739500290060";
            HtlrDiscordRichPresence.discordRPC.Discord_Initialize(discordID, eventHandlers, true, null);
            HtlrDiscordRichPresence.discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
            HtlrDiscordRichPresence.discordRichPresence.details = "by MaxRockatasky";
            HtlrDiscordRichPresence.discordRichPresence.largeImageKey = "dripler";
            HtlrDiscordRichPresence.discordRichPresence.largeImageText = "HitlerHax by MaxRockatasky";
            HtlrDiscordRichPresence.discordRichPresence.state = null;
        }
        else if (mode.equalsIgnoreCase("Vanilla")) {
            final String discordID = "901118741436309505";
            HtlrDiscordRichPresence.discordRPC.Discord_Initialize(discordID, eventHandlers, true, null);
            HtlrDiscordRichPresence.discordRichPresence.startTimestamp = Main.startTimeStamp / 1000L;
            HtlrDiscordRichPresence.discordRichPresence.details = null;
            HtlrDiscordRichPresence.discordRichPresence.largeImageKey = "icon";
            HtlrDiscordRichPresence.discordRichPresence.state = null;
        }
        HtlrDiscordRichPresence.discordRPC.Discord_UpdatePresence(HtlrDiscordRichPresence.discordRichPresence);
    }
    
    public static void stop() {
        HtlrDiscordRichPresence.discordRPC.Discord_Shutdown();
        HtlrDiscordRichPresence.discordRPC.Discord_ClearPresence();
    }
    
    static {
        discordRichPresence = new DiscordRichPresence();
        discordRPC = DiscordRPC.INSTANCE;
    }
}
