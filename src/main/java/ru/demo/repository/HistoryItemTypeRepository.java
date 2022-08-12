package ru.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.demo.entity.HistoryItemType;

public interface HistoryItemTypeRepository extends CrudRepository<HistoryItemType, Integer> {
}
