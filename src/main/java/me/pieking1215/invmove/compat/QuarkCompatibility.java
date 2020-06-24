package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import vazkii.quark.client.module.ChestSearchingModule;

import java.lang.reflect.Field;
import java.util.Optional;

public class QuarkCompatibility implements IModCompatibility {

    Field ChestSearchingModule_searchBar = null;

    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if(ChestSearchingModule_searchBar == null){
            Field[] fs = ChestSearchingModule.class.getDeclaredFields();
            for(Field f : fs){
                if(f.getType() == TextFieldWidget.class){
                    ChestSearchingModule_searchBar = f;
                    ChestSearchingModule_searchBar.setAccessible(true);
                }
            }
        }else{
            try {
                TextFieldWidget tw = (TextFieldWidget) ChestSearchingModule_searchBar.get(null);
                if(tw.canWrite()) return Optional.of(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {
        return Optional.empty();
    }
}
