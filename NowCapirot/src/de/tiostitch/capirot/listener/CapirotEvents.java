package de.tiostitch.capirot.listener;

import de.tiostitch.capirot.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CapirotEvents implements Listener {

    String CAP_NAME = Main.getInstance().getConfig().getString("apple-settings.displayName");
    String CAP_MESSAGE = Main.getInstance().getConfig().getString("apple-settings.consume-message");
    boolean CAP_NOTIFICATION = Main.getInstance().getConfig().getBoolean("apple-settings.consume-notification");

    @EventHandler
    public void onCapAppleInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        // Verifica se o item utilizado é um pote de XP Grande
        if (item != null && item.getType() != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            String displayName = item.getItemMeta().getDisplayName();

            if (displayName.contains(CAP_NAME)) {

                List<String> effectList = Main.getInstance().getConfig().getStringList("apple-settings.effect-list");
                Collection<PotionEffect> potionEffects = new ArrayList<>();
                for (String effectString : effectList) {
                    String[] parts = effectString.split(":");
                    if (parts.length == 3) {
                        PotionEffectType type = PotionEffectType.getByName(parts[0]);
                        if (type != null) {
                            int duration = Integer.parseInt(parts[1]);
                            int amplifier = Integer.parseInt(parts[2]);
                            PotionEffect effect = new PotionEffect(type, duration, amplifier);
                            potionEffects.add(effect);
                        }
                    }
                }

                player.addPotionEffects(potionEffects);

                if (CAP_NOTIFICATION) {
                    player.sendMessage(CAP_MESSAGE);
                }

                if (item.getAmount() == 1) {
                    player.setItemInHand(null);
                } else {
                    item.setAmount(item.getAmount() - 1);
                }
            }
        }
    }
}
