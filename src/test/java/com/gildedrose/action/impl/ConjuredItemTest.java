package com.gildedrose.action.impl;

import com.gildedrose.Item;
import com.gildedrose.action.Action;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConjuredItemTest {

    @Test
    public void testConjuredActions_PositiveSellIn() {
        List<Action> actions = ConjuredItemActions.createActions();
        Item item = new Item("Conjured", 10, 10);

        actions.stream()
            .filter(action -> action.rules().test(item))
            .findFirst().orElseThrow(NullPointerException::new)
            .function().accept(item);

        assertEquals("Conjured", item.getName());
        assertEquals(9, item.getSellIn());
        assertEquals(9, item.getQuality());
    }

    @Test
    public void testConjuredActions_NegativeSellIn() {
        List<Action> actions = ConjuredItemActions.createActions();
        Item conjured = new Item("Conjured", -10, 10);

        actions.stream()
            .filter(action -> action.rules().test(conjured))
            .findFirst().orElseThrow(NullPointerException::new)
            .function().accept(conjured);

        assertEquals("Conjured", conjured.getName());
        assertEquals(-11, conjured.getSellIn());
        assertEquals(8, conjured.getQuality());
    }

}
