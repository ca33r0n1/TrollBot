package net.projectrefresh.TrollBot.Twitch;

import com.github.twitch4j.chat.events.channel.IRCMessageEvent;
import net.projectrefresh.TrollBot.RCON.TrollEvents;
import net.projectrefresh.TrollBot.TrollBot;

public class ChannelPoints {

    public static void Redeemed(IRCMessageEvent event){
        String User = event.getUser().getName();
        String Message = event.toString().split("PRIVMSG #bamco :")[1].replace(")", "");
        //Utils.Log(User + ": " + Message);
        try {
            switch (event.getTags().get("custom-reward-id").toLowerCase()) {
                case "c1453ab0-0c7d-45b6-ae81-f61c8f0f83ae": {
                    TrollEvents.Smite("BAMCO_");
                    break;
                }

                case "0fd61844-9869-4ceb-a0d8-5ddb50abb816": {
                    TrollBot.getRcon().SendCommand("sudo", "bamco_", "summon creeper ~ ~1 ~ {Invulnerable:1,PersistenceRequired:1,Silent:1,powered:1,CustomName:\"\\\"Twitch Loves You\\\"\"}");
                    break;
                }

                case "05a232de-a607-435a-ab67-1a7bf6a02fcf": {
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
                    TrollEvents.setTime(event.getUser().getName(), Message.trim());
                    break;
                }

                case "8b599805-2b0f-442a-b56d-41600b09acfb":{
                    TrollEvents.CarePackage(Message, "BAMCO_");
                    break;
                }

                case "57fcb397-4b14-44d9-8950-4374c3860374": {
                    TrollEvents.BlockCommands();
                    TrollBot.client.getChat().sendMessage("bamco", "The Home and Back command has been toggled Kappa");
                    break;
                }

                case "e84d18c0-19a4-48a2-95cf-86d8487cb164": {
                    TrollEvents.DropHand("bamco", Message);
                    break;
                }

                //MadPack 2
                //Lucky Block
                case "78a70abc-3a1c-4fd0-95aa-11f8dd6f84b7": {
                    TrollEvents.GiveItem("bamco_", "165", "1", "");
                    break;
                }

                //MadPack 2
                //Pandora's Box
                case "62240cae-a9c8-4191-b253-b8eabc80eeb0": {
                    TrollEvents.GiveItem("bamco_", "1263", "1", "");
                    break;
                }

                default: {
                    TrollBot.client.getChat().sendMessage("bamco", "@ca33r0n1 Unknown ID - " + event.getTags().get("custom-reward-id").toLowerCase() + ". Needs adding please. <3");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
