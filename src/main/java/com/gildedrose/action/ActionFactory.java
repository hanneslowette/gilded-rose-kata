package com.gildedrose.action;

import com.gildedrose.Item;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ActionFactory {

    public static Predicate<Item> nameMatches(String name) {
        return item -> item.getName().contains(name);
    }

    public static Predicate<Item> sellInBetween(int min, int max) {
        return item -> item.getSellIn() >= min && item.getSellIn() <= max;
    }

    public static Predicate<Item> sellIn(int amount) {
        return item -> item.getSellIn() == amount;
    }

    public static Consumer<Item> setQuality(int amount) {
        return item -> item.setQuality(amount);
    }

    public static Consumer<Item> increaseQuality(int amount) {
        return item -> item.setQuality(Math.min(item.getQuality() + amount, 50));
    }

    public static Consumer<Item> decreaseQuality(int amount) {
        return item -> item.setQuality(Math.max(item.getQuality() - amount, 0));
    }

    public static Consumer<? super Item> decreaseSellIn(int amount) {
        return item -> item.setSellIn(item.getSellIn() - amount);
    }

}
