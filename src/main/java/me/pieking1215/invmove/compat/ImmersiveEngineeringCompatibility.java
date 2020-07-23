package me.pieking1215.invmove.compat;

import blusunrize.immersiveengineering.client.gui.GuiAlloySmelter;
import blusunrize.immersiveengineering.client.gui.GuiArcFurnace;
import blusunrize.immersiveengineering.client.gui.GuiAssembler;
import blusunrize.immersiveengineering.client.gui.GuiAutoWorkbench;
import blusunrize.immersiveengineering.client.gui.GuiBelljar;
import blusunrize.immersiveengineering.client.gui.GuiBlastFurnace;
import blusunrize.immersiveengineering.client.gui.GuiCokeOven;
import blusunrize.immersiveengineering.client.gui.GuiCrate;
import blusunrize.immersiveengineering.client.gui.GuiFermenter;
import blusunrize.immersiveengineering.client.gui.GuiFluidSorter;
import blusunrize.immersiveengineering.client.gui.GuiMaintenanceKit;
import blusunrize.immersiveengineering.client.gui.GuiMixer;
import blusunrize.immersiveengineering.client.gui.GuiModWorkbench;
import blusunrize.immersiveengineering.client.gui.GuiRefinery;
import blusunrize.immersiveengineering.client.gui.GuiRevolver;
import blusunrize.immersiveengineering.client.gui.GuiSorter;
import blusunrize.immersiveengineering.client.gui.GuiSqueezer;
import blusunrize.immersiveengineering.client.gui.GuiToolbox;
import blusunrize.immersiveengineering.client.gui.GuiToolboxBlock;
import blusunrize.immersiveengineering.client.gui.GuiTurret;
import net.minecraft.client.gui.GuiScreen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class ImmersiveEngineeringCompatibility extends ModCompatibility {

    AtomicBoolean CraftingTableScreen_movement = new AtomicBoolean(true);
    AtomicBoolean AlloySmelterScreen_movement = new AtomicBoolean(true);
    AtomicBoolean ArcFurnaceScreen_movement = new AtomicBoolean(true);
    AtomicBoolean AssemblerScreen_movement = new AtomicBoolean(true);
    AtomicBoolean AutoWorkbenchScreen_movement = new AtomicBoolean(true);
    AtomicBoolean BlastFurnaceScreen_movement = new AtomicBoolean(true);
    AtomicBoolean GunTurretScreen_movement = new AtomicBoolean(true);
    AtomicBoolean ChemTurretScreen_movement = new AtomicBoolean(true);
    AtomicBoolean ClocheScreen_movement = new AtomicBoolean(true);
    AtomicBoolean CokeOvenScreen_movement = new AtomicBoolean(true);
    AtomicBoolean CrateScreen_movement = new AtomicBoolean(true);
    AtomicBoolean FermenterScreen_movement = new AtomicBoolean(true);
    AtomicBoolean FluidSorterScreen_movement = new AtomicBoolean(true);
    AtomicBoolean ItemBatcherScreen_movement = new AtomicBoolean(true);
    AtomicBoolean MaintenanceKitScreen_movement = new AtomicBoolean(true);
    AtomicBoolean MixerScreen_movement = new AtomicBoolean(true);
    AtomicBoolean ModWorkbenchScreen_movement = new AtomicBoolean(true);
    AtomicBoolean RedstoneConnectorScreen_movement = new AtomicBoolean(true);
    AtomicBoolean RedstoneProbeScreen_movement = new AtomicBoolean(true);
    AtomicBoolean RefineryScreen_movement = new AtomicBoolean(true);
    AtomicBoolean RevolverScreen_movement = new AtomicBoolean(true);
    AtomicBoolean SorterScreen_movement = new AtomicBoolean(true);
    AtomicBoolean SqueezerScreen_movement = new AtomicBoolean(true);
    AtomicBoolean ToolboxBlockScreen_movement = new AtomicBoolean(true);
    AtomicBoolean ToolboxScreen_movement = new AtomicBoolean(true);

    AtomicBoolean CraftingTableScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean AlloySmelterScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean ArcFurnaceScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean AssemblerScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean AutoWorkbenchScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean BlastFurnaceScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean GunTurretScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean ChemTurretScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean ClocheScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean CokeOvenScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean CrateScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean FermenterScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean FluidSorterScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean ItemBatcherScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean MaintenanceKitScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean MixerScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean ModWorkbenchScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean RedstoneConnectorScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean RedstoneProbeScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean RefineryScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean RevolverScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean SorterScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean SqueezerScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean ToolboxBlockScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean ToolboxScreen_background_disable = new AtomicBoolean(true);
    
    public ImmersiveEngineeringCompatibility(){
        movementOptions = Arrays.asList(
                //new BoolOption("Crafting Table", "CraftingTableScreen_movement", CraftingTableScreen_movement, true),
                new BoolOption("Alloy Smelter", "AlloySmelterScreen_movement", AlloySmelterScreen_movement, true),
                new BoolOption("Arc Furnace", "ArcFurnaceScreen_movement", ArcFurnaceScreen_movement, true),
                new BoolOption("Assembler", "AssemblerScreen_movement", AssemblerScreen_movement, true),
                new BoolOption("Auto Workbench", "AutoWorkbenchScreen_movement", AutoWorkbenchScreen_movement, true),
                new BoolOption("Blast Furnace", "BlastFurnaceScreen_movement", BlastFurnaceScreen_movement, true),
                new BoolOption("Turret", "GunTurretScreen_movement", GunTurretScreen_movement, true),
                //new BoolOption("Chem Turret", "ChemTurretScreen_movement", ChemTurretScreen_movement, true),
                new BoolOption("Garden Cloche", "ClocheScreen_movement", ClocheScreen_movement, true),
                new BoolOption("Coke Oven", "CokeOvenScreen_movement", CokeOvenScreen_movement, true),
                new BoolOption("Crate", "CrateScreen_movement", CrateScreen_movement, true),
                new BoolOption("Fermenter", "FermenterScreen_movement", FermenterScreen_movement, true),
                new BoolOption("Fluid Sorter", "FluidSorterScreen_movement", FluidSorterScreen_movement, true),
                new BoolOption("Item Batcher", "ItemBatcherScreen_movement", ItemBatcherScreen_movement, true),
                new BoolOption("Maintenance Kit", "MaintenanceKitScreen_movement", MaintenanceKitScreen_movement, true),
                new BoolOption("Mixer", "MixerScreen_movement", MixerScreen_movement, true),
                new BoolOption("Mod Workbench", "ModWorkbenchScreen_movement", ModWorkbenchScreen_movement, true),
                //new BoolOption("Redstone Connector", "RedstoneConnectorScreen_movement", RedstoneConnectorScreen_movement, true),
                //new BoolOption("Redstone Probe", "RedstoneProbeScreen_movement", RedstoneProbeScreen_movement, true),
                new BoolOption("Refinery", "RefineryScreen_movement", RefineryScreen_movement, true),
                new BoolOption("Revolver", "RevolverScreen_movement", RevolverScreen_movement, true),
                new BoolOption("Sorter", "SorterScreen_movement", SorterScreen_movement, true),
                new BoolOption("Squeezer", "SqueezerScreen_movement", SqueezerScreen_movement, true),
                new BoolOption("Toolbox Block", "ToolboxBlockScreen_movement", ToolboxBlockScreen_movement, true),
                new BoolOption("Toolbox", "ToolboxScreen_movement", ToolboxScreen_movement, true)
        );
        backgroundOptions = Arrays.asList(
                //new BoolOption("Crafting Table", "CraftingTableScreen_background_disable", CraftingTableScreen_background_disable, true),
                new BoolOption("Alloy Smelter", "AlloySmelterScreen_background_disable", AlloySmelterScreen_background_disable, true),
                new BoolOption("Arc Furnace", "ArcFurnaceScreen_background_disable", ArcFurnaceScreen_background_disable, true),
                new BoolOption("Assembler", "AssemblerScreen_background_disable", AssemblerScreen_background_disable, true),
                new BoolOption("Auto Workbench", "AutoWorkbenchScreen_background_disable", AutoWorkbenchScreen_background_disable, true),
                new BoolOption("Blast Furnace", "BlastFurnaceScreen_background_disable", BlastFurnaceScreen_background_disable, true),
                new BoolOption("Turret", "GunTurretScreen_background_disable", GunTurretScreen_background_disable, true),
                //new BoolOption("Chem Turret", "ChemTurretScreen_background_disable", ChemTurretScreen_background_disable, true),
                new BoolOption("Garden Cloche", "ClocheScreen_background_disable", ClocheScreen_background_disable, true),
                new BoolOption("Coke Oven", "CokeOvenScreen_background_disable", CokeOvenScreen_background_disable, true),
                new BoolOption("Crate", "CrateScreen_background_disable", CrateScreen_background_disable, true),
                new BoolOption("Fermenter", "FermenterScreen_background_disable", FermenterScreen_background_disable, true),
                new BoolOption("Fluid Sorter", "FluidSorterScreen_background_disable", FluidSorterScreen_background_disable, true),
                new BoolOption("Item Batcher", "ItemBatcherScreen_background_disable", ItemBatcherScreen_background_disable, true),
                new BoolOption("Maintenance Kit", "MaintenanceKitScreen_background_disable", MaintenanceKitScreen_background_disable, true),
                new BoolOption("Mixer", "MixerScreen_background_disable", MixerScreen_background_disable, true),
                new BoolOption("Mod Workbench", "ModWorkbenchScreen_background_disable", ModWorkbenchScreen_background_disable, true),
                //new BoolOption("Redstone Connector", "RedstoneConnectorScreen_background_disable", RedstoneConnectorScreen_background_disable, true),
                //new BoolOption("Redstone Probe", "RedstoneProbeScreen_background_disable", RedstoneProbeScreen_background_disable, true),
                new BoolOption("Refinery", "RefineryScreen_background_disable", RefineryScreen_background_disable, true),
                new BoolOption("Revolver", "RevolverScreen_background_disable", RevolverScreen_background_disable, true),
                new BoolOption("Sorter", "SorterScreen_background_disable", SorterScreen_background_disable, true),
                new BoolOption("Squeezer", "SqueezerScreen_background_disable", SqueezerScreen_background_disable, true),
                new BoolOption("Toolbox Block", "ToolboxBlockScreen_background_disable", ToolboxBlockScreen_background_disable, true),
                new BoolOption("Toolbox", "ToolboxScreen_background_disable", ToolboxScreen_background_disable, true)
        );
    }
    
    @Override
    public Optional<Boolean> shouldAllowMovement(GuiScreen screen) {

        if(screen instanceof GuiAlloySmelter) return Optional.of(AlloySmelterScreen_movement.get());
        if(screen instanceof GuiArcFurnace) return Optional.of(ArcFurnaceScreen_movement.get());
        if(screen instanceof GuiAssembler) return Optional.of(AssemblerScreen_movement.get());
        if(screen instanceof GuiAutoWorkbench) return Optional.of(AutoWorkbenchScreen_movement.get());
        if(screen instanceof GuiBlastFurnace) return Optional.of(BlastFurnaceScreen_movement.get());
        //if(screen instanceof ChemTurretScreen) return Optional.of(ChemTurretScreen_movement.get());
        if(screen instanceof GuiBelljar) return Optional.of(ClocheScreen_movement.get());
        if(screen instanceof GuiCokeOven) return Optional.of(CokeOvenScreen_movement.get());
        //if(screen instanceof CraftingTableScreen) return Optional.of(CraftingTableScreen_movement.get());
        if(screen instanceof GuiCrate) return Optional.of(CrateScreen_movement.get());
        if(screen instanceof GuiFermenter) return Optional.of(FermenterScreen_movement.get());
        if(screen instanceof GuiFluidSorter) return Optional.of(FluidSorterScreen_movement.get());
        if(screen instanceof GuiTurret) return Optional.of(GunTurretScreen_movement.get());
        //if(screen instanceof ItemBatcherScreen) return Optional.of(ItemBatcherScreen_movement.get());
        if(screen instanceof GuiMaintenanceKit) return Optional.of(MaintenanceKitScreen_movement.get());
        if(screen instanceof GuiMixer) return Optional.of(MixerScreen_movement.get());
        if(screen instanceof GuiModWorkbench) return Optional.of(ModWorkbenchScreen_movement.get());
        //if(screen instanceof RedstoneConnectorScreen) return Optional.of(RedstoneConnectorScreen_movement.get());
        //if(screen instanceof RedstoneProbeScreen) return Optional.of(RedstoneProbeScreen_movement.get());
        if(screen instanceof GuiRefinery) return Optional.of(RefineryScreen_movement.get());
        if(screen instanceof GuiRevolver) return Optional.of(RevolverScreen_movement.get());
        if(screen instanceof GuiSorter) return Optional.of(SorterScreen_movement.get());
        if(screen instanceof GuiSqueezer) return Optional.of(SqueezerScreen_movement.get());
        if(screen instanceof GuiToolboxBlock) return Optional.of(ToolboxBlockScreen_movement.get());
        if(screen instanceof GuiToolbox) return Optional.of(ToolboxScreen_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(GuiScreen screen) {

        if(screen instanceof GuiAlloySmelter) return Optional.of(AlloySmelterScreen_background_disable.get());
        if(screen instanceof GuiArcFurnace) return Optional.of(ArcFurnaceScreen_background_disable.get());
        if(screen instanceof GuiAssembler) return Optional.of(AssemblerScreen_background_disable.get());
        if(screen instanceof GuiAutoWorkbench) return Optional.of(AutoWorkbenchScreen_background_disable.get());
        if(screen instanceof GuiBlastFurnace) return Optional.of(BlastFurnaceScreen_background_disable.get());
        //if(screen instanceof ChemTurretScreen) return Optional.of(ChemTurretScreen_background_disable.get());
        if(screen instanceof GuiBelljar) return Optional.of(ClocheScreen_background_disable.get());
        if(screen instanceof GuiCokeOven) return Optional.of(CokeOvenScreen_background_disable.get());
        //if(screen instanceof CraftingTableScreen) return Optional.of(CraftingTableScreen_background_disable.get());
        if(screen instanceof GuiCrate) return Optional.of(CrateScreen_background_disable.get());
        if(screen instanceof GuiFermenter) return Optional.of(FermenterScreen_background_disable.get());
        if(screen instanceof GuiFluidSorter) return Optional.of(FluidSorterScreen_background_disable.get());
        if(screen instanceof GuiTurret) return Optional.of(GunTurretScreen_background_disable.get());
        //if(screen instanceof ItemBatcherScreen) return Optional.of(ItemBatcherScreen_background_disable.get());
        if(screen instanceof GuiMaintenanceKit) return Optional.of(MaintenanceKitScreen_background_disable.get());
        if(screen instanceof GuiMixer) return Optional.of(MixerScreen_background_disable.get());
        if(screen instanceof GuiModWorkbench) return Optional.of(ModWorkbenchScreen_background_disable.get());
        //if(screen instanceof RedstoneConnectorScreen) return Optional.of(RedstoneConnectorScreen_background_disable.get());
        //if(screen instanceof RedstoneProbeScreen) return Optional.of(RedstoneProbeScreen_background_disable.get());
        if(screen instanceof GuiRefinery) return Optional.of(RefineryScreen_background_disable.get());
        if(screen instanceof GuiRevolver) return Optional.of(RevolverScreen_background_disable.get());
        if(screen instanceof GuiSorter) return Optional.of(SorterScreen_background_disable.get());
        if(screen instanceof GuiSqueezer) return Optional.of(SqueezerScreen_background_disable.get());
        if(screen instanceof GuiToolboxBlock) return Optional.of(ToolboxBlockScreen_background_disable.get());
        if(screen instanceof GuiToolbox) return Optional.of(ToolboxScreen_background_disable.get());

        return Optional.empty();
    }
}
