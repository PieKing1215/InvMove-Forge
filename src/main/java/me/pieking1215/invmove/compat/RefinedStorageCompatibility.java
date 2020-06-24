package me.pieking1215.invmove.compat;

import com.refinedmods.refinedstorage.screen.ConstructorScreen;
import com.refinedmods.refinedstorage.screen.ControllerScreen;
import com.refinedmods.refinedstorage.screen.CrafterManagerScreen;
import com.refinedmods.refinedstorage.screen.CrafterScreen;
import com.refinedmods.refinedstorage.screen.CraftingMonitorScreen;
import com.refinedmods.refinedstorage.screen.DestructorScreen;
import com.refinedmods.refinedstorage.screen.DetectorScreen;
import com.refinedmods.refinedstorage.screen.DiskDriveScreen;
import com.refinedmods.refinedstorage.screen.DiskManipulatorScreen;
import com.refinedmods.refinedstorage.screen.ExporterScreen;
import com.refinedmods.refinedstorage.screen.ExternalStorageScreen;
import com.refinedmods.refinedstorage.screen.FilterScreen;
import com.refinedmods.refinedstorage.screen.FluidAmountScreen;
import com.refinedmods.refinedstorage.screen.FluidInterfaceScreen;
import com.refinedmods.refinedstorage.screen.FluidStorageBlockScreen;
import com.refinedmods.refinedstorage.screen.ImporterScreen;
import com.refinedmods.refinedstorage.screen.InterfaceScreen;
import com.refinedmods.refinedstorage.screen.ItemAmountScreen;
import com.refinedmods.refinedstorage.screen.NetworkTransmitterScreen;
import com.refinedmods.refinedstorage.screen.PriorityScreen;
import com.refinedmods.refinedstorage.screen.RelayScreen;
import com.refinedmods.refinedstorage.screen.SecurityManagerScreen;
import com.refinedmods.refinedstorage.screen.StorageBlockScreen;
import com.refinedmods.refinedstorage.screen.StorageMonitorScreen;
import com.refinedmods.refinedstorage.screen.StorageScreen;
import com.refinedmods.refinedstorage.screen.WirelessTransmitterScreen;
import com.refinedmods.refinedstorage.screen.grid.GridScreen;
import net.minecraft.client.gui.screen.Screen;

import java.util.Optional;

public class RefinedStorageCompatibility implements IModCompatibility {
    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof ConstructorScreen)         return Optional.of(true);
        if(screen instanceof ControllerScreen)          return Optional.of(true);
        if(screen instanceof CrafterManagerScreen)      return Optional.of(true);
        if(screen instanceof CrafterScreen)             return Optional.of(true);
        if(screen instanceof CraftingMonitorScreen)     return Optional.of(true);
        if(screen instanceof GridScreen)                return Optional.of(true);
        if(screen instanceof DestructorScreen)          return Optional.of(true);
        if(screen instanceof DetectorScreen)            return Optional.of(true);
        if(screen instanceof DiskDriveScreen)           return Optional.of(true);
        if(screen instanceof DiskManipulatorScreen)     return Optional.of(true);
        if(screen instanceof ExporterScreen)            return Optional.of(true);
        if(screen instanceof ExternalStorageScreen)     return Optional.of(true);
        if(screen instanceof FilterScreen)              return Optional.of(true);
        if(screen instanceof FluidAmountScreen)         return Optional.of(true);
        if(screen instanceof FluidInterfaceScreen)      return Optional.of(true);
        if(screen instanceof FluidStorageBlockScreen)   return Optional.of(true);
        if(screen instanceof ImporterScreen)            return Optional.of(true);
        if(screen instanceof InterfaceScreen)           return Optional.of(true);
        if(screen instanceof ItemAmountScreen)          return Optional.of(true);
        if(screen instanceof NetworkTransmitterScreen)  return Optional.of(true);
        if(screen instanceof PriorityScreen)            return Optional.of(true);
        if(screen instanceof RelayScreen)               return Optional.of(true);
        if(screen instanceof SecurityManagerScreen)     return Optional.of(true);
        if(screen instanceof StorageBlockScreen)        return Optional.of(true);
        if(screen instanceof StorageMonitorScreen)      return Optional.of(true);
        if(screen instanceof WirelessTransmitterScreen) return Optional.of(true);

        return Optional.empty();
    }
}
