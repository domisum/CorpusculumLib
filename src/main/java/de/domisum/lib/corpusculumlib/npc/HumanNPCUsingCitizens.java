package de.domisum.lib.corpusculumlib.npc;

import de.domisum.lib.auxiliumspigot.util.ReflectionUtil;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.trait.trait.Equipment.EquipmentSlot;
import net.citizensnpcs.util.PlayerAnimation;
import net.minecraft.server.v1_13_R2.Packet;
import net.minecraft.server.v1_13_R2.PacketPlayOutAnimation;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class HumanNPCUsingCitizens implements HumanNPC {

    // CONSTANTS
    private static final double VIEW_DISTANCE = 64;

    // REFERENCES
    private final NPC citizensNpc;


    // INIT
    public HumanNPCUsingCitizens(NPC citizensNpc) {
        Validate.notNull(citizensNpc, "citizensNpc can't be null");
        Validate.isTrue(citizensNpc.isSpawned(), "citizensNps has to be spawned");

        this.citizensNpc = citizensNpc;


        citizensNpc.setProtected(false);
    }


    // EQUIPMENT
    @Override
    public ItemStack getHelmet() {
        return getEquipment().get(EquipmentSlot.HELMET);
    }

    @Override
    public void setHelmet(ItemStack helmet) {
        getEquipment().set(EquipmentSlot.HELMET, helmet);
    }

    @Override
    public ItemStack getChestPlate() {
        return getEquipment().get(EquipmentSlot.CHESTPLATE);
    }

    @Override
    public void setChestPlate(ItemStack chestPlate) {
        getEquipment().set(EquipmentSlot.CHESTPLATE, chestPlate);
    }

    @Override
    public ItemStack getLeggings() {
        return getEquipment().get(EquipmentSlot.LEGGINGS);
    }

    @Override
    public void setLeggings(ItemStack leggings) {
        getEquipment().set(EquipmentSlot.LEGGINGS, leggings);
    }

    @Override
    public ItemStack getBoots() {
        return getEquipment().get(EquipmentSlot.BOOTS);
    }

    @Override
    public void setBoots(ItemStack boots) {
        getEquipment().set(EquipmentSlot.BOOTS, boots);
    }

    @Override
    public ItemStack getItemInMainHand() {
        return getEquipment().get(EquipmentSlot.HAND);
    }

    @Override
    public void setItemInMainHand(ItemStack itemInMainHand) {
        getEquipment().set(EquipmentSlot.HAND, itemInMainHand);
    }

    @Override
    public ItemStack getItemInOffHand() {
        return getEquipment().get(EquipmentSlot.OFF_HAND);
    }

    @Override
    public void setItemInOffHand(ItemStack itemInOffHand) {
        getEquipment().set(EquipmentSlot.OFF_HAND, itemInOffHand);
    }

    private Equipment getEquipment() {
        return citizensNpc.getTrait(Equipment.class);
    }


    // MOVEMENT
    @Override
    public Location getLocation() {
        return citizensNpc.getEntity().getLocation();
    }

    @Override
    public void teleport(Location location) {
        citizensNpc.teleport(location, TeleportCause.PLUGIN);
    }

    @Override
    public void lookAt(Location location) {
        citizensNpc.faceLocation(location);
    }

    @Override
    public Vector getVelocity() {
        return citizensNpc.getEntity().getVelocity();
    }

    @Override
    public void setVelocity(Vector velocity) {
        citizensNpc.getEntity().setVelocity(velocity);
    }

    @Override
    public void jump() {
        if (!getPlayer().isOnGround())
            return;

        final double jumpYVelocity = 0.4;
        Vector newVelocity = getVelocity().setY(jumpYVelocity);
        setVelocity(newVelocity);
    }

    @Override
    public void swingMainArm() {
        sendAnimationPacket(0);
    }

    @Override
    public void swingOffArm() {
        sendAnimationPacket(3);
    }


    // ACTIONS
    @Override
    public boolean isSneaking() {
        return getPlayer().isSneaking();
    }

    @Override
    public void setSneaking(boolean sneaking) {
        getPlayer().setSneaking(sneaking);
    }

    @Override
    public boolean isSprinting() {
        return getPlayer().isSprinting();
    }

    @Override
    public void setSprinting(boolean sprinting) {
        getPlayer().setSprinting(true);
    }

    @Override
    public void showDamage() {
        PlayerAnimation.HURT.play(getPlayer());
    }

    //TODO: start doing blocking with a 'counter' var?

    @Override
    public void setBlocking(boolean blocking) {
        if (blocking)
            PlayerAnimation.START_USE_MAINHAND_ITEM.play(getPlayer());
        else PlayerAnimation.STOP_USE_ITEM.play(getPlayer());
    }

    @Override
    public void getBlocking() {

    }

    @Override
    public boolean getGlowing() {
        return getPlayer().isGlowing();
    }

    @Override
    public void setGlowing(boolean glowing) {
        getPlayer().setGlowing(glowing);
    }


    @Override
    public void setNumberOfArrowsInBody(int arrows) {
        //TODO: do
    }

    @Override
    public int getNumberOfArrowsInBody() {
        return 0;
    }

    // PACKETS
    private void sendAnimationPacket(int animationId) {
        PacketPlayOutAnimation packet = new PacketPlayOutAnimation();
        ReflectionUtil.setDeclaredFieldValue(packet, "a", getPlayer().getEntityId());
        ReflectionUtil.setDeclaredFieldValue(packet, "b", animationId);

        sendPacket(packet);
    }

    private void sendPacket(Packet<?> packet) {
        for (Player p : Bukkit.getOnlinePlayers())
            if (p.getLocation().distance(getLocation()) < VIEW_DISTANCE)
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }

    // UTIL
    private Player getPlayer() {
        return (Player) citizensNpc.getEntity();
    }

}
