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

public class DiceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("setdice")){
            sender.getServer().getLogger().info("diceが実行されました");
            if(args.length < 2){//サブコマンドの個数が0,サブコマンド無し
                sender.sendMessage(ChatColor.RED+"§a 引数が足りません");
                sender.sendMessage("§a/dice <サイコロの数> <サイコロの面の数>");
            }else{
//                if(args.length > 2){
//                    sender.sendMessage(ChatColor.RED+"§a 引数が多すぎます");
//                    sender.sendMessage("§a/dice <サイコロの数> <サイコロの面の数>");
//                    return true;
//                }else {
                    if(!(args[1].chars().allMatch(Character::isDigit)) || !(args[0].chars().allMatch(Character::isDigit))){
                        sender.sendMessage(ChatColor.RED+"§a 引数が間違っています。");
                        sender.sendMessage("第2,3引数には数値が必要です");
                        return true;
                    }else{
                        int times = Byte.parseByte(args[0]);
                        int number = Byte.parseByte(args[1]);
                        int person = args.length; String[] people = new String[person-2];
                        int[] result = Dicesystem.Dicemain(times,number);
                        Player[] player=new Player[args.length-2];

                        if(args.length > 2){
                            for(int j=2;j<person;j++){
                                player[j-2]= Bukkit.getPlayer(args[j]);
                            }
                        }

                        for(int i=0; i<times; i++){
                            sender.sendMessage((i+1)+"回目の結果: "+result[i]);
                            if(args.length >2){
                                for(int j=2;j<person;j++){
                                    if(!(player[j - 2]==null)){
                                        player[j - 2].sendMessage((i+1)+"回目の結果: "+result[i]);
                                    }else {
                                        sender.sendMessage(args[j]+"はオンラインじゃないよ");
                                    }
                                }
                            }

                        }
                    }
//                }
            }
            return true;//終わり
        }
        return false;
    }
}
