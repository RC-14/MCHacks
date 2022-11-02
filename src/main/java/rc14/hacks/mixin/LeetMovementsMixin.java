package rc14.hacks.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerEntity.class)
public class LeetMovementsMixin {
	@Redirect(method = "sendMovementPackets()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getX()D"))
	private double returnLeetX(ClientPlayerEntity player) {
		double x = Math.abs(player.getX());
		x -= x % 0.001;
		x += 0.0001337;
		if (player.getX() < 0) x = -x;

		return x;
	}

	@Redirect(method = "sendMovementPackets()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getZ()D"))
	private double returnLeetZ(ClientPlayerEntity player) {
		double z = Math.abs(player.getZ());
		z -= z % 0.001;
		z += 0.0001337;
		if (player.getZ() < 0) z = -z;

		return z;
	}
}
