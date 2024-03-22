package com.gildedrose.action.impl;

import com.gildedrose.action.Action;

import java.util.List;

import static com.gildedrose.action.ActionFactory.*;

public class BackstagePassActions {

    private static final String ITEM_NAME = "Backstage passes";

    public static List<Action> createActions() {
        return List.of(
            new Action(nameMatches(ITEM_NAME).and(sellIn(0)),
                setQuality(0).andThen(decreaseSellIn(1))),

            new Action(nameMatches(ITEM_NAME).and(sellInBetween(1, 5)),
                increaseQuality(3).andThen(decreaseSellIn(1))),

            new Action(nameMatches(ITEM_NAME).and(sellInBetween(6, 10)),
                increaseQuality(2).andThen(decreaseSellIn(1))),

            new Action(nameMatches(ITEM_NAME).and(sellInBetween(11, Integer.MAX_VALUE)),
                increaseQuality(1).andThen(decreaseSellIn(1)))
        );
    }

}
