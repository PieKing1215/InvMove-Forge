package me.pieking1215.invmove.compat;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import me.pieking1215.invmove.Config;
import me.shedaniel.forge.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.forge.clothconfig2.impl.builders.BooleanToggleBuilder;
import me.shedaniel.forge.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Tuple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ModCompatibility {

    // displayName, optionID, bool
    protected List<BoolOption> movementOptions = new ArrayList<>();
    protected List<BoolOption> backgroundOptions = new ArrayList<>();

    class BoolOption {
        String displayName;
        String optionID;
        AtomicBoolean bool;
        boolean defaultState;
        String tooltip;

        public BoolOption(String displayName, String optionID, AtomicBoolean bool, boolean defaultState, String tooltip){
            this.displayName = displayName;
            this.optionID = optionID;
            this.bool = bool;
            this.defaultState = defaultState;
            this.tooltip = tooltip;
        }

        public BoolOption(String displayName, String optionID, AtomicBoolean bool, boolean defaultState){
            this(displayName, optionID, bool, defaultState, null);
        }

    }

    abstract Optional<Boolean> shouldAllowMovement(Screen screen);
    abstract Optional<Boolean> shouldDisableBackground(Screen screen);

    public boolean setupClothMovement(SubCategoryBuilder compatCat, ConfigEntryBuilder eb) {
        if(movementOptions.isEmpty()) return false;
        for(BoolOption opt : movementOptions){
            BooleanToggleBuilder tb = eb.startBooleanToggle(opt.displayName, opt.bool.get()).setDefaultValue(opt.defaultState).setSaveConsumer(b -> opt.bool.set(b)).setYesNoTextSupplier(Config.movement_yesNoText);
            if(opt.tooltip != null) tb.setTooltip(opt.tooltip.split("\n"));
            compatCat.add(tb.build());
        }
        return true;
    }

    public boolean setupClothBackground(SubCategoryBuilder compatCat, ConfigEntryBuilder eb) {
        if(backgroundOptions.isEmpty()) return false;
        for(BoolOption opt : backgroundOptions){
            BooleanToggleBuilder tb = eb.startBooleanToggle(opt.displayName, opt.bool.get()).setDefaultValue(opt.defaultState).setSaveConsumer(b -> opt.bool.set(b)).setYesNoTextSupplier(Config.background_yesNoText);
            if(opt.tooltip != null) tb.setTooltip(opt.tooltip.split("\n"));
            compatCat.add(tb.build());
        }
        return true;
    }

    public void loadConfig(JsonObject obj) {

        for(BoolOption opt : movementOptions){
            if(obj.has(opt.optionID)) opt.bool.set(obj.get(opt.optionID).getAsBoolean());
        }

        for(BoolOption opt : backgroundOptions){
            if(obj.has(opt.optionID)) opt.bool.set(obj.get(opt.optionID).getAsBoolean());
        }
    }

    public void saveConfig(JsonWriter jw) throws IOException {
        for(BoolOption opt : movementOptions){
            jw.name(opt.optionID).value(opt.bool.get());
        }

        for(BoolOption opt : backgroundOptions){
            jw.name(opt.optionID).value(opt.bool.get());
        }
    }
}
