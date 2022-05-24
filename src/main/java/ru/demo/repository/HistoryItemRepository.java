package ru.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.demo.entity.HistoryItem;

public interface HistoryItemRepository extends CrudRepository<HistoryItem, Integer> {
}
