package ru.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.demo.entity.Comment;

import java.util.Collection;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Collection<Comment> findByIssueId(Integer issueId);
}
