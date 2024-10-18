package com.project.demo;

import java.util.List;

public interface ItemCulturalRepository {
    void addItem(ItemCultural item);
    void removeItem(ItemCultural item);
    List<ItemCultural> getAllItems();
}
