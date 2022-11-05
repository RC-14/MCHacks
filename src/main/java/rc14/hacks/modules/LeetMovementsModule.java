package rc14.hacks.modules;

import net.minecraft.util.ActionResult;
import rc14.hacks.events.network.SendPlayerMoveC2SPacketCallback;
import rc14.hacks.mixin.PlayerMoveC2SPacketAccessor;

public class LeetMovementsModule extends Module {
	public LeetMovementsModule() {
		super(true);

		SendPlayerMoveC2SPacketCallback.EVENT.register((packet) -> {
			if (!isEnabled() || !packet.changesPosition()) return ActionResult.PASS;

			((PlayerMoveC2SPacketAccessor) packet).setX(makeLeet(packet.getX(0)));
			((PlayerMoveC2SPacketAccessor) packet).setZ(makeLeet(packet.getZ(0)));

			return ActionResult.PASS;
		});
	}

	private double makeLeet(double n) {
		double leetN = Math.abs(n);

		leetN -= leetN % 0.001;
		leetN += 0.0001337;
		if (n < 0) leetN = -leetN;

		return leetN;
	}
}
