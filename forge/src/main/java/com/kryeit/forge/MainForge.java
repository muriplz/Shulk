package com.kryeit.forge;

import com.kryeit.Main;
import net.minecraftforge.fml.common.Mod;

@Mod(Main.MOD_ID)
public class MainForge {
    public MainForge() {
        Main.init();
    }
}
