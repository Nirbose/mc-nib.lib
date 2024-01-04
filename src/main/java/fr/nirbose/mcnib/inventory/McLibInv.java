package fr.nirbose.mcnib.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class McLibInv implements InventoryHolder {

    private final Inventory inventory;
    protected final Map<ItemStack, Consumer<InventoryClickEvent>> consumers = new HashMap<>();
    protected final List<AnimatedSlot> animatedSlots = new ArrayList<>();

    public McLibInv(String title) {
        this(title, 27);
    }

    public McLibInv(String title, int size) {
        this(owner -> Bukkit.createInventory(owner, size, title));
    }

    public McLibInv(Function<InventoryHolder, Inventory> function) {
        this.inventory = function.apply(this);
    }

    public void setItem(int slot, ItemStack itemStack) {
        this.inventory.setItem(slot, itemStack);
    }

    public void setItem(int slot, ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        this.setItem(slot, itemStack);
        this.consumers.put(itemStack, consumer);
    }

    public void setItems(int[] slots, ItemStack itemStack) {
        for (int slot : slots) {
            this.setItem(slot, itemStack);
        }
    }

    public void setItems(int[] slots, ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        for (int slot : slots) {
            this.setItem(slot, itemStack, consumer);
        }
    }

    public void addItem(ItemStack itemStack) {
        this.inventory.addItem(itemStack);
    }

    public void addAnimatedSlot(AnimatedSlot animatedSlot) {
        this.animatedSlots.add(animatedSlot);
    }

    public void addAnimatedSlot(int slot, int delay, ItemStack ...items) {
        this.animatedSlots.add(new AnimatedSlot(slot, delay, items));
    }

    public void addAnimatedSlot(int slot, ItemStack ...items) {
        this.animatedSlots.add(new AnimatedSlot(slot, items));
    }

    public void setSchema(InventorySchema schema) {
        this.consumers.putAll(
                schema.apply(this)
        );
    }

    public void onClick(InventoryClickEvent event) {
        final ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null) {
            return;
        }

        Consumer<InventoryClickEvent> consumer = this.consumers.get(itemStack);
        if (consumer != null) {
            consumer.accept(event);
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
