package com.github.majisyou.dice.system;

import org.bukkit.ChatColor;

import java.util.Random;
import java.util.logging.Logger;

public class Dicesystem {

    public static int[] Dicemain(int times,int number) {

        int[] resultdice;  //初期化と呼び出し

            int result;
            resultdice = new int[times];
            Random ranNum = new Random();
            for (int i = 0; i < times; i++) {
                result = ranNum.nextInt(number) + 1;
                resultdice[i] = result;
            }

        return resultdice;

    }
}
