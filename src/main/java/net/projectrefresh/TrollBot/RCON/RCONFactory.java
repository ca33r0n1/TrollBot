package net.projectrefresh.TrollBot.RCON;

import net.projectrefresh.TrollBot.Twitch.Configuration;

import java.io.IOException;

public class RCONFactory {

    private boolean connected;
    private RCONClient client;
    private final String ip, port, password;

    public RCONFactory(Configuration configuration){
            ip = configuration.getRcon().get("ip");
            port = configuration.getRcon().get("port");
            password = configuration.getRcon().get("password");
            connect();
    }

    public void connect(){
        try {
            client = new RCONClient(ip,port,password.toCharArray());
            connected = true;
        } catch (IOException | AuthenticationException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            client.close();
            connected = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RCONClient getClient() {
        return client;
    }

    public void setClient(RCONClient client) {
        this.client = client;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
