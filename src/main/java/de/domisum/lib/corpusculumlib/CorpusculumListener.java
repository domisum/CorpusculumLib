package de.domisum.lib.corpusculumlib;

import de.domisum.lib.auxiliumspigot.util.DebugUtil;
import de.domisum.lib.corpusculumlib.npc.HumanNPC;
import de.domisum.lib.corpusculumlib.npc.HumanNPCUsingCitizens;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CorpusculumListener implements Listener
{

	private final CorpusculumLib corpusculumLib;


	// INIT
	public CorpusculumListener(CorpusculumLib corpusculumLib)
	{
		this.corpusculumLib = corpusculumLib;
		register(corpusculumLib);
	}

	private void register(CorpusculumLib corpusculumLib)
	{
		corpusculumLib.getServer().getPluginManager().registerEvents(this, corpusculumLib);
	}


	// EVENTS
	@EventHandler
	public void test(PlayerInteractEvent e)
	{
		if (e.getAction() == Action.PHYSICAL)
			return;

		if (e.getPlayer().getInventory().getItemInMainHand().getType() != Material.GOLDEN_SWORD)
			return;

		NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Notch");
		npc.spawn(e.getPlayer().getLocation());


		HumanNPC humanNpc = new HumanNPCUsingCitizens(npc);
		DebugUtil.say("boots: " + humanNpc.getBoots());
		humanNpc.setItemInOffHand(new ItemStack(Material.GOLD_INGOT));
		humanNpc.setBoots(new ItemStack(Material.DIAMOND_BOOTS));

		corpusculumLib.getServer().getScheduler().runTaskLater(corpusculumLib, () ->
		{
			humanNpc.swingMainArm();
			humanNpc.jump();
			humanNpc.lookAt(e.getPlayer().getLocation());
			humanNpc.showDamage();
		}, 3 * 20L);


		corpusculumLib.getServer().getScheduler().runTaskLater(corpusculumLib, npc::destroy, 10 * 20L);
	}

}
