package com.gildedrose.action.impl;

import com.gildedrose.Item;
import com.gildedrose.action.Action;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SulfurasActionsTest {

    @Test
    public void testSulfurasActions_HappyFlow() {
        List<Action> actions = SulfurasActions.createActions();
        Item sulfuras = new Item("Sulfuras", 10, 10);

        actions.stream()
            .filter(action -> action.rules().test(sulfuras))
            .findFirst().orElseThrow(NullPointerException::new)
            .function().accept(sulfuras);

        assertEquals("Sulfuras", sulfuras.getName());
        assertEquals(10, sulfuras.getSellIn());
        assertEquals(80, sulfuras.getQuality());
    }
}
