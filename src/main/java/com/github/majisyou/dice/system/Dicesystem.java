package com.github.majisyou.dice.system;

import org.bukkit.ChatColor;

import java.util.Random;
import java.util.logging.Logger;

public class Dicesystem {

    public static int[] Dicemain(int times,int number) {

        int[] resultdice;  //初期化と呼び出し

            int result; //結果
            resultdice = new int[times]; //timseから箱の大きさを決定する
            Random ranNum = new Random(); //ランダム変数を定義
            for (int i = 0; i < times; i++) {
                result = ranNum.nextInt(number) + 1; //結果をランダムに変更させ、
                resultdice[i] = result;             //resultdice[i回目]に代入する
            }

        return resultdice;

    }
}
