package ru.vspochernin.short_link_service.entity;

public record ConfigValues(
        int maxClicks,
        long expirationSeconds)
{
}
