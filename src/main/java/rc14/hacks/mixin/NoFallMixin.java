package rc14.hacks.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class NoFallMixin {
	@Inject(method = "tickMovement()V", at = @At("HEAD"))
	private void sendNoFallPacket(CallbackInfo ci) {
		if (!((ClientPlayerEntity)(Object)this).isOnGround()) ((ClientPlayerEntity)(Object)this).networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
	}
}
