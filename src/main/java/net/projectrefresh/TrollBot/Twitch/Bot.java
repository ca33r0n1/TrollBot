package net.projectrefresh.TrollBot.Twitch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import net.projectrefresh.TrollBot.RCON.AuthenticationException;
import net.projectrefresh.TrollBot.RCON.RCon;
import net.projectrefresh.TrollBot.TrollBotApplication;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class Bot {

    /**
     * Holds the Bot Configuration
     */
    private Configuration configuration;

    private Properties TrollBot;


    private OAuth2Credential credential;

    /**
     * Twitch4J API
     */
    private TwitchClient twitchClient;




    /**
     * Constructor
     */
    public Bot() {
        // Load Configuration
        loadConfiguration(true);

        TwitchClientBuilder clientBuilder = TwitchClientBuilder.builder();

        //region Auth
        //endregion

        //region TwitchClient
        twitchClient = clientBuilder
                .withClientId(configuration.getApi().get("twitch_client_id"))
                .withClientSecret(configuration.getApi().get("twitch_client_secret"))
                .withEnableHelix(true)
                /*
                 * Chat Module
                 * Joins irc and triggers all chat based events (viewer join/leave/sub/bits/gifted subs/...)
                 */
                .withChatAccount(credential)
                .withEnableChat(true)
                /*
                 * GraphQL has a limited support
                 * Don't expect a bunch of features enabling it
                 */
                /*
                 * Kraken is going to be deprecated
                 * see : https://dev.twitch.tv/docs/v5/#which-api-version-can-you-use
                 * It is only here so you can call methods that are not (yet)
                 * implemented in Helix
                 */
                .withEnableKraken(true)
                /*
                 * Build the TwitchClient Instance
                 */
                .withEnablePubSub(false)
                .build();
        //endregion
    }

    /**
     * Method to register all features
     */
    public void registerFeatures() {
        // Register Event-based features
        new IRC(twitchClient.getEventManager());
    }

    /**
     * Load the Configuration
     */
    public void loadConfiguration(boolean cached){
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("bot_config.yaml");
            InputStream inputStream = classloader.getResourceAsStream("TrollBot.conf");
            OutputStream os = new FileOutputStream(new File("bot_config.yaml"));
            os.write(Objects.requireNonNull(classloader.getResourceAsStream("bot_config.yaml")).read());
            os = new FileOutputStream(new File("TrollBot.conf"));
            os.write(Objects.requireNonNull(classloader.getResourceAsStream("TrollBot.conf")).read());
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            configuration = mapper.readValue(is, Configuration.class);
            TrollBot = new Properties();
            TrollBot.load(inputStream);

            credential = new OAuth2Credential("twitch", TrollBot.getProperty("oauth"));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Unable to load Configuration ... Exiting.");
            System.exit(1);
        }
    }

    public void start() {
        TrollBotApplication.client = twitchClient;
        // Connect to all channels
        for (String channel : configuration.getChannels()) {
            twitchClient.getChat().joinChannel(channel);
            twitchClient.getChat().sendMessage(channel, "Troll Bot " + TrollBotApplication.Version + " - Online LUL - Made by @ca33r0n1 KappaPride <3 bamcotOpbrew");
            try {
                TrollBotApplication.rcon = new RCon(TrollBot.getProperty(channel + "-rcon-ip", "localhost"),TrollBot.getProperty(channel + "-rcon-port", "25575"), TrollBot.getProperty(channel+"-rcon-password", "password").toCharArray());
            } catch (IOException | AuthenticationException e) {
                e.printStackTrace();
                twitchClient.getChat().sendMessage(channel, "Troll Bot has failed to connect to the Minecraft Server. Stopping the Troll Bot");
                System.exit(0);
            }
        }

        try {
            TrollBotApplication.rcon.say("Troll bot is active. Watch your backs. ");

        } catch (AuthenticationException | IOException e) {
            e.printStackTrace();
        }
    }

}