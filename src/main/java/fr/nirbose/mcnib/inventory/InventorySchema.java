package fr.nirbose.mcnib.inventory;

import com.google.common.base.Preconditions;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class InventorySchema {

    private final List<String> masks = new ArrayList<>();
    private final Map<Character, ItemStack> items = new HashMap<>();
    private final Map<ItemStack, Consumer<InventoryClickEvent>> consumers = new HashMap<>();

    public InventorySchema() {
    }

    public InventorySchema mask(String mask) {
        Preconditions.checkArgument(mask != null, "mask cannot be null");

        this.masks.add(mask.length() > 9 ? mask.substring(0, 9) : mask);
        return this;
    }

    public InventorySchema mask(String ...mask) {
        for (String m : mask) {
            this.mask(m);
        }

        return this;
    }

    public InventorySchema mask(List<String> mask) {
        for (String m : mask) {
            this.mask(m);
        }

        return this;
    }

    public InventorySchema bind(char c, ItemStack itemStack) {
        return this.bind(c, itemStack, null);
    }

    public InventorySchema bind(char c, ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        this.items.put(c, itemStack);

        if (consumer != null) {
            this.consumers.put(itemStack, consumer);
        }

        return this;
    }

    public InventorySchema unbind(char c) {
        ItemStack itemStack = this.items.remove(c);

        if (itemStack != null) {
            this.consumers.remove(itemStack);
        }

        return this;
    }

    public Map<ItemStack, Consumer<InventoryClickEvent>> apply(McLibInv inv) {
        Preconditions.checkArgument(inv != null, "inv cannot be null");

        final Inventory inventory = inv.getInventory();

        int i = 0;
        for (String mask : this.masks) {
            for (char c : mask.toCharArray()) {
                final ItemStack itemStack = this.items.get(c);

                if (itemStack != null) {
                    inventory.setItem(i, itemStack);
                }

                i++;
            }
        }

        return this.consumers;
    }

}
