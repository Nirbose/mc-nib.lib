package fr.nirbose.mcnib.inventory;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnimatedSlot {

    private final int slot;
    private final List<ItemStack> items = new ArrayList<>();
    private int delay = 5;

    public AnimatedSlot(int slot, ItemStack ...items) {
        this.slot = slot;

        Collections.addAll(this.items, items);
    }

    public AnimatedSlot(int slot, int delay, ItemStack ...items) {
        this.slot = slot;
        this.delay = delay;

        Collections.addAll(this.items, items);
    }

    public int getSlot() {
        return slot;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public int getDelay() {
        return delay;
    }

}
