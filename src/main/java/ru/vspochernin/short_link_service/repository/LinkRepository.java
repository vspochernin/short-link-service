package ru.vspochernin.short_link_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vspochernin.short_link_service.entity.Link;

public interface LinkRepository extends JpaRepository<Link, Integer> {

    boolean existsByShortUrl(String shortLink);
}
