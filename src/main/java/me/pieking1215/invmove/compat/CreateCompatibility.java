package me.pieking1215.invmove.compat;

import com.simibubi.create.content.contraptions.relays.advanced.sequencer.SequencedGearshiftScreen;
import com.simibubi.create.content.curiosities.symmetry.SymmetryWandScreen;
import com.simibubi.create.content.curiosities.zapper.ZapperScreen;
import com.simibubi.create.content.curiosities.zapper.blockzapper.BlockzapperScreen;
import com.simibubi.create.content.curiosities.zapper.terrainzapper.WorldshaperScreen;
import com.simibubi.create.content.logistics.block.inventories.AdjustableCrateScreen;
import com.simibubi.create.content.logistics.block.redstone.StockpileSwitchScreen;
import com.simibubi.create.content.logistics.item.filter.AbstractFilterScreen;
import com.simibubi.create.content.logistics.item.filter.AttributeFilterScreen;
import com.simibubi.create.content.logistics.item.filter.FilterScreen;
import com.simibubi.create.content.schematics.block.SchematicTableScreen;
import com.simibubi.create.content.schematics.block.SchematicannonScreen;
import com.simibubi.create.content.schematics.client.SchematicEditScreen;
import com.simibubi.create.foundation.gui.AbstractSimiContainerScreen;
import com.simibubi.create.foundation.gui.AbstractSimiScreen;
import com.simibubi.create.foundation.gui.TextInputPromptScreen;
import com.simibubi.create.foundation.gui.ToolSelectionScreen;
import net.minecraft.client.gui.screen.Screen;

import java.util.Optional;

public class CreateCompatibility implements IModCompatibility {
    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof SymmetryWandScreen)          return Optional.of(true);
        if(screen instanceof WorldshaperScreen)           return Optional.of(true);
        if(screen instanceof BlockzapperScreen)           return Optional.of(true);
        if(screen instanceof SequencedGearshiftScreen)    return Optional.of(true);
        if(screen instanceof SchematicannonScreen)        return Optional.of(true);
        if(screen instanceof SchematicTableScreen)        return Optional.of(true);
        if(screen instanceof SchematicEditScreen)         return Optional.of(true);
        if(screen instanceof AdjustableCrateScreen)       return Optional.of(true);
        if(screen instanceof StockpileSwitchScreen)       return Optional.of(true);
        if(screen instanceof AttributeFilterScreen)       return Optional.of(true);
        if(screen instanceof FilterScreen)                return Optional.of(true);
        if(screen instanceof AbstractSimiScreen)          return Optional.of(true);
        if(screen instanceof ToolSelectionScreen)         return Optional.of(true);
        if(screen instanceof TextInputPromptScreen)       return Optional.of(true);
        if(screen instanceof AbstractSimiContainerScreen) return Optional.of(true);

        return Optional.empty();
    }
}
