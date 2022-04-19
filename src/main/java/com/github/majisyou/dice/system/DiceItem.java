package com.github.majisyou.dice.system;

import com.github.majisyou.dice.Dice;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DiceItem {

    public static ItemStack DiceItemStack(){

        Material diceItemMaterial = Material.getMaterial(ConfigManager.getItemType());
        if(diceItemMaterial==null){
            return new ItemStack(Material.AIR,1);
        }
        ItemStack diceItem = new ItemStack(diceItemMaterial,1);
        ItemMeta diceItemMeta = diceItem.getItemMeta();
        diceItemMeta.setDisplayName(ConfigManager.getDisplayname());
        diceItemMeta.setLore(ConfigManager.getLore());
        diceItemMeta.setCustomModelData(ConfigManager.getCustomModelData());
        diceItem.setItemMeta(diceItemMeta);
        return diceItem;
    }
}
