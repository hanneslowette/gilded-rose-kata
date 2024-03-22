package com.gildedrose.action;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActionTest {

    @Test
    public void testCreateAction_HappyFlow() {
        new Action(item -> true, item -> {});
    }

    @Test
    public void testCreateAction_MissingArguments() {
        assertThrows(NullPointerException.class, () -> new Action(null, item -> {}));
        assertThrows(NullPointerException.class, () -> new Action(item -> true, null));
    }

}
