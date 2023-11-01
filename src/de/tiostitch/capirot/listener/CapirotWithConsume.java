package de.tiostitch.capirot.listener;

import de.tiostitch.capirot.Main;
import de.tiostitch.capirot.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CapirotWithConsume implements Listener {

    String CAP_NAME = Main.getInstance().getConfig().getString("apple-settings.displayName");

    @EventHandler
    public void aoConsumirNormal(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        //Checagem de nulos.
        if (!Utils.hasHandItem(player, CAP_NAME)) {
            return;
        }

        //Considerações finais.
        Utils.executeAction(player, item);
    }
}

