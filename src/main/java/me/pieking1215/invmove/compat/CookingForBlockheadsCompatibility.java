package me.pieking1215.invmove.compat;

import net.blay09.mods.cookingforblockheads.client.gui.GuiCounter;
import net.blay09.mods.cookingforblockheads.client.gui.GuiFridge;
import net.blay09.mods.cookingforblockheads.client.gui.GuiFruitBasket;
import net.blay09.mods.cookingforblockheads.client.gui.GuiOven;
import net.blay09.mods.cookingforblockheads.client.gui.GuiRecipeBook;
import net.blay09.mods.cookingforblockheads.client.gui.GuiSpiceRack;
import net.minecraft.client.gui.GuiScreen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class CookingForBlockheadsCompatibility extends ModCompatibility {

    AtomicBoolean Counter_movement  = new AtomicBoolean(true);
    AtomicBoolean Fridge_movement  = new AtomicBoolean(true);
    AtomicBoolean FruitBasket_movement  = new AtomicBoolean(true);
    AtomicBoolean Oven_movement  = new AtomicBoolean(true);
    AtomicBoolean RecipeBook_movement  = new AtomicBoolean(true);
    AtomicBoolean SpiceRack_movement  = new AtomicBoolean(true);

    AtomicBoolean Counter_background_disable  = new AtomicBoolean(true);
    AtomicBoolean Fridge_background_disable  = new AtomicBoolean(true);
    AtomicBoolean FruitBasket_background_disable  = new AtomicBoolean(true);
    AtomicBoolean Oven_background_disable  = new AtomicBoolean(true);
    AtomicBoolean RecipeBook_background_disable  = new AtomicBoolean(true);
    AtomicBoolean SpiceRack_background_disable  = new AtomicBoolean(true);

    public CookingForBlockheadsCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Counter", "Counter_movement", Counter_movement, true),
                new BoolOption("Fridge", "Fridge_movement", Fridge_movement, true),
                new BoolOption("Fruit Basket", "FruitBasket_movement", FruitBasket_movement, true),
                new BoolOption("Oven", "Oven_movement", Oven_movement, true),
                new BoolOption("Recipe Books", "RecipeBook_movement", RecipeBook_movement, true),
                new BoolOption("Spice Rack", "SpiceRack_movement", SpiceRack_movement, true)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Counter", "Counter_background_disable", Counter_background_disable, true),
                new BoolOption("Fridge", "Fridge_background_disable", Fridge_background_disable, true),
                new BoolOption("Fruit Basket", "FruitBasket_background_disable", FruitBasket_background_disable, true),
                new BoolOption("Oven", "Oven_background_disable", Oven_background_disable, true),
                new BoolOption("Recipe Books", "RecipeBook_background_disable", RecipeBook_background_disable, true),
                new BoolOption("Spice Rack", "SpiceRack_background_disable", SpiceRack_background_disable, true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(GuiScreen screen) {

        if(screen instanceof GuiCounter)     return Optional.of(Counter_movement.get());
        if(screen instanceof GuiFridge)      return Optional.of(Fridge_movement.get());
        if(screen instanceof GuiFruitBasket) return Optional.of(FruitBasket_movement.get());
        if(screen instanceof GuiOven)        return Optional.of(Oven_movement.get());
        if(screen instanceof GuiRecipeBook)  return Optional.of(RecipeBook_movement.get());
        if(screen instanceof GuiSpiceRack)   return Optional.of(SpiceRack_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(GuiScreen screen) {

        if(screen instanceof GuiCounter)     return Optional.of(Counter_background_disable.get());
        if(screen instanceof GuiFridge)      return Optional.of(Fridge_background_disable.get());
        if(screen instanceof GuiFruitBasket) return Optional.of(FruitBasket_background_disable.get());
        if(screen instanceof GuiOven)        return Optional.of(Oven_background_disable.get());
        if(screen instanceof GuiRecipeBook)  return Optional.of(RecipeBook_background_disable.get());
        if(screen instanceof GuiSpiceRack)   return Optional.of(SpiceRack_background_disable.get());

        return Optional.empty();
    }

}
