package io.github.kickit123.TranquilBot.commands;

import net.kautler.command.api.Command;
import net.kautler.command.api.annotation.Usage;
import org.javacord.api.entity.message.Message;
import org.javacord.api.util.logging.ExceptionLogger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Usage("ping")
public class PingCommand implements Command<Message> {
    @Override
    public void execute(Message incomingMessage, String prefix, String usedAlias, String parameterString) {
        incomingMessage.getChannel()
                .sendMessage("pong: " + parameterString)
                .exceptionally(ExceptionLogger.get());
    }
}