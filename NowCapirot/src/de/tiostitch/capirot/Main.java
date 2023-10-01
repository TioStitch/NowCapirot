package de.tiostitch.capirot;

import de.tiostitch.capirot.commands.AppleGiveCMD;
import de.tiostitch.capirot.listener.CapirotEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;

        Bukkit.getConsoleSender().sendMessage("§a------------------");
        Bukkit.getConsoleSender().sendMessage("  §b§lNowCapirot");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(" §fEstado:");
        Bukkit.getConsoleSender().sendMessage("  §aIniciado com sucesso!");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§a------------------");
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new CapirotEvents(), this);
        Bukkit.getPluginCommand("cap").setExecutor(new AppleGiveCMD());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§a------------------");
        Bukkit.getConsoleSender().sendMessage("  §b§lNowCapirot");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(" §fEstado:");
        Bukkit.getConsoleSender().sendMessage("  §cDesligado com sucesso!");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§a------------------");
    }

    public static Main getInstance() {
        return plugin;
    }
}
