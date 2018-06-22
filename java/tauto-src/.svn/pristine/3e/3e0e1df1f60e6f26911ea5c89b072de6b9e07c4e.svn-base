package main.com.utils.eyekey;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class UpdateCheckThread //implements Runnable {
{
    private static final Logger LOG = Logger.getLogger(UpdateCheckThread.class.getName());

//    @Override
//    public void run() {
//        String webUrl = "";//Configuration.getString("update-url");
//        if (webUrl == null || webUrl.trim().equals("")) {
//            LOG.info("update web url is null !");
//            return;
//        }
//        LOG.info("web update url ["+ webUrl +"]");
//        int count = 0;
//        while (true) {
//            JSONObject o = httpGet(webUrl);
//            String v = o.getString("sdk_version");
////            if (!MANIFESTConfig.getVersion().equals(v)) {
////                LOG.info("有新版本的sdk，建议更新，版本号为[" + v + "]");
////            }
//
//            count++;
//            if (count == 5) {
//                break;
//            }
//            
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static JSONObject httpGet(String url) {
        HttpURLConnection conn = null;
        try {
            URL connUrl = new URL("");
            conn = (HttpURLConnection) connUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);
            conn.setReadTimeout(10 * 1000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            JSONObject jObject = null;
            String errString = null;
            try {
                if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                    jObject = JSONObject.parseObject(readString(conn.getInputStream()));
                } else {
                    if (conn.getErrorStream() != null) {
                        errString = readString(conn.getErrorStream());
                        jObject = JSONObject.parseObject(errString);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return jObject;
        } catch (MalformedURLException e) {
            throw new EyeKeyException(e);
        } catch (IOException e) {
            throw new EyeKeyException(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static String readString(InputStream in) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len;
        byte[] toBuf = null;
        try {
            len = in.read(buf);
            while (len > 0) {
                out.write(buf, 0, len);
                len = in.read(buf);
            }
            toBuf = out.toByteArray();

            return new String(toBuf, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
