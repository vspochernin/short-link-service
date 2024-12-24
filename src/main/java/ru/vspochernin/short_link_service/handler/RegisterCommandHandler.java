package ru.vspochernin.short_link_service.handler;

import java.util.List;

import org.springframework.stereotype.Service;
import ru.vspochernin.short_link_service.command.CommandType;
import ru.vspochernin.short_link_service.entity.User;
import ru.vspochernin.short_link_service.repository.UserRepository;
import ru.vspochernin.short_link_service.utils.ValidationUtils;

@Service
public class RegisterCommandHandler implements CommandHandler {

    private final UserRepository userRepository;

    public RegisterCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void handle(List<String> arguments) {
        System.out.println("Будет произведена регистрация");
        User savedUser = userRepository.save(new User());
        System.out.println("Регистрация прошла успешно, запишите ваш uuid: " + savedUser.getId());
    }

    @Override
    public void validate(List<String> arguments) {
        ValidationUtils.validateEmptyArguments(arguments);
    }

    @Override
    public CommandType getHandlingCommandType() {
        return CommandType.REGISTER;
    }
}
