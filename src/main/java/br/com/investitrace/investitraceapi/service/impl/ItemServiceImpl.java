package br.com.investitrace.investitraceapi.service.impl;

import br.com.investitrace.investitraceapi.domain.model.Item;
import br.com.investitrace.investitraceapi.domain.repository.ItemRepository;
import br.com.investitrace.investitraceapi.domain.repository.UserRepository;
import br.com.investitrace.investitraceapi.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final UserRepository userRepository;

    public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Item createItem(Item item) {
        validateItemDoesNotExist(item);
        validateUserExists(item.getUserId());
        return itemRepository.save(item);
    }

    @Override
    @Transactional
    public void updateItem(Item item) {
        Item existingItem = getItemOrThrow(item.getId());

        if (!existingItem.getUserId().equals(item.getUserId()))
            throw new IllegalArgumentException("UserId mismatch. Cannot update item with a different user.");

        existingItem.setName(item.getName());
        existingItem.setDescription(item.getDescription());

        itemRepository.save(existingItem);
    }

    @Override
    @Transactional
    public void deleteItem(Long itemId, Long userId) {
        if(!itemRepository.existsById(itemId))
            throw new IllegalArgumentException("Item not found with id: " + itemId);

        if(!userRepository.existsById(userId))
            throw new IllegalArgumentException("User not found with id: " + userId);

        itemRepository.deleteById(itemId);
    }

    @Override
    @Transactional
    public Item getItemById(Long itemId) {
        return getItemOrThrow(itemId);
    }

    @Override
    @Transactional
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    private void validateItemDoesNotExist(Item item) {
        if (itemRepository.existsById(item.getId())) {
            throw new IllegalArgumentException("Item already exists with id: " + item.getId());
        }
    }

    private void validateUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
    }

    private Item getItemOrThrow(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + itemId));
    }
}
