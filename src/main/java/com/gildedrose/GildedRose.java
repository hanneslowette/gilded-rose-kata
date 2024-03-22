package com.gildedrose;

import com.gildedrose.action.impl.ActionsDatabase;
import lombok.Getter;

import java.util.List;

import static com.gildedrose.action.impl.ActionsDatabase.defaultAction;

public class GildedRose {

    @Getter
    private final List<Item> items;

    public GildedRose(Item[] items) {
        this.items = List.of(items);
    }

    public void updateQuality() {
        items.stream().parallel().forEach(item -> ActionsDatabase.getActions()
            .stream()
            .filter(action -> action.rules().test(item))
            .findFirst().orElse(defaultAction())
            .function().accept(item));
    }

}
