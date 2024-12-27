package ru.vspochernin.short_link_service.handler;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import ru.vspochernin.short_link_service.command.CommandType;
import ru.vspochernin.short_link_service.context.ShortLinkContext;
import ru.vspochernin.short_link_service.entity.User;
import ru.vspochernin.short_link_service.exception.ShortLinkServiceException;
import ru.vspochernin.short_link_service.repository.UserRepository;
import ru.vspochernin.short_link_service.utils.ValidationUtils;

@Service
public class LoginCommandHandler implements CommandHandler {

    private final UserRepository userRepository;

    public LoginCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void handle(List<String> arguments) {
        UUID uuid = UUID.fromString(arguments.get(0));

        Optional<User> userO = userRepository.findById(uuid);
        userO.orElseThrow(() -> new ShortLinkServiceException("Пользователь " + uuid + " не существует"));

        System.out.println("Добро пожаловать, " + uuid);
        ShortLinkContext.currentUser = userO;
    }

    @Override
    public void validate(List<String> arguments) {
        ValidationUtils.validateArgumentsCount(arguments, 1);

        String uuidStr = arguments.get(0);
        try {
            UUID.fromString(uuidStr);
        } catch (IllegalArgumentException e) {
            throw new ShortLinkServiceException("Некорректный UUID: " + uuidStr);
        }
    }

    @Override
    public CommandType getHandlingCommandType() {
        return CommandType.LOGIN;
    }
}
