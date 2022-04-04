// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.module.modules.utilities;

import mod.hitlerhax.setting.Setting;
import net.minecraft.network.play.server.SPacketWorldBorder;
import net.minecraft.network.play.server.SPacketWindowProperty;
import net.minecraft.network.play.server.SPacketWindowItems;
import net.minecraft.network.play.server.SPacketUseBed;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.network.play.server.SPacketUpdateScore;
import net.minecraft.network.play.server.SPacketUpdateHealth;
import net.minecraft.network.play.server.SPacketUpdateBossInfo;
import net.minecraft.network.play.server.SPacketUnloadChunk;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.network.play.server.SPacketTeams;
import net.minecraft.network.play.server.SPacketTabComplete;
import net.minecraft.network.play.server.SPacketStatistics;
import net.minecraft.network.play.server.SPacketSpawnPosition;
import net.minecraft.network.play.server.SPacketSpawnPlayer;
import net.minecraft.network.play.server.SPacketSpawnPainting;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.network.play.server.SPacketSpawnMob;
import net.minecraft.network.play.server.SPacketSpawnGlobalEntity;
import net.minecraft.network.play.server.SPacketSpawnExperienceOrb;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.network.play.server.SPacketSignEditorOpen;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraft.network.play.server.SPacketSetPassengers;
import net.minecraft.network.play.server.SPacketSetExperience;
import net.minecraft.network.play.server.SPacketServerDifficulty;
import net.minecraft.network.play.server.SPacketSelectAdvancementsTab;
import net.minecraft.network.play.server.SPacketScoreboardObjective;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.network.play.server.SPacketResourcePackSend;
import net.minecraft.network.play.server.SPacketRemoveEntityEffect;
import net.minecraft.network.play.server.SPacketRecipeBook;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraft.network.play.server.SPacketPlayerListHeaderFooter;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketPlaceGhostRecipe;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.network.play.server.SPacketOpenWindow;
import net.minecraft.network.play.server.SPacketMultiBlockChange;
import net.minecraft.network.play.server.SPacketMoveVehicle;
import net.minecraft.network.play.server.SPacketMaps;
import net.minecraft.network.play.server.SPacketKeepAlive;
import net.minecraft.network.play.server.SPacketJoinGame;
import net.minecraft.network.play.server.SPacketHeldItemChange;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketEntityTeleport;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.network.play.server.SPacketEntityProperties;
import net.minecraft.network.play.server.SPacketEntityMetadata;
import net.minecraft.network.play.server.SPacketEntityHeadLook;
import net.minecraft.network.play.server.SPacketEntityEquipment;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketEntityAttach;
import net.minecraft.network.play.server.SPacketEntity;
import net.minecraft.network.play.server.SPacketEffect;
import net.minecraft.network.play.server.SPacketDisplayObjective;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.network.play.server.SPacketDestroyEntities;
import net.minecraft.network.play.server.SPacketCustomSound;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.network.play.server.SPacketCooldown;
import net.minecraft.network.play.server.SPacketConfirmTransaction;
import net.minecraft.network.play.server.SPacketCombatEvent;
import net.minecraft.network.play.server.SPacketCollectItem;
import net.minecraft.network.play.server.SPacketCloseWindow;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.network.play.server.SPacketCamera;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.network.play.server.SPacketBlockBreakAnim;
import net.minecraft.network.play.server.SPacketBlockAction;
import net.minecraft.network.play.server.SPacketAnimation;
import net.minecraft.network.play.server.SPacketAdvancementInfo;
import java.util.function.Predicate;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketUpdateSign;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.network.play.client.CPacketSteerBoat;
import net.minecraft.network.play.client.CPacketSpectate;
import net.minecraft.network.play.client.CPacketSeenAdvancements;
import net.minecraft.network.play.client.CPacketResourcePackStatus;
import net.minecraft.network.play.client.CPacketRecipeInfo;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerAbilities;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlaceRecipe;
import net.minecraft.network.play.client.CPacketKeepAlive;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEnchantItem;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.client.CPacketCreativeInventoryAction;
import net.minecraft.network.play.client.CPacketConfirmTransaction;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.network.play.client.CPacketClientStatus;
import net.minecraft.network.play.client.CPacketClientSettings;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketAnimation;
import mod.hitlerhax.module.Category;
import me.zero.alpine.listener.EventHandler;
import mod.hitlerhax.event.events.HtlrEventPacket;
import me.zero.alpine.listener.Listener;
import mod.hitlerhax.setting.settings.BooleanSetting;
import mod.hitlerhax.module.Module;

