package com.github.majisyou.dice.commands;

import com.github.majisyou.dice.system.C_losystem;
import com.github.majisyou.dice.system.Dicesystem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class C_locommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.getServer().getLogger().info("diceが実行されました");
        if(!(args.length==0)){
            sender.sendMessage("引数が多いよ");
            return true;
        }

        List<Integer> result = Dicesystem.Dicemain(3,6);
        String yaku = C_losystem.c_losmain(result);

        sender.sendMessage("----------");

        for (int i=0; i<3; i++){
            sender.sendMessage("|"+(i+1)+"回目の結果 : "+result.get(i)+"|");
        }
            if(yaku.equals("ピンゾロ")) sender.sendMessage(ChatColor.GREEN+"--"+yaku+"--");
            else if(yaku.equals("一二三")) sender.sendMessage(ChatColor.RED+"--"+yaku+"--");
            else if(yaku.equals("四五六")) sender.sendMessage(ChatColor.AQUA+"--"+yaku+"--");
            else if(yaku.equals("役無し")) sender.sendMessage("--"+yaku+"--");
            else sender.sendMessage(ChatColor.YELLOW+"--"+yaku+"--");

            return true;
    }

}
