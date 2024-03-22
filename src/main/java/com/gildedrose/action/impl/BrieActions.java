package com.gildedrose.action.impl;

import com.gildedrose.action.Action;

import java.util.List;

import static com.gildedrose.action.ActionFactory.*;

/**
 * Factory class to create the actions for the Brie cheese
 */
public class BrieActions {

    private static final String ITEM_NAME = "Aged Brie";

    public static List<Action> createActions() {
        return List.of(
            new Action(nameMatches(ITEM_NAME).and(sellInBetween(Integer.MIN_VALUE, 0)),
                increaseQuality(2).andThen(decreaseSellIn(1))),

            new Action(nameMatches(ITEM_NAME).and(sellInBetween(0, Integer.MAX_VALUE)),
                increaseQuality(1).andThen(decreaseSellIn(1)))
        );
    }

}