public class PacketCancel extends Module
{
    final BooleanSetting cPacketAnimation;
    final BooleanSetting cPacketChatMessage;
    final BooleanSetting cPacketClickWindow;
    final BooleanSetting cPacketClientSettings;
    final BooleanSetting cPacketClientStatus;
    final BooleanSetting cPacketCloseWindow;
    final BooleanSetting cPacketConfirmTeleport;
    final BooleanSetting cPacketConfirmTransaction;
    final BooleanSetting cPacketCreativeInventoryAction;
    final BooleanSetting cPacketCustomPayload;
    final BooleanSetting cPacketEnchantItem;
    final BooleanSetting cPacketEntityAction;
    final BooleanSetting cPacketHeldItemChange;
    final BooleanSetting cPacketInput;
    final BooleanSetting cPacketKeepAlive;
    final BooleanSetting cPacketPlaceRecipe;
    final BooleanSetting cPacketPlayer;
    final BooleanSetting cPacketPlayerPosition;
    final BooleanSetting cPacketPlayerPositionRotation;
    final BooleanSetting cPacketPlayerRotation;
    final BooleanSetting cPacketPlayerAbilities;
    final BooleanSetting cPacketPlayerDigging;
    final BooleanSetting cPacketPlayerTryUseItem;
    final BooleanSetting cPacketPlayerTryUseItemOnBlock;
    final BooleanSetting cPacketRecipeInfo;
    final BooleanSetting cPacketResourcePackStatus;
    final BooleanSetting cPacketSeenAdvancements;
    final BooleanSetting cPacketSpectate;
    final BooleanSetting cPacketSteerBoat;
    final BooleanSetting cPacketTabComplete;
    final BooleanSetting cPacketUpdateSign;
    final BooleanSetting cPacketUseEntity;
    final BooleanSetting cPacketVehicleMove;
    final BooleanSetting sPacketAdvancementInfo;
    final BooleanSetting sPacketAnimation;
    final BooleanSetting sPacketBlockAction;
    final BooleanSetting sPacketBlockBreakAnim;
    final BooleanSetting sPacketBlockChange;
    final BooleanSetting sPacketCamera;
    final BooleanSetting sPacketChangeGameState;
    final BooleanSetting sPacketChat;
    final BooleanSetting sPacketChunkData;
    final BooleanSetting sPacketCloseWindow;
    final BooleanSetting sPacketCollectItem;
    final BooleanSetting sPacketCombatEvent;
    final BooleanSetting sPacketConfirmTransaction;
    final BooleanSetting sPacketCooldown;
    final BooleanSetting sPacketCustomPayload;
    final BooleanSetting sPacketCustomSound;
    final BooleanSetting sPacketDestroyEntities;
    final BooleanSetting sPacketDisconnect;
    final BooleanSetting sPacketDisplayObjective;
    final BooleanSetting sPacketEffect;
    final BooleanSetting sPacketEntity;
    final BooleanSetting sPacketEntityRelMove;
    final BooleanSetting sPacketEntityLook;
    final BooleanSetting sPacketEntityLookMove;
    final BooleanSetting sPacketEntityAttach;
    final BooleanSetting sPacketEntityEffect;
    final BooleanSetting sPacketEntityEquipment;
    final BooleanSetting sPacketEntityHeadLook;
    final BooleanSetting sPacketEntityMetadata;
    final BooleanSetting sPacketEntityProperties;
    final BooleanSetting sPacketEntityStatus;
    final BooleanSetting sPacketEntityTeleport;
    final BooleanSetting sPacketEntityVelocity;
    final BooleanSetting sPacketExplosion;
    final BooleanSetting sPacketHeldItemChange;
    final BooleanSetting sPacketJoinGame;
    final BooleanSetting sPacketKeepAlive;
    final BooleanSetting sPacketMaps;
    final BooleanSetting sPacketMoveVehicle;
    final BooleanSetting sPacketMultiBlockChange;
    final BooleanSetting sPacketOpenWindow;
    final BooleanSetting sPacketParticles;
    final BooleanSetting sPacketPlaceGhostRecipe;
    final BooleanSetting sPacketPlayerAbilities;
    final BooleanSetting sPacketPlayerListHeaderFooter;
    final BooleanSetting sPacketPlayerListItem;
    final BooleanSetting sPacketPlayerPosLook;
    final BooleanSetting sPacketRecipeBook;
    final BooleanSetting sPacketRemoveEntityEffect;
    final BooleanSetting sPacketResourcePackSend;
    final BooleanSetting sPacketRespawn;
    final BooleanSetting sPacketScoreboardObjective;
    final BooleanSetting sPacketSelectAdvancementsTab;
    final BooleanSetting sPacketServerDifficulty;
    final BooleanSetting sPacketSetExperience;
    final BooleanSetting sPacketSetPassengers;
    final BooleanSetting sPacketSetSlot;
    final BooleanSetting sPacketSignEditorOpen;
    final BooleanSetting sPacketSoundEffect;
    final BooleanSetting sPacketSpawnExperienceOrb;
    final BooleanSetting sPacketSpawnGlobalEntity;
    final BooleanSetting sPacketSpawnMob;
    final BooleanSetting sPacketSpawnObject;
    final BooleanSetting sPacketSpawnPainting;
    final BooleanSetting sPacketSpawnPlayer;
    final BooleanSetting sPacketSpawnPosition;
    final BooleanSetting sPacketStatistics;
    final BooleanSetting sPacketTabComplete;
    final BooleanSetting sPacketTeams;
    final BooleanSetting sPacketTimeUpdate;
    final BooleanSetting sPacketTitle;
    final BooleanSetting sPacketUnloadChunk;
    final BooleanSetting sPacketUpdateBossInfo;
    final BooleanSetting sPacketUpdateHealth;
    final BooleanSetting sPacketUpdateScore;
    final BooleanSetting sPacketUpdateTileEntity;
    final BooleanSetting sPacketUseBed;
    final BooleanSetting sPacketWindowItems;
    final BooleanSetting sPacketWindowProperty;
    final BooleanSetting sPacketWorldBorder;
    @EventHandler
    private final Listener<HtlrEventPacket.SendPacket> PacketSendEvent;
    @EventHandler
    private final Listener<HtlrEventPacket.ReceivePacket> PacketRecieveEvent;
    
