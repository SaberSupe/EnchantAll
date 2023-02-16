package saber.enchantall.commands;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import saber.enchantall.EnchantAll;

import java.util.List;

public class EnchantMax implements CommandExecutor {

    private EnchantAll plugin;

    public EnchantMax (EnchantAll p1){
        plugin = p1;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if (cmd.getName().equalsIgnoreCase("enchantmax")) {

            //Check if sender has perms, send message if not
            if (!sender.hasPermission("enchantall.enchant")) {
                String noPerms = plugin.getConfig().getString("msgNoPerms");
                noPerms = ChatColor.translateAlternateColorCodes('&', noPerms);
                sender.sendMessage(noPerms);
                return true;
            }

            //Get the player, the item in their hand
            Player play = (Player) sender;
            ItemStack item = play.getInventory().getItemInMainHand();

            //Retrieve enchantments list from config
            List<String> enchants = plugin.getConfig().getStringList("enchants");

            //Loop through all enchantments
            for (String enchan : enchants) {
                Enchantment enchant = Enchantment.getByKey(NamespacedKey.minecraft(enchan.toLowerCase()));
                if (enchant != null) { //If enchantment exists
                    int max = enchant.getMaxLevel();

                    //Add enchantment if the item can be enchanted and doesn't alreay have a higher level enchantment
                    if (max > item.getEnchantmentLevel(enchant) && enchant.canEnchantItem(item)) {
                        item.addEnchantment(enchant, max);
                    }
                }
            }

            return true;

        }
        return true;
    }
}
