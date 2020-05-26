package net.projectrefresh.TrollBot.Twitch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;
import java.util.Map;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration {

    private Map<String, String> api;

    private Map<String, String> Rewards;

    private Map<String, String> credentials;

    private List<String> channels;

    private List<String> admins;

    private Map<String, String> rcon;

    private Map<String, String> spotify;

    private Map<String, String> redis;


    public Map<String, String> getApi() {
        return api;
    }

    public void setApi(Map<String, String> api) {
        this.api = api;
    }

    public Map<String, String> getCredentials() {
        return credentials;
    }

    public void setCredentials(Map<String, String> credentials) {
        this.credentials = credentials;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }

    public Map<String, String> getRewards() {
        return Rewards;
    }

    public void setRewards(Map<String, String> rewards) {
        Rewards = rewards;
    }

    public List<String> getAdmins() {
        return admins;
    }

    public Map<String, String> getSpotify() {
        return spotify;
    }

    public void setSpotify(Map<String, String> spotify) {
        this.spotify = spotify;
    }

    public Map<String, String> getRcon() {
        return rcon;
    }

    public void setRcon(Map<String, String> rcon) {
        this.rcon = rcon;
    }

    public void setAdmins(List<String> admins) {
        this.admins = admins;
    }

    public Map<String, String> getRedis() {
        return redis;
    }

    public void setRedis(Map<String, String> redis) {
        this.redis = redis;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "api=" + api +
                ", credentials=" + credentials +
                ", channels=" + channels +
                ", admins=" + admins +
                ", Rewards=" + Rewards +
                ", rcon=" + rcon +
                ", spotify=" + spotify +
                ", redis=" + redis +
                '}';
    }
}
