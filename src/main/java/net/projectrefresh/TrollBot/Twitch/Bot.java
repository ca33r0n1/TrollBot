package net.projectrefresh.TrollBot.Twitch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import net.projectrefresh.TrollBot.RCON.AuthenticationException;
import net.projectrefresh.TrollBot.RCON.RCONClient;
import net.projectrefresh.TrollBot.RCON.TrollEvents;
import net.projectrefresh.TrollBot.TrollBot;

import java.io.*;

public class Bot {

    private OAuth2Credential credential;

    /**
     * Twitch4J API
     */
    private final TwitchClient twitchClient;

    /**
     * Constructor
     */
    public Bot() {
                TwitchClientBuilder clientBuilder = TwitchClientBuilder.builder();
        credential = new OAuth2Credential(
                "twitch",
                TrollBot.configuration.getCredentials().get("irc")
        );
        twitchClient = clientBuilder
                .withClientId(TrollBot.configuration.getApi().get("twitch_client_id"))
                .withClientSecret(TrollBot.configuration.getApi().get("twitch_client_secret"))
                .withEnableHelix(true)
                .withChatAccount(credential)
                .withEnableChat(true)
                .withEnableKraken(true)
                .withEnablePubSub(false)
                .withEnableTMI(true)
                .build();
        TrollBot.Admins.addAll(TrollBot.configuration.getAdmins());
        registerFeatures();
        start();
    }



    /**
     * Method to register all features
     */
    public void registerFeatures() {
        // Register Event-based features
        new IRC(twitchClient.getEventManager());
    }

    public void start() {
        TrollEvents.BotPaused = false;
        TrollBot.client = twitchClient;
        // Connect to all channels
        for (String channel : TrollBot.configuration.getChannels()) {
            twitchClient.getChat().joinChannel(channel);
            twitchClient.getChat().sendMessage(channel, "Troll Bot " + TrollBot.Version + " - Online LUL - Made by @ca33r0n1 KappaPride <3 bamcotOpbrew");
        }

        try {
            TrollBot.getRcon().say("Troll bot " + TrollBot.Version + " is active. Watch your backs. ");

        } catch (AuthenticationException | IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        twitchClient.getChat().sendMessage("bamco","Trollbot is taking a break. In a bit laddy!");
        TrollBot.client.close();
        TrollBot.getRconFactory().disconnect();
    }

}