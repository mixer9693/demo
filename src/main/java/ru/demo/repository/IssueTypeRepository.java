package ru.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.demo.entity.IssueType;

public interface IssueTypeRepository extends CrudRepository<IssueType, Integer> {
}
