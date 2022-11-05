package rc14.hacks.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rc14.hacks.events.network.SendPacketCallback;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
	 @Inject(method = "send(Lnet/minecraft/network/Packet;Lnet/minecraft/network/PacketCallbacks;)V", at = @At("HEAD"), cancellable = true)
	private void invokePacketSendEvent(Packet<?> packet, @Nullable PacketCallbacks callbacks, CallbackInfo ci) {
		 ActionResult result = SendPacketCallback.EVENT.invoker().interact(packet);

		 if (result == ActionResult.FAIL) ci.cancel();
	 }
}
