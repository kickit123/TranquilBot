/*
 * Copyright 2019 Björn Kautler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.kickit123.TranquilBot;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Named;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Boolean.TRUE;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@ApplicationScoped
public class TranquilBot {
    @Produces
    @Named
    public static String discordToken;

    public static MongoDatabase mongoDatabase;

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream is = TranquilBot.class.getResourceAsStream("/BotProperties.properties")) {
            properties.load(is);
        } catch (IOException e) {
            System.out.println("Can't find the .properties file. Exiting the program.");
            System.exit(1);
        }

        MongoClient mongoClient = MongoClients.create(properties.getProperty("mongoDBURL"));

        // create codec registry for POJOs
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        // get handle to "db" database
        mongoDatabase = mongoClient.getDatabase("db").withCodecRegistry(pojoCodecRegistry);

        discordToken = properties.getProperty("discordToken");
        SeContainerInitializer.newInstance()
                .addProperty("javax.enterprise.inject.scan.implicit", TRUE)
                .initialize();
    }
}