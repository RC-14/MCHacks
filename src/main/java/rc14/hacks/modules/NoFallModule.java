package rc14.hacks.modules;

import net.minecraft.util.ActionResult;
import rc14.hacks.events.network.SendPlayerMoveC2SPacketCallback;
import rc14.hacks.mixin.PlayerMoveC2SPacketAccessor;

public class NoFallModule extends Module {
	public NoFallModule(boolean enabled) {
		super(enabled);

		SendPlayerMoveC2SPacketCallback.EVENT.register((packet) -> {
			if (client.player == null) return ActionResult.PASS;

			if (this.isEnabled() && client.player.fallDistance > 2.5 && !client.player.isFallFlying() && !client.player.getAbilities().flying) ((PlayerMoveC2SPacketAccessor) packet).setOnGround(true);

			return ActionResult.PASS;
		});
	}
}
