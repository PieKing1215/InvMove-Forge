package me.pieking1215.invmove.compat;

import net.minecraft.client.gui.screen.Screen;
import wile.engineersdecor.blocks.EdCraftingTable;
import wile.engineersdecor.blocks.EdDropper;
import wile.engineersdecor.blocks.EdElectricalFurnace;
import wile.engineersdecor.blocks.EdFurnace;
import wile.engineersdecor.blocks.EdHopper;
import wile.engineersdecor.blocks.EdLabeledCrate;
import wile.engineersdecor.blocks.EdPlacer;
import wile.engineersdecor.blocks.EdWasteIncinerator;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class EngineersDecorCompatibility extends ModCompatibility {

    AtomicBoolean CraftingTableGui_movement = new AtomicBoolean(true);
    AtomicBoolean DropperGui_movement = new AtomicBoolean(true);
    AtomicBoolean ElectricalFurnaceGui_movement = new AtomicBoolean(true);
    AtomicBoolean FurnaceGui_movement = new AtomicBoolean(true);
    AtomicBoolean HopperGui_movement = new AtomicBoolean(true);
    AtomicBoolean LabeledCrateGui_movement = new AtomicBoolean(true);
    AtomicBoolean PlacerGui_movement = new AtomicBoolean(true);
    AtomicBoolean WasteIncineratorGui_movement = new AtomicBoolean(true);

    AtomicBoolean CraftingTableGui_background_disable = new AtomicBoolean(true);
    AtomicBoolean DropperGui_background_disable = new AtomicBoolean(true);
    AtomicBoolean ElectricalFurnaceGui_background_disable = new AtomicBoolean(true);
    AtomicBoolean FurnaceGui_background_disable = new AtomicBoolean(true);
    AtomicBoolean HopperGui_background_disable = new AtomicBoolean(true);
    AtomicBoolean LabeledCrateGui_background_disable = new AtomicBoolean(true);
    AtomicBoolean PlacerGui_background_disable = new AtomicBoolean(true);
    AtomicBoolean WasteIncineratorGui_background_disable = new AtomicBoolean(true);
    
    public EngineersDecorCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Treated Wood Crafting Table", "CraftingTableGui_movement", CraftingTableGui_movement, true),
                new BoolOption("Factory Dropper", "DropperGui_movement", DropperGui_movement, true),
                new BoolOption("Electrical Furnace", "ElectricalFurnaceGui_movement", ElectricalFurnaceGui_movement, true),
                new BoolOption("Laboratory Furnace", "FurnaceGui_movement", FurnaceGui_movement, true),
                new BoolOption("Factory Hopper", "HopperGui_movement", HopperGui_movement, true),
                new BoolOption("Labeled Crate", "LabeledCrateGui_movement", LabeledCrateGui_movement, true),
                new BoolOption("Factory Block Placer", "PlacerGui_movement", PlacerGui_movement, true),
                new BoolOption("Waste Incinerator", "WasteIncineratorGui_movement", WasteIncineratorGui_movement, true)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Treated Wood Crafting Table", "CraftingTableGui_background_disable", CraftingTableGui_background_disable, true),
                new BoolOption("Factory Dropper", "DropperGui_background_disable", DropperGui_background_disable, true),
                new BoolOption("Electrical Furnace", "ElectricalFurnaceGui_background_disable", ElectricalFurnaceGui_background_disable, true),
                new BoolOption("Laboratory Furnace", "FurnaceGui_background_disable", FurnaceGui_background_disable, true),
                new BoolOption("Factory Hopper", "HopperGui_background_disable", HopperGui_background_disable, true),
                new BoolOption("Labeled Crate", "LabeledCrateGui_background_disable", LabeledCrateGui_background_disable, true),
                new BoolOption("Factory Block Placer", "PlacerGui_background_disable", PlacerGui_background_disable, true),
                new BoolOption("Waste Incinerator", "WasteIncineratorGui_background_disable", WasteIncineratorGui_background_disable, true)
        );
    }
    
    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if(screen instanceof EdCraftingTable.CraftingTableGui)         return Optional.of(CraftingTableGui_movement.get());
        if(screen instanceof EdDropper.DropperGui)                     return Optional.of(DropperGui_movement.get());
        if(screen instanceof EdElectricalFurnace.ElectricalFurnaceGui) return Optional.of(ElectricalFurnaceGui_movement.get());
        if(screen instanceof EdFurnace.FurnaceGui)                     return Optional.of(FurnaceGui_movement.get());
        if(screen instanceof EdHopper.HopperGui)                       return Optional.of(HopperGui_movement.get());
        if(screen instanceof EdLabeledCrate.LabeledCrateGui)           return Optional.of(LabeledCrateGui_movement.get());
        if(screen instanceof EdPlacer.PlacerGui)                       return Optional.of(PlacerGui_movement.get());
        if(screen instanceof EdWasteIncinerator.WasteIncineratorGui)   return Optional.of(WasteIncineratorGui_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof EdCraftingTable.CraftingTableGui)         return Optional.of(CraftingTableGui_background_disable.get());
        if(screen instanceof EdDropper.DropperGui)                     return Optional.of(DropperGui_background_disable.get());
        if(screen instanceof EdElectricalFurnace.ElectricalFurnaceGui) return Optional.of(ElectricalFurnaceGui_background_disable.get());
        if(screen instanceof EdFurnace.FurnaceGui)                     return Optional.of(FurnaceGui_background_disable.get());
        if(screen instanceof EdHopper.HopperGui)                       return Optional.of(HopperGui_background_disable.get());
        if(screen instanceof EdLabeledCrate.LabeledCrateGui)           return Optional.of(LabeledCrateGui_background_disable.get());
        if(screen instanceof EdPlacer.PlacerGui)                       return Optional.of(PlacerGui_background_disable.get());
        if(screen instanceof EdWasteIncinerator.WasteIncineratorGui)   return Optional.of(WasteIncineratorGui_background_disable.get());

        return Optional.empty();
    }
}
