package com.songbo.dicshop.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @ClassName RandomNum
 * @Description TODO
 * @Author songbo
 * @Date 2020/4/11 下午3:59
 **/
@Slf4j
public class RandomNum {

    public RandomNum(){}

    public static List<Integer> getRandomList(int total, int num, int seeds) {

        List<Integer> result = new ArrayList<>();
        while (total <= num + seeds) {
            if (total <= num) {
                num = total;
                seeds = 0;
                break;
            }
            seeds /= 10;
        }

        for (int i = 0; i < num; i++) {
            result.add(i+seeds);
        }
        Collections.shuffle(result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(RandomNum.getRandomList(4, 5, 6));
    }

}
