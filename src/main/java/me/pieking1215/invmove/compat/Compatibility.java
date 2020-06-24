package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.fml.ModList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Compatibility {

    private static List<IModCompatibility> compats;

    public static void loadCompatibility(){
        compats = new ArrayList<>();

        HashMap<String, String> compatMods = new HashMap<>();

        compatMods.put("jei"                 , "me.pieking1215.invmove.compat.JEICompatibility");
        compatMods.put("immersiveengineering", "me.pieking1215.invmove.compat.ImmersiveEngineeringCompatibility");
        compatMods.put("engineersdecor"      , "me.pieking1215.invmove.compat.EngineersDecorCompatibility");
        compatMods.put("computercraft"       , "me.pieking1215.invmove.compat.CCTweakedCompatibility");
        compatMods.put("xercamusic"          , "me.pieking1215.invmove.compat.MusicMakerModCompatibility");
        compatMods.put("embellishcraft"      , "me.pieking1215.invmove.compat.EmbellishCraftCompatibility");
        compatMods.put("refinedstorage"      , "me.pieking1215.invmove.compat.RefinedStorageCompatibility");
        compatMods.put("cfm"                 , "me.pieking1215.invmove.compat.CrayfishFurnitureCompatibility");
        compatMods.put("corail_woodcutter"   , "me.pieking1215.invmove.compat.CorailWoodcutterCompatibility");
        compatMods.put("charm"               , "me.pieking1215.invmove.compat.CharmCompatibility");
        compatMods.put("quark"               , "me.pieking1215.invmove.compat.QuarkCompatibility");
        compatMods.put("create"              , "me.pieking1215.invmove.compat.CreateCompatibility");
        compatMods.put("curios"              , "me.pieking1215.invmove.compat.CuriosCompatibility");

        for (String s : compatMods.keySet()){
            if(ModList.get().isLoaded(s)){
                try {
                    compats.add(Class.forName(compatMods.get(s)).asSubclass(IModCompatibility.class).newInstance());
                } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static Optional<Boolean> shouldAllowMovement(Screen screen) {
        for(IModCompatibility c : compats){
            try{
                Optional<Boolean> ob = c.shouldAllowMovement(screen);
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

    public static Optional<Boolean> shouldDisableBackground(Screen screen) {
        for(IModCompatibility c : compats){
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
}
