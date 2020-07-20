package me.pieking1215.invmove.compat;

import me.pieking1215.invmove.Config;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import vazkii.quark.client.feature.ChestSearchBar;

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
    public Optional<Boolean> shouldAllowMovement(GuiScreen screen) {

        if(!SPECIAL_ChestSearchFocus_movement.get() && Config.UI_MOVEMENT.textFieldDisablesMovement) {
            if (ChestSearchingModule_searchBar == null) {
                Field[] fs = ChestSearchBar.class.getDeclaredFields();
                for (Field f : fs) {
                    if (f.getType() == GuiTextField.class) {
                        ChestSearchingModule_searchBar = f;
                        ChestSearchingModule_searchBar.setAccessible(true);
                    }
                }
            } else {
                try {
                    GuiTextField tw = (GuiTextField) ChestSearchingModule_searchBar.get(null);
                    if (tw != null && tw.isFocused()) return Optional.of(false); //TODO isEnabled
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(GuiScreen screen) {
        return Optional.empty();
    }
}
