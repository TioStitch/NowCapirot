package de.tiostitch.capirot.utils;

import de.tiostitch.capirot.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Utils {

    private static final int CAP_SECONDS = Main.getInstance().getConfig().getInt("apple-settings.cap-seconds");
    private static final boolean CAP_NOTIFICATION = Main.getInstance().getConfig().getBoolean("apple-settings.consume-notification");
    private static final String CAP_MESSAGE = Main.getInstance().getConfig().getString("apple-settings.consume-message");

    public static boolean hasHandItem(Player player, String displayName) {
        ItemStack item = player.getItemInHand();
        if (item == null || item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null) {
            return false;
        }
        return item.getItemMeta().getDisplayName().contains(displayName);
    }

    public static void executeAction(Player player, ItemStack item) {
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
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

        }, CAP_SECONDS * 20L);
    }
}
