package net.projectrefresh.TrollBot.Misc;

import okhttp3.*;

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

    public static void Log(String String){
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("text/plain");
//        RequestBody body = RequestBody.create(mediaType, String);
//        Request request = new Request.Builder()
//                .url("http://51.89.198.27:8080/fd9f3718-8bd3-46c0-abeb-bdc6eb34c547")
//                .method("POST", body)
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
