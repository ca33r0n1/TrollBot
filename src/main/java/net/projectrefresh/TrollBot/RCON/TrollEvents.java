package net.projectrefresh.TrollBot.RCON;

import net.projectrefresh.TrollBot.TrollBotApplication;
import net.projectrefresh.TrollBot.Twitch.IRC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("/troll/")
public class TrollEvents {

    public TrollEvents(){

    }

    public static Boolean HomeCommand;

    @GetMapping("effect")
    public static void Effect(@RequestParam String User, @RequestParam String Effect, @RequestParam Integer time, @RequestParam Integer amp) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("effect", "give", User, Effect, time.toString(), amp.toString());
        IRC.SendMsg(User + " has given Bam the effect " + Effect);
    }

    @GetMapping("666")
    public static void HellOnEarth(@RequestParam String Message,@RequestParam String User) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("msg", User, ("Hey, Soooo " + Message + " has caused a Random event right. You will never guess what it landed on"));
        TrollBotApplication.rcon.SendCommand("msg", User, "&4666 &rof all things. Sorry the devil has been summoned. Prepare for hell mate. Kisses");
        TrollBotApplication.rcon.SendCommand("effect", "give", User, "minecraft:mining_fatigue", "120", "3");
        TrollBotApplication.rcon.SendCommand("effect", "give", User, "minecraft:slowness", "120", "5");
        TrollBotApplication.rcon.SendCommand("effect", "give", User, "minecraft:nausea", "60", "3");
        TrollBotApplication.rcon.SendCommand("effect", "give", User, "minecraft:BLINDNESS", "60", "3");
        TrollBotApplication.rcon.SendCommand("effect", "give", User, "minecraft:WITHER", "60", "3");
        TrollBotApplication.rcon.SendCommand("effect", "give", User, "minecraft:HUNGER", "60", "3");
        TrollBotApplication.rcon.SendCommand("void", User);
        TrollBotApplication.rcon.SendCommand("burn", User);
        IRC.SendMsg(User + " has called on the devil and is about to cause Hell in the overworld.");
    }

    @GetMapping("slow-digging")
    public static void SlowDigging(@RequestParam String Message,@RequestParam String User) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("effect", "give", User, "minecraft:mining_fatigue", "90", "3");
        IRC.SendMsg("Bam is about to be one weak as player! #NoMining");
    }

    @GetMapping("slow-move")
    public static void SlowMove(@RequestParam String Message, @RequestParam String User) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("effect", "give", User, "minecraft:slowness", "90", "3");
        IRC.SendMsg("Bam ain't going to be able to move anywhere soon! #SlowBam");
    }

    @GetMapping("pain")
    public static void Pain(@RequestParam String Message,@RequestParam String User) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("effect", "give", User, "minecraft:HUNGER", "60", "3");
        TrollBotApplication.rcon.SendCommand("effect", "give", User, "minecraft:nausea", "60", "3");
        TrollBotApplication.rcon.SendCommand("effect", "give", User, "minecraft:BLINDNESS", "60", "3");
        TrollBotApplication.rcon.SendCommand("effect", "give", User, "HARM", "1", "1");
        IRC.SendMsg("Kappa Bam's about to get a small prick LUL");
    }

    @GetMapping("summonmob")
    public static void SummonMob(@RequestParam String Mob, @RequestParam Integer Amount, @RequestParam String User) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("spawnmob", Mob, Amount.toString(), User);
        IRC.SendMsg("Currently Spawning " + Mob + " x " + Amount.toString());
    }

    @GetMapping("smite")
    public static void Smite(@RequestParam String User) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("Smite", User);
        IRC.SendMsg("Zap incoming!");
    }


    @GetMapping("fate")
    public static void WheelofMisfortune(String Message, String User, Integer EventID) throws IOException, IncorrectRequestIdException {
        if (EventID == 666) {
            //TrollEvents.HellOnEarth(Message, User);
            IRC.SendMsg("This would normally have caused the most painful troll however its broken. Ask Cam to manually invoke this");
            return;
        }
        if (EventID <= 100) {
            IRC.SendMsg("[Wheel of Misfortune] You Number was " + EventID + "This is going to make bam run otherwise he gonna die");
            LavaPlace(User, "Wheel");
            return;
        }
        if (EventID <= 200) {
            IRC.SendMsg("[Wheel of Misfortune] You Number was " + EventID + ". This might be broken however some aliens should be coming to abduct bam");
            DigDeepTroll(User,Message);
            return;
        }
        if (EventID <= 300) {
            IRC.SendMsg("[Wheel of Misfortune] You Number was " + EventID + ". Who likes loud noises?");
            TrollEvents.ScremTroll(User, Message);
            return;
        }
        if (EventID <= 400) {
            IRC.SendMsg("[Wheel of Misfortune] You Number was " + EventID + ". Double Smite and things might get toasty. Ow and lots of rabbits as requested by Ste Kappa");
            TrollEvents.Smite(User);
            TrollEvents.Smite(User);
            TrollEvents.LavaPlace(User, Message);
            TrollEvents.SummonMob("Rabbit", 100, User);
            return;
        }
        if (EventID <= 500) {
            IRC.SendMsg("[Wheel of Misfortune] You Number was " + EventID + ". Someone just slipped bam some MC Drug. 1 incoming trip LUL");
            TrollBotApplication.rcon.SendCommand("drunktroll", User);
            TrollBotApplication.rcon.SendCommand("effect", "give", User, "minecraft:mining_fatigue", "120", "3");
            TrollBotApplication.rcon.SendCommand("effect", "give", User, "minecraft:slowness", "120", "5");
            return;
        }

        if (EventID <= 600) {
            IRC.SendMsg("[Wheel of Misfortune] You Number was " + EventID + ". Bam's food is going now! Ow forgot to mention you spin me right round.");
            TrollBotApplication.rcon.SendCommand("starvetroll", User);
            SpinMeRound(User, Message);
            undoToggle(60000L, "autorotatetroll " + User);
            return;
        }
        if (EventID <= 700) {
            IRC.SendMsg("[Wheel of Misfortune] You Number was " + EventID + ". You just helped bam NotLikeThis ");
            TrollBotApplication.rcon.SendCommand("msg", User, "Lucky you Random event is a good one!");
            TrollBotApplication.rcon.SendCommand("effect", "give", User, "minecraft:absorption", "360", "3");
            TrollBotApplication.rcon.SendCommand("give", User, "godapple", "1");
        }
    }

    @GetMapping("dirt")
    private static void Dirt(@RequestParam String user, @RequestParam String message) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("dirttroll", user);
        IRC.SendMsg("Sips Co. Premium Dirt Ordered. Express 3-7 Seconds Delivery.");
    }

    @GetMapping("care-package")
    public static void CarePackage(@RequestParam String Message, @RequestParam String User) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("minecraft:give", User, "minecraft:chest{display:{Name:\"\\\"Twitch TV Care Package\\\"\",Lore:[\"\\\""+ Message +"\\\"\"]},BlockEntityTag:{Items:[{Slot:0,id:dirt,Count:1},{Slot:1,id:dirt,Count:1},{Slot:2,id:dirt,Count:1},{Slot:3,id:dirt,Count:1},{Slot:4,id:dirt,Count:1},{Slot:5,id:dirt,Count:1},{Slot:6,id:dirt,Count:1},{Slot:7,id:dirt,Count:1},{Slot:8,id:dirt,Count:1},{Slot:9,id:arrow,Count:32},{Slot:10,id:experience_bottle,Count:32},{Slot:11,id:totem_of_undying,Count:1},{Slot:12,id:coal,Count:16},{Slot:13,id:ender_pearl,Count:1},{Slot:14,id:iron_ingot,Count:8},{Slot:15,id:enchanted_golden_apple,Count:2},{Slot:16,id:diamond,Count:2},{Slot:17,id:bow,Count:1},{Slot:18,id:dirt,Count:1},{Slot:19,id:dirt,Count:1},{Slot:20,id:dirt,Count:1},{Slot:21,id:dirt,Count:1},{Slot:22,id:dirt,Count:1},{Slot:23,id:dirt,Count:1},{Slot:24,id:dirt,Count:1},{Slot:25,id:dirt,Count:1},{Slot:26,id:dirt,Count:1}]}}", "1");
    }

    @GetMapping("time")
    public static void setTime(@RequestParam String User, @RequestParam String Time) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("time", "set", Time);
        TrollBotApplication.rcon.SendCommand("say", User, "has changed the time!");
    }

    @Deprecated
    public static void BlockCommands() throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("cb", "addop", "home", "Chat has stopped you from using /home. FeelsBadMan");
        TrollBotApplication.rcon.SendCommand("cb", "addop", "back", "Chat has stopped you from using /back. FeelsBadMan");
    }
    @Deprecated
    public static void UnblockCommands() throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("cb", "removeop", "home");
    }

    @GetMapping("spin")
    public static void SpinMeRound(@RequestParam String user,@RequestParam String Message) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("autorotatetroll", user);
        IRC.SendMsg("You spin "+ user + " right round like a record baby right round LUL");
    }

    @GetMapping("void")
    public static void VoidPlayer(@RequestParam String User, @RequestParam String Message) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("voidtroll", User);
        IRC.SendMsg("Who need the floor. You don't Kappa. Enjoy the void!");
    }

    @GetMapping("drop")
    public static void DropHand(@RequestParam String User,@RequestParam String Message) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("droptroll", User);
        IRC.SendMsg("Anyone notice who clumsy bam is? The poor items Kappa");
    }

    @GetMapping("lavaplace")
    public static void LavaPlace(@RequestParam String User, @RequestParam String Message) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("lavaplacetroll", User);
        IRC.SendMsg("Things are about to get heated panicBasket ");
        undoToggle(60000L,"lavaplacetroll BAMCO_");
    }

    @GetMapping("DigDeep")
    public static void DigDeepTroll(@RequestParam String User, @RequestParam String Message) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("deepdigtroll", User);
        IRC.SendMsg("Hope Bam likes bedrock LUL");
    }

    @GetMapping("scream")
    public static void ScremTroll(@RequestParam String User, @RequestParam String Message) throws IOException, IncorrectRequestIdException {
        TrollBotApplication.rcon.SendCommand("screamtroll", User);
        IRC.SendMsg("RIP HEADPHONE USERS...");
    }

    public static void undoToggle(Long Time, String Command){
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Task performed on: " + new Date() + "n" +
                        "Thread's name: " + Thread.currentThread().getName());
                try {
                    TrollBotApplication.rcon.SendCommand(Command);
                } catch (IOException | IncorrectRequestIdException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer(Command);
        timer.schedule(task, Time);
    }

}
