package com.djw.util;

import org.junit.jupiter.api.MethodOrderer;

import java.util.*;


/**
 * @Author: dai jiawei
 * @Date: 2020/11/17 9:31
 */
public class RandomizedSet {
    List<Integer> randomizedList;
    Map<Integer,Integer> randomizedMap;
    Random random;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        randomizedList = new ArrayList<>();
        randomizedMap = new HashMap<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(randomizedList.contains(val)){
            return false;
        }else{
            return randomizedList.add(val);
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(randomizedList.contains(val)) {
            return randomizedList.remove(Integer.valueOf(val));
        }else{
            return false;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return randomizedList.get(random.nextInt(randomizedList.size()));
    }
}
