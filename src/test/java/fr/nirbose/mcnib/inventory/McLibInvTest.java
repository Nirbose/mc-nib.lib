package fr.nirbose.mcnib.inventory;

import be.seeseemelk.mockbukkit.MockBukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class McLibInvTest {

    private McLibInv inv;

    @BeforeEach
    void setUp() {
        MockBukkit.mock();

        inv = new McLibInv("test");

        inv.setItem(0, new ItemStack(Material.APPLE));
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void testInvNotNull() {
        assertNotNull(inv);
    }

    @Test
    void testExistItem() {
        final ItemStack item = inv.getInventory().getItem(0);

        assertNotNull(item);
        assertEquals(Material.APPLE, item.getType());
    }

}
