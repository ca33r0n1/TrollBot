package net.projectrefresh.TrollBot.Misc;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

public class Utils {

    private static File BaseDir = new File("TrollBot");
    private static File Configs = new File(BaseDir, "Configs");
    private static HashMap<ConfigType, Properties> Props = new HashMap<>();

    public static void createDir(){
        BaseDir.mkdir();
        Configs.mkdir();
    }

    public static void LoadConfigs() {
        try {
            Properties StreamLabs = new Properties();
            StreamLabs.load(new FileInputStream(new File(Configs, "Streamlabs.conf")));
            Props.put(ConfigType.Streamlabs, StreamLabs);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getConfig(ConfigType Config, String Key){
        return Props.get(Config).getProperty(Key);
    }
}
