package com.gildedrose.action;

import com.gildedrose.Item;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public record Action(Predicate<Item> rules, Consumer<Item> function) {

    public Action {
        Objects.requireNonNull(function);
        Objects.requireNonNull(rules);
    }

}
