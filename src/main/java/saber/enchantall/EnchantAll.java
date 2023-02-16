package saber.enchantall;

import org.bukkit.plugin.java.JavaPlugin;
import saber.enchantall.commands.EnchantMax;

import java.util.logging.Level;

public final class EnchantAll extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();

        //Load Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Register command
        getCommand("enchantmax").setExecutor(new EnchantMax(this));

        //Log successful launch
        this.getLogger().log(Level.INFO, "EnchantAll loaded Successfully");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
