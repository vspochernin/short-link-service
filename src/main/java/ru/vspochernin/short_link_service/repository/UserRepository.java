package ru.vspochernin.short_link_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vspochernin.short_link_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
