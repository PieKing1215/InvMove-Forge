package me.pieking1215.invmove.compat;

import me.pieking1215.invmove.InvMove;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class FreeCamCompatibility extends ModCompatibility {

    AtomicBoolean SPECIAL_FreeCamera_fix = new AtomicBoolean(true);

    public FreeCamCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Turning Fix", "SPECIAL_FreeCamera_fix", SPECIAL_FreeCamera_fix,true)
        );
        backgroundOptions = Arrays.asList(
        );

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    Optional<Boolean> shouldAllowMovement(GuiScreen screen) {
        return Optional.empty();
    }

    @Override
    Optional<Boolean> shouldDisableBackground(GuiScreen screen) {
        return Optional.empty();
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onCameraSetup1(EntityViewRenderEvent.CameraSetup event) {
        if(SPECIAL_FreeCamera_fix.get() && InvMove.allowMovementInScreen(Minecraft.getMinecraft().currentScreen)) {
            Minecraft.getMinecraft().gameSettings.keyBindLeft.setKeyConflictContext(KeyConflictContext.UNIVERSAL);
            Minecraft.getMinecraft().gameSettings.keyBindRight.setKeyConflictContext(KeyConflictContext.UNIVERSAL);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onCameraSetup2(EntityViewRenderEvent.CameraSetup event) {
        if(SPECIAL_FreeCamera_fix.get() && InvMove.allowMovementInScreen(Minecraft.getMinecraft().currentScreen)) {
            Minecraft.getMinecraft().gameSettings.keyBindLeft.setKeyConflictContext(KeyConflictContext.IN_GAME);
            Minecraft.getMinecraft().gameSettings.keyBindRight.setKeyConflictContext(KeyConflictContext.IN_GAME);
        }
    }
}
