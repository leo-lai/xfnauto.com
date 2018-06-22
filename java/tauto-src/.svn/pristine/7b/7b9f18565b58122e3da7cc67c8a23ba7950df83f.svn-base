package main.com.utils.eyekey;

import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.jar.Manifest;

public class MANIFESTConfig {

    private static Manifest fest = null;

    private static String sdk_version = "0.0.0";

    static {
        try {
            URL jarUrl = MANIFESTConfig.class.getClassLoader().getResource(
                            MANIFESTConfig.class.getName().replaceAll("\\.", "/") + ".class");
            URLConnection conn;
            conn = jarUrl.openConnection();
            if (conn instanceof JarURLConnection) {
                fest = ((JarURLConnection) conn).getManifest();
            }

            if (getValue("Implementation-Version") != null && getValue("Implementation-Version").trim().length() > 0) {
                sdk_version = getValue("Implementation-Version");
            }
        } catch (Throwable e) {

        }
    }

    public static String getValue(String key) {
        if (fest == null) {
            return "0.0.0";
        }

        return fest.getMainAttributes().getValue(key);
    }

    public static String getVersion() {
        return sdk_version;
    }
}
