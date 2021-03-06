package com.github.majisyou.dice.commands;

import com.github.majisyou.dice.Dice;
import com.github.majisyou.dice.system.ConfigManager;
import com.github.majisyou.dice.system.Dicesystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiceCommand implements CommandExecutor {
    public HashMap<String, Long> cooldowns = new HashMap<String,Long>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(command.getName().equals("setdice")){
            int Cooldowntime = 3;
            int times = 1; //サイコロを振る回数
            int number = 1; //サイコロの面の数

            if(cooldowns.containsKey(sender.getName())){
                long secondleft = ((cooldowns.get(sender.getName())/1000)+Cooldowntime) -(System.currentTimeMillis()/ 1000);
                if(secondleft>0){
                    sender.sendMessage("このコマンドは3秒のcooltimeがあります");
                    return true;
                }
            }
            if(args.length < 2) {//サブコマンドの個数が0,サブコマンド無し
                sender.sendMessage(ChatColor.RED + "§a 引数が足りません");
                sender.sendMessage("§a/dice <サイコロの数> <サイコロの面の数>");
                return true;
            }

            if(!(args[1].chars().allMatch(Character::isDigit) && args[0].chars().allMatch(Character::isDigit))){
                sender.sendMessage(ChatColor.RED+"§a 引数が間違っています。");
                sender.sendMessage("第1,2引数には数値が必要です");
                return true;
            }

            if(args.length<22){
                sender.sendMessage(ChatColor.RED+"追加できるプレイヤーは20人までです");
                return true;
            }

            try {
                times = Byte.parseByte(args[0]); //サイコロを振る回数
                number = Byte.parseByte(args[1]); //サイコロの面の数
            }catch (Exception e){
                sender.sendMessage("引数1は100未満,引数2は128未満にしてください");
                return true;
            }
            if(times > 100){
                sender.sendMessage("引数1は100未満,引数2は128未満にしてください");
                return true;
            }


            List<Integer> result = Dicesystem.Dicemain(times,number);
            List<Player> players = new ArrayList<>();
            if(args.length > 2){
                for(int i=2; i< args.length; i++){
                    if(sender.getServer().getPlayer(args[i])==null) {
                        players.add(null);
                    }else {
                        players.add(sender.getServer().getPlayer(args[i]).isOnline() ? sender.getServer().getPlayer(args[i]): null);
                    }
                }
            }

            for(int i=0; i<times; i++){
                String result_message=(i+1)+"回目の結果"+result.get(i);
                sender.sendMessage(result_message);

                if(args.length>2){
                    if(sender instanceof Player player){
                        List<Entity> NearEntity =  player.getNearbyEntities(20,20,20);
                        for (int j=0;j<players.size();j++){
                            if (players.get(j)!=null){
                                if(NearEntity.contains(players.get(j))){
                                    players.get(j).sendMessage(sender.getName()+":"+result_message);
                                }else {
                                    player.sendMessage("近くに"+args[j+2]+"はいないよ");
                                }
                            } else sender.sendMessage(args[j+2]+"はオンラインじゃないか存在しないよ");
                        }
                    }else {
                        for (int j=0;j<players.size();j++){
                            if (players.get(j)!=null){
                                players.get(j).sendMessage(sender.getName()+":"+result_message);
                            } else sender.sendMessage(args[j]+"オンラインじゃないか存在しないよ");
                        }

                    }
                }
            }
            cooldowns.put(sender.getName(),System.currentTimeMillis());
            for (String player2: cooldowns.keySet()){
                long secondleft = ((cooldowns.get(player2)/1000)+Cooldowntime) -(System.currentTimeMillis()/ 1000);
                if(secondleft <= 0){
                 players.remove(player2);
                }
            }
        }
        return true;
    }
}
