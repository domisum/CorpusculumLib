package de.domisum.lib.corpusculumlib;

import org.bukkit.plugin.java.JavaPlugin;

public class CorpusculumLib extends JavaPlugin
{

	// INITIALIZATION
	@Override
	public void onEnable()
	{
		new CorpusculumListener(this);

		getLogger().info(getClass().getSimpleName()+" has been enabled");
	}

	@Override
	public void onDisable()
	{
		getLogger().info(getClass().getSimpleName()+" has been disabled");
	}

}
