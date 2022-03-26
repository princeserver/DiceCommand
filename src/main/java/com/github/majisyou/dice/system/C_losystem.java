package com.github.majisyou.dice.system;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class C_losystem {
    public static String c_losmain(List<Integer> list){
        if(!(list.size()==3)) {
            System.out.println("引数が多い。3要素の配列を求める");
            return "c_los";
        }

        int firstSurface = list.get(0);
        int secondSurface = list.get(1);
        int thirdSurface = list.get(2);

        int total = firstSurface + secondSurface + thirdSurface;


        if (firstSurface == secondSurface && firstSurface == thirdSurface && firstSurface == 1) return "ピンゾロ";
        if (firstSurface == secondSurface && firstSurface == thirdSurface) return firstSurface + "のゾロ目";

        //2面が等しいとき
        if (firstSurface == secondSurface) return Integer.valueOf(firstSurface).toString() + "の" + Integer.valueOf(thirdSurface).toString();
        if (firstSurface == thirdSurface) return Integer.valueOf(firstSurface).toString() + "の" + Integer.valueOf(secondSurface).toString();
        if (secondSurface == thirdSurface) return Integer.valueOf(secondSurface).toString() + "の" + Integer.valueOf(firstSurface).toString();

        if (total == 15) return "四五六";
        if (total == 6) return "一二三";
        return "役無し";
    }

}
