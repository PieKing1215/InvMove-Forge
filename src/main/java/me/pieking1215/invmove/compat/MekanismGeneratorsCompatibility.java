package me.pieking1215.invmove.compat;

import mekanism.generators.client.gui.GuiBioGenerator;
import mekanism.generators.client.gui.GuiFissionReactorLogicAdapter;
import mekanism.generators.client.gui.GuiFissionReactorStats;
import mekanism.generators.client.gui.GuiFusionReactorController;
import mekanism.generators.client.gui.GuiFusionReactorFuel;
import mekanism.generators.client.gui.GuiFusionReactorHeat;
import mekanism.generators.client.gui.GuiFusionReactorInfo;
import mekanism.generators.client.gui.GuiFusionReactorLogicAdapter;
import mekanism.generators.client.gui.GuiFusionReactorStats;
import mekanism.generators.client.gui.GuiGasGenerator;
import mekanism.generators.client.gui.GuiHeatGenerator;
import mekanism.generators.client.gui.GuiIndustrialTurbine;
import mekanism.generators.client.gui.GuiSolarGenerator;
import mekanism.generators.client.gui.GuiTurbineStats;
import mekanism.generators.client.gui.GuiWindGenerator;
import net.minecraft.client.gui.screen.Screen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class MekanismGeneratorsCompatibility extends ModCompatibility {
    AtomicBoolean BioGenerator_movement = new AtomicBoolean(true);
    AtomicBoolean GasGenerator_movement = new AtomicBoolean(true);
    AtomicBoolean HeatGenerator_movement = new AtomicBoolean(true);
    AtomicBoolean IndustrialTurbine_movement = new AtomicBoolean(true);
    AtomicBoolean ReactorController_movement = new AtomicBoolean(true);
    AtomicBoolean ReactorFuel_movement = new AtomicBoolean(true);
    AtomicBoolean ReactorHeat_movement = new AtomicBoolean(true);
    AtomicBoolean ReactorInfo_movement = new AtomicBoolean(true);
    AtomicBoolean ReactorLogicAdapter_movement = new AtomicBoolean(true);
    AtomicBoolean ReactorStats_movement = new AtomicBoolean(true);
    AtomicBoolean SolarGenerator_movement = new AtomicBoolean(true);
    AtomicBoolean TurbineStats_movement = new AtomicBoolean(true);
    AtomicBoolean WindGenerator_movement = new AtomicBoolean(true);

    AtomicBoolean BioGenerator_background_disable = new AtomicBoolean(true);
    AtomicBoolean GasGenerator_background_disable = new AtomicBoolean(true);
    AtomicBoolean HeatGenerator_background_disable = new AtomicBoolean(true);
    AtomicBoolean IndustrialTurbine_background_disable = new AtomicBoolean(true);
    AtomicBoolean ReactorController_background_disable = new AtomicBoolean(true);
    AtomicBoolean ReactorFuel_background_disable = new AtomicBoolean(true);
    AtomicBoolean ReactorHeat_background_disable = new AtomicBoolean(true);
    AtomicBoolean ReactorInfo_background_disable = new AtomicBoolean(true);
    AtomicBoolean ReactorLogicAdapter_background_disable = new AtomicBoolean(true);
    AtomicBoolean ReactorStats_background_disable = new AtomicBoolean(true);
    AtomicBoolean SolarGenerator_background_disable = new AtomicBoolean(true);
    AtomicBoolean TurbineStats_background_disable = new AtomicBoolean(true);
    AtomicBoolean WindGenerator_background_disable = new AtomicBoolean(true);

    public MekanismGeneratorsCompatibility() {
        movementOptions = Arrays.asList(
                new BoolOption("Bio Generator", "BioGenerator_movement", BioGenerator_movement, true),
                new BoolOption("Gas Generator", "GasGenerator_movement", GasGenerator_movement, true),
                new BoolOption("Heat Generator", "HeatGenerator_movement", HeatGenerator_movement, true),
                new BoolOption("Industrial Turbine", "IndustrialTurbine_movement", IndustrialTurbine_movement, true),
                new BoolOption("Reactor Controller", "ReactorController_movement", ReactorController_movement, true),
                new BoolOption("Reactor Fuel", "ReactorFuel_movement", ReactorFuel_movement, true),
                new BoolOption("Reactor Heat", "ReactorHeat_movement", ReactorHeat_movement, true),
                new BoolOption("Reactor Info", "ReactorInfo_movement", ReactorInfo_movement, true),
                new BoolOption("Reactor Logic Adapter", "ReactorLogicAdapter_movement", ReactorLogicAdapter_movement, true),
                new BoolOption("Reactor Stats", "ReactorStats_movement", ReactorStats_movement, true),
                new BoolOption("Solar Generator", "SolarGenerator_movement", SolarGenerator_movement, true),
                new BoolOption("Turbine Stats", "TurbineStats_movement", TurbineStats_movement, true),
                new BoolOption("Wind Generator", "WindGenerator_movement", WindGenerator_movement, true)
        );
        backgroundOptions = Arrays.asList(
                new BoolOption("Bio Generator", "BioGenerator_background_disable", BioGenerator_background_disable, true),
                new BoolOption("Gas Generator", "GasGenerator_background_disable", GasGenerator_background_disable, true),
                new BoolOption("Heat Generator", "HeatGenerator_background_disable", HeatGenerator_background_disable, true),
                new BoolOption("Industrial Turbine", "IndustrialTurbine_background_disable", IndustrialTurbine_background_disable, true),
                new BoolOption("Reactor Controller", "ReactorController_background_disable", ReactorController_background_disable, true),
                new BoolOption("Reactor Fuel", "ReactorFuel_background_disable", ReactorFuel_background_disable, true),
                new BoolOption("Reactor Heat", "ReactorHeat_background_disable", ReactorHeat_background_disable, true),
                new BoolOption("Reactor Info", "ReactorInfo_background_disable", ReactorInfo_background_disable, true),
                new BoolOption("Reactor Logic Adapter", "ReactorLogicAdapter_background_disable", ReactorLogicAdapter_background_disable, true),
                new BoolOption("Reactor Stats", "ReactorStats_background_disable", ReactorStats_background_disable, true),
                new BoolOption("Solar Generator", "SolarGenerator_background_disable", SolarGenerator_background_disable, true),
                new BoolOption("Turbine Stats", "TurbineStats_background_disable", TurbineStats_background_disable, true),
                new BoolOption("Wind Generator", "WindGenerator_background_disable", WindGenerator_background_disable, true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        if (screen instanceof GuiBioGenerator) return Optional.of(BioGenerator_movement.get());
        if (screen instanceof GuiGasGenerator) return Optional.of(GasGenerator_movement.get());
        if (screen instanceof GuiHeatGenerator) return Optional.of(HeatGenerator_movement.get());
        if (screen instanceof GuiIndustrialTurbine) return Optional.of(IndustrialTurbine_movement.get());
        if (screen instanceof GuiFusionReactorController) return Optional.of(ReactorController_movement.get());
        if (screen instanceof GuiFusionReactorFuel) return Optional.of(ReactorFuel_movement.get());
        if (screen instanceof GuiFusionReactorHeat) return Optional.of(ReactorHeat_movement.get());
        if (screen instanceof GuiFusionReactorInfo) return Optional.of(ReactorInfo_movement.get());
        if (screen instanceof GuiFissionReactorLogicAdapter) return Optional.of(ReactorLogicAdapter_movement.get());
        if (screen instanceof GuiFusionReactorLogicAdapter) return Optional.of(ReactorLogicAdapter_movement.get());
        if (screen instanceof GuiFissionReactorStats) return Optional.of(ReactorStats_movement.get());
        if (screen instanceof GuiFusionReactorStats) return Optional.of(ReactorStats_movement.get());
        if (screen instanceof GuiSolarGenerator) return Optional.of(SolarGenerator_movement.get());
        if (screen instanceof GuiTurbineStats) return Optional.of(TurbineStats_movement.get());
        if (screen instanceof GuiWindGenerator) return Optional.of(WindGenerator_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        if (screen instanceof GuiBioGenerator) return Optional.of(BioGenerator_background_disable.get());
        if (screen instanceof GuiGasGenerator) return Optional.of(GasGenerator_background_disable.get());
        if (screen instanceof GuiHeatGenerator) return Optional.of(HeatGenerator_background_disable.get());
        if (screen instanceof GuiIndustrialTurbine) return Optional.of(IndustrialTurbine_background_disable.get());
        if (screen instanceof GuiFusionReactorController) return Optional.of(ReactorController_background_disable.get());
        if (screen instanceof GuiFusionReactorFuel) return Optional.of(ReactorFuel_background_disable.get());
        if (screen instanceof GuiFusionReactorHeat) return Optional.of(ReactorHeat_background_disable.get());
        if (screen instanceof GuiFusionReactorInfo) return Optional.of(ReactorInfo_background_disable.get());
        if (screen instanceof GuiFissionReactorLogicAdapter) return Optional.of(ReactorLogicAdapter_background_disable.get());
        if (screen instanceof GuiFusionReactorLogicAdapter) return Optional.of(ReactorLogicAdapter_background_disable.get());
        if (screen instanceof GuiFissionReactorStats) return Optional.of(ReactorStats_background_disable.get());
        if (screen instanceof GuiFusionReactorStats) return Optional.of(ReactorStats_background_disable.get());
        if (screen instanceof GuiSolarGenerator) return Optional.of(SolarGenerator_background_disable.get());
        if (screen instanceof GuiTurbineStats) return Optional.of(TurbineStats_background_disable.get());
        if (screen instanceof GuiWindGenerator) return Optional.of(WindGenerator_background_disable.get());


        return Optional.empty();
    }
}
