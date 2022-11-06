package rc14.hacks.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rc14.hacks.events.game.TickCallback;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	@Inject(method = "tick()V", at = @At("TAIL"), cancellable = true)
	private void invokeTickEvent(CallbackInfo ci) {
		ActionResult result = TickCallback.EVENT.invoker().interact();

		if (result == ActionResult.FAIL) ci.cancel();
	}
}
