package io.github.kickit123.TranquilBot.commands.economy;

import com.mongodb.client.MongoCollection;
import io.github.kickit123.TranquilBot.models.Economy;
import io.github.kickit123.TranquilBot.models.EconomyUser;
import net.kautler.command.api.Command;
import net.kautler.command.api.annotation.Usage;
import org.javacord.api.entity.message.Message;

import javax.enterprise.context.ApplicationScoped;

import static io.github.kickit123.TranquilBot.TranquilBot.mongoDatabase;

@ApplicationScoped
@Usage("register")
public class RegisterCommand implements Command<Message> {
    @Override
    public void execute(Message incomingMessage, String prefix, String usedAlias, String parameterString) {
        incomingMessage.getChannel().sendMessage("Received command.");
        MongoCollection<Economy> collection = mongoDatabase.getCollection("servers", Economy.class);
        incomingMessage.getServer().ifPresent(s -> {
            collection.insertOne(new Economy(s.getId(), s.getMembers().stream().map(u -> new EconomyUser(u.getId())).toList()));
            incomingMessage.getChannel().sendMessage("Added server ID to Mongo database!");
        });
    }
}