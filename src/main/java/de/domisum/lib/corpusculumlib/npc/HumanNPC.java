package de.domisum.lib.corpusculumlib.npc;

import de.domisum.lib.auxilium.util.java.annotations.API;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public interface HumanNPC {

    // EQUIPMENT
    @API
    ItemStack getHelmet();

    @API
    void setHelmet(ItemStack helmet);

    @API
    ItemStack getChestPlate();

    @API
    void setChestPlate(ItemStack chestPlate);

    @API
    ItemStack getLeggings();

    @API
    void setLeggings(ItemStack leggings);

    @API
    ItemStack getBoots();

    @API
    void setBoots(ItemStack boots);

    @API
    ItemStack getItemInMainHand();

    @API
    void setItemInMainHand(ItemStack itemInMainHand);

    @API
    ItemStack getItemInOffHand();

    @API
    void setItemInOffHand(ItemStack itemInOffHand);


    // MOVEMENT
    @API
    Location getLocation();

    @API
    void teleport(Location location);

    @API
    void lookAt(Location location);

    @API
    Vector getVelocity();

    @API
    void setVelocity(Vector velocity);

    @API
    void jump();

    @API
    void swingMainArm();

    @API
    void swingOffArm();

    @API
    void setBlocking(boolean blocking);

    @API
    boolean getBlocking();


    // ACTIONS
    @API
    boolean isSneaking();

    @API
    void setSneaking(boolean sneaking);

    @API
    boolean isSprinting();

    @API
    void setSprinting(boolean sprinting);

    @API
    void showDamage();

    @API
    void setGlowing(boolean glowing);

    @API
    boolean getGlowing();

    @API
    void setOnFire(boolean onFire);

    //OTHER
    @API
    void setNumberOfArrowsInBody(int arrows);

    @API
    int getNumberOfArrowsInBody();

    @API
    void setDisplayName(String displayName);

    @API
    String getDisplayName();

    @API
    void showToPlayer(Player player);

    @API
    void hideFromPlayer(Player player);


}
