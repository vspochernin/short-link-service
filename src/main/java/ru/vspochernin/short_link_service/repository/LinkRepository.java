package ru.vspochernin.short_link_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vspochernin.short_link_service.entity.Link;
import ru.vspochernin.short_link_service.entity.User;

public interface LinkRepository extends JpaRepository<Link, Integer> {

    boolean existsByShortUrl(String shortLink);

    List<Link> findAllByUser(User user);
}
