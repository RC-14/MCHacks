package rc14.hacks;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.slf4j.Logger;
import rc14.hacks.modules.Module;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Environment(EnvType.CLIENT)
public class Hacks implements ClientModInitializer {
	private static Hacks INSTANCE;
	private final Logger LOGGER = LogUtils.getLogger();

	private final List<Module> modules = new ArrayList<>();

	@Override
	public void onInitializeClient() {
		INSTANCE = this;
	}

	public List<Module> getEnabledModules() {
		return modules.stream().filter(Module::isEnabled).collect(Collectors.toList());
	}
}
