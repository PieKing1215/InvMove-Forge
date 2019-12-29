package me.pieking1215.invmove;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

@Mod("invmove")
public class InvMove
{
    private static final Logger LOGGER = LogManager.getLogger();

    public InvMove() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onInputUpdate(InputUpdateEvent event){
        if(Minecraft.getInstance().currentScreen instanceof InventoryScreen){
            // tick keybinds (since opening the ui unpresses all keys)
            KeyBinding.updateKeyBindState();

            // tick movement
            manualTickMovement(event.getMovementInput(), Minecraft.getInstance().player.shouldRenderSneaking() || Minecraft.getInstance().player.func_213300_bk(), Minecraft.getInstance().player.isSpectator());

            // set sprinting using raw keybind data
            Minecraft.getInstance().player.setSprinting(rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindSprint));
        }
    }

    /**
     * Clone of MovementInputFromOptions.tick but uses raw keybind data
     */
    public void manualTickMovement(MovementInput input, boolean slow, boolean noDampening) {
        input.forwardKeyDown = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindForward);
        input.backKeyDown = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindBack);
        input.leftKeyDown = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindLeft);
        input.rightKeyDown = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindRight);
        input.moveForward = input.forwardKeyDown == input.backKeyDown ? 0.0F : (float)(input.forwardKeyDown ? 1 : -1);
        input.moveStrafe = input.leftKeyDown == input.rightKeyDown ? 0.0F : (float)(input.leftKeyDown ? 1 : -1);
        input.jump = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindJump);
        input.sneak = rawIsKeyDown(Minecraft.getInstance().gameSettings.keyBindSneak);
        if (!noDampening && (input.sneak || slow)) {
            input.moveStrafe = (float)((double)input.moveStrafe * 0.3D);
            input.moveForward = (float)((double)input.moveForward * 0.3D);
        }
    }

    /**
     * Returns KeyBinding.pressed, which is normally a private field
     */
    public boolean rawIsKeyDown(KeyBinding key){
        try{
            Field f = KeyBinding.class.getDeclaredField("pressed");
            f.setAccessible(true);
            return f.getBoolean(key);
        }catch(Exception e){
            LOGGER.warn("Failed to access KeyBinding.pressed on \"" + key + "\": " + e.getMessage());
        }

        return false;
    }
}
