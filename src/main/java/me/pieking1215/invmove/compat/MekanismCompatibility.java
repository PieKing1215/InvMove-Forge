package me.pieking1215.invmove.compat;

import mekanism.client.gui.GuiBoilerStats;
import mekanism.client.gui.GuiChemicalTank;
import mekanism.client.gui.element.custom.GuiSideConfiguration;
import mekanism.client.gui.element.custom.GuiTransporterConfig;
import mekanism.client.gui.element.filter.GuiFilter;
import mekanism.client.gui.item.GuiDictionary;
import mekanism.client.gui.item.GuiPersonalChestItem;
import mekanism.client.gui.item.GuiPortableTeleporter;
import mekanism.client.gui.item.GuiSeismicReader;
import mekanism.client.gui.machine.GuiChemicalCrystallizer;
import mekanism.client.gui.machine.GuiChemicalDissolutionChamber;
import mekanism.client.gui.machine.GuiChemicalInfuser;
import mekanism.client.gui.machine.GuiChemicalOxidizer;
import mekanism.client.gui.machine.GuiChemicalWasher;
import mekanism.client.gui.machine.GuiCombiner;
import mekanism.client.gui.machine.GuiDigitalMiner;
import mekanism.client.gui.machine.GuiDigitalMinerConfig;
import mekanism.client.gui.GuiDynamicTank;
import mekanism.client.gui.machine.GuiElectricMachine;
import mekanism.client.gui.machine.GuiElectricPump;
import mekanism.client.gui.machine.GuiElectrolyticSeparator;
import mekanism.client.gui.GuiEnergyCube;
import mekanism.client.gui.machine.GuiFactory;
import mekanism.client.gui.GuiFilterHolder;
import mekanism.client.gui.GuiFluidTank;
import mekanism.client.gui.machine.GuiFluidicPlenisher;
import mekanism.client.gui.machine.GuiFormulaicAssemblicator;
import mekanism.client.gui.machine.GuiFuelwoodHeater;
import mekanism.client.gui.GuiInductionMatrix;
import mekanism.client.gui.GuiLaserAmplifier;
import mekanism.client.gui.GuiLaserTractorBeam;
import mekanism.client.gui.GuiLogisticalSorter;
import mekanism.client.gui.GuiMatrixStats;
import mekanism.client.gui.machine.GuiMetallurgicInfuser;
import mekanism.client.gui.machine.GuiOredictionificator;
import mekanism.client.gui.machine.GuiPRC;
import mekanism.client.gui.GuiPersonalChestTile;
import mekanism.client.gui.machine.GuiPrecisionSawmill;
import mekanism.client.gui.GuiQuantumEntangloporter;
import mekanism.client.gui.machine.GuiResistiveHeater;
import mekanism.client.gui.machine.GuiRotaryCondensentrator;
import mekanism.client.gui.GuiSecurityDesk;
import mekanism.client.gui.machine.GuiSeismicVibrator;
import mekanism.client.gui.machine.GuiSolarNeutronActivator;
import mekanism.client.gui.GuiTeleporter;
import mekanism.client.gui.GuiThermalEvaporationController;
import mekanism.client.gui.GuiThermoelectricBoiler;
import mekanism.client.gui.GuiUpgradeManagement;
import mekanism.client.gui.robit.GuiRobit;
import mekanism.client.gui.robit.GuiRobitMain;
import net.minecraft.client.gui.screen.Screen;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class MekanismCompatibility extends ModCompatibility {
    AtomicBoolean Filters_movement = new AtomicBoolean(true);
    AtomicBoolean Robit_movement = new AtomicBoolean(true);
    AtomicBoolean Boiler_movement = new AtomicBoolean(true);
    AtomicBoolean ChemicalCrystallizer_movement = new AtomicBoolean(true);
    AtomicBoolean ChemicalDissolutionChamber_movement = new AtomicBoolean(true);
    AtomicBoolean ChemicalInfuser_movement = new AtomicBoolean(true);
    AtomicBoolean ChemicalOxidizer_movement = new AtomicBoolean(true);
    AtomicBoolean ChemicalWasher_movement = new AtomicBoolean(true);
    AtomicBoolean Combiner_movement = new AtomicBoolean(true);
    AtomicBoolean Dictionary_movement = new AtomicBoolean(true);
    AtomicBoolean DigitalMiner_movement = new AtomicBoolean(true);
    AtomicBoolean DigitalMinerConfig_movement = new AtomicBoolean(true);
    AtomicBoolean DynamicTank_movement = new AtomicBoolean(true);
    AtomicBoolean ElectricMachine_movement = new AtomicBoolean(true);
    AtomicBoolean ElectricPump_movement = new AtomicBoolean(true);
    AtomicBoolean ElectrolyticSeparator_movement = new AtomicBoolean(true);
    AtomicBoolean EnergyCube_movement = new AtomicBoolean(true);
    AtomicBoolean Factory_movement = new AtomicBoolean(true);
    AtomicBoolean FilterHolder_movement = new AtomicBoolean(true);
    AtomicBoolean FluidicPlenisher_movement = new AtomicBoolean(true);
    AtomicBoolean FluidTank_movement = new AtomicBoolean(true);
    AtomicBoolean FormulaicAssembicator_movement = new AtomicBoolean(true);
    AtomicBoolean FuelwoodHeater_movement = new AtomicBoolean(true);
    AtomicBoolean GasTank_movement = new AtomicBoolean(true);
    AtomicBoolean InductionMatrix_movement = new AtomicBoolean(true);
    AtomicBoolean LaserAmplifier_movement = new AtomicBoolean(true);
    AtomicBoolean LaserTractorBeam_movement = new AtomicBoolean(true);
    AtomicBoolean LogisticalSorter_movement = new AtomicBoolean(true);
    AtomicBoolean MatrixStats_movement = new AtomicBoolean(true);
    AtomicBoolean MetallurgicInfuser_movement = new AtomicBoolean(true);
    AtomicBoolean Oredictionificator_movement = new AtomicBoolean(true);
    AtomicBoolean PersonalChest_movement = new AtomicBoolean(true);
    AtomicBoolean PortableTeleporter_movement = new AtomicBoolean(true);
    AtomicBoolean PressurizedReactionChamber_movement = new AtomicBoolean(true);
    AtomicBoolean PrecisionSawmill_movement = new AtomicBoolean(true);
    AtomicBoolean QuantumEntangloporter_movement = new AtomicBoolean(true);
    AtomicBoolean ResistiveHeater_movement = new AtomicBoolean(true);
    AtomicBoolean RotaryCondensentrator_movement = new AtomicBoolean(true);
    AtomicBoolean SecurityDesk_movement = new AtomicBoolean(true);
    AtomicBoolean SeismicReader_movement = new AtomicBoolean(true);
    AtomicBoolean SeismicVibrator_movement = new AtomicBoolean(true);
    AtomicBoolean SideConfiguration_movement = new AtomicBoolean(true);
    AtomicBoolean SolarNeutronActivator_movement = new AtomicBoolean(true);
    AtomicBoolean Teleporter_movement = new AtomicBoolean(true);
    AtomicBoolean ThermalEvaporationController_movement = new AtomicBoolean(true);
    AtomicBoolean ThermoelectricBoiler_movement = new AtomicBoolean(true);
    AtomicBoolean TransporterConfig_movement = new AtomicBoolean(true);
    AtomicBoolean UpgradeManagement_movement = new AtomicBoolean(true);

    AtomicBoolean Filters_background_disable = new AtomicBoolean(true);
    AtomicBoolean Robit_background_disable = new AtomicBoolean(true);
    AtomicBoolean Boiler_background_disable = new AtomicBoolean(true);
    AtomicBoolean ChemicalCrystallizer_background_disable = new AtomicBoolean(true);
    AtomicBoolean ChemicalDissolutionChamber_background_disable = new AtomicBoolean(true);
    AtomicBoolean ChemicalInfuser_background_disable = new AtomicBoolean(true);
    AtomicBoolean ChemicalOxidizer_background_disable = new AtomicBoolean(true);
    AtomicBoolean ChemicalWasher_background_disable = new AtomicBoolean(true);
    AtomicBoolean Combiner_background_disable = new AtomicBoolean(true);
    AtomicBoolean Dictionary_background_disable = new AtomicBoolean(true);
    AtomicBoolean DigitalMiner_background_disable = new AtomicBoolean(true);
    AtomicBoolean DigitalMinerConfig_background_disable = new AtomicBoolean(true);
    AtomicBoolean DynamicTank_background_disable = new AtomicBoolean(true);
    AtomicBoolean ElectricMachine_background_disable = new AtomicBoolean(true);
    AtomicBoolean ElectricPump_background_disable = new AtomicBoolean(true);
    AtomicBoolean ElectrolyticSeparator_background_disable = new AtomicBoolean(true);
    AtomicBoolean EnergyCube_background_disable = new AtomicBoolean(true);
    AtomicBoolean Factory_background_disable = new AtomicBoolean(true);
    AtomicBoolean FilterHolder_background_disable = new AtomicBoolean(true);
    AtomicBoolean FluidicPlenisher_background_disable = new AtomicBoolean(true);
    AtomicBoolean FluidTank_background_disable = new AtomicBoolean(true);
    AtomicBoolean FormulaicAssembicator_background_disable = new AtomicBoolean(true);
    AtomicBoolean FuelwoodHeater_background_disable = new AtomicBoolean(true);
    AtomicBoolean GasTank_background_disable = new AtomicBoolean(true);
    AtomicBoolean InductionMatrix_background_disable = new AtomicBoolean(true);
    AtomicBoolean LaserAmplifier_background_disable = new AtomicBoolean(true);
    AtomicBoolean LaserTractorBeam_background_disable = new AtomicBoolean(true);
    AtomicBoolean LogisticalSorter_background_disable = new AtomicBoolean(true);
    AtomicBoolean MatrixStats_background_disable = new AtomicBoolean(true);
    AtomicBoolean MetallurgicInfuser_background_disable = new AtomicBoolean(true);
    AtomicBoolean Oredictionificator_background_disable = new AtomicBoolean(true);
    AtomicBoolean PersonalChest_background_disable = new AtomicBoolean(true);
    AtomicBoolean PortableTeleporter_background_disable = new AtomicBoolean(true);
    AtomicBoolean PressurizedReactionChamber_background_disable = new AtomicBoolean(true);
    AtomicBoolean PrecisionSawmill_background_disable = new AtomicBoolean(true);
    AtomicBoolean QuantumEntangloporter_background_disable = new AtomicBoolean(true);
    AtomicBoolean ResistiveHeater_background_disable = new AtomicBoolean(true);
    AtomicBoolean RotaryCondensentrator_background_disable = new AtomicBoolean(true);
    AtomicBoolean SecurityDesk_background_disable = new AtomicBoolean(true);
    AtomicBoolean SeismicReader_background_disable = new AtomicBoolean(true);
    AtomicBoolean SeismicVibrator_background_disable = new AtomicBoolean(true);
    AtomicBoolean SideConfiguration_background_disable = new AtomicBoolean(true);
    AtomicBoolean SolarNeutronActivator_background_disable = new AtomicBoolean(true);
    AtomicBoolean Teleporter_background_disable = new AtomicBoolean(true);
    AtomicBoolean ThermalEvaporationController_background_disable = new AtomicBoolean(true);
    AtomicBoolean ThermoelectricBoiler_background_disable = new AtomicBoolean(true);
    AtomicBoolean TransporterConfig_background_disable = new AtomicBoolean(true);
    AtomicBoolean UpgradeManagement_background_disable = new AtomicBoolean(true);

    public MekanismCompatibility() {
        movementOptions = Arrays.asList(
                //new BoolOption("Filters", "Filters_movement", Filters_movement, true),
                new BoolOption("Robit", "Robit_movement", Robit_movement, true),
                new BoolOption("Boiler", "Boiler_movement", Boiler_movement, true),
                new BoolOption("Chemical Crystallizer", "ChemicalCrystallizer_movement", ChemicalCrystallizer_movement, true),
                new BoolOption("Chemical DissolutionChamber", "ChemicalDissolutionChamber_movement", ChemicalDissolutionChamber_movement, true),
                new BoolOption("Chemical Infuser", "ChemicalInfuser_movement", ChemicalInfuser_movement, true),
                new BoolOption("Chemical Oxidizer", "ChemicalOxidizer_movement", ChemicalOxidizer_movement, true),
                new BoolOption("Chemical Washer", "ChemicalWasher_movement", ChemicalWasher_movement, true),
                new BoolOption("Combiner", "Combiner_movement", Combiner_movement, true),
                new BoolOption("Dictionary", "Dictionary_movement", Dictionary_movement, true),
                new BoolOption("Digital Miner", "DigitalMiner_movement", DigitalMiner_movement, true),
                new BoolOption("Digital Miner Config", "DigitalMinerConfig_movement", DigitalMinerConfig_movement, true),
                new BoolOption("Dynamic Tank", "DynamicTank_movement", DynamicTank_movement, true),
                new BoolOption("Energized Smelter", "ElectricMachine_movement", ElectricMachine_movement, true),
                new BoolOption("Electric Pump", "ElectricPump_movement", ElectricPump_movement, true),
                new BoolOption("Electrolytic Separator", "ElectrolyticSeparator_movement", ElectrolyticSeparator_movement, true),
                new BoolOption("Energy Cube", "EnergyCube_movement", EnergyCube_movement, true),
                new BoolOption("Factory", "Factory_movement", Factory_movement, true),
                new BoolOption("Filter Holder", "FilterHolder_movement", FilterHolder_movement, true),
                new BoolOption("Fluidic Plenisher", "FluidicPlenisher_movement", FluidicPlenisher_movement, true),
                new BoolOption("Fluid Tank", "FluidTank_movement", FluidTank_movement, true),
                new BoolOption("Formulaic Assembicator", "FormulaicAssembicator_movement", FormulaicAssembicator_movement, true),
                new BoolOption("Fuelwood Heater", "FuelwoodHeater_movement", FuelwoodHeater_movement, true),
                new BoolOption("Chemical Tank", "GasTank_movement", GasTank_movement, true),
                new BoolOption("Induction Matrix", "InductionMatrix_movement", InductionMatrix_movement, true),
                new BoolOption("Laser Amplifier", "LaserAmplifier_movement", LaserAmplifier_movement, true),
                new BoolOption("Laser Tractor Beam", "LaserTractorBeam_movement", LaserTractorBeam_movement, true),
                new BoolOption("Logistical Sorter", "LogisticalSorter_movement", LogisticalSorter_movement, true),
                new BoolOption("Matrix Stats", "MatrixStats_movement", MatrixStats_movement, true),
                new BoolOption("Metallurgic Infuser", "MetallurgicInfuser_movement", MetallurgicInfuser_movement, true),
                new BoolOption("Oredictionificator", "Oredictionificator_movement", Oredictionificator_movement, true),
                new BoolOption("Personal Chest", "PersonalChest_movement", PersonalChest_movement, true),
                new BoolOption("Portable Teleporter", "PortableTeleporter_movement", PortableTeleporter_movement, true),
                new BoolOption("Pressurized Reaction Chamber", "PressurizedReactionChamber_movement", PressurizedReactionChamber_movement, true),
                new BoolOption("Precision Sawmill", "PrecisionSawmill_movement", PrecisionSawmill_movement, true),
                new BoolOption("Quantum Entangloporter", "QuantumEntangloporter_movement", QuantumEntangloporter_movement, true),
                new BoolOption("Resistive Heater", "ResistiveHeater_movement", ResistiveHeater_movement, true),
                new BoolOption("Rotary Condensentrator", "RotaryCondensentrator_movement", RotaryCondensentrator_movement, true),
                new BoolOption("Security Desk", "SecurityDesk_movement", SecurityDesk_movement, true),
                new BoolOption("Seismic Reader", "SeismicReader_movement", SeismicReader_movement, true),
                new BoolOption("Seismic Vibrator", "SeismicVibrator_movement", SeismicVibrator_movement, true),
                //new BoolOption("Side Configuration", "SideConfiguration_movement", SideConfiguration_movement, true),
                new BoolOption("Solar Neutron Activator", "SolarNeutronActivator_movement", SolarNeutronActivator_movement, true),
                new BoolOption("Teleporter", "Teleporter_movement", Teleporter_movement, true),
                new BoolOption("Thermal Evaporation Controller", "ThermalEvaporationController_movement", ThermalEvaporationController_movement, true),
                new BoolOption("Thermoelectric Boiler", "ThermoelectricBoiler_movement", ThermoelectricBoiler_movement, true),
                //new BoolOption("Transporter Config", "TransporterConfig_movement", TransporterConfig_movement, true),
                new BoolOption("Upgrade Management", "UpgradeManagement_movement", UpgradeManagement_movement, true)
        );
        backgroundOptions = Arrays.asList(
                //new BoolOption("Filters", "Filters_background_disable", Filters_background_disable, true),
                new BoolOption("Robit", "Robit_background_disable", Robit_background_disable, true),
                new BoolOption("Boiler", "Boiler_background_disable", Boiler_background_disable, true),
                new BoolOption("Chemical Crystallizer", "ChemicalCrystallizer_background_disable", ChemicalCrystallizer_background_disable, true),
                new BoolOption("Chemical Dissolution Chamber", "ChemicalDissolutionChamber_background_disable", ChemicalDissolutionChamber_background_disable, true),
                new BoolOption("Chemical Infuser", "ChemicalInfuser_background_disable", ChemicalInfuser_background_disable, true),
                new BoolOption("Chemical Oxidizer", "ChemicalOxidizer_background_disable", ChemicalOxidizer_background_disable, true),
                new BoolOption("Chemical Washer", "ChemicalWasher_background_disable", ChemicalWasher_background_disable, true),
                new BoolOption("Combiner", "Combiner_background_disable", Combiner_background_disable, true),
                new BoolOption("Dictionary", "Dictionary_background_disable", Dictionary_background_disable, true),
                new BoolOption("Digital Miner", "DigitalMiner_background_disable", DigitalMiner_background_disable, true),
                new BoolOption("Digital Miner Config", "DigitalMinerConfig_background_disable", DigitalMinerConfig_background_disable, true),
                new BoolOption("Dynamic Tank", "DynamicTank_background_disable", DynamicTank_background_disable, true),
                new BoolOption("Energized Smelter", "ElectricMachine_background_disable", ElectricMachine_background_disable, true),
                new BoolOption("Electric Pump", "ElectricPump_background_disable", ElectricPump_background_disable, true),
                new BoolOption("Electrolytic Separator", "ElectrolyticSeparator_background_disable", ElectrolyticSeparator_background_disable, true),
                new BoolOption("Energy Cube", "EnergyCube_background_disable", EnergyCube_background_disable, true),
                new BoolOption("Factory", "Factory_background_disable", Factory_background_disable, true),
                new BoolOption("Filter Holder", "FilterHolder_background_disable", FilterHolder_background_disable, true),
                new BoolOption("Fluidic Plenisher", "FluidicPlenisher_background_disable", FluidicPlenisher_background_disable, true),
                new BoolOption("Fluid Tank", "FluidTank_background_disable", FluidTank_background_disable, true),
                new BoolOption("Formulaic Assembicator", "FormulaicAssembicator_background_disable", FormulaicAssembicator_background_disable, true),
                new BoolOption("Fuelwood Heater", "FuelwoodHeater_background_disable", FuelwoodHeater_background_disable, true),
                new BoolOption("Chemical Tank", "GasTank_background_disable", GasTank_background_disable, true),
                new BoolOption("Induction Matrix", "InductionMatrix_background_disable", InductionMatrix_background_disable, true),
                new BoolOption("Laser Amplifier", "LaserAmplifier_background_disable", LaserAmplifier_background_disable, true),
                new BoolOption("Laser Tractor Beam", "LaserTractorBeam_background_disable", LaserTractorBeam_background_disable, true),
                new BoolOption("Logistical Sorter", "LogisticalSorter_background_disable", LogisticalSorter_background_disable, true),
                new BoolOption("Matrix Stats", "MatrixStats_background_disable", MatrixStats_background_disable, true),
                new BoolOption("Metallurgic Infuser", "MetallurgicInfuser_background_disable", MetallurgicInfuser_background_disable, true),
                new BoolOption("Oredictionificator", "Oredictionificator_background_disable", Oredictionificator_background_disable, true),
                new BoolOption("Personal Chest", "PersonalChest_background_disable", PersonalChest_background_disable, true),
                new BoolOption("Portable Teleporter", "PortableTeleporter_background_disable", PortableTeleporter_background_disable, true),
                new BoolOption("Pressurized Reaction Chamber", "PressurizedReactionChamber_background_disable", PressurizedReactionChamber_background_disable, true),
                new BoolOption("Precision Sawmill", "PrecisionSawmill_background_disable", PrecisionSawmill_background_disable, true),
                new BoolOption("Quantum Entangloporter", "QuantumEntangloporter_background_disable", QuantumEntangloporter_background_disable, true),
                new BoolOption("Resistive Heater", "ResistiveHeater_background_disable", ResistiveHeater_background_disable, true),
                new BoolOption("Rotary Condensentrator", "RotaryCondensentrator_background_disable", RotaryCondensentrator_background_disable, true),
                new BoolOption("Security Desk", "SecurityDesk_background_disable", SecurityDesk_background_disable, true),
                new BoolOption("Seismic Reader", "SeismicReader_background_disable", SeismicReader_background_disable, true),
                new BoolOption("Seismic Vibrator", "SeismicVibrator_background_disable", SeismicVibrator_background_disable, true),
                //new BoolOption("Side Configuration", "SideConfiguration_background_disable", SideConfiguration_background_disable, true),
                new BoolOption("Solar Neutron Activator", "SolarNeutronActivator_background_disable", SolarNeutronActivator_background_disable, true),
                new BoolOption("Teleporter", "Teleporter_background_disable", Teleporter_background_disable, true),
                new BoolOption("Thermal Evaporation Controller", "ThermalEvaporationController_background_disable", ThermalEvaporationController_background_disable, true),
                new BoolOption("Thermoelectric Boiler", "ThermoelectricBoiler_background_disable", ThermoelectricBoiler_background_disable, true),
                //new BoolOption("Transporter Config", "TransporterConfig_background_disable", TransporterConfig_background_disable, true),
                new BoolOption("Upgrade Management", "UpgradeManagement_background_disable", UpgradeManagement_background_disable, true)
        );
    }

    @Override
    public Optional<Boolean> shouldAllowMovement(Screen screen) {

        // TODO: mekanism refactored all of the GuiFilters to be popup windows and not their own GUI.
        //         I should be able to handle this
        //if (screen instanceof GuiFilter) return Optional.of(Filters_movement.get());
        if (screen instanceof GuiRobit) return Optional.of(Robit_movement.get());
        if (screen instanceof GuiRobitMain) return Optional.of(Robit_movement.get());
        if (screen instanceof GuiBoilerStats) return Optional.of(Boiler_movement.get());
        if (screen instanceof GuiChemicalCrystallizer) return Optional.of(ChemicalCrystallizer_movement.get());
        if (screen instanceof GuiChemicalDissolutionChamber) return Optional.of(ChemicalDissolutionChamber_movement.get());
        if (screen instanceof GuiChemicalInfuser) return Optional.of(ChemicalInfuser_movement.get());
        if (screen instanceof GuiChemicalOxidizer) return Optional.of(ChemicalOxidizer_movement.get());
        if (screen instanceof GuiChemicalWasher) return Optional.of(ChemicalWasher_movement.get());
        if (screen instanceof GuiCombiner) return Optional.of(Combiner_movement.get());
        if (screen instanceof GuiDictionary) return Optional.of(Dictionary_movement.get());
        if (screen instanceof GuiDigitalMiner) return Optional.of(DigitalMiner_movement.get());
        if (screen instanceof GuiDigitalMinerConfig) return Optional.of(DigitalMinerConfig_movement.get());
        if (screen instanceof GuiDynamicTank) return Optional.of(DynamicTank_movement.get());
        if (screen instanceof GuiElectricMachine) return Optional.of(ElectricMachine_movement.get());
        if (screen instanceof GuiElectricPump) return Optional.of(ElectricPump_movement.get());
        if (screen instanceof GuiElectrolyticSeparator) return Optional.of(ElectrolyticSeparator_movement.get());
        if (screen instanceof GuiEnergyCube) return Optional.of(EnergyCube_movement.get());
        if (screen instanceof GuiFactory) return Optional.of(Factory_movement.get());
        if (screen instanceof GuiLogisticalSorter) return Optional.of(LogisticalSorter_movement.get());
        if (screen instanceof GuiFilterHolder) return Optional.of(FilterHolder_movement.get());
        if (screen instanceof GuiFluidicPlenisher) return Optional.of(FluidicPlenisher_movement.get());
        if (screen instanceof GuiFluidTank) return Optional.of(FluidTank_movement.get());
        if (screen instanceof GuiFormulaicAssemblicator) return Optional.of(FormulaicAssembicator_movement.get());
        if (screen instanceof GuiFuelwoodHeater) return Optional.of(FuelwoodHeater_movement.get());
        if (screen instanceof GuiChemicalTank) return Optional.of(GasTank_movement.get());
        if (screen instanceof GuiInductionMatrix) return Optional.of(InductionMatrix_movement.get());
        if (screen instanceof GuiLaserAmplifier) return Optional.of(LaserAmplifier_movement.get());
        if (screen instanceof GuiLaserTractorBeam) return Optional.of(LaserTractorBeam_movement.get());
        if (screen instanceof GuiMatrixStats) return Optional.of(MatrixStats_movement.get());
        if (screen instanceof GuiMetallurgicInfuser) return Optional.of(MetallurgicInfuser_movement.get());
        if (screen instanceof GuiOredictionificator) return Optional.of(Oredictionificator_movement.get());
        if (screen instanceof GuiPersonalChestItem) return Optional.of(PersonalChest_movement.get());
        if (screen instanceof GuiPersonalChestTile) return Optional.of(PersonalChest_movement.get());
        if (screen instanceof GuiPortableTeleporter) return Optional.of(PortableTeleporter_movement.get());
        if (screen instanceof GuiPRC) return Optional.of(PressurizedReactionChamber_movement.get());
        if (screen instanceof GuiPrecisionSawmill) return Optional.of(PrecisionSawmill_movement.get());
        if (screen instanceof GuiQuantumEntangloporter) return Optional.of(QuantumEntangloporter_movement.get());
        if (screen instanceof GuiResistiveHeater) return Optional.of(ResistiveHeater_movement.get());
        if (screen instanceof GuiRotaryCondensentrator) return Optional.of(RotaryCondensentrator_movement.get());
        if (screen instanceof GuiSecurityDesk) return Optional.of(SecurityDesk_movement.get());
        if (screen instanceof GuiSeismicReader) return Optional.of(SeismicReader_movement.get());
        if (screen instanceof GuiSeismicVibrator) return Optional.of(SeismicVibrator_movement.get());
        //if (screen instanceof GuiSideConfiguration) return Optional.of(SideConfiguration_movement.get());
        if (screen instanceof GuiSolarNeutronActivator) return Optional.of(SolarNeutronActivator_movement.get());
        if (screen instanceof GuiTeleporter) return Optional.of(Teleporter_movement.get());
        if (screen instanceof GuiThermalEvaporationController) return Optional.of(ThermalEvaporationController_movement.get());
        if (screen instanceof GuiThermoelectricBoiler) return Optional.of(ThermoelectricBoiler_movement.get());
        //if (screen instanceof GuiTransporterConfig) return Optional.of(TransporterConfig_movement.get());
        if (screen instanceof GuiUpgradeManagement) return Optional.of(UpgradeManagement_movement.get());

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> shouldDisableBackground(Screen screen) {

        //if (screen instanceof GuiFilter) return Optional.of(Filters_background_disable.get());
        if (screen instanceof GuiRobit) return Optional.of(Robit_background_disable.get());
        if (screen instanceof GuiRobitMain) return Optional.of(Robit_background_disable.get());
        if (screen instanceof GuiBoilerStats) return Optional.of(Boiler_background_disable.get());
        if (screen instanceof GuiChemicalCrystallizer) return Optional.of(ChemicalCrystallizer_background_disable.get());
        if (screen instanceof GuiChemicalDissolutionChamber) return Optional.of(ChemicalDissolutionChamber_background_disable.get());
        if (screen instanceof GuiChemicalInfuser) return Optional.of(ChemicalInfuser_background_disable.get());
        if (screen instanceof GuiChemicalOxidizer) return Optional.of(ChemicalOxidizer_background_disable.get());
        if (screen instanceof GuiChemicalWasher) return Optional.of(ChemicalWasher_background_disable.get());
        if (screen instanceof GuiCombiner) return Optional.of(Combiner_background_disable.get());
        if (screen instanceof GuiDictionary) return Optional.of(Dictionary_background_disable.get());
        if (screen instanceof GuiDigitalMiner) return Optional.of(DigitalMiner_background_disable.get());
        if (screen instanceof GuiDigitalMinerConfig) return Optional.of(DigitalMinerConfig_background_disable.get());
        if (screen instanceof GuiDynamicTank) return Optional.of(DynamicTank_background_disable.get());
        if (screen instanceof GuiElectricMachine) return Optional.of(ElectricMachine_background_disable.get());
        if (screen instanceof GuiElectricPump) return Optional.of(ElectricPump_background_disable.get());
        if (screen instanceof GuiElectrolyticSeparator) return Optional.of(ElectrolyticSeparator_background_disable.get());
        if (screen instanceof GuiEnergyCube) return Optional.of(EnergyCube_background_disable.get());
        if (screen instanceof GuiFactory) return Optional.of(Factory_background_disable.get());
        if (screen instanceof GuiLogisticalSorter) return Optional.of(LogisticalSorter_background_disable.get());
        if (screen instanceof GuiFilterHolder) return Optional.of(FilterHolder_background_disable.get());
        if (screen instanceof GuiFluidicPlenisher) return Optional.of(FluidicPlenisher_background_disable.get());
        if (screen instanceof GuiFluidTank) return Optional.of(FluidTank_background_disable.get());
        if (screen instanceof GuiFormulaicAssemblicator) return Optional.of(FormulaicAssembicator_background_disable.get());
        if (screen instanceof GuiFuelwoodHeater) return Optional.of(FuelwoodHeater_background_disable.get());
        if (screen instanceof GuiChemicalTank) return Optional.of(GasTank_background_disable.get());
        if (screen instanceof GuiInductionMatrix) return Optional.of(InductionMatrix_background_disable.get());
        if (screen instanceof GuiLaserAmplifier) return Optional.of(LaserAmplifier_background_disable.get());
        if (screen instanceof GuiLaserTractorBeam) return Optional.of(LaserTractorBeam_background_disable.get());
        if (screen instanceof GuiMatrixStats) return Optional.of(MatrixStats_background_disable.get());
        if (screen instanceof GuiMetallurgicInfuser) return Optional.of(MetallurgicInfuser_background_disable.get());
        if (screen instanceof GuiOredictionificator) return Optional.of(Oredictionificator_background_disable.get());
        if (screen instanceof GuiPersonalChestItem) return Optional.of(PersonalChest_background_disable.get());
        if (screen instanceof GuiPersonalChestTile) return Optional.of(PersonalChest_background_disable.get());
        if (screen instanceof GuiPortableTeleporter) return Optional.of(PortableTeleporter_background_disable.get());
        if (screen instanceof GuiPRC) return Optional.of(PressurizedReactionChamber_background_disable.get());
        if (screen instanceof GuiPrecisionSawmill) return Optional.of(PrecisionSawmill_background_disable.get());
        if (screen instanceof GuiQuantumEntangloporter) return Optional.of(QuantumEntangloporter_background_disable.get());
        if (screen instanceof GuiResistiveHeater) return Optional.of(ResistiveHeater_background_disable.get());
        if (screen instanceof GuiRotaryCondensentrator) return Optional.of(RotaryCondensentrator_background_disable.get());
        if (screen instanceof GuiSecurityDesk) return Optional.of(SecurityDesk_background_disable.get());
        if (screen instanceof GuiSeismicReader) return Optional.of(SeismicReader_background_disable.get());
        if (screen instanceof GuiSeismicVibrator) return Optional.of(SeismicVibrator_background_disable.get());
        //if (screen instanceof GuiSideConfiguration) return Optional.of(SideConfiguration_background_disable.get());
        if (screen instanceof GuiSolarNeutronActivator) return Optional.of(SolarNeutronActivator_background_disable.get());
        if (screen instanceof GuiTeleporter) return Optional.of(Teleporter_background_disable.get());
        if (screen instanceof GuiThermalEvaporationController) return Optional.of(ThermalEvaporationController_background_disable.get());
        if (screen instanceof GuiThermoelectricBoiler) return Optional.of(ThermoelectricBoiler_background_disable.get());
        //if (screen instanceof GuiTransporterConfig) return Optional.of(TransporterConfig_background_disable.get());
        if (screen instanceof GuiUpgradeManagement) return Optional.of(UpgradeManagement_background_disable.get());

        return Optional.empty();
    }
}
