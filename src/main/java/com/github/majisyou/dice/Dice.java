package com.github.majisyou.dice;

import com.github.majisyou.dice.commands.C_locommand;
import com.github.majisyou.dice.commands.DiceCommand;
import com.github.majisyou.dice.commands.DiceTest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dice extends JavaPlugin {
    private static Plugin plugin;//ここは相変わらず何を意味しているかわからん

    @Override
    public void onEnable() {
        getLogger().info("Diceプラグインジェネレート");
        getCommand("setdice").setExecutor(new DiceCommand());
        getCommand("setdicetest").setExecutor(new DiceTest());
//        getCommand("setc_lo").setExecutor(new C_locommand());

    }

    @Override
    public void onDisable() {
        getLogger().info("Diceプラグインが停止した");
        // Plugin shutdown logic
    }

    public static Plugin getPlugin(){   //この意味わからない
        return plugin;
    }
}
