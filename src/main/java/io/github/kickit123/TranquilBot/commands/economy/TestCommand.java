package io.github.kickit123.TranquilBot.commands.economy;

import io.github.kickit123.TranquilBot.models.Economy;
import io.github.kickit123.TranquilBot.models.EconomyCommand;
import net.kautler.command.api.annotation.Usage;
import org.javacord.api.entity.message.Message;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Usage("testecon")
public class TestCommand implements EconomyCommand<Message> {
    @Override
    public void execute(Message incomingMessage, String prefix, String usedAlias, String parameterString, Economy economy) {
        incomingMessage.getChannel().sendMessage(economy.getId().toString());
    }
}
