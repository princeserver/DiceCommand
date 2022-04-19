package com.github.majisyou.dice.commands;

import com.github.majisyou.dice.system.DiceItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveDiceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            player.getWorld().dropItem(player.getLocation(),DiceItem.DiceItemStack());
        }
        return true;
    }
}
