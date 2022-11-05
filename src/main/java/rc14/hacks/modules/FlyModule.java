package rc14.hacks.modules;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3i;
import rc14.hacks.events.game.TickCallback;
import rc14.hacks.events.network.SendPlayerMoveC2SPacketCallback;

public class FlyModule extends Module {
	public FlyModule(boolean enabled) {
		super(enabled);

		SendPlayerMoveC2SPacketCallback.EVENT.register((packet) -> {
			if (!packet.changesPosition()) return ActionResult.PASS;

			lastPacketX = packet.getX(0);
			lastPacketY = packet.getY(0);
			lastPacketZ = packet.getZ(0);

			return ActionResult.PASS;
		});

		TickCallback.EVENT.register(() -> {
			if (client.player == null) return ActionResult.PASS;

			client.player.getAbilities().allowFlying = isEnabled();

			if (!isEnabled()) return ActionResult.PASS;

			if (client.player.getAbilities().flying && client.player.world.getBlockState(client.player.getBlockPos().subtract(new Vec3i(0, -1, 0))).isAir()) {
				if (floatingTicks++ > 19) {
					floatingTicks = 0;
					client.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(lastPacketX, lastPacketY - 0.05, lastPacketZ, false));
				}
			} else if (floatingTicks > 0) {
				floatingTicks = 0;
			}

			return ActionResult.PASS;
		});
	}

	int floatingTicks = 0;

	double lastPacketX = 0;
	double lastPacketY = 0;
	double lastPacketZ = 0;
}
