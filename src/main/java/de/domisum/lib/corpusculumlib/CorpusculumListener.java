package de.domisum.lib.corpusculumlib;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.trait.trait.Equipment.EquipmentSlot;
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
		if(e.getAction() == Action.PHYSICAL)
			return;

		if(e.getPlayer().getInventory().getItemInMainHand().getType() != Material.GOLDEN_SWORD)
			return;

		NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Notch");
		npc.spawn(e.getPlayer().getLocation());
		npc.setProtected(false);

		Equipment equipment = npc.getTrait(Equipment.class);
		equipment.set(EquipmentSlot.HELMET, new ItemStack(Material.DIAMOND_HELMET));
		equipment.set(EquipmentSlot.HAND, new ItemStack(Material.TORCH));
	}

}
