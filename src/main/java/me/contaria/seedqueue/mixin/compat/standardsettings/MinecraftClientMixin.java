package me.contaria.seedqueue.mixin.compat.standardsettings;

import com.bawnorton.mixinsquared.TargetHandler;
import me.contaria.seedqueue.SeedQueue;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Dynamic
    @TargetHandler(
            mixin = "me.contaria.standardsettings.mixin.MinecraftClientMixin",
            name = "reset"
    )
    @Inject(
            method = "@MixinSquared:Handler",
            at = @At("HEAD"),
            cancellable = true
    )
    private void doNotTriggerStandardSettings_reset_inQueue(CallbackInfo ci) {
        if (SeedQueue.inQueue()) {
            ci.cancel();
            return;
        }
        if (SeedQueue.currentEntry != null && SeedQueue.currentEntry.loadSettingsCache()) {
            ci.cancel();
        }
    }

    @Dynamic
    @TargetHandler(
            mixin = "me.contaria.standardsettings.mixin.MinecraftClientMixin",
            name = "onWorldJoin"
    )
    @Inject(
            method = "@MixinSquared:Handler",
            at = @At("HEAD"),
            cancellable = true
    )
    private void doNotTriggerStandardSettings_onWorldJoin_inQueue(CallbackInfo ci) {
        if (SeedQueue.inQueue()) {
            ci.cancel();
        }
    }

    @Dynamic
    @TargetHandler(
            mixin = "me.contaria.standardsettings.mixin.MinecraftClientMixin",
            name = "resetPendingActions"
    )
    @Inject(
            method = "@MixinSquared:Handler",
            at = @At("HEAD"),
            cancellable = true
    )
    private void doNotTriggerStandardSettings_resetPendingActions_inQueue(CallbackInfo ci) {
        if (SeedQueue.inQueue()) {
            ci.cancel();
        }
    }

    @Dynamic
    @TargetHandler(
            mixin = "me.contaria.standardsettings.mixin.MinecraftClientMixin",
            name = "setLastWorld"
    )
    @Inject(
            method = "@MixinSquared:Handler",
            at = @At("HEAD"),
            cancellable = true
    )
    private void doNotTriggerStandardSettings_setLastWorld_inQueue(CallbackInfo ci) {
        if (SeedQueue.inQueue()) {
            ci.cancel();
        }
    }
}
