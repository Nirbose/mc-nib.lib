package fr.nirbose.mcnib.inventory;

import org.bukkit.inventory.ItemStack;

public class Slot {

    private final ItemStack itemStack;

    public Slot(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

}
