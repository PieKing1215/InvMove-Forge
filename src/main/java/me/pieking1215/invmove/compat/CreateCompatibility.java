package me.pieking1215.invmove.compat;

import com.simibubi.create.modules.contraptions.relays.advanced.sequencer.SequencedGearshiftScreen;
import com.simibubi.create.modules.curiosities.symmetry.SymmetryWandScreen;
import com.simibubi.create.modules.curiosities.zapper.blockzapper.BlockzapperScreen;
import com.simibubi.create.modules.curiosities.zapper.terrainzapper.TerrainzapperScreen;
import com.simibubi.create.modules.logistics.block.inventories.FlexcrateScreen;
import com.simibubi.create.modules.logistics.block.StockswitchScreen;
import com.simibubi.create.modules.logistics.item.filter.AttributeFilterScreen;
import com.simibubi.create.modules.logistics.item.filter.FilterScreen;
import com.simibubi.create.modules.schematics.block.SchematicTableScreen;
import com.simibubi.create.modules.schematics.block.SchematicannonScreen;
import com.simibubi.create.modules.schematics.client.SchematicEditScreen;
import com.simibubi.create.foundation.gui.TextInputPromptScreen;
import com.simibubi.create.foundation.gui.ToolSelectionScreen;
import net.minecraft.client.gui.screen.Screen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class CreateCompatibility extends ModCompatibility {


    AtomicBoolean SymmetryWandScreen_movement = new AtomicBoolean(true);
    AtomicBoolean WorldshaperScreen_movement = new AtomicBoolean(true);
    AtomicBoolean BlockzapperScreen_movement = new AtomicBoolean(true);
    AtomicBoolean SequencedGearshiftScreen_movement = new AtomicBoolean(true);
    AtomicBoolean SchematicannonScreen_movement = new AtomicBoolean(true);
    AtomicBoolean SchematicTableScreen_movement = new AtomicBoolean(true);
    AtomicBoolean SchematicEditScreen_movement = new AtomicBoolean(true);
    AtomicBoolean AdjustableCrateScreen_movement = new AtomicBoolean(true);
    AtomicBoolean StockpileSwitchScreen_movement = new AtomicBoolean(true);
    AtomicBoolean AttributeFilterScreen_movement = new AtomicBoolean(true);
    AtomicBoolean FilterScreen_movement = new AtomicBoolean(true);
    AtomicBoolean ToolSelectionScreen_movement = new AtomicBoolean(true);
    AtomicBoolean TextInputPromptScreen_movement = new AtomicBoolean(true);

    AtomicBoolean SymmetryWandScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean WorldshaperScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean BlockzapperScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean SequencedGearshiftScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean SchematicannonScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean SchematicTableScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean SchematicEditScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean AdjustableCrateScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean StockpileSwitchScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean AttributeFilterScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean FilterScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean ToolSelectionScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean TextInputPromptScreen_background_disable = new AtomicBoolean(true);

    public CreateCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Symmetry Wand", "SymmetryWandScreen_movement", SymmetryWandScreen_movement, true),
                new BoolOption("Worldshaper", "WorldshaperScreen_movement", WorldshaperScreen_movement, true),
                new BoolOption("Blockzapper", "BlockzapperScreen_movement", BlockzapperScreen_movement, true),
                new BoolOption("Sequenced Gearshift", "SequencedGearshiftScreen_movement", SequencedGearshiftScreen_movement, true),
                new BoolOption("Schematicannon", "SchematicannonScreen_movement", SchematicannonScreen_movement, true),
                new BoolOption("Schematic Table", "SchematicTableScreen_movement", SchematicTableScreen_movement, true),
                new BoolOption("Schematic Edit", "SchematicEditScreen_movement", SchematicEditScreen_movement, true),
                new BoolOption("Adjustable Crate", "AdjustableCrateScreen_movement", AdjustableCrateScreen_movement, true),
                new BoolOption("Stockpile Switch", "StockpileSwitchScreen_movement", StockpileSwitchScreen_movement, true),
                new BoolOption("Attribute Filter", "AttributeFilterScreen_movement", AttributeFilterScreen_movement, true),
                new BoolOption("Filter", "FilterScreen_movement", FilterScreen_movement, true),
                new BoolOption("Tool Selection", "ToolSelectionScreen_movement", ToolSelectionScreen_movement, true),
                new BoolOption("Text Input Prompt", "TextInputPromptScreen_movement", TextInputPromptScreen_movement, true)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Symmetry Wand", "SymmetryWandScreen_background_disable", SymmetryWandScreen_background_disable, true),
                new BoolOption("Worldshaper", "WorldshaperScreen_background_disable", WorldshaperScreen_background_disable, true),
                new BoolOption("Blockzapper", "BlockzapperScreen_background_disable", BlockzapperScreen_background_disable, true),
                new BoolOption("Sequenced Gearshift", "SequencedGearshiftScreen_background_disable", SequencedGearshiftScreen_background_disable, true),
                new BoolOption("Schematicannon", "SchematicannonScreen_background_disable", SchematicannonScreen_background_disable, true),
                new BoolOption("Schematic Table", "SchematicTableScreen_background_disable", SchematicTableScreen_background_disable, true),
                new BoolOption("Schematic Edit", "SchematicEditScreen_background_disable", SchematicEditScreen_background_disable, true),
                new BoolOption("Adjustable Crate", "AdjustableCrateScreen_background_disable", AdjustableCrateScreen_background_disable, true),
                new BoolOption("Stockpile Switch", "StockpileSwitchScreen_background_disable", StockpileSwitchScreen_background_disable, true),
                new BoolOption("Attribute Filter", "AttributeFilterScreen_background_disable", AttributeFilterScreen_background_disable, true),
                new BoolOption("Filter", "FilterScreen_background_disable", FilterScreen_background_disable, true),
                new BoolOption("Tool Selection", "ToolSelectionScreen_background_disable", ToolSelectionScreen_background_disable, true),
                new BoolOption("Text Input Prompt", "TextInputPromptScreen_background_disable", TextInputPromptScreen_background_disable, true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if(screen instanceof SymmetryWandScreen)       return Optional.of(SymmetryWandScreen_movement.get());
        if(screen instanceof TerrainzapperScreen)      return Optional.of(WorldshaperScreen_movement.get());
        if(screen instanceof BlockzapperScreen)        return Optional.of(BlockzapperScreen_movement.get());
        if(screen instanceof SequencedGearshiftScreen) return Optional.of(SequencedGearshiftScreen_movement.get());
        if(screen instanceof SchematicannonScreen)     return Optional.of(SchematicannonScreen_movement.get());
        if(screen instanceof SchematicTableScreen)     return Optional.of(SchematicTableScreen_movement.get());
        if(screen instanceof SchematicEditScreen)      return Optional.of(SchematicEditScreen_movement.get());
        if(screen instanceof FlexcrateScreen)          return Optional.of(AdjustableCrateScreen_movement.get());
        if(screen instanceof StockswitchScreen)        return Optional.of(StockpileSwitchScreen_movement.get());
        if(screen instanceof AttributeFilterScreen)    return Optional.of(AttributeFilterScreen_movement.get());
        if(screen instanceof FilterScreen)             return Optional.of(FilterScreen_movement.get());
        if(screen instanceof ToolSelectionScreen)      return Optional.of(ToolSelectionScreen_movement.get());
        if(screen instanceof TextInputPromptScreen)    return Optional.of(TextInputPromptScreen_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof SymmetryWandScreen)       return Optional.of(SymmetryWandScreen_background_disable.get());
        if(screen instanceof TerrainzapperScreen)      return Optional.of(WorldshaperScreen_background_disable.get());
        if(screen instanceof BlockzapperScreen)        return Optional.of(BlockzapperScreen_background_disable.get());
        if(screen instanceof SequencedGearshiftScreen) return Optional.of(SequencedGearshiftScreen_background_disable.get());
        if(screen instanceof SchematicannonScreen)     return Optional.of(SchematicannonScreen_background_disable.get());
        if(screen instanceof SchematicTableScreen)     return Optional.of(SchematicTableScreen_background_disable.get());
        if(screen instanceof SchematicEditScreen)      return Optional.of(SchematicEditScreen_background_disable.get());
        if(screen instanceof FlexcrateScreen)          return Optional.of(AdjustableCrateScreen_background_disable.get());
        if(screen instanceof StockswitchScreen)        return Optional.of(StockpileSwitchScreen_background_disable.get());
        if(screen instanceof AttributeFilterScreen)    return Optional.of(AttributeFilterScreen_background_disable.get());
        if(screen instanceof FilterScreen)             return Optional.of(FilterScreen_background_disable.get());
        if(screen instanceof ToolSelectionScreen)      return Optional.of(ToolSelectionScreen_background_disable.get());
        if(screen instanceof TextInputPromptScreen)    return Optional.of(TextInputPromptScreen_background_disable.get());

        return Optional.empty();
    }
}