    public PacketCancel() {
        super("PacketCancel", "Cancels Packets", Category.UTILITIES);
        this.cPacketAnimation = new BooleanSetting("CPacketAnimation", this, false);
        this.cPacketChatMessage = new BooleanSetting("CPacketChatMessage", this, false);
        this.cPacketClickWindow = new BooleanSetting("CPacketClickWindow", this, false);
        this.cPacketClientSettings = new BooleanSetting("CPacketClientSettings", this, false);
        this.cPacketClientStatus = new BooleanSetting("CPacketClientStatus", this, false);
        this.cPacketCloseWindow = new BooleanSetting("CPacketCloseWindow", this, false);
        this.cPacketConfirmTeleport = new BooleanSetting("CPacketConfirmTeleport", this, false);
        this.cPacketConfirmTransaction = new BooleanSetting("CPacketConfirmTransaction", this, false);
        this.cPacketCreativeInventoryAction = new BooleanSetting("CPacketCreativeInventoryAction", this, false);
        this.cPacketCustomPayload = new BooleanSetting("CPacketCustomPayload", this, false);
        this.cPacketEnchantItem = new BooleanSetting("CPacketEnchantItem", this, false);
        this.cPacketEntityAction = new BooleanSetting("CPacketEntityAction", this, false);
        this.cPacketHeldItemChange = new BooleanSetting("CPacketHeldItemChange", this, false);
        this.cPacketInput = new BooleanSetting("CPacketInput", this, false);
        this.cPacketKeepAlive = new BooleanSetting("CPacketKeepAlive", this, false);
        this.cPacketPlaceRecipe = new BooleanSetting("CPacketPlaceRecipe", this, false);
        this.cPacketPlayer = new BooleanSetting("CPacketPlayer", this, false);
        this.cPacketPlayerPosition = new BooleanSetting("CPacketPlayer.Position", this, false);
        this.cPacketPlayerPositionRotation = new BooleanSetting("CPacketPlayer.PositionRotation", this, false);
        this.cPacketPlayerRotation = new BooleanSetting("CPacketPlayer.Rotation", this, false);
        this.cPacketPlayerAbilities = new BooleanSetting("CPacketPlayerAbilities", this, false);
        this.cPacketPlayerDigging = new BooleanSetting("CPacketPlayerDigging", this, false);
        this.cPacketPlayerTryUseItem = new BooleanSetting("CPacketPlayerTryUseItem", this, false);
        this.cPacketPlayerTryUseItemOnBlock = new BooleanSetting("CPacketPlayerTryUseItemOnBlock", this, false);
        this.cPacketRecipeInfo = new BooleanSetting("CPacketRecipeInfo", this, false);
        this.cPacketResourcePackStatus = new BooleanSetting("CPacketResourcePackStatus", this, false);
        this.cPacketSeenAdvancements = new BooleanSetting("CPacketSeenAdvancements", this, false);
        this.cPacketSpectate = new BooleanSetting("CPacketSpectate", this, false);
        this.cPacketSteerBoat = new BooleanSetting("CPacketSteerBoat", this, false);
        this.cPacketTabComplete = new BooleanSetting("CPacketTabComplete", this, false);
        this.cPacketUpdateSign = new BooleanSetting("CPacketUpdateSign", this, false);
        this.cPacketUseEntity = new BooleanSetting("CPacketUseEntity", this, false);
        this.cPacketVehicleMove = new BooleanSetting("CPacketVehicleMove", this, false);
        this.sPacketAdvancementInfo = new BooleanSetting("SPacketAdvancementInfo", this, false);
        this.sPacketAnimation = new BooleanSetting("SPacketAnimation", this, false);
        this.sPacketBlockAction = new BooleanSetting("SPacketBlockAction", this, false);
        this.sPacketBlockBreakAnim = new BooleanSetting("SPacketBlockBreakAnim", this, false);
        this.sPacketBlockChange = new BooleanSetting("SPacketBlockChange", this, false);
        this.sPacketCamera = new BooleanSetting("SPacketCamera", this, false);
        this.sPacketChangeGameState = new BooleanSetting("SPacketChangeGameState", this, false);
        this.sPacketChat = new BooleanSetting("SPacketChat", this, false);
        this.sPacketChunkData = new BooleanSetting("SPacketChunkData", this, false);
        this.sPacketCloseWindow = new BooleanSetting("SPacketCloseWindow", this, false);
        this.sPacketCollectItem = new BooleanSetting("SPacketCollectItem", this, false);
        this.sPacketCombatEvent = new BooleanSetting("SPacketCombatEvent", this, false);
        this.sPacketConfirmTransaction = new BooleanSetting("SPacketConfirmTransaction", this, false);
        this.sPacketCooldown = new BooleanSetting("SPacketCooldown", this, false);
        this.sPacketCustomPayload = new BooleanSetting("SPacketCustomPayload", this, false);
        this.sPacketCustomSound = new BooleanSetting("SPacketCustomSound", this, false);
        this.sPacketDestroyEntities = new BooleanSetting("SPacketDestroyEntities", this, false);
        this.sPacketDisconnect = new BooleanSetting("SPacketDisconnect", this, false);
        this.sPacketDisplayObjective = new BooleanSetting("SPacketDisplayObjective", this, false);
        this.sPacketEffect = new BooleanSetting("SPacketEffect", this, false);
        this.sPacketEntity = new BooleanSetting("SPacketEntity", this, false);
        this.sPacketEntityRelMove = new BooleanSetting("SPacketEntityRelMove", this, false);
        this.sPacketEntityLook = new BooleanSetting("SPacketEntityLook", this, false);
        this.sPacketEntityLookMove = new BooleanSetting("SPacketEntityLookMove", this, false);
        this.sPacketEntityAttach = new BooleanSetting("SPacketEntityAttach", this, false);
        this.sPacketEntityEffect = new BooleanSetting("SPacketEntityEffect", this, false);
        this.sPacketEntityEquipment = new BooleanSetting("SPacketEntityEquipment", this, false);
        this.sPacketEntityHeadLook = new BooleanSetting("SPacketEntityHeadLook", this, false);
        this.sPacketEntityMetadata = new BooleanSetting("SPacketEntityMetadata", this, false);
        this.sPacketEntityProperties = new BooleanSetting("SPacketEntityProperties", this, false);
        this.sPacketEntityStatus = new BooleanSetting("SPacketEntityStatus", this, false);
        this.sPacketEntityTeleport = new BooleanSetting("SPacketEntityTeleport", this, false);
        this.sPacketEntityVelocity = new BooleanSetting("SPacketEntityVelocity", this, false);
        this.sPacketExplosion = new BooleanSetting("SPacketExplosion", this, false);
        this.sPacketHeldItemChange = new BooleanSetting("SPacketHeldItemChange", this, false);
        this.sPacketJoinGame = new BooleanSetting("SPacketJoinGame", this, false);
        this.sPacketKeepAlive = new BooleanSetting("SPacketKeepAlive", this, false);
        this.sPacketMaps = new BooleanSetting("SPacketMaps", this, false);
        this.sPacketMoveVehicle = new BooleanSetting("SPacketMoveVehicle", this, false);
        this.sPacketMultiBlockChange = new BooleanSetting("SPacketMultiBlockChange", this, false);
        this.sPacketOpenWindow = new BooleanSetting("SPacketOpenWindow", this, false);
        this.sPacketParticles = new BooleanSetting("SPacketParticles", this, false);
        this.sPacketPlaceGhostRecipe = new BooleanSetting("SPacketPlaceGhostRecipe", this, false);
        this.sPacketPlayerAbilities = new BooleanSetting("SPacketPlayerAbilities", this, false);
        this.sPacketPlayerListHeaderFooter = new BooleanSetting("SPacketPlayerListHeaderFooter", this, false);
        this.sPacketPlayerListItem = new BooleanSetting("SPacketPlayerListItem", this, false);
        this.sPacketPlayerPosLook = new BooleanSetting("SPacketPlayerPosLook", this, false);
        this.sPacketRecipeBook = new BooleanSetting("SPacketRecipeBook", this, false);
        this.sPacketRemoveEntityEffect = new BooleanSetting("SPacketRemoveEntityEffect", this, false);
        this.sPacketResourcePackSend = new BooleanSetting("SPacketResourcePackSend", this, false);
        this.sPacketRespawn = new BooleanSetting("SPacketRespawn", this, false);
        this.sPacketScoreboardObjective = new BooleanSetting("SPacketScoreboardObjective", this, false);
        this.sPacketSelectAdvancementsTab = new BooleanSetting("SPacketSelectAdvancementsTab", this, false);
        this.sPacketServerDifficulty = new BooleanSetting("SPacketServerDifficulty", this, false);
        this.sPacketSetExperience = new BooleanSetting("SPacketSetExperience", this, false);
        this.sPacketSetPassengers = new BooleanSetting("SPacketSetPassengers", this, false);
        this.sPacketSetSlot = new BooleanSetting("SPacketSetSlot", this, false);
        this.sPacketSignEditorOpen = new BooleanSetting("SPacketSignEditorOpen", this, false);
        this.sPacketSoundEffect = new BooleanSetting("SPacketSoundEffect", this, false);
        this.sPacketSpawnExperienceOrb = new BooleanSetting("SPacketSpawnExperienceOrb", this, false);
        this.sPacketSpawnGlobalEntity = new BooleanSetting("SPacketSpawnGlobalEntity", this, false);
        this.sPacketSpawnMob = new BooleanSetting("SPacketSpawnMob", this, false);
        this.sPacketSpawnObject = new BooleanSetting("SPacketSpawnObject", this, false);
        this.sPacketSpawnPainting = new BooleanSetting("SPacketSpawnPainting", this, false);
        this.sPacketSpawnPlayer = new BooleanSetting("SPacketSpawnPlayer", this, false);
        this.sPacketSpawnPosition = new BooleanSetting("SPacketSpawnPosition", this, false);
        this.sPacketStatistics = new BooleanSetting("SPacketStatistics", this, false);
        this.sPacketTabComplete = new BooleanSetting("SPacketTabComplete", this, false);
        this.sPacketTeams = new BooleanSetting("SPacketTeams", this, false);
        this.sPacketTimeUpdate = new BooleanSetting("SPacketTimeUpdate", this, false);
        this.sPacketTitle = new BooleanSetting("SPacketTitle", this, false);
        this.sPacketUnloadChunk = new BooleanSetting("SPacketUnloadChunk", this, false);
        this.sPacketUpdateBossInfo = new BooleanSetting("SPacketUpdateBossInfo", this, false);
        this.sPacketUpdateHealth = new BooleanSetting("SPacketUpdateHealth", this, false);
        this.sPacketUpdateScore = new BooleanSetting("SPacketUpdateScore", this, false);
        this.sPacketUpdateTileEntity = new BooleanSetting("SPacketUpdateTileEntity", this, false);
        this.sPacketUseBed = new BooleanSetting("SPacketUseBed", this, false);
        this.sPacketWindowItems = new BooleanSetting("SPacketWindowItems", this, false);
        this.sPacketWindowProperty = new BooleanSetting("SPacketWindowProperty", this, false);
        this.sPacketWorldBorder = new BooleanSetting("SPacketWorldBorder", this, false);
        this.PacketSendEvent = new Listener<HtlrEventPacket.SendPacket>(event -> {
            if (event.get_packet() instanceof CPacketAnimation) {
                if (this.cPacketAnimation.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketChatMessage) {
                if (this.cPacketChatMessage.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketClickWindow) {
                if (this.cPacketClickWindow.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketClientSettings) {
                if (this.cPacketClientSettings.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketClientStatus) {
                if (this.cPacketClientStatus.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketCloseWindow) {
                if (this.cPacketCloseWindow.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketConfirmTeleport) {
                if (this.cPacketConfirmTeleport.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketConfirmTransaction) {
                if (this.cPacketConfirmTransaction.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketCreativeInventoryAction) {
                if (this.cPacketCreativeInventoryAction.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketCustomPayload) {
                if (this.cPacketCustomPayload.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketEnchantItem) {
                if (this.cPacketEnchantItem.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketEntityAction) {
                if (this.cPacketEntityAction.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketHeldItemChange) {
                if (this.cPacketHeldItemChange.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketInput) {
                if (this.cPacketInput.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketKeepAlive) {
                if (this.cPacketKeepAlive.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketPlaceRecipe) {
                if (this.cPacketPlaceRecipe.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketPlayer) {
                if (this.cPacketPlayer.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketPlayer.Rotation) {
                if (this.cPacketPlayerRotation.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketPlayer.Position) {
                if (this.cPacketPlayerPosition.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketPlayer.PositionRotation) {
                if (this.cPacketPlayerPositionRotation.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketPlayerAbilities) {
                if (this.cPacketPlayerAbilities.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketPlayerDigging) {
                if (this.cPacketPlayerDigging.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketPlayerTryUseItem) {
                if (this.cPacketPlayerTryUseItem.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketRecipeInfo) {
                if (this.cPacketRecipeInfo.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketResourcePackStatus) {
                if (this.cPacketResourcePackStatus.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketSeenAdvancements) {
                if (this.cPacketSeenAdvancements.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketSpectate) {
                if (this.cPacketSpectate.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketSteerBoat) {
                if (this.cPacketSteerBoat.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketTabComplete) {
                if (this.cPacketTabComplete.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketUpdateSign) {
                if (this.cPacketUpdateSign.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketUseEntity) {
                if (this.cPacketUseEntity.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof CPacketVehicleMove && this.cPacketVehicleMove.enabled) {
                event.cancel();
            }
            return;
        }, (Predicate<HtlrEventPacket.SendPacket>[])new Predicate[0]);
        this.PacketRecieveEvent = new Listener<HtlrEventPacket.ReceivePacket>(event -> {
            if (event.get_packet() instanceof SPacketAdvancementInfo) {
                if (this.sPacketAdvancementInfo.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketAnimation) {
                if (this.sPacketAnimation.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketBlockAction) {
                if (this.sPacketBlockAction.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketBlockBreakAnim) {
                if (this.sPacketBlockBreakAnim.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketBlockChange) {
                if (this.sPacketBlockChange.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketCamera) {
                if (this.sPacketCamera.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketChangeGameState) {
                if (this.sPacketChangeGameState.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketChat) {
                if (this.sPacketChat.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketChunkData) {
                if (this.sPacketChunkData.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketCloseWindow) {
                if (this.sPacketCloseWindow.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketCollectItem) {
                if (this.sPacketCollectItem.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketCombatEvent) {
                if (this.sPacketCombatEvent.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketConfirmTransaction) {
                if (this.sPacketConfirmTransaction.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketCooldown) {
                if (this.sPacketCooldown.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketCustomPayload) {
                if (this.sPacketCustomPayload.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketCustomSound) {
                if (this.sPacketCustomSound.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketDestroyEntities) {
                if (this.sPacketDestroyEntities.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketDisconnect) {
                if (this.sPacketDisconnect.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketDisplayObjective) {
                if (this.sPacketDisplayObjective.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketEffect) {
                if (this.sPacketEffect.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketEntity) {
                if (this.sPacketEntity.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketEntity.S15PacketEntityRelMove) {
                if (this.sPacketEntityRelMove.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketEntity.S16PacketEntityLook) {
                if (this.sPacketEntityLook.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketEntity.S17PacketEntityLookMove) {
                if (this.sPacketEntityLookMove.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketEntityAttach) {
                if (this.sPacketEntityAttach.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketEntityEffect) {
                if (this.sPacketEntityEffect.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketEntityEquipment) {
                if (this.sPacketEntityEquipment.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketEntityHeadLook) {
                if (this.sPacketEntityHeadLook.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketEntityMetadata) {
                if (this.sPacketEntityMetadata.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketEntityProperties) {
                if (this.sPacketEntityProperties.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketEntityStatus) {
                if (this.sPacketEntityStatus.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketEntityTeleport) {
                if (this.sPacketEntityTeleport.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketEntityVelocity) {
                if (this.sPacketEntityVelocity.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketExplosion) {
                if (this.sPacketExplosion.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketHeldItemChange) {
                if (this.sPacketHeldItemChange.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketJoinGame) {
                if (this.sPacketJoinGame.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketKeepAlive) {
                if (this.sPacketKeepAlive.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketMaps) {
                if (this.sPacketMaps.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketMoveVehicle) {
                if (this.sPacketMoveVehicle.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketMultiBlockChange) {
                if (this.sPacketMultiBlockChange.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketOpenWindow) {
                if (this.sPacketOpenWindow.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketParticles) {
                if (this.sPacketParticles.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketPlaceGhostRecipe) {
                if (this.sPacketPlaceGhostRecipe.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketPlayerAbilities) {
                if (this.sPacketPlayerAbilities.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketPlayerListHeaderFooter) {
                if (this.sPacketPlayerListHeaderFooter.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketPlayerListItem) {
                if (this.sPacketPlayerListItem.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketPlayerPosLook) {
                if (this.sPacketPlayerPosLook.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketRecipeBook) {
                if (this.sPacketRecipeBook.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketRemoveEntityEffect) {
                if (this.sPacketRemoveEntityEffect.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketResourcePackSend) {
                if (this.sPacketResourcePackSend.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketRespawn) {
                if (this.sPacketRespawn.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketScoreboardObjective) {
                if (this.sPacketScoreboardObjective.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketSelectAdvancementsTab) {
                if (this.sPacketSelectAdvancementsTab.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketServerDifficulty) {
                if (this.sPacketServerDifficulty.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketSetExperience) {
                if (this.sPacketSetExperience.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketSetPassengers) {
                if (this.sPacketSetPassengers.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketSetSlot) {
                if (this.sPacketSetSlot.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketSignEditorOpen) {
                if (this.sPacketSignEditorOpen.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketSoundEffect) {
                if (this.sPacketSoundEffect.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketSpawnExperienceOrb) {
                if (this.sPacketSpawnExperienceOrb.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketSpawnGlobalEntity) {
                if (this.sPacketSpawnGlobalEntity.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketSpawnMob) {
                if (this.sPacketSpawnMob.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketSpawnObject) {
                if (this.sPacketSpawnObject.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketSpawnPainting) {
                if (this.sPacketSpawnPainting.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketSpawnPlayer) {
                if (this.sPacketSpawnPlayer.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketSpawnPosition) {
                if (this.sPacketSpawnPosition.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketStatistics) {
                if (this.sPacketStatistics.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketTabComplete) {
                if (this.sPacketTabComplete.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketTeams) {
                if (this.sPacketTeams.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketTimeUpdate) {
                if (this.sPacketTimeUpdate.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketTitle) {
                if (this.sPacketTitle.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketUnloadChunk) {
                if (this.sPacketUnloadChunk.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketUpdateBossInfo) {
                if (this.sPacketUpdateBossInfo.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketUpdateHealth) {
                if (this.sPacketUpdateHealth.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketUpdateScore) {
                if (this.sPacketUpdateScore.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketUpdateTileEntity) {
                if (this.sPacketUpdateTileEntity.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketUseBed) {
                if (this.sPacketUseBed.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketWindowItems) {
                if (this.sPacketWindowItems.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketWindowProperty) {
                if (this.sPacketWindowProperty.enabled) {
                    event.cancel();
                }
            }
            else if (event.get_packet() instanceof SPacketWorldBorder && this.sPacketWorldBorder.enabled) {
                event.cancel();
            }
            return;
        }, (Predicate<HtlrEventPacket.ReceivePacket>[])new Predicate[0]);
        this.addSetting(this.cPacketAnimation);
        this.addSetting(this.cPacketChatMessage);
        this.addSetting(this.cPacketClickWindow);
        this.addSetting(this.cPacketClientSettings);
        this.addSetting(this.cPacketClientStatus);
        this.addSetting(this.cPacketCloseWindow);
        this.addSetting(this.cPacketConfirmTeleport);
        this.addSetting(this.cPacketConfirmTransaction);
        this.addSetting(this.cPacketCreativeInventoryAction);
        this.addSetting(this.cPacketCustomPayload);
        this.addSetting(this.cPacketEnchantItem);
        this.addSetting(this.cPacketEntityAction);
        this.addSetting(this.cPacketHeldItemChange);
        this.addSetting(this.cPacketInput);
        this.addSetting(this.cPacketKeepAlive);
        this.addSetting(this.cPacketPlaceRecipe);
        this.addSetting(this.cPacketPlayer);
        this.addSetting(this.cPacketPlayerPosition);
        this.addSetting(this.cPacketPlayerPositionRotation);
        this.addSetting(this.cPacketPlayerRotation);
        this.addSetting(this.cPacketPlayerAbilities);
        this.addSetting(this.cPacketPlayerDigging);
        this.addSetting(this.cPacketPlayerTryUseItem);
        this.addSetting(this.cPacketPlayerTryUseItemOnBlock);
        this.addSetting(this.cPacketRecipeInfo);
        this.addSetting(this.cPacketResourcePackStatus);
        this.addSetting(this.cPacketSeenAdvancements);
        this.addSetting(this.cPacketSpectate);
        this.addSetting(this.cPacketSteerBoat);
        this.addSetting(this.cPacketTabComplete);
        this.addSetting(this.cPacketUpdateSign);
        this.addSetting(this.cPacketUseEntity);
        this.addSetting(this.cPacketVehicleMove);
        this.addSetting(this.sPacketAdvancementInfo);
        this.addSetting(this.sPacketAnimation);
        this.addSetting(this.sPacketBlockAction);
        this.addSetting(this.sPacketBlockBreakAnim);
        this.addSetting(this.sPacketBlockChange);
        this.addSetting(this.sPacketCamera);
        this.addSetting(this.sPacketChangeGameState);
        this.addSetting(this.sPacketChat);
        this.addSetting(this.sPacketChunkData);
        this.addSetting(this.sPacketCloseWindow);
        this.addSetting(this.sPacketCollectItem);
        this.addSetting(this.sPacketCombatEvent);
        this.addSetting(this.sPacketConfirmTransaction);
        this.addSetting(this.sPacketCooldown);
        this.addSetting(this.sPacketCustomPayload);
        this.addSetting(this.sPacketCustomSound);
        this.addSetting(this.sPacketDestroyEntities);
        this.addSetting(this.sPacketDisconnect);
        this.addSetting(this.sPacketDisplayObjective);
        this.addSetting(this.sPacketEffect);
        this.addSetting(this.sPacketEntity);
        this.addSetting(this.sPacketEntityRelMove);
        this.addSetting(this.sPacketEntityLook);
        this.addSetting(this.sPacketEntityLookMove);
        this.addSetting(this.sPacketEntityAttach);
        this.addSetting(this.sPacketEntityEffect);
        this.addSetting(this.sPacketEntityEquipment);
        this.addSetting(this.sPacketEntityHeadLook);
        this.addSetting(this.sPacketEntityMetadata);
        this.addSetting(this.sPacketEntityProperties);
        this.addSetting(this.sPacketEntityStatus);
        this.addSetting(this.sPacketEntityTeleport);
        this.addSetting(this.sPacketEntityVelocity);
        this.addSetting(this.sPacketExplosion);
        this.addSetting(this.sPacketHeldItemChange);
        this.addSetting(this.sPacketJoinGame);
        this.addSetting(this.sPacketKeepAlive);
        this.addSetting(this.sPacketMaps);
        this.addSetting(this.sPacketMoveVehicle);
        this.addSetting(this.sPacketMultiBlockChange);
        this.addSetting(this.sPacketOpenWindow);
        this.addSetting(this.sPacketParticles);
        this.addSetting(this.sPacketPlaceGhostRecipe);
        this.addSetting(this.sPacketPlayerAbilities);
        this.addSetting(this.sPacketPlayerListHeaderFooter);
        this.addSetting(this.sPacketPlayerListItem);
        this.addSetting(this.sPacketPlayerPosLook);
        this.addSetting(this.sPacketRecipeBook);
        this.addSetting(this.sPacketRemoveEntityEffect);
        this.addSetting(this.sPacketResourcePackSend);
        this.addSetting(this.sPacketRespawn);
        this.addSetting(this.sPacketScoreboardObjective);
        this.addSetting(this.sPacketSelectAdvancementsTab);
        this.addSetting(this.sPacketServerDifficulty);
        this.addSetting(this.sPacketSetExperience);
        this.addSetting(this.sPacketSetPassengers);
        this.addSetting(this.sPacketSetSlot);
        this.addSetting(this.sPacketSignEditorOpen);
        this.addSetting(this.sPacketSoundEffect);
        this.addSetting(this.sPacketSpawnExperienceOrb);
        this.addSetting(this.sPacketSpawnGlobalEntity);
        this.addSetting(this.sPacketSpawnMob);
        this.addSetting(this.sPacketSpawnObject);
        this.addSetting(this.sPacketSpawnPainting);
        this.addSetting(this.sPacketSpawnPlayer);
        this.addSetting(this.sPacketSpawnPosition);
        this.addSetting(this.sPacketStatistics);
        this.addSetting(this.sPacketTabComplete);
        this.addSetting(this.sPacketTabComplete);
        this.addSetting(this.sPacketTeams);
        this.addSetting(this.sPacketTimeUpdate);
        this.addSetting(this.sPacketTitle);
        this.addSetting(this.sPacketUnloadChunk);
        this.addSetting(this.sPacketUpdateBossInfo);
        this.addSetting(this.sPacketUpdateHealth);
        this.addSetting(this.sPacketUpdateScore);
        this.addSetting(this.sPacketUpdateTileEntity);
    }
}
