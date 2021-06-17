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

import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class JavacordProducer {
    @Inject
    private Logger logger;

    @Inject
    @Named
    private String discordToken;

    @Produces
    @ApplicationScoped
    private DiscordApi produceDiscordApi() {
        return new DiscordApiBuilder()
                .setToken(discordToken)
                .setAllIntents()
                .login()
                .whenComplete((discordApi, throwable) -> {
                    if (throwable != null) {
                        logger.error("Exception while logging in to Discord", throwable);
                    }
                })
                .join();
    }

    private void disposeDiscordApi(@Disposes DiscordApi discordApi) {
        discordApi.disconnect();
    }
}