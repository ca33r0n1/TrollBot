package net.projectrefresh.TrollBot.Twitch;

import com.github.philippheuer.events4j.core.EventManager;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.IRCMessageEvent;
import net.projectrefresh.TrollBot.RCON.TrollEvents;
import net.projectrefresh.TrollBot.TrollBotApplication;

import java.util.Date;
import java.util.TimerTask;

public class IRC {



    /**
     * Main IRC handler
     * @param eventManager
     */
    public IRC (EventManager eventManager){
        eventManager.getEventHandler(SimpleEventHandler.class).onEvent(IRCMessageEvent.class, event -> {
            if (event.toString().toLowerCase().contains("privmsg")) {

                String Message = event.toString().split("PRIVMSG #bamco :")[1].replace(")", "");
                if (event.getTags().containsKey("custom-reward-id")) {
                    try {
                        switch (event.getTags().get("custom-reward-id").toLowerCase()) {
                            case "c1453ab0-0c7d-45b6-ae81-f61c8f0f83ae": {
                                TrollEvents.Smite("BAMCO_");

                                break;
                            }

                            case "0fd61844-9869-4ceb-a0d8-5ddb50abb816": {
                                TrollBotApplication.rcon.SendCommand("spawnmob", "creeper:charged", "1", "BAMCO_");
                                break;
                            }

                            case "05a232de-a607-435a-ab67-1a7bf6a02fcf": {
                                TrollBotApplication.rcon.SendCommand("Chat thinks you need to slow the F down matey");
                                TrollEvents.SlowMove(Message, "BAMCO_");
                                break;
                            }

                            case "04b60ca3-dc73-48dd-9821-40a088e06574": {
                                int random_int = (int) (Math.random() * (700 - 1 + 1) + 1);
                                TrollEvents.WheelofMisfortune(Message, "BAMCO_", random_int);
                                break;
                            }

                            case "f0968bd3-50f4-462a-a066-875b21d57751": {
                                TrollEvents.SlowDigging(Message, "BAMCO_");
                                break;
                            }

                            case "8300dbf2-a2c7-4232-903d-0dc2ffd2eb69": {
                                TrollEvents.setTime("SomePleb", Message.trim());
                                break;
                            }

                            case "8b599805-2b0f-442a-b56d-41600b09acfb":{
                                TrollEvents.CarePackage(Message, "BAMCO_");
                                break;
                            }

                            case "57fcb397-4b14-44d9-8950-4374c3860374": {
                                TrollEvents.BlockCommands();
                                TrollBotApplication.client.getChat().sendMessage("bamco", "The Home and Back command has been toggled Kappa");
                                break;
                            }

                            case "e84d18c0-19a4-48a2-95cf-86d8487cb164": {
                                TrollEvents.DropHand("bamco", Message);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    public static void SendMsg(String Message){
        TrollBotApplication.client.getChat().sendMessage("BAMCO", Message);
    }
}
