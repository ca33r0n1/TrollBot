package net.projectrefresh.TrollBot.Streamlabs;

import net.projectrefresh.TrollBot.Misc.ConfigType;
import net.projectrefresh.TrollBot.Misc.Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Properties;

@RestController
public class StreamLabs {

    private String ClientID;

    Properties properties = new Properties();

    public StreamLabs(){

    }

    /**
     * Store the StreamLabs' API Code Here
     */
    public static String StreamLabsAPICode;

    /**
     *
     * @param code Code retuned from Streamlabs.
     * @return Return a set template.
     */
    @RequestMapping("/streamlabs")
    public String getAPIKey(@RequestParam String code){
        StreamLabsAPICode = code;
        return "Connected";
    }

    public String getClientID(){
        return Utils.getConfig(ConfigType.Streamlabs, "client-id");
    }

    public String getClientSecret(){
        return Utils.getConfig(ConfigType.Streamlabs, "client-secret");
    }

    public static void Authorize(){

    }


}
