package com.gildedrose.action.impl;

import com.gildedrose.Item;
import com.gildedrose.action.Action;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrieActionsTest {

    @Test
    public void testBrieActions_HappyFlow() {
        List<Action> actions = BrieActions.createActions();
        Item brie = new Item("Aged Brie", 10, 10);

        actions.stream()
            .filter(action -> action.rules().test(brie))
            .findFirst().orElseThrow(NullPointerException::new)
            .function().accept(brie);

        assertEquals("Aged Brie", brie.getName());
        assertEquals(9, brie.getSellIn());
        assertEquals(11, brie.getQuality());
    }

    @Test
    public void testBrieActions_HappyFlowOldBrie() {
        List<Action> actions = BrieActions.createActions();
        Item brie = new Item("Aged Brie", -10, 10);

        actions.stream()
            .filter(action -> action.rules().test(brie))
            .findFirst().orElseThrow(NullPointerException::new)
            .function().accept(brie);

        assertEquals("Aged Brie", brie.getName());
        assertEquals(-11, brie.getSellIn());
        assertEquals(12, brie.getQuality());
    }

}
