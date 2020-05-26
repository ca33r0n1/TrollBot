package net.projectrefresh.TrollBot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.twitch4j.TwitchClient;
import com.wrapper.spotify.SpotifyApi;
import net.projectrefresh.TrollBot.Data.RedisController;
import net.projectrefresh.TrollBot.RCON.RCONClient;
import net.projectrefresh.TrollBot.RCON.RCONFactory;
import net.projectrefresh.TrollBot.RCON.TrollEvents;
import net.projectrefresh.TrollBot.Spotify.SpotifyController;
import net.projectrefresh.TrollBot.Twitch.Bot;
import net.projectrefresh.TrollBot.Twitch.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
public class TrollBot {

    public static TwitchClient client;
    public static String Version = "BETA - #1";
    public static List<String> Admins = new ArrayList<>();
    public static Bot bot;
    public static Configuration configuration;

    //Controllers
    private static RCONFactory rcon;
    private static SpotifyController spotify;
    private static RedisController redis;



    public static void main(String[] args) {

        SpringApplication.run(TrollBot.class, args);
        loadConfiguration();
        bot = new Bot();
        spotify = new SpotifyController();
        redis = new RedisController();
        rcon = new RCONFactory(configuration);

    }

    /**
     * Load the Configuration
     */
    private static void loadConfiguration() {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("TrollBot.yaml");

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            configuration = mapper.readValue(is, Configuration.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Unable to load Configuration ... Exiting.");
            System.exit(1);
        }
    }

    //Getter and Setter's


    public static RCONClient getRcon() {
        return rcon.getClient();
    }

    public static RCONFactory getRconFactory(){
        return rcon;
    }

    public static SpotifyApi getSpotify() {
        return spotify.getApi();
    }

    public static Jedis getRedis() {
        return redis.getJedis();
    }

}
