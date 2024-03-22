package com.gildedrose.action.impl;

import com.gildedrose.action.Action;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

import static com.gildedrose.action.ActionFactory.decreaseQuality;
import static com.gildedrose.action.ActionFactory.decreaseSellIn;

public class ActionsDatabase {

    @Getter
    private static final List<Action> actions = new LinkedList<>();

    static {
        actions.addAll(BackstagePassActions.createActions());
        actions.addAll(BrieActions.createActions());
        actions.addAll(ConjuredItemActions.createActions());
        actions.addAll(SulfurasActions.createActions());
    }

    public static Action defaultAction() {
        return new Action(item -> true, decreaseQuality(1).andThen(decreaseSellIn(1)));
    }

}
