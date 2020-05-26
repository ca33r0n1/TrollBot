package net.projectrefresh.TrollBot.RCON;

import net.projectrefresh.TrollBot.TrollBot;
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

    public static Boolean BotPaused;

    @GetMapping("effect")
    public static void Effect(@RequestParam String User, @RequestParam String Effect, @RequestParam Integer time, @RequestParam Integer amp) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("effect", "give", User, Effect, time.toString(), amp.toString());
        IRC.SendMsg(User + " has given Bam the effect " + Effect);
    }

    @GetMapping("slow-digging")
    public static void SlowDigging(@RequestParam String Message,@RequestParam String User) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("effect", "give", User, "minecraft:mining_fatigue", "90", "3");
        IRC.SendMsg("Bam is about to be one weak as player! #NoMining");
    }

    @GetMapping("slow-move")
    public static void SlowMove(@RequestParam String Message, @RequestParam String User) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("effect", "give", User, "minecraft:slowness", "90", "3");
        IRC.SendMsg("Bam ain't going to be able to move anywhere soon! #SlowBam");
    }

    @GetMapping("pain")
    public static void Pain(@RequestParam String Message,@RequestParam String User) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("effect", "give", User, "minecraft:HUNGER", "60", "3");
        TrollBot.getRcon().SendCommand("effect", "give", User, "minecraft:nausea", "60", "3");
        TrollBot.getRcon().SendCommand("effect", "give", User, "minecraft:BLINDNESS", "60", "3");
        TrollBot.getRcon().SendCommand("effect", "give", User, "HARM", "1", "1");
        IRC.SendMsg("Kappa Bam's about to get a small prick LUL");
    }

    @GetMapping("summonmob")
    public static void SummonMob(@RequestParam String Mob, @RequestParam Integer Amount, @RequestParam String User) throws IncorrectRequestIdException, IOException {
        TrollBot.getRcon().SendCommand("spawnmob", Mob, Amount.toString(), User);
        IRC.SendMsg("Currently Spawning " + Mob + " x " + Amount.toString());
    }

    @GetMapping("smite")
    public static void Smite(@RequestParam String User) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("Smite", User);
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
            IRC.SendMsg("[Wheel of Misfortune] You Number was " + EventID + " bam will now place lava for a few mins Kappa");
            LavaPlace(User, "Wheel");
            return;
        }
        if (EventID <= 200) {
            IRC.SendMsg("[Wheel of Misfortune] You Number was " + EventID + ". Who like big hole. I do. Why not hit a block at your feet Kappa");
            DigDeepTroll(User,Message);
            return;
        }
        if (EventID <= 300) {
            IRC.SendMsg("[Wheel of Misfortune] You Number was " + EventID + ". Come fly with me!");
            TrollEvents.Levitate(User, Message);
            return;
        }
        if (EventID <= 400) {
            IRC.SendMsg("[Wheel of Misfortune] You Number was " + EventID + ". Double Smite and things might get toasty. Get bam to place some blocks Ow and lots of rabbits as requested by Ste Kappa");
            TrollEvents.Smite(User);
            TrollEvents.Smite(User);
            TrollEvents.LavaPlace(User, Message);
            TrollEvents.SummonMob("Rabbit", 10, User);
            return;
        }
        if (EventID <= 500) {
            IRC.SendMsg("[Wheel of Misfortune] You Number was " + EventID + ". Someone just slipped bam some MC Gang Drugs. 1 incoming trip LUL");
            TrollBot.getRcon().SendCommand("drunktroll", User);
            TrollBot.getRcon().SendCommand("effect", "give", User, "minecraft:mining_fatigue", "120", "3", "true");
            DropHand(User, Message);
            TrollBot.getRcon().SendCommand("effect", "give", User, "minecraft:slowness", "120", "5", "true");
            TrollBot.getRcon().SendCommand("effect", "give", User, "minecraft:jump_boost", "120", "30", "true");
            return;
        }

        if (EventID <= 600) {
            IRC.SendMsg("[Wheel of Misfortune] You Number was " + EventID + ". Bam's food is going now! Ow forgot to mention you spin me right round.");
            TrollBot.getRcon().SendCommand("starvetroll", User);
            SpinMeRound(User, Message);
            undoToggle(60000L, "autorotatetroll " + User);
            return;
        }
        if (EventID <= 700) {
            IRC.SendMsg("[Wheel of Misfortune] You Number was " + EventID + ". You just helped bam NotLikeThis ");
            TrollBot.getRcon().SendCommand("msg", User, "Lucky you Random event is a good one!");
            TrollBot.getRcon().SendCommand("effect", "give", User, "minecraft:absorption", "360", "3", "true");
            TrollBot.getRcon().SendCommand("give", User, "godapple", "1");
        }
    }

    @GetMapping("dirt")
    private static void Dirt(@RequestParam String user, @RequestParam String message) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("dirttroll", user);
        IRC.SendMsg("Sips Co. Premium Dirt Ordered. Express 3-7 Seconds Delivery.");
    }

    @GetMapping("care-package")
    public static void CarePackage(@RequestParam String Message, @RequestParam String User) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("minecraft:give", User, "minecraft:chest{display:{Name:\"\\\"Twitch TV Care Package\\\"\",Lore:[\"\\\""+ Message +"\\\"\"]},BlockEntityTag:{Items:[{Slot:0,id:dirt,Count:1},{Slot:1,id:dirt,Count:1},{Slot:2,id:dirt,Count:1},{Slot:3,id:dirt,Count:1},{Slot:4,id:dirt,Count:1},{Slot:5,id:dirt,Count:1},{Slot:6,id:dirt,Count:1},{Slot:7,id:dirt,Count:1},{Slot:8,id:dirt,Count:1},{Slot:9,id:arrow,Count:32},{Slot:10,id:experience_bottle,Count:32},{Slot:11,id:totem_of_undying,Count:1},{Slot:12,id:coal,Count:16},{Slot:13,id:ender_pearl,Count:1},{Slot:14,id:iron_ingot,Count:8},{Slot:15,id:enchanted_golden_apple,Count:2},{Slot:16,id:diamond,Count:2},{Slot:17,id:bow,Count:1},{Slot:18,id:dirt,Count:1},{Slot:19,id:dirt,Count:1},{Slot:20,id:dirt,Count:1},{Slot:21,id:dirt,Count:1},{Slot:22,id:dirt,Count:1},{Slot:23,id:dirt,Count:1},{Slot:24,id:dirt,Count:1},{Slot:25,id:dirt,Count:1},{Slot:26,id:dirt,Count:1}]}}", "1");
    }

    @GetMapping("time")
    public static void setTime(@RequestParam String User, @RequestParam String Time) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("time", "set", Time);
        TrollBot.getRcon().SendCommand("say", User, "has changed the time!");
    }

    @Deprecated
    public static void BlockCommands() throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("cb", "addop", "home", "Chat has stopped you from using /home. FeelsBadMan");
        TrollBot.getRcon().SendCommand("cb", "addop", "back", "Chat has stopped you from using /back. FeelsBadMan");
    }
    @Deprecated
    public static void UnblockCommands() throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("cb", "removeop", "home");
    }

    @GetMapping("spin")
    public static void SpinMeRound(@RequestParam String user,@RequestParam String Message) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("autorotatetroll", user);
        IRC.SendMsg("You spin "+ user + " right round like a record baby right round LUL");
        undoToggle(60000L, "autorotatetroll " + user);
    }

    @GetMapping("void")
    public static void VoidPlayer(@RequestParam String User, @RequestParam String Message) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("voidtroll", User);
        IRC.SendMsg("Who need the floor. You don't Kappa. Enjoy the void!");
    }

    @GetMapping("drop")
    public static void DropHand(@RequestParam String User,@RequestParam String Message) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("droptroll", User);
        undoToggle(10000L, "droptroll " + User);
        undoToggle(35000L, "droptroll " + User);
        undoToggle(60000L, "droptroll " + User);
        IRC.SendMsg("Anyone notice who clumsy bam is? The poor items Kappa");
    }

    @GetMapping("lavaplace")
    public static void LavaPlace(@RequestParam String User, @RequestParam String Message) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("lavaplacetroll", User);
        IRC.SendMsg("Things are about to get heated panicBasket ");
        undoToggle(120000L,"lavaplacetroll " + User);
    }

    @GetMapping("DigDeep")
    public static void DigDeepTroll(@RequestParam String User, @RequestParam String Message) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("deepdigtroll", User);
        IRC.SendMsg("Hope Bam likes bedrock LUL");
        undoToggle(123000L, "deepdigtroll " + User );
    }

    @GetMapping("scream")
    public static void ScremTroll(@RequestParam String User, @RequestParam String Message) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("screamtroll", User);
        IRC.SendMsg("Is that an enderman?");
    }

    @GetMapping("levitate")
    public static void Levitate(@RequestParam String User, @RequestParam String Message) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("levitatetroll", User);
        IRC.SendMsg("Let's go fly in the sky. Ow your going to be so high Kappa");
        undoToggle(60000L, "levitatetroll " + User);
    }

    @GetMapping("giveitem")
    public static void GiveItem(@RequestParam String User, @RequestParam String Item, @RequestParam String Amount, @RequestParam String Meta) throws IOException, IncorrectRequestIdException {
        TrollBot.getRcon().SendCommand("give " + User + " " + Item + " " + Amount + " " + Meta);
        IRC.SendMsg("Sending " + Item + " to " + User);
    }

    public static void undoToggle(Long Time, String Command){
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Task performed on: " + new Date() + " " +
                        "Thread's name: " + Thread.currentThread().getName());
                try {
                    TrollBot.getRcon().SendCommand(Command);
                } catch (IOException | IncorrectRequestIdException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer(Command);
        timer.schedule(task, Time);
    }

}
