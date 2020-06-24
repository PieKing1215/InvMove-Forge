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

import java.util.Optional;

public class EngineersDecorCompatibility implements IModCompatibility {
    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof EdCraftingTable.CraftingTableGui)         return Optional.of(true);
        if(screen instanceof EdDropper.DropperGui)                     return Optional.of(true);
        if(screen instanceof EdElectricalFurnace.ElectricalFurnaceGui) return Optional.of(true);
        if(screen instanceof EdFurnace.FurnaceGui)                     return Optional.of(true);
        if(screen instanceof EdHopper.HopperGui)                       return Optional.of(true);
        if(screen instanceof EdLabeledCrate.LabeledCrateGui)           return Optional.of(true);
        if(screen instanceof EdPlacer.PlacerGui)                       return Optional.of(true);
        if(screen instanceof EdWasteIncinerator.WasteIncineratorGui)   return Optional.of(true);

        return Optional.empty();
    }
}
