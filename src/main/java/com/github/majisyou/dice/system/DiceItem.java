package com.github.majisyou.dice.system;

import com.github.majisyou.dice.Dice;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class DiceItem {

    private static final Dice plugin = Dice.getInstance();

    public static ItemStack DiceItemStack(){

        Material diceItemMaterial = Material.getMaterial(ConfigManager.getItemType());
        if(diceItemMaterial==null){
            return new ItemStack(Material.AIR,1);
        }
        ItemStack diceItem = new ItemStack(diceItemMaterial,1);
        ItemMeta diceItemMeta = diceItem.getItemMeta();
        diceItemMeta.setDisplayName(ChatColor.WHITE +ConfigManager.getDisplayname());
        String lore = ChatColor.WHITE+ConfigManager.getLore().get(0);
        List<String> Lore = new ArrayList<>();
        Lore.add(lore);
        diceItemMeta.setLore(Lore);
        try {
            NamespacedKey itemTag = new NamespacedKey(plugin,"DiceType");
            for (String key: ConfigManager.getItemTag().getKeys(false)){
                diceItemMeta.getPersistentDataContainer().set(itemTag, PersistentDataType.INTEGER,ConfigManager.getItemTag().getInt(key));
            }
        }catch (Exception e){
            plugin.getLogger().info("アイテムタグがダメだね");
            //ConfigurationSectionは設定していないとnullが出てくる
        }
        diceItemMeta.setCustomModelData(ConfigManager.getCustomModelData());
        diceItem.setItemMeta(diceItemMeta);
        return diceItem;
    }
}
