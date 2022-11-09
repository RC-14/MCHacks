package rc14.hacks.events.network;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.ActionResult;

public interface SendPlayerMoveC2SPacketCallback {
	/**
	 * <p>Called before a PlayerMoveC2SPacket gets send.</p>
	 * <p></p>
	 * <p>Upon return:</p>
	 * <ul>
	 *     <li>SUCCESS cancels further processing and sends the packet.</li>
	 *     <li>PASS falls back to further processing and defaults to SUCCESS if no other listeners are available.</li>
	 *     <li>FAIL cancels further processing and prevents sending the packet.</li>
	 * </ul>
	 */
	Event<SendPlayerMoveC2SPacketCallback> EVENT = EventFactory.createArrayBacked(SendPlayerMoveC2SPacketCallback.class,
			(listeners) -> (packet) -> {
				for (SendPlayerMoveC2SPacketCallback listener : listeners) {
					ActionResult result = listener.interact(packet);

					if (result != ActionResult.PASS) return result;
				}

				return ActionResult.PASS;
			});

	ActionResult interact(PlayerMoveC2SPacket packet);
}
