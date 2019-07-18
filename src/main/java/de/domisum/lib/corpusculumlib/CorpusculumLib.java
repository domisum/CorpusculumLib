package de.domisum.lib.corpusculumlib;

import de.domisum.lib.auxiliumspigot.AuxiliumSpigotLib;
import org.bukkit.plugin.java.JavaPlugin;

public class CorpusculumLib extends JavaPlugin {

    // INITIALIZATION
    @Override
    public void onEnable() {
        new CorpusculumListener(this);

        AuxiliumSpigotLib.enable(this);

        getLogger().info(getClass().getSimpleName() + " has been enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info(getClass().getSimpleName() + " has been disabled");
    }

}
