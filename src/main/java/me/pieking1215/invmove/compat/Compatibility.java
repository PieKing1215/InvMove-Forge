package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.fml.ModList;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;

public class Compatibility {

    private static LinkedHashMap<String, ModCompatibility> compats;

    public static void loadCompatibility(){
        // it's linked since the order matters
        compats = new LinkedHashMap<>();

        LinkedHashMap<String, String> compatMods = new LinkedHashMap<>();

        // mods with special behavior in shouldAllowMovement or
        //   shouldDisableBackground that need to have higher priority
        compatMods.put("jei"                 , "me.pieking1215.invmove.compat.JEICompatibility");
        compatMods.put("quark"               , "me.pieking1215.invmove.compat.QuarkCompatibility");

        compatMods.put("cloth-config2"       , "me.pieking1215.invmove.compat.ClothConfigCompatibility");
        compatMods.put("immersiveengineering", "me.pieking1215.invmove.compat.ImmersiveEngineeringCompatibility");
        compatMods.put("engineersdecor"      , "me.pieking1215.invmove.compat.EngineersDecorCompatibility");
        compatMods.put("computercraft"       , "me.pieking1215.invmove.compat.CCTweakedCompatibility");
        compatMods.put("xercamusic"          , "me.pieking1215.invmove.compat.MusicMakerModCompatibility");
        compatMods.put("embellishcraft"      , "me.pieking1215.invmove.compat.EmbellishCraftCompatibility");
        compatMods.put("refinedstorage"      , "me.pieking1215.invmove.compat.RefinedStorageCompatibility");
        compatMods.put("cfm"                 , "me.pieking1215.invmove.compat.CrayfishFurnitureCompatibility");
        compatMods.put("corail_woodcutter"   , "me.pieking1215.invmove.compat.CorailWoodcutterCompatibility");
        compatMods.put("charm"               , "me.pieking1215.invmove.compat.CharmCompatibility");
        compatMods.put("create"              , "me.pieking1215.invmove.compat.CreateCompatibility");
        compatMods.put("curios"              , "me.pieking1215.invmove.compat.CuriosCompatibility");

        for (String s : compatMods.keySet()){
            if(ModList.get().isLoaded(s)){
                try {
                    compats.put(s, Class.forName(compatMods.get(s)).asSubclass(ModCompatibility.class).newInstance());
                } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static Optional<Boolean> shouldAllowMovement(Screen screen) {
        for(ModCompatibility c : compats.values()){
            if(c == null) continue;
            try{
                Optional<Boolean> ob = c.shouldAllowMovement(screen);
                if(ob != null && ob.isPresent()){
                    return Optional.of(ob.get());
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        // stupid way of doing it but whatever
        if(screen.getClass().getName().equals("net.optifine.gui.GuiChatOF")) return Optional.of(false);

        return Optional.empty();
    }

    public static Optional<Boolean> shouldDisableBackground(Screen screen) {
        for(ModCompatibility c : compats.values()){
            try{
                Optional<Boolean> ob = c.shouldDisableBackground(screen);
                if(ob.isPresent()){
                    return Optional.of(ob.get());
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        // stupid way of doing it but whatever
        if(screen.getClass().getName().equals("net.optifine.gui.GuiChatOF")) return Optional.of(false);

        return Optional.empty();
    }

    public static HashMap<String, ModCompatibility> getCompatibilities(){
        return compats;
    }

}
