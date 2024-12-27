package ru.vspochernin.short_link_service.handler;

import java.util.List;

import org.springframework.stereotype.Service;
import ru.vspochernin.short_link_service.command.CommandType;
import ru.vspochernin.short_link_service.context.ShortLinkContext;
import ru.vspochernin.short_link_service.entity.Link;
import ru.vspochernin.short_link_service.repository.LinkRepository;
import ru.vspochernin.short_link_service.utils.ValidationUtils;

@Service
public class ListCommandHandler implements CommandHandler {

    private final LinkRepository linkRepository;

    public ListCommandHandler(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public void handle(List<String> arguments) {
        List<Link> links = linkRepository.findAllByUser(ShortLinkContext.currentUser.get());

        System.out.println("Ваши ссылки:");
        links.forEach(System.out::println);
    }

    @Override
    public void validate(List<String> arguments) {
        ValidationUtils.validateIdentification();
        ValidationUtils.validateEmptyArguments(arguments);
    }

    @Override
    public CommandType getHandlingCommandType() {
        return CommandType.LIST;
    }
}
