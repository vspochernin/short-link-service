package ru.vspochernin.short_link_service.handler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import ru.vspochernin.short_link_service.command.CommandType;
import ru.vspochernin.short_link_service.context.ShortLinkContext;
import ru.vspochernin.short_link_service.entity.ConfigValues;
import ru.vspochernin.short_link_service.entity.Link;
import ru.vspochernin.short_link_service.exception.ShortLinkServiceException;
import ru.vspochernin.short_link_service.repository.LinkRepository;
import ru.vspochernin.short_link_service.utils.ShortLinkUtils;
import ru.vspochernin.short_link_service.utils.ValidationUtils;

@Service
public class CreateCommandHandler implements CommandHandler {

    private ConfigValues currentConfigValues;

    private final LinkRepository linkRepository;

    public CreateCommandHandler(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public void handle(List<String> arguments) {
        String shortUrl = ShortLinkUtils.generateShortUrl();
        while (linkRepository.existsByShortUrl(shortUrl)) {
            shortUrl = ShortLinkUtils.generateShortUrl();
        }

        Link link = Link.builder()
                .user(ShortLinkContext.currentUser.get())
                .longUrl(arguments.get(0))
                .shortUrl(shortUrl)
                .maxClicks(currentConfigValues.maxClicks())
                .expirationDatetime(LocalDateTime.now().plusSeconds(currentConfigValues.expirationSeconds()))
                .build();

        linkRepository.save(link);
        System.out.println("Успешно создана короткая ссылка: " + shortUrl);
    }

    @Override
    public void validate(List<String> arguments) {
        ValidationUtils.validateIdentification();

        if (arguments.size() == 1) {
            ValidationUtils.validateLongUrlNotBlank(arguments.get(0));
            if (ShortLinkContext.configFileValues.isEmpty()) {
                throw new ShortLinkServiceException("Отсутствует конфигурационный файл");
            }
            currentConfigValues = ShortLinkContext.configFileValues.get();
        } else if (arguments.size() == 3) {
            ValidationUtils.validateLongUrlNotBlank(arguments.get(0));
            int maxClicks = ValidationUtils.validateParseMaxClicks(arguments.get(1));
            long expirationSeconds = ValidationUtils.validateParseExpirationSeconds(arguments.get(2));
            currentConfigValues = ConfigValues.mergeWithConfigFile(maxClicks, expirationSeconds);
        } else {
            throw new ShortLinkServiceException("Некорректное количество аргументов, ожидается 1 или 3");
        }
    }

    @Override
    public CommandType getHandlingCommandType() {
        return CommandType.CREATE;
    }
}
