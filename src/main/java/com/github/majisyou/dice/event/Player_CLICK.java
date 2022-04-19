package com.github.majisyou.dice.event;

import com.github.majisyou.dice.Dice;
import com.github.majisyou.dice.system.ConfigManager;
import com.github.majisyou.dice.system.Dicesystem;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import static org.bukkit.Material.CLOCK;
import static org.bukkit.Material.REPEATING_COMMAND_BLOCK;
import static org.bukkit.event.block.Action.RIGHT_CLICK_AIR;
import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;

public class Player_CLICK implements Listener {

    @EventHandler
    public static void DiceEvent(PlayerInteractEvent event){

        if(event.getAction() == RIGHT_CLICK_AIR || event.getAction() == RIGHT_CLICK_BLOCK) {
            // 時計を持っているかの判定。
            Material dice_material = Material.getMaterial(ConfigManager.getItemType());
            if(dice_material==null){
                Dice.getInstance().getLogger().info("ダイスプラグインのconfigのアイテムタイプが無いよ");
                return;
            }

            if(event.getMaterial() == dice_material) {
                // 時計がクールダウンを終了しているかを判定。
                if(event.getItem().hasItemMeta()){
                    if(event.getItem().getItemMeta().hasCustomModelData()){
                        if(event.getItem().getItemMeta().hasLore()){
                            if(event.getItem().getItemMeta().getLore().get(0).equals(ConfigManager.getLore().get(0))){
                                if(event.getPlayer().getCooldown(dice_material) == 0) {

                                    List<Integer> result = Dicesystem.Dicemain(1,6);
                                    event.getPlayer().sendMessage(event.getItem().getItemMeta().getDisplayName()+"の結果："+result);

                                    List<Entity> NearEntity =  event.getPlayer().getNearbyEntities(10,10,10);
                                    for(Entity entity:NearEntity){
                                        if(entity instanceof Player player){
                                            player.sendMessage(event.getPlayer().getName()+":の"+ConfigManager.getDisplayname()+"の結果:"+result);
                                        }
                                    }
                                    event.getPlayer().setCooldown(dice_material,100);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
