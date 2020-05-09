package net.projectrefresh.TrollBot;

import com.github.twitch4j.TwitchClient;
import net.projectrefresh.TrollBot.RCON.RCon;
import net.projectrefresh.TrollBot.RCON.TrollEvents;
import net.projectrefresh.TrollBot.Twitch.Bot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Properties;

@SpringBootApplication
public class TrollBotApplication {

    public static RCon rcon;
    public static TwitchClient client;
    public static HashMap<String, Boolean> ApprovedTrollers = new HashMap<>();
    public static TrollEvents events;
    public static Properties properties;
    public static String Version = "2.9 No StreamLabs";



    public static void main(String[] args) {

        SpringApplication.run(TrollBotApplication.class, args);
        properties = new Properties();
        events = new TrollEvents();
        Bot bot = new Bot();
        bot.registerFeatures();
        bot.start();
    }

}
