package de.tiostitch.capirot;

import de.tiostitch.capirot.commands.AppleCMD;
import de.tiostitch.capirot.listener.CapirotWithConsume;
import de.tiostitch.capirot.listener.CapirotNoConsume;
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

        boolean isAutoCap = Main.getInstance().getConfig().getBoolean("apple-settings.auto-cap");

        if (isAutoCap) {
            Bukkit.getPluginManager().registerEvents(new CapirotNoConsume(), this);
        } else {
            Bukkit.getPluginManager().registerEvents(new CapirotWithConsume(), this);
        }


        Bukkit.getPluginCommand("cap").setExecutor(new AppleCMD());
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
