package me.pieking1215.invmove.compat;

import blusunrize.immersiveengineering.client.gui.AlloySmelterScreen;
import blusunrize.immersiveengineering.client.gui.ArcFurnaceScreen;
import blusunrize.immersiveengineering.client.gui.AssemblerScreen;
import blusunrize.immersiveengineering.client.gui.AutoWorkbenchScreen;
import blusunrize.immersiveengineering.client.gui.BlastFurnaceScreen;
import blusunrize.immersiveengineering.client.gui.ChemTurretScreen;
import blusunrize.immersiveengineering.client.gui.ClocheScreen;
import blusunrize.immersiveengineering.client.gui.CokeOvenScreen;
import blusunrize.immersiveengineering.client.gui.CraftingTableScreen;
import blusunrize.immersiveengineering.client.gui.CrateScreen;
import blusunrize.immersiveengineering.client.gui.FermenterScreen;
import blusunrize.immersiveengineering.client.gui.FluidSorterScreen;
import blusunrize.immersiveengineering.client.gui.GunTurretScreen;
import blusunrize.immersiveengineering.client.gui.ItemBatcherScreen;
import blusunrize.immersiveengineering.client.gui.MaintenanceKitScreen;
import blusunrize.immersiveengineering.client.gui.MixerScreen;
import blusunrize.immersiveengineering.client.gui.ModWorkbenchScreen;
import blusunrize.immersiveengineering.client.gui.RedstoneConnectorScreen;
import blusunrize.immersiveengineering.client.gui.RedstoneProbeScreen;
import blusunrize.immersiveengineering.client.gui.RefineryScreen;
import blusunrize.immersiveengineering.client.gui.RevolverScreen;
import blusunrize.immersiveengineering.client.gui.SorterScreen;
import blusunrize.immersiveengineering.client.gui.SqueezerScreen;
import blusunrize.immersiveengineering.client.gui.ToolboxBlockScreen;
import blusunrize.immersiveengineering.client.gui.ToolboxScreen;
import net.minecraft.client.gui.screen.Screen;

import java.util.Optional;

public class ImmersiveEngineeringCompatibility implements IModCompatibility  {

    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof AlloySmelterScreen) return Optional.of(true);
        if(screen instanceof ArcFurnaceScreen) return Optional.of(true);
        if(screen instanceof AssemblerScreen) return Optional.of(true);
        if(screen instanceof AutoWorkbenchScreen) return Optional.of(true);
        if(screen instanceof BlastFurnaceScreen) return Optional.of(true);
        if(screen instanceof ChemTurretScreen) return Optional.of(true);
        if(screen instanceof ClocheScreen) return Optional.of(true);
        if(screen instanceof CokeOvenScreen) return Optional.of(true);
        if(screen instanceof CraftingTableScreen) return Optional.of(true);
        if(screen instanceof CrateScreen) return Optional.of(true);
        if(screen instanceof FermenterScreen) return Optional.of(true);
        if(screen instanceof FluidSorterScreen) return Optional.of(true);
        if(screen instanceof GunTurretScreen) return Optional.of(true);
        if(screen instanceof ItemBatcherScreen) return Optional.of(true);
        if(screen instanceof MaintenanceKitScreen) return Optional.of(true);
        if(screen instanceof MixerScreen) return Optional.of(true);
        if(screen instanceof ModWorkbenchScreen) return Optional.of(true);
        if(screen instanceof RedstoneConnectorScreen) return Optional.of(true);
        if(screen instanceof RedstoneProbeScreen) return Optional.of(true);
        if(screen instanceof RefineryScreen) return Optional.of(true);
        if(screen instanceof RevolverScreen) return Optional.of(true);
        if(screen instanceof SorterScreen) return Optional.of(true);
        if(screen instanceof SqueezerScreen) return Optional.of(true);
        if(screen instanceof ToolboxBlockScreen) return Optional.of(true);
        if(screen instanceof ToolboxScreen) return Optional.of(true);

        return Optional.empty();
    }
}
