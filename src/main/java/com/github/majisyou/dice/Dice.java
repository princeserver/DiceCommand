package com.github.majisyou.dice;

import com.github.majisyou.dice.commands.C_locommand;
import com.github.majisyou.dice.commands.DiceCommand;
import com.github.majisyou.dice.commands.DiceTest;
import com.github.majisyou.dice.commands.GiveDiceCommand;
import com.github.majisyou.dice.event.Player_CLICK;
import com.github.majisyou.dice.system.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dice extends JavaPlugin {

    private static Dice instance;
    public Dice(){instance = this;}
    public static Dice getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        getLogger().info("Diceプラグインジェネレート");
        getCommand("setdice").setExecutor(new DiceCommand());
        getCommand("setdicetest").setExecutor(new DiceTest());
        getCommand("setc_lo").setExecutor(new C_locommand());
        getCommand("give_dice").setExecutor(new GiveDiceCommand());

        //config
        saveDefaultConfig();
        ConfigManager.loadConfig();

        //event系
        getServer().getPluginManager().registerEvents(new Player_CLICK(),this);

    }

    @Override
    public void onDisable() {
        getLogger().info("Diceプラグインが停止した");
        // Plugin shutdown logic
    }
}
