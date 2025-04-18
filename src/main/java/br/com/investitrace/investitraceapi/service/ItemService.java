package br.com.investitrace.investitraceapi.service;

import br.com.investitrace.investitraceapi.domain.model.Item;

import java.util.List;

public interface ItemService {
    Item createItem(Item item);

    void updateItem(Item item);

    void deleteItem(Long itemId, Long userId);

    Item getItemById(Long itemId);

    List<Item> getAllItems();
}
