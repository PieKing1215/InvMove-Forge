package me.pieking1215.invmove.compat;

import com.raoulvdberge.refinedstorage.screen.ConstructorScreen;
import com.raoulvdberge.refinedstorage.screen.ControllerScreen;
import com.raoulvdberge.refinedstorage.screen.CrafterManagerScreen;
import com.raoulvdberge.refinedstorage.screen.CrafterScreen;
import com.raoulvdberge.refinedstorage.screen.CraftingMonitorScreen;
import com.raoulvdberge.refinedstorage.screen.DestructorScreen;
import com.raoulvdberge.refinedstorage.screen.DetectorScreen;
import com.raoulvdberge.refinedstorage.screen.DiskDriveScreen;
import com.raoulvdberge.refinedstorage.screen.DiskManipulatorScreen;
import com.raoulvdberge.refinedstorage.screen.ExporterScreen;
import com.raoulvdberge.refinedstorage.screen.ExternalStorageScreen;
import com.raoulvdberge.refinedstorage.screen.FilterScreen;
import com.raoulvdberge.refinedstorage.screen.FluidAmountScreen;
import com.raoulvdberge.refinedstorage.screen.FluidInterfaceScreen;
import com.raoulvdberge.refinedstorage.screen.FluidStorageBlockScreen;
import com.raoulvdberge.refinedstorage.screen.ImporterScreen;
import com.raoulvdberge.refinedstorage.screen.InterfaceScreen;
import com.raoulvdberge.refinedstorage.screen.ItemAmountScreen;
import com.raoulvdberge.refinedstorage.screen.NetworkTransmitterScreen;
import com.raoulvdberge.refinedstorage.screen.PriorityScreen;
import com.raoulvdberge.refinedstorage.screen.RelayScreen;
import com.raoulvdberge.refinedstorage.screen.SecurityManagerScreen;
import com.raoulvdberge.refinedstorage.screen.StorageBlockScreen;
import com.raoulvdberge.refinedstorage.screen.StorageMonitorScreen;
import com.raoulvdberge.refinedstorage.screen.WirelessTransmitterScreen;
import com.raoulvdberge.refinedstorage.screen.grid.GridScreen;
import net.minecraft.client.gui.screen.Screen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class RefinedStorageCompatibility extends ModCompatibility {

    AtomicBoolean ConstructorScreen_movement = new AtomicBoolean(true);
    AtomicBoolean ControllerScreen_movement = new AtomicBoolean(true);
    AtomicBoolean CrafterManagerScreen_movement = new AtomicBoolean(true);
    AtomicBoolean CrafterScreen_movement = new AtomicBoolean(true);
    AtomicBoolean CraftingMonitorScreen_movement = new AtomicBoolean(true);
    AtomicBoolean GridScreen_movement = new AtomicBoolean(true);
    AtomicBoolean DestructorScreen_movement = new AtomicBoolean(true);
    AtomicBoolean DetectorScreen_movement = new AtomicBoolean(true);
    AtomicBoolean DiskDriveScreen_movement = new AtomicBoolean(true);
    AtomicBoolean DiskManipulatorScreen_movement = new AtomicBoolean(true);
    AtomicBoolean ExporterScreen_movement = new AtomicBoolean(true);
    AtomicBoolean ExternalStorageScreen_movement = new AtomicBoolean(true);
    AtomicBoolean FilterScreen_movement = new AtomicBoolean(true);
    AtomicBoolean FluidAmountScreen_movement = new AtomicBoolean(true);
    AtomicBoolean FluidInterfaceScreen_movement = new AtomicBoolean(true);
    AtomicBoolean FluidStorageBlockScreen_movement = new AtomicBoolean(true);
    AtomicBoolean ImporterScreen_movement = new AtomicBoolean(true);
    AtomicBoolean InterfaceScreen_movement = new AtomicBoolean(true);
    AtomicBoolean ItemAmountScreen_movement = new AtomicBoolean(true);
    AtomicBoolean NetworkTransmitterScreen_movement = new AtomicBoolean(true);
    AtomicBoolean PriorityScreen_movement = new AtomicBoolean(true);
    AtomicBoolean RelayScreen_movement = new AtomicBoolean(true);
    AtomicBoolean SecurityManagerScreen_movement = new AtomicBoolean(true);
    AtomicBoolean StorageBlockScreen_movement = new AtomicBoolean(true);
    AtomicBoolean StorageMonitorScreen_movement = new AtomicBoolean(true);
    AtomicBoolean WirelessTransmitterScreen_movement = new AtomicBoolean(true);

    AtomicBoolean ConstructorScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean ControllerScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean CrafterManagerScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean CrafterScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean CraftingMonitorScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean GridScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean DestructorScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean DetectorScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean DiskDriveScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean DiskManipulatorScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean ExporterScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean ExternalStorageScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean FilterScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean FluidAmountScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean FluidInterfaceScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean FluidStorageBlockScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean ImporterScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean InterfaceScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean ItemAmountScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean NetworkTransmitterScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean PriorityScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean RelayScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean SecurityManagerScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean StorageBlockScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean StorageMonitorScreen_background_disable = new AtomicBoolean(true);
    AtomicBoolean WirelessTransmitterScreen_background_disable = new AtomicBoolean(true);
    
    public RefinedStorageCompatibility(){
        movementOptions = Arrays.asList(
                new BoolOption("Constructor", "ConstructorScreen_movement", ConstructorScreen_movement, true),
                new BoolOption("Controller", "ControllerScreen_movement", ControllerScreen_movement, true),
                new BoolOption("Crafter Manager", "CrafterManagerScreen_movement", CrafterManagerScreen_movement, true),
                new BoolOption("Crafter", "CrafterScreen_movement", CrafterScreen_movement, true),
                new BoolOption("Crafting Monitor", "CraftingMonitorScreen_movement", CraftingMonitorScreen_movement, true),
                new BoolOption("Grid", "GridScreen_movement", GridScreen_movement, true),
                new BoolOption("Destructor", "DestructorScreen_movement", DestructorScreen_movement, true),
                new BoolOption("Detector", "DetectorScreen_movement", DetectorScreen_movement, true),
                new BoolOption("Disk Drive", "DiskDriveScreen_movement", DiskDriveScreen_movement, true),
                new BoolOption("Disk Manipulator", "DiskManipulatorScreen_movement", DiskManipulatorScreen_movement, true),
                new BoolOption("Exporter", "ExporterScreen_movement", ExporterScreen_movement, true),
                new BoolOption("External Storage", "ExternalStorageScreen_movement", ExternalStorageScreen_movement, true),
                new BoolOption("Filter", "FilterScreen_movement", FilterScreen_movement, true),
                new BoolOption("Fluid Amount GUI", "FluidAmountScreen_movement", FluidAmountScreen_movement, true),
                new BoolOption("Fluid Interface", "FluidInterfaceScreen_movement", FluidInterfaceScreen_movement, true),
                new BoolOption("Fluid Storage Block", "FluidStorageBlockScreen_movement", FluidStorageBlockScreen_movement, true),
                new BoolOption("Importer", "ImporterScreen_movement", ImporterScreen_movement, true),
                new BoolOption("Interface", "InterfaceScreen_movement", InterfaceScreen_movement, true),
                new BoolOption("Item Amount GUI", "ItemAmountScreen_movement", ItemAmountScreen_movement, true),
                new BoolOption("Network Transmitter", "NetworkTransmitterScreen_movement", NetworkTransmitterScreen_movement, true),
                new BoolOption("Priority GUI", "PriorityScreen_movement", PriorityScreen_movement, true),
                new BoolOption("Relay", "RelayScreen_movement", RelayScreen_movement, true),
                new BoolOption("Security Manager", "SecurityManagerScreen_movement", SecurityManagerScreen_movement, true),
                new BoolOption("Storage Block", "StorageBlockScreen_movement", StorageBlockScreen_movement, true),
                new BoolOption("Storage Monitor", "StorageMonitorScreen_movement", StorageMonitorScreen_movement, true),
                new BoolOption("Wireless Transmitter", "WirelessTransmitterScreen_movement", WirelessTransmitterScreen_movement, true)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Constructor", "ConstructorScreen_background_disable", ConstructorScreen_background_disable, true),
                new BoolOption("Controller", "ControllerScreen_background_disable", ControllerScreen_background_disable, true),
                new BoolOption("Crafter Manager", "CrafterManagerScreen_background_disable", CrafterManagerScreen_background_disable, true),
                new BoolOption("Crafter", "CrafterScreen_background_disable", CrafterScreen_background_disable, true),
                new BoolOption("Crafting Monitor", "CraftingMonitorScreen_background_disable", CraftingMonitorScreen_background_disable, true),
                new BoolOption("Grid", "GridScreen_background_disable", GridScreen_background_disable, true),
                new BoolOption("Destructor", "DestructorScreen_background_disable", DestructorScreen_background_disable, true),
                new BoolOption("Detector", "DetectorScreen_background_disable", DetectorScreen_background_disable, true),
                new BoolOption("Disk Drive", "DiskDriveScreen_background_disable", DiskDriveScreen_background_disable, true),
                new BoolOption("Disk Manipulator", "DiskManipulatorScreen_background_disable", DiskManipulatorScreen_background_disable, true),
                new BoolOption("Exporter", "ExporterScreen_background_disable", ExporterScreen_background_disable, true),
                new BoolOption("External Storage", "ExternalStorageScreen_background_disable", ExternalStorageScreen_background_disable, true),
                new BoolOption("Filter", "FilterScreen_background_disable", FilterScreen_background_disable, true),
                new BoolOption("Fluid Amount GUI", "FluidAmountScreen_background_disable", FluidAmountScreen_background_disable, true),
                new BoolOption("Fluid Interface", "FluidInterfaceScreen_background_disable", FluidInterfaceScreen_background_disable, true),
                new BoolOption("Fluid Storage Block", "FluidStorageBlockScreen_background_disable", FluidStorageBlockScreen_background_disable, true),
                new BoolOption("Importer", "ImporterScreen_background_disable", ImporterScreen_background_disable, true),
                new BoolOption("Interface", "InterfaceScreen_background_disable", InterfaceScreen_background_disable, true),
                new BoolOption("Item Amount GUI", "ItemAmountScreen_background_disable", ItemAmountScreen_background_disable, true),
                new BoolOption("Network Transmitter", "NetworkTransmitterScreen_background_disable", NetworkTransmitterScreen_background_disable, true),
                new BoolOption("Priority GUI", "PriorityScreen_background_disable", PriorityScreen_background_disable, true),
                new BoolOption("Relay", "RelayScreen_background_disable", RelayScreen_background_disable, true),
                new BoolOption("Security Manager", "SecurityManagerScreen_background_disable", SecurityManagerScreen_background_disable, true),
                new BoolOption("Storage Block", "StorageBlockScreen_background_disable", StorageBlockScreen_background_disable, true),
                new BoolOption("Storage Monitor", "StorageMonitorScreen_background_disable", StorageMonitorScreen_background_disable, true),
                new BoolOption("Wireless Transmitter", "WirelessTransmitterScreen_background_disable", WirelessTransmitterScreen_background_disable, true)
        );
    }
    
    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if(screen instanceof ConstructorScreen)         return Optional.of(ConstructorScreen_movement.get());
        if(screen instanceof ControllerScreen)          return Optional.of(ControllerScreen_movement.get());
        if(screen instanceof CrafterManagerScreen)      return Optional.of(CrafterManagerScreen_movement.get());
        if(screen instanceof CrafterScreen)             return Optional.of(CrafterScreen_movement.get());
        if(screen instanceof CraftingMonitorScreen)     return Optional.of(CraftingMonitorScreen_movement.get());
        if(screen instanceof GridScreen)                return Optional.of(GridScreen_movement.get());
        if(screen instanceof DestructorScreen)          return Optional.of(DestructorScreen_movement.get());
        if(screen instanceof DetectorScreen)            return Optional.of(DetectorScreen_movement.get());
        if(screen instanceof DiskDriveScreen)           return Optional.of(DiskDriveScreen_movement.get());
        if(screen instanceof DiskManipulatorScreen)     return Optional.of(DiskManipulatorScreen_movement.get());
        if(screen instanceof ExporterScreen)            return Optional.of(ExporterScreen_movement.get());
        if(screen instanceof ExternalStorageScreen)     return Optional.of(ExternalStorageScreen_movement.get());
        if(screen instanceof FilterScreen)              return Optional.of(FilterScreen_movement.get());
        if(screen instanceof FluidAmountScreen)         return Optional.of(FluidAmountScreen_movement.get());
        if(screen instanceof FluidInterfaceScreen)      return Optional.of(FluidInterfaceScreen_movement.get());
        if(screen instanceof FluidStorageBlockScreen)   return Optional.of(FluidStorageBlockScreen_movement.get());
        if(screen instanceof ImporterScreen)            return Optional.of(ImporterScreen_movement.get());
        if(screen instanceof InterfaceScreen)           return Optional.of(InterfaceScreen_movement.get());
        if(screen instanceof ItemAmountScreen)          return Optional.of(ItemAmountScreen_movement.get());
        if(screen instanceof NetworkTransmitterScreen)  return Optional.of(NetworkTransmitterScreen_movement.get());
        if(screen instanceof PriorityScreen)            return Optional.of(PriorityScreen_movement.get());
        if(screen instanceof RelayScreen)               return Optional.of(RelayScreen_movement.get());
        if(screen instanceof SecurityManagerScreen)     return Optional.of(SecurityManagerScreen_movement.get());
        if(screen instanceof StorageBlockScreen)        return Optional.of(StorageBlockScreen_movement.get());
        if(screen instanceof StorageMonitorScreen)      return Optional.of(StorageMonitorScreen_movement.get());
        if(screen instanceof WirelessTransmitterScreen) return Optional.of(WirelessTransmitterScreen_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if(screen instanceof ConstructorScreen)         return Optional.of(ConstructorScreen_background_disable.get());
        if(screen instanceof ControllerScreen)          return Optional.of(ControllerScreen_background_disable.get());
        if(screen instanceof CrafterManagerScreen)      return Optional.of(CrafterManagerScreen_background_disable.get());
        if(screen instanceof CrafterScreen)             return Optional.of(CrafterScreen_background_disable.get());
        if(screen instanceof CraftingMonitorScreen)     return Optional.of(CraftingMonitorScreen_background_disable.get());
        if(screen instanceof GridScreen)                return Optional.of(GridScreen_background_disable.get());
        if(screen instanceof DestructorScreen)          return Optional.of(DestructorScreen_background_disable.get());
        if(screen instanceof DetectorScreen)            return Optional.of(DetectorScreen_background_disable.get());
        if(screen instanceof DiskDriveScreen)           return Optional.of(DiskDriveScreen_background_disable.get());
        if(screen instanceof DiskManipulatorScreen)     return Optional.of(DiskManipulatorScreen_background_disable.get());
        if(screen instanceof ExporterScreen)            return Optional.of(ExporterScreen_background_disable.get());
        if(screen instanceof ExternalStorageScreen)     return Optional.of(ExternalStorageScreen_background_disable.get());
        if(screen instanceof FilterScreen)              return Optional.of(FilterScreen_background_disable.get());
        if(screen instanceof FluidAmountScreen)         return Optional.of(FluidAmountScreen_background_disable.get());
        if(screen instanceof FluidInterfaceScreen)      return Optional.of(FluidInterfaceScreen_background_disable.get());
        if(screen instanceof FluidStorageBlockScreen)   return Optional.of(FluidStorageBlockScreen_background_disable.get());
        if(screen instanceof ImporterScreen)            return Optional.of(ImporterScreen_background_disable.get());
        if(screen instanceof InterfaceScreen)           return Optional.of(InterfaceScreen_background_disable.get());
        if(screen instanceof ItemAmountScreen)          return Optional.of(ItemAmountScreen_background_disable.get());
        if(screen instanceof NetworkTransmitterScreen)  return Optional.of(NetworkTransmitterScreen_background_disable.get());
        if(screen instanceof PriorityScreen)            return Optional.of(PriorityScreen_background_disable.get());
        if(screen instanceof RelayScreen)               return Optional.of(RelayScreen_background_disable.get());
        if(screen instanceof SecurityManagerScreen)     return Optional.of(SecurityManagerScreen_background_disable.get());
        if(screen instanceof StorageBlockScreen)        return Optional.of(StorageBlockScreen_background_disable.get());
        if(screen instanceof StorageMonitorScreen)      return Optional.of(StorageMonitorScreen_background_disable.get());
        if(screen instanceof WirelessTransmitterScreen) return Optional.of(WirelessTransmitterScreen_background_disable.get());

        return Optional.empty();
    }
}
