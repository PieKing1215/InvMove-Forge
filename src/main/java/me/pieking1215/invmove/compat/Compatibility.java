package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.fml.ModList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Compatibility {

    private static List<IModCompatibility> compats;

    public static void loadCompatibility(){
        compats = new ArrayList<>();

        if(ModList.get().isLoaded("jei")){
            try {
                compats.add(Class.forName("me.pieking1215.invmove.compat.JEICompatibility").asSubclass(IModCompatibility.class).newInstance());
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static Optional<Boolean> shouldAllowMovement(Screen screen) {
        for(IModCompatibility c : compats){
            Optional<Boolean> ob = c.shouldAllowMovement(screen);
            if(ob.isPresent()){
                return Optional.of(ob.get());
            }
        }

        return Optional.empty();
    }

    public static Optional<Boolean> shouldDisableBackground(Screen screen) {
        for(IModCompatibility c : compats){
            Optional<Boolean> ob = c.shouldDisableBackground(screen);
            if(ob.isPresent()){
                return Optional.of(ob.get());
            }
        }

        return Optional.empty();
    }
}
