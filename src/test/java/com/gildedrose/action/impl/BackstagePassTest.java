package com.gildedrose.action.impl;

import com.gildedrose.Item;
import com.gildedrose.action.Action;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackstagePassTest {

    @Test
    public void testBackstageActions_FiftyDaysLeft() {
        List<Action> actions = BackstagePassActions.createActions();
        Item item = new Item("Backstage passes", 50, 10);

        actions.stream()
            .filter(action -> action.rules().test(item))
            .findFirst().orElseThrow(NullPointerException::new)
            .function().accept(item);

        assertEquals("Backstage passes", item.getName());
        assertEquals(49, item.getSellIn());
        assertEquals(11, item.getQuality());
    }

    @Test
    public void testBackstageActions_TenDaysLeft() {
        List<Action> actions = BackstagePassActions.createActions();
        Item item = new Item("Backstage passes", 10, 10);

        actions.stream()
            .filter(action -> action.rules().test(item))
            .findFirst().orElseThrow(NullPointerException::new)
            .function().accept(item);

        assertEquals("Backstage passes", item.getName());
        assertEquals(9, item.getSellIn());
        assertEquals(12, item.getQuality());
    }

    @Test
    public void testBackstageActions_FiveDaysLeft() {
        List<Action> actions = BackstagePassActions.createActions();
        Item item = new Item("Backstage passes", 5, 10);

        actions.stream()
            .filter(action -> action.rules().test(item))
            .findFirst().orElseThrow(NullPointerException::new)
            .function().accept(item);

        assertEquals("Backstage passes", item.getName());
        assertEquals(4, item.getSellIn());
        assertEquals(13, item.getQuality());
    }

}
