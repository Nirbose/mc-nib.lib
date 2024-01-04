package fr.cubycraft.mcnib.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class McLibInv implements InventoryHolder {

    private final Inventory inventory;

    public McLibInv(String title) {
        this(title, 27);
    }

    public McLibInv(String title, int size) {
        this(owner -> Bukkit.createInventory(owner, size, Component.text(title)));
    }

    public McLibInv(Function<InventoryHolder, Inventory> function) {
        this.inventory = function.apply(this);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    public Slot slot(ItemStack itemStack) {
        return new Slot(itemStack);
    }
}
