package com.gildedrose.action.impl;

import com.gildedrose.action.Action;

import java.util.List;

import static com.gildedrose.action.ActionFactory.*;

public class ConjuredItemActions {

    private static final String ITEM_NAME = "Conjured";

    public static List<Action> createActions() {
        return List.of(
            new Action(nameMatches(ITEM_NAME).and(sellInBetween(Integer.MIN_VALUE, 0)),
                decreaseQuality(2).andThen(decreaseSellIn(1))),

            new Action(nameMatches(ITEM_NAME).and(sellInBetween(0, Integer.MAX_VALUE)),
                decreaseQuality(1).andThen(decreaseSellIn(1)))
        );
    }
}
