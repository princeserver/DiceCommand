package com.github.majisyou.dice.commands;

import com.github.majisyou.dice.system.Dicesystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DiceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        sender.getServer().getLogger().info("diceが実行されました");

        if(args.length < 2) {//サブコマンドの個数が0,サブコマンド無し
            sender.sendMessage(ChatColor.RED + "§a 引数が足りません");
            sender.sendMessage("§a/dice <サイコロの数> <サイコロの面の数>");
            return true;
        }

        if(!(args[1].chars().allMatch(Character::isDigit) && args[1].chars().allMatch(Character::isDigit))){
            sender.sendMessage(ChatColor.RED+"§a 引数が間違っています。");
            sender.sendMessage("第2,3引数には数値が必要です");
            return true;
        }

        int times = Byte.parseByte(args[0]); //サイコロを振る回数
        int number = Byte.parseByte(args[1]); //サイコロの面の数

        List<Integer> result = Dicesystem.Dicemain(times,number);
        List<Player> players = new ArrayList<Player>();
        if(args.length > 2){
            for(int i=2; i< args.length; i++){
                if(sender.getServer().getPlayer(args[i])==null) players.add(null);
                players.add(sender.getServer().getPlayer(args[i]).isOnline() ? sender.getServer().getPlayer(args[i]): null);
            }
        }

        for(int i=0; i<times; i++){
            String result_message=(i+1)+"回目の結果"+result.get(i);
            sender.sendMessage(result_message);
            if(args.length>2){
                for (int j=0;j<players.size();j++){
                    if (players.get(j)!=null){
                        players.get(j).sendMessage(result_message);
                    } else sender.sendMessage(args[j]+"オンラインじゃないか存在しないよ");
                }
            }
        }
        return true;
    }
}
