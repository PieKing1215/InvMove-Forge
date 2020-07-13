package me.pieking1215.invmove.compat;

import net.blay09.mods.cookingforblockheads.client.gui.screen.CounterScreen;
import net.blay09.mods.cookingforblockheads.client.gui.screen.FridgeScreen;
import net.blay09.mods.cookingforblockheads.client.gui.screen.FruitBasketScreen;
import net.blay09.mods.cookingforblockheads.client.gui.screen.OvenScreen;
import net.blay09.mods.cookingforblockheads.client.gui.screen.RecipeBookScreen;
import net.blay09.mods.cookingforblockheads.client.gui.screen.SpiceRackScreen;
import net.minecraft.client.gui.screen.Screen;

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
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if(screen instanceof CounterScreen)     return Optional.of(Counter_movement.get());
        if(screen instanceof FridgeScreen)      return Optional.of(Fridge_movement.get());
        if(screen instanceof FruitBasketScreen) return Optional.of(FruitBasket_movement.get());
        if(screen instanceof OvenScreen)        return Optional.of(Oven_movement.get());
        if(screen instanceof RecipeBookScreen)  return Optional.of(RecipeBook_movement.get());
        if(screen instanceof SpiceRackScreen)   return Optional.of(SpiceRack_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof CounterScreen)     return Optional.of(Counter_background_disable.get());
        if(screen instanceof FridgeScreen)      return Optional.of(Fridge_background_disable.get());
        if(screen instanceof FruitBasketScreen) return Optional.of(FruitBasket_background_disable.get());
        if(screen instanceof OvenScreen)        return Optional.of(Oven_background_disable.get());
        if(screen instanceof RecipeBookScreen)  return Optional.of(RecipeBook_background_disable.get());
        if(screen instanceof SpiceRackScreen)   return Optional.of(SpiceRack_background_disable.get());

        return Optional.empty();
    }

}
