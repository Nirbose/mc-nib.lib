package fr.nirbose.mcnib;

import org.bukkit.plugin.Plugin;

public class Manager {

    private static Plugin plugin = null;

    public static void register(Plugin plugin) {
        Manager.plugin = plugin;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

}
