package ru.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.demo.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
