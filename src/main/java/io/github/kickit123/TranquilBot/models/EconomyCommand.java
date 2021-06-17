package io.github.kickit123.TranquilBot.models;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import io.github.kickit123.TranquilBot.TranquilBot;
import net.kautler.command.api.Command;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import static com.mongodb.client.model.Filters.eq;


public interface EconomyCommand<M extends Message> extends Command<M> {
    default void execute(M incomingMessage, String prefix, String usedAlias, String parameterString) {
        if (incomingMessage.isServerMessage()) {
            incomingMessage.getServer().ifPresent(server -> {
                MongoCollection<Economy> collection = TranquilBot.mongoDatabase.getCollection("servers", Economy.class);
                FindIterable<Economy> fi = collection.find(eq("server_id", server.getId()));
                if (fi.spliterator().getExactSizeIfKnown() != -1) {
                    execute(incomingMessage, prefix, usedAlias, parameterString, fi.first());
                } else {
                    EmbedBuilder eBuilder = new EmbedBuilder()
                            .setTitle("Economy Not Set Up")
                            .setDescription("The economy functions of this bot have not been activated on this server." +
                                    "To activate them, an administrator must use the command **`t!register`**.");
                    incomingMessage.getChannel().sendMessage(eBuilder);
                }
            });
        }
    }

    void execute(M incomingMessage, String prefix, String usedAlias, String parameterString, Economy economy);
}
