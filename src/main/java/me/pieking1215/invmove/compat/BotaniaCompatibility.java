package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.GuiScreen;
import vazkii.botania.client.gui.bag.GuiFlowerBag;
import vazkii.botania.client.gui.box.GuiBaubleBox;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class BotaniaCompatibility extends ModCompatibility {

    AtomicBoolean FlowerBag_movement  = new AtomicBoolean(true);
    AtomicBoolean BaubleBox_movement  = new AtomicBoolean(true);

    AtomicBoolean FlowerBag_background_disable  = new AtomicBoolean(true);
    AtomicBoolean BaubleBox_background_disable  = new AtomicBoolean(true);

    public BotaniaCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Flower Pouch", "FlowerBag_movement", FlowerBag_movement, true),
                new BoolOption("Trinket Case", "BaubleBox_movement", BaubleBox_movement, true)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Flower Pouch", "FlowerBag_background_disable", FlowerBag_background_disable, true),
                new BoolOption("Trinket Case", "BaubleBox_background_disable", BaubleBox_background_disable, true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(GuiScreen screen) {

        if(screen instanceof GuiFlowerBag) return Optional.of(FlowerBag_movement.get());
        if(screen instanceof GuiBaubleBox) return Optional.of(BaubleBox_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(GuiScreen screen) {

        if(screen instanceof GuiFlowerBag) return Optional.of(FlowerBag_background_disable.get());
        if(screen instanceof GuiBaubleBox) return Optional.of(BaubleBox_background_disable.get());

        return Optional.empty();
    }
}
