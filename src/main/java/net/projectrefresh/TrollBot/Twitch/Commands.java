package net.projectrefresh.TrollBot.Twitch;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import net.projectrefresh.TrollBot.RCON.IncorrectRequestIdException;
import net.projectrefresh.TrollBot.RCON.TrollEvents;
import net.projectrefresh.TrollBot.TrollBot;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class Commands {

    public static void ToggleBot(Boolean isActive) {
        TrollEvents.BotPaused = isActive;
        if (!isActive){
            IRC.SendMsg("Troll Bot is going into standby. Later Dudes! <3");
        }
        if (isActive){
            IRC.SendMsg("Troll Bot is back baby let the pain start............. now Kappa #SmiteBam");
            try {
                TrollEvents.Smite("bamco_");
            } catch (IOException | IncorrectRequestIdException e) {
                e.printStackTrace();
            }
        }
    }

    public static void SongRequest(String spotifyurl){
        SpotifyApi api = TrollBot.getSpotify();
        api.addItemToUsersPlaybackQueue(spotifyurl);
        IRC.SendMsg("Added song to the queue!");
    }

    public static void CurrentSong() throws ParseException, SpotifyWebApiException, IOException {
        IRC.SendMsg("Current song playing via spotify is: " + TrollBot.getSpotify().getUsersCurrentlyPlayingTrack().build().execute());
    }



}

