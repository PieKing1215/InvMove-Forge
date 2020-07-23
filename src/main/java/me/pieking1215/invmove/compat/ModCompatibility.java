package me.pieking1215.invmove.compat;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Property;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ModCompatibility {

    // displayName, optionID, bool
    public List<BoolOption> movementOptions = new ArrayList<>();
    public List<BoolOption> backgroundOptions = new ArrayList<>();

    public ConfigCategory movementCategory;
    public ConfigCategory backgroundCategory;

    public class BoolOption {
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

    abstract Optional<Boolean> shouldAllowMovement(GuiScreen screen);
    abstract Optional<Boolean> shouldDisableBackground(GuiScreen screen);

    public static class CallbackProperty extends Property{

        public interface Callback {
            void onValueChanged(String value);
        }

        Callback callback;

        public CallbackProperty(String name, String value, Type type) {
            super(name, value, type);
        }

        public CallbackProperty(String name, String value, Type type, boolean read) {
            super(name, value, type, read);
        }

        public CallbackProperty(String name, String value, Type type, String[] validValues) {
            super(name, value, type, validValues);
        }

        public CallbackProperty(String name, String value, Type type, String[] validValues, String[] validValuesDisplay) {
            super(name, value, type, validValues, validValuesDisplay);
        }

        public CallbackProperty(String name, String value, Type type, String langKey) {
            super(name, value, type, langKey);
        }

        public CallbackProperty(String name, String value, Type type, boolean read, String langKey) {
            super(name, value, type, read, langKey);
        }

        public CallbackProperty(String name, String value, Type type, String[] validValues, String langKey) {
            super(name, value, type, validValues, langKey);
        }

        public CallbackProperty(String name, String value, Type type, String[] validValues, String[] validValuesDisplay, String langKey) {
            super(name, value, type, validValues, validValuesDisplay, langKey);
        }

        public CallbackProperty(String name, String[] values, Type type) {
            super(name, values, type);
        }

        public CallbackProperty(String name, String[] values, Type type, String langKey) {
            super(name, values, type, langKey);
        }

        @Override
        public Property setValue(String value) {
            if(callback != null) callback.onValueChanged(value);
            return super.setValue(value);
        }
    }

    public boolean setupConfigMovement(ConfigCategory category) {
        this.movementCategory = category;
        if(movementOptions.isEmpty()) return false;
        for(BoolOption opt : movementOptions){

            CallbackProperty testProp = new CallbackProperty(opt.displayName, opt.bool.get() + "", Property.Type.BOOLEAN);
            testProp.callback = (s) -> opt.bool.set(s.equals("true"));
            testProp.setDefaultValue(opt.defaultState);
            testProp.setComment(opt.tooltip);

            category.put(opt.displayName, testProp);
        }
        return true;
    }

    public boolean setupConfigBackground(ConfigCategory category) {
        this.backgroundCategory = category;
        if(backgroundOptions.isEmpty()) return false;
        for(BoolOption opt : backgroundOptions){
            CallbackProperty testProp = new CallbackProperty(opt.displayName, opt.bool.get() + "", Property.Type.BOOLEAN);
            testProp.callback = (s) -> opt.bool.set(s.equals("true"));
            testProp.setDefaultValue(opt.defaultState);
            testProp.setComment(opt.tooltip);

            category.put(opt.displayName, testProp);
        }
        return true;
    }

    public void loadConfig(JsonObject obj) {
        System.out.println("LOADING COMPAT: " + this.getClass().getSimpleName());
        for(BoolOption opt : movementOptions){
            System.out.println(opt.optionID + " " + obj.has(opt.optionID));
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
