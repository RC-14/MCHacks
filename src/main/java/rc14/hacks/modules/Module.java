package rc14.hacks.modules;

import net.minecraft.client.MinecraftClient;

public abstract class Module {
	protected final MinecraftClient client;

	private boolean enabled = false;

	public Module(boolean enabled) {
		this.client = MinecraftClient.getInstance();

		if (enabled) enable();
	}

	public void enable() {
		if (enabled) return;
		enabled = true;
	}

	public void disable() {
		if (!enabled) return;
		enabled = false;
	}

	public boolean isEnabled() {
		return enabled;
	}
}
