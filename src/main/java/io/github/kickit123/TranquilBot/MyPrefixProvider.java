package io.github.kickit123.TranquilBot;

import net.kautler.command.api.prefix.PrefixProvider;
import org.javacord.api.entity.message.Message;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyPrefixProvider implements PrefixProvider<Message> {
    @Override
    public String getCommandPrefix(Message message) {
        return "t!";
    }
}

