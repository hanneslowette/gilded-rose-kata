package com.gildedrose.action.impl;

import com.gildedrose.action.Action;

import java.util.List;

import static com.gildedrose.action.ActionFactory.*;

public class SulfurasActions {

    private static final String ITEM_NAME = "Sulfuras";

    public static List<Action> createActions() {
        return List.of(new Action(nameMatches(ITEM_NAME), setQuality(80)));
    }

}
