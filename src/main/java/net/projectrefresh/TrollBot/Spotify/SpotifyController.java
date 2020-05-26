package net.projectrefresh.TrollBot.Spotify;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import net.projectrefresh.TrollBot.TrollBot;
import org.apache.hc.core5.http.ParseException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URI;

public class SpotifyController {

    private SpotifyApi api;
    private String accesstoken;
    private static final ClientCredentialsRequest clientCredentialsRequest = TrollBot.getSpotify().clientCredentials()
            .build();

    private boolean SongRequest;

    public SpotifyController(){
        api = new SpotifyApi.Builder()
                .setClientId(TrollBot.configuration.getSpotify().get("spotify-ClientID"))
                .setClientSecret(TrollBot.configuration.getSpotify().get("spotify-Secret"))
                .setRedirectUri(URI.create("localhost/api/spotify-callback"))
                .build();
        SongRequest = false;
    }



    public static void clientCredentials_Sync() {
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            TrollBot.getSpotify().setAccessToken(clientCredentials.getAccessToken());

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @GetMapping("/api/spotify-callback")
    public String callback(Model model, @RequestParam String token){
        accesstoken = token;
        return "spotify-callback";
    }


    public SpotifyApi getApi() {
        return api;
    }

    public boolean isSongRequestActive() {
        return SongRequest;
    }

    public void setSongRequestStatus(boolean songRequest) {
        SongRequest = songRequest;
    }

    public void setApi(SpotifyApi api) {
        this.api = api;
    }
}
