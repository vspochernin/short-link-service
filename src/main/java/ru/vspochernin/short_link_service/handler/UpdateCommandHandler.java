package ru.vspochernin.short_link_service.handler;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import ru.vspochernin.short_link_service.command.CommandType;
import ru.vspochernin.short_link_service.context.ShortLinkContext;
import ru.vspochernin.short_link_service.entity.Link;
import ru.vspochernin.short_link_service.exception.ShortLinkServiceException;
import ru.vspochernin.short_link_service.repository.LinkRepository;
import ru.vspochernin.short_link_service.utils.ValidationUtils;

@Service
public class UpdateCommandHandler implements CommandHandler {

    private final LinkRepository linkRepository;

    public UpdateCommandHandler(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public void handle(List<String> arguments) {
        int linkId = ValidationUtils.validateParseLinkId(arguments.get(0));

        Optional<Link> linkO = linkRepository.findById(linkId);
        Link link = linkO.orElseThrow(() -> new ShortLinkServiceException("У вас отсутствует ссылка с таким id"));

        if (!link.getUser().equals(ShortLinkContext.currentUser.get())) {
            throw new ShortLinkServiceException("У вас отсутствует ссылка с таким id");
        }

        int newMaxClicks = ValidationUtils.validateParseMaxClicks(arguments.get(1));
        link.setMaxClicks(newMaxClicks);

        linkRepository.save(link);
    }

    @Override
    public void validate(List<String> arguments) {
        ValidationUtils.validateIdentification();
        ValidationUtils.validateArgumentsCount(arguments, 2);
        ValidationUtils.validateParseLinkId(arguments.get(0));
        ValidationUtils.validateParseMaxClicks(arguments.get(1));
    }

    @Override
    public CommandType getHandlingCommandType() {
        return CommandType.UPDATE;
    }
}
