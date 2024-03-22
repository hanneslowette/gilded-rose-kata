package com.gildedrose.action;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class ActionFactoryTest {

    @Test
    public void testNameMatches_HappyFlow() {
        Item item = new Item("rune scimmy", 0, 0);
        assertTrue(ActionFactory.nameMatches("rune scimmy").test(item));
        assertEquals("rune scimmy", item.getName());
        assertEquals(0, item.getSellIn());
        assertEquals(0, item.getQuality());
    }

    @Test
    public void testNameMatches_Mismatch() {
        Item item = new Item("adamant scimmy", 0, 0);
        assertFalse(ActionFactory.nameMatches("rune scimmy").test(item));
        assertEquals("adamant scimmy", item.getName());
        assertEquals(0, item.getSellIn());
        assertEquals(0, item.getQuality());
    }

    @Test
    public void testSellInBetween_HappyFlow() {
        Item item = new Item("rune dagger", 10, 0);
        assertTrue(ActionFactory.sellInBetween(5, 15).test(item));
        assertEquals("rune dagger", item.getName());
        assertEquals(10, item.getSellIn());
        assertEquals(0, item.getQuality());
    }

    @Test
    public void testSellInBetween_OutsideRange() {
        Item item = new Item("rune dagger", 20, 0);
        assertFalse(ActionFactory.sellInBetween(5, 15).test(item));
        assertEquals("rune dagger", item.getName());
        assertEquals(20, item.getSellIn());
        assertEquals(0, item.getQuality());
    }

    @Test
    public void testSellIn_HappyFlow() {
        Item item = new Item("rune dagger", 20, 0);
        assertTrue(ActionFactory.sellIn(20).test(item));
        assertEquals("rune dagger", item.getName());
        assertEquals(20, item.getSellIn());
        assertEquals(0, item.getQuality());
    }

    @Test
    public void testSellIn_NotEqual() {
        Item item = new Item("rune dagger", 15, 0);
        assertFalse(ActionFactory.sellIn(20).test(item));
        assertEquals("rune dagger", item.getName());
        assertEquals(15, item.getSellIn());
        assertEquals(0, item.getQuality());
    }

    @Test
    public void testSetQuality_HappyFlow() {
        Item item = new Item("rune dagger", 15, 0);
        ActionFactory.setQuality(22).accept(item);

        assertEquals("rune dagger", item.getName());
        assertEquals(15, item.getSellIn());
        assertEquals(22, item.getQuality());
    }

    @Test
    public void testIncreaseQuality_HappyFlow() {
        Item item = new Item("rune dagger", 15, 0);
        ActionFactory.increaseQuality(1).accept(item);

        assertEquals("rune dagger", item.getName());
        assertEquals(15, item.getSellIn());
        assertEquals(1, item.getQuality());
    }

    @Test
    public void testIncreaseQuality_Overflow50() {
        Item item = new Item("rune dagger", 15, 49);
        ActionFactory.increaseQuality(88).accept(item);

        assertEquals("rune dagger", item.getName());
        assertEquals(15, item.getSellIn());
        assertEquals(50, item.getQuality());
    }

    @Test
    public void testIncreaseQuality_Overflow100() {
        Item item = new Item("rune dagger", 15, 4);
        ActionFactory.increaseQuality(88).accept(item);

        assertEquals("rune dagger", item.getName());
        assertEquals(15, item.getSellIn());
        assertEquals(50, item.getQuality());
    }

    @Test
    public void testIncreaseQuality_AlreadyOverflowed() {
        Item item = new Item("rune dagger", 15, 100);
        ActionFactory.increaseQuality(1).accept(item);

        assertEquals("rune dagger", item.getName());
        assertEquals(15, item.getSellIn());
        assertEquals(50, item.getQuality());
    }

    @Test
    public void testDecreaseQuality_HappyFlow() {
        Item item = new Item("rune dagger", 15, 30);
        ActionFactory.decreaseQuality(1).accept(item);

        assertEquals("rune dagger", item.getName());
        assertEquals(15, item.getSellIn());
        assertEquals(29, item.getQuality());
    }

    @Test
    public void testDecreaseQuality_Underflow0() {
        Item item = new Item("rune dagger", 15, 30);
        ActionFactory.decreaseQuality(100).accept(item);

        assertEquals("rune dagger", item.getName());
        assertEquals(15, item.getSellIn());
        assertEquals(0, item.getQuality());
    }

    @Test
    public void testDecreaseQuality_AlreadyUnderflow() {
        Item item = new Item("rune dagger", 15, -50);
        ActionFactory.decreaseQuality(100).accept(item);

        assertEquals("rune dagger", item.getName());
        assertEquals(15, item.getSellIn());
        assertEquals(0, item.getQuality());
    }

    @Test
    public void testDecreaseSellIn_HappyFlow() {
        Item item = new Item("rune dagger", 15, 30);
        ActionFactory.decreaseSellIn(1).accept(item);

        assertEquals("rune dagger", item.getName());
        assertEquals(14, item.getSellIn());
        assertEquals(30, item.getQuality());
    }

    @Test
    public void testDecreaseSellIn_Negative() {
        Item item = new Item("rune dagger", 0, 30);
        ActionFactory.decreaseSellIn(1).accept(item);

        assertEquals("rune dagger", item.getName());
        assertEquals(-1, item.getSellIn());
        assertEquals(30, item.getQuality());
    }

    public static Consumer<? super Item> decreaseSellIn(int amount) {
        return item -> item.setSellIn(item.getSellIn() - amount);
    }


}
