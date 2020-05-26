package net.projectrefresh.TrollBot.Twitch;

import com.github.philippheuer.events4j.core.EventManager;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.IRCMessageEvent;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import net.projectrefresh.TrollBot.Misc.Utils;
import net.projectrefresh.TrollBot.RCON.AuthenticationException;
import net.projectrefresh.TrollBot.RCON.TrollEvents;
import net.projectrefresh.TrollBot.TrollBot;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class IRC {

    /**
     * Main IRC handler
     * @param eventManager
     */
    public IRC (EventManager eventManager){
        eventManager.getEventHandler(SimpleEventHandler.class).onEvent(IRCMessageEvent.class, event -> {
            //Utils.Log(event.toString());
            if (event.toString().toLowerCase().contains("privmsg")) {
                String User = event.getUser().getName();
                String Message = event.toString().split("PRIVMSG #bamco :")[1].replace(")", "");
                Utils.Log(User + ": " + Message);
                if (event.getTags().containsKey("custom-reward-id")) {
                    if (TrollEvents.BotPaused) {
                        TrollBot.client.getChat().sendMessage("bamco", "@" + event.getUser().getName() + " the bot is paused ask a mod for a refund!");
                        return;
                    }
                    ChannelPoints.Redeemed(event);
                }
                if (TrollBot.Admins.contains(User)) {
                    if (Message.startsWith("!tb")) {
                        String[] cmd = Message.split(" ");
                        switch (cmd[1]) {
                            case "version": {
                                TrollBot.client.getChat().sendMessage("bamco", "Troll Bot Version is " + TrollBot.Version + " - Made by @ca33r0n1 KappaPride <3 bamcotOpbrew");
                                break;
                            }

                            case "song": {
                                try {
                                    Commands.CurrentSong();
                                } catch (ParseException | SpotifyWebApiException | IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            case "admin": {
                                switch (cmd[1]) {
                                    case "on": {
                                        Commands.ToggleBot(true);
                                        break;
                                    }

                                    case "off": {
                                        Commands.ToggleBot(false);
                                        break;
                                    }

                                    case "reboot": {
                                        TrollBot.getRconFactory().disconnect();
                                        TrollBot.getRconFactory().connect();
                                        break;
                                    }

                                    case "whitelist": {
                                        switch (cmd[2]) {
                                            case "add": {
                                                try {
                                                    TrollBot.getRcon().whitelistAdd(cmd[3]);
                                                } catch (IOException | AuthenticationException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            }
                                            case "remove": {
                                                try {
                                                    TrollBot.getRcon().whitelistRemove(cmd[3]);
                                                } catch (IOException | AuthenticationException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            }
                                            default: {
                                                IRC.SendMsg("Incorrect Usage. Please use like -> !tb whitelist add ca33r0n1");
                                                break;
                                            }
                                        }
                                        break;
                                    }

                                    case "spotify": {
                                        switch (cmd[2]) {
                                            case "play": {

                                            }

                                            case "pause": {

                                            }

                                            case "queue": {

                                            }

                                            case "sr": {
                                                if (cmd[3].equalsIgnoreCase("on")) {

                                                }
                                            }
                                        }
                                    }

                                    default: {
                                        IRC.SendMsg("Unknown Command. Ask Cam for help.");
                                        break;
                                    }


                                }
                            }
                        }
                    }
                }
            }

        });
    }

    public static void SendMsg(String Message){
        TrollBot.client.getChat().sendMessage("BAMCO", Message);
    }

    public static void TwitchCommand(String User, String Message){

    }
}
