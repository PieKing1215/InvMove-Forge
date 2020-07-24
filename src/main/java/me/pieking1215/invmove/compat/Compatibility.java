package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

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

        compatMods.put("immersiveengineering", "me.pieking1215.invmove.compat.ImmersiveEngineeringCompatibility");
        compatMods.put("engineersdecor"      , "me.pieking1215.invmove.compat.EngineersDecorCompatibility");
        compatMods.put("computercraft"       , "me.pieking1215.invmove.compat.CCTweakedCompatibility");
        compatMods.put("refinedstorage"      , "me.pieking1215.invmove.compat.RefinedStorageCompatibility");
        compatMods.put("cfm"                 , "me.pieking1215.invmove.compat.CrayfishFurnitureCompatibility");
        compatMods.put("charm"               , "me.pieking1215.invmove.compat.CharmCompatibility");
        compatMods.put("ironchest"           , "me.pieking1215.invmove.compat.IronChestsCompatibility");
        compatMods.put("cookingforblockheads", "me.pieking1215.invmove.compat.CookingForBlockheadsCompatibility");
        compatMods.put("patchouli"           , "me.pieking1215.invmove.compat.PatchouliCompatibility");
        compatMods.put("botania"             , "me.pieking1215.invmove.compat.BotaniaCompatibility");
        compatMods.put("enderstorage"        , "me.pieking1215.invmove.compat.EnderStorageCompatibility");
        compatMods.put("mekanism"            , "me.pieking1215.invmove.compat.MekanismCompatibility");
        compatMods.put("waystones"           , "me.pieking1215.invmove.compat.WaystonesCompatibility");
        compatMods.put("industrialforegoing" , "me.pieking1215.invmove.compat.IndustrialForegoingCompatibility");
        compatMods.put("freecam"             , "me.pieking1215.invmove.compat.FreeCamCompatibility");

        for (String s : compatMods.keySet()){
            if(Loader.isModLoaded(s)){
                try {
                    ModCompatibility comp = Class.forName(compatMods.get(s)).asSubclass(ModCompatibility.class).newInstance();
                    compats.put(s, comp);
                } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void setupConfigScreen(){
        getCompatibilities().forEach((modid, compat) -> {
            ModContainer modInfo = Loader.instance().getModList().stream().filter(con -> con.getModId().equals(modid)).findFirst().get();

            ConfigCategory categoryM = new ConfigCategory(modInfo.getName());
            compat.setupConfigMovement(categoryM);
            ConfigCategory categoryB = new ConfigCategory(modInfo.getName());
            compat.setupConfigBackground(categoryB);
        });
    }

    public static Optional<Boolean> shouldAllowMovement(GuiScreen screen) {
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

    public static Optional<Boolean> shouldDisableBackground(GuiScreen screen) {
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
