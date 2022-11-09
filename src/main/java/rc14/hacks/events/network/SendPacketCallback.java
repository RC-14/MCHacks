package rc14.hacks.events.network;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.ActionResult;

public interface SendPacketCallback {
	/**
	 * <p>Called before a packet gets send and before the packet specific event is invoked.</p>
	 * <p></p>
	 * <p>Upon return:</p>
	 * <ul>
	 *     <li>SUCCESS cancels further processing and sends the packet.</li>
	 *     <li>PASS falls back to further processing and defaults to SUCCESS if no other listeners are available.</li>
	 *     <li>FAIL cancels further processing and prevents sending the packet.</li>
	 * </ul>
	 */
	Event<SendPacketCallback> EVENT = EventFactory.createArrayBacked(SendPacketCallback.class,
			(listeners) -> (packet) -> {
				for (SendPacketCallback listener : listeners) {
					ActionResult result = listener.interact(packet);

					if (result != ActionResult.PASS) return result;
				}

				if (packet instanceof PlayerMoveC2SPacket) {
					return SendPlayerMoveC2SPacketCallback.EVENT.invoker().interact((PlayerMoveC2SPacket) packet);
				}

				return ActionResult.PASS;
			});

	ActionResult interact(Packet<?> packet);
}
