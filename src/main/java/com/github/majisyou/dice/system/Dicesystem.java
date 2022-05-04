package com.github.majisyou.dice.system;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Dicesystem {

    public static List<Integer> Dicemain(int times, int surfaceCount) {

        List<Integer> resultdice=new ArrayList<Integer>();  //初期化と呼び出し

        Random randomNumber = new Random(); //ランダム変数を定義
        for (int i = 0; i < times; i++) {
            try {

                int result = randomNumber.nextInt(surfaceCount) + 1; //結果をランダムに変更させ、
                resultdice.add(result);//resultdice[i回目]に代入する
            }catch (Exception e){
                resultdice.add(0);
            }
        }

        return resultdice;

    }
}
