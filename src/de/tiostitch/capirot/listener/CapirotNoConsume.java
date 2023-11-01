package de.tiostitch.capirot.listener;

import de.tiostitch.capirot.Main;
import de.tiostitch.capirot.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CapirotNoConsume implements Listener {

    private final String CAP_NAME = Main.getInstance().getConfig().getString("apple-settings.displayName");
    private final String CAP_COOLDOWN_MESSAGE = Main.getInstance().getConfig().getString("apple-settings.cooldown-message");
    private final int CAP_COOLDOWN = Main.getInstance().getConfig().getInt("apple-settings.cap-cooldown");
    private final boolean CAP_COOLDOWN_NOTIFICATION = Main.getInstance().getConfig().getBoolean("apple-settings.cooldown-notification");
    private static HashMap<Player, Long> capiroto_cooldown = new HashMap<>();

    @EventHandler
    public void aoConsumirEvento(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            //Checagem de nulos.
            if (!Utils.hasHandItem(player, CAP_NAME)) {
                return;
            }

            //Adição do intervalo.
            long currentTime = System.currentTimeMillis();
            if (capiroto_cooldown.containsKey(player) && capiroto_cooldown.get(player) > currentTime) {
                long remainingTime = (capiroto_cooldown.get(player) - currentTime) / 1000;
                if (CAP_COOLDOWN_NOTIFICATION) {
                    player.sendMessage(CAP_COOLDOWN_MESSAGE.replace("%time%", String.valueOf(remainingTime)));
                }
                return;
            }

            //Considerações finais.
            capiroto_cooldown.put(player, currentTime + CAP_COOLDOWN * 1000L);
            Utils.executeAction(player, item);
        }
    }
}