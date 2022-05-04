package com.github.majisyou.dice.system;

import com.github.majisyou.dice.Dice;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigManager {
    private static Dice plugin = Dice.getInstance();
    private static FileConfiguration config = plugin.getConfig();

    private static String ItemType;
    private static String displayname;
    private static Integer customModelData;
    private static List<String> Lore;
    private static ConfigurationSection ItemTag;

    public static void loadConfig(){
        ItemType = config.getString("DiceItem.ItemType");
        displayname = config.getString("DiceItem.displayname");
        customModelData = config.getInt("DiceItem.customModelData");
        Lore = config.getStringList("DiceItem.Lore");
        ItemTag = config.getConfigurationSection("DiceItem.ItemTag");
    }

    public static String getItemType(){return ItemType;}
    public static String getDisplayname(){return displayname;}
    public static Integer getCustomModelData(){return customModelData;}
    public static List<String> getLore(){return Lore;}
    public static ConfigurationSection getItemTag(){return ItemTag;}



}
