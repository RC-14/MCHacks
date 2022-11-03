package rc14.hacks.mixin;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerMoveC2SPacket.class)
public class LeetMovementsMixin {
	@ModifyVariable(method = "<init>(DDDFFZZZ)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
	private static double returnLeetX(double x) {
		double leetX = Math.abs(x);
		leetX -= leetX % 0.001;
		leetX += 0.0001337;
		if (x < 0) leetX = -leetX;

		return leetX;
	}

	@ModifyVariable(method = "<init>(DDDFFZZZ)V", at = @At("HEAD"), ordinal = 2, argsOnly = true)
	private static double returnLeetZ(double z) {
		double leetZ = Math.abs(z);
		leetZ -= leetZ % 0.001;
		leetZ += 0.0001337;
		if (z < 0) leetZ = -leetZ;

		return leetZ;
	}
}
