package rc14.hacks.events.game;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

public interface TickCallback {
	/**
	 * <p>Called at the end of a tick.</p>
	 * <p></p>
	 * <p>Processing continues regardless of what is returned.</p>
	 */
	Event<TickCallback> EVENT = EventFactory.createArrayBacked(TickCallback.class,
			(listeners) -> () -> {
				for (TickCallback listener : listeners) listener.interact();

				return ActionResult.PASS;
			});

	ActionResult interact();
}
