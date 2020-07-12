package me.pieking1215.invmove.compat;

import me.pieking1215.invmove.Config;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import vazkii.quark.client.module.ChestSearchingModule;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class QuarkCompatibility extends ModCompatibility {

    Field ChestSearchingModule_searchBar = null;

    AtomicBoolean SPECIAL_ChestSearchFocus_movement = new AtomicBoolean(false);

    public QuarkCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("When Chest Search Focused", "SPECIAL_ChestSearchFocus_movement", SPECIAL_ChestSearchFocus_movement,false)
        );
        backgroundOptions = Arrays.asList(
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if(!SPECIAL_ChestSearchFocus_movement.get() && Config.GENERAL.textFieldDisablesMovement.get()) {
            if (ChestSearchingModule_searchBar == null) {
                Field[] fs = ChestSearchingModule.class.getDeclaredFields();
                for (Field f : fs) {
                    if (f.getType() == TextFieldWidget.class) {
                        ChestSearchingModule_searchBar = f;
                        ChestSearchingModule_searchBar.setAccessible(true);
                    }
                }
            } else {
                try {
                    TextFieldWidget tw = (TextFieldWidget) ChestSearchingModule_searchBar.get(null);
                    if (tw != null && tw.func_212955_f()) return Optional.of(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {
        return Optional.empty();
    }
}
