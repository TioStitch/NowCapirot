package de.tiostitch.capirot.commands;

import de.tiostitch.capirot.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class AppleGiveCMD implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;


        if (!player.hasPermission("NowCapirot.capGive")) {
            player.sendMessage(Main.getInstance().getConfig().getString("apple-settings.no-permission-message"));
            return false;
        }

            if (args.length == 0) {
                player.sendMessage("§b§lLista de Comandos §e- NowCapirot");
                player.sendMessage("");
                player.sendMessage("§e/cap give <player> <quantidade> - Dá a maçã ao jogador.");
                player.sendMessage("");
                return false;
            }

          if (args[0].equalsIgnoreCase("give")) {
              if (args.length == 1 || args.length > 3 || args.length == 2) {
                  player.sendMessage("§b§lNowCapirot §cUso correto: /cap give <player> <quantidade>");
                  return false;
              }

              Player target = Bukkit.getPlayer(args[1]);
              int amount = Integer.parseInt(args[2]);

                if (target == null) {
                    player.sendMessage("§b§lNowCapirot §cO jogador desejado é inválido ou está offline!");
                    return false;
                }

                ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, amount);
                ItemMeta appleMeta = apple.getItemMeta();
                appleMeta.setDisplayName(Main.getInstance().getConfig().getString("apple-itemStack.displayName"));
                List<String> enchantments = Main.getInstance().getConfig().getStringList("apple-itemStack.enchantments");
                List<String> lore = Main.getInstance().getConfig().getStringList("apple-itemStack.lore");
                enchantments.forEach(fore -> {
                    String enchant = fore.split(":")[0];
                    int enchantLevel = Integer.parseInt(fore.split(":")[1]);

                    appleMeta.addEnchant(Enchantment.getByName(enchant), enchantLevel, true);
                });
                appleMeta.setLore(lore);
                apple.setItemMeta(appleMeta);

                target.getInventory().addItem(apple);
            }


        return false;
    }
}
