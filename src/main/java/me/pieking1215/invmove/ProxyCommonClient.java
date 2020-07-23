package me.pieking1215.invmove;

import me.pieking1215.invmove.compat.Compatibility;
import net.minecraftforge.common.MinecraftForge;

public class ProxyCommonClient extends ProxyCommon {

    @Override
    public void init(InvMove invMove) {

        MinecraftForge.EVENT_BUS.register(invMove);
        Compatibility.loadCompatibility();
        Config.doneLoading();
        Compatibility.setupConfigScreen();
    }
}
