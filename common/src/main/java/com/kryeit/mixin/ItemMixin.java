package com.kryeit.mixin;


import com.kryeit.Utils;
import com.mojang.blaze3d.platform.ScreenManager;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.ShulkerBoxScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(
            method = "use",
            at = @At("HEAD")
    )
    public void use(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack item = player.getItemInHand(interactionHand);
        if(!item.getItem().toString().contains("shulker_box")) return;
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.invento
        }

        player.displayClientMessage(Component.nullToEmpty(Utils.parseItemStackToContainer(item) + "tu mama"), false);

        player.openMenu()
        player.openMenu((MenuProvider) ChestMenu.threeRows(0, player.getInventory(), Utils.parseItemStackToContainer(item)));



    }


}
