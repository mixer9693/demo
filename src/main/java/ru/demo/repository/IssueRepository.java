package ru.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.demo.entity.Issue;

public interface IssueRepository extends CrudRepository<Issue, Integer> {
}
