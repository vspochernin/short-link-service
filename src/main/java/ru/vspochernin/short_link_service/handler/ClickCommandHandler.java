package ru.vspochernin.short_link_service.handler;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import ru.vspochernin.short_link_service.command.CommandType;
import ru.vspochernin.short_link_service.entity.Link;
import ru.vspochernin.short_link_service.exception.ShortLinkServiceException;
import ru.vspochernin.short_link_service.repository.LinkRepository;
import ru.vspochernin.short_link_service.utils.ValidationUtils;

@Service
public class ClickCommandHandler implements CommandHandler {

    private final LinkRepository linkRepository;

    public ClickCommandHandler(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public void handle(List<String> arguments) {
        Optional<Link> linkO = linkRepository.findByShortUrl(arguments.get(0));
        Link link = linkO.orElseThrow(() -> new ShortLinkServiceException("Данная короткая ссылка отсутствует"));

        if (link.isExpired()) {
            linkRepository.delete(link);
            throw new ShortLinkServiceException("Время жизни ссылки истекло, она будет удалена");
        }

        try {
            Desktop.getDesktop().browse(new URI(link.getLongUrl()));
        } catch (IOException | URISyntaxException e) {
            throw new ShortLinkServiceException("Невозможно перейти по длинной ссылке");
        }

        link.incClicks();
        if (link.isClicksSpent()) {
            System.out.println("По ссылке совершено максимально возможное количество переходов, она будет удалена");
            linkRepository.delete(link);
        } else {
            linkRepository.save(link);
        }
    }

    @Override
    public void validate(List<String> arguments) {
        ValidationUtils.validateArgumentsCount(arguments, 1);
        ValidationUtils.validateShortUrlNotBlank(arguments.get(0));
    }

    @Override
    public CommandType getHandlingCommandType() {
        return CommandType.CLICK;
    }
}
