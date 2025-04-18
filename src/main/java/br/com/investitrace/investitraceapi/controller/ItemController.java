package br.com.investitrace.investitraceapi.controller;

import br.com.investitrace.investitraceapi.domain.model.Item;
import br.com.investitrace.investitraceapi.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable Long itemId) {
        Item item = itemService.getItemById(itemId);
        return ResponseEntity.ok(item);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{itemId}")
                .buildAndExpand(createdItem.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdItem);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Void> updateItem(@PathVariable Long itemId, @RequestBody Item item) {
        item.setId(itemId);
        itemService.updateItem(item);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{itemId}/user/{userId}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable Long userId,
            @PathVariable Long itemId) {
        itemService.deleteItem(itemId, userId);
        return ResponseEntity.noContent().build();
    }

}
