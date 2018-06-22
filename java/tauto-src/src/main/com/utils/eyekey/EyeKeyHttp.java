package main.com.utils.eyekey;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.com.utils.GeneralConstant;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * 与www.eyekey.com api 通信类 可以通过配置文件获取app_id和app_key,或者通过类构造方法
 * </P>
 * 
 * @Title: EyekeyHttp.java
 * @Package: com.eyekey.http
 * @ClassName: EyekeyHttp
 * @author zhangxinlei
 * @date 2015年1月6日 上午10:40:05
 * 
 */
public class EyeKeyHttp {

    private static final Logger LOG = Logger.getLogger(EyeKeyHttp.class.getName());

    // api.face-key.com
    private static String webSite = "http://api.eyekey.com";

    /* 连接超时时间 */
    private static int connTimeOut = 10 * 1000;

    /* 读取超时时间 */
    private static int readTimeOut = 10 * 1000;

    /* 应用的app_id */
    private static String appId = "8a52c59cce28461e8bd47ebd2f4f094b";

    /* 应用的app_key */
    private static String appKey = "e86e1386220f43afaa2422103aedc11c";

    /**
     * 通过app_id 和 app_key 构造类
     * 
     * @Title: EyekeyHttp
     * @param appId
     * @param appKey
     */
    public EyeKeyHttp(String appId, String appKey) {
//        this.appId = appId;
//        this.appKey = appKey;

//        if (Configuration.getInt("readTimeOut") > 0) {
//            this.readTimeOut = Configuration.getInt("readTimeOut");
//        }
//        if (Configuration.getInt("connTimeOut") > 0) {
//            this.connTimeOut = Configuration.getInt("connTimeOut");
//        }
//
//        if (Configuration.getString("website") != null && Configuration.getString("website").length() > 0) {
//            this.webSite = Configuration.getString("website");
//        }
        this.readTimeOut = 10000;
        this.connTimeOut = 10000;
        this.webSite = "http://api.eyekey.com";
        
        LOG.log(Level.INFO, "readTimeOut [" + this.readTimeOut + "],connTimeOut [{" + this.connTimeOut + "}],website ["
                        + this.webSite + "]");
//        new Thread(new UpdateCheckThread()).start();
        
    }

    /**
     * 通过websit、app_id和app_key构造类
     */
    public EyeKeyHttp(String website, String appId, String appKey) {
        if (!isEmpty(website)) {
            this.webSite = website;
        }
        this.appId = appId;
        this.appKey = appKey;
        this.readTimeOut = 10000;//Configuration.getInt("readTimeOut");
        this.connTimeOut = 10000;//Configuration.getInt("connTimeOut");

        LOG.log(Level.INFO, "readTimeOut [" + this.readTimeOut + "],connTimeOut [{" + this.connTimeOut + "}],website ["
                        + this.webSite + "]");
//        new Thread(new UpdateCheckThread()).start();
    }

    /**
     * 通过 app_id、app_key、connTimeOut、readTimeOut 来构造类
     * 
     * @Title: EyekeyHttp
     * @param appId
     * @param appKey
     * @param connTimeOut
     * @param readTimeOut
     */
    public EyeKeyHttp(String appId, String appKey, int connTimeOut, int readTimeOut) {
        this.appId = appId;
        this.appKey = appKey;
        this.connTimeOut = connTimeOut;
        this.readTimeOut = readTimeOut;

        this.webSite = "";//Configuration.getString("website");

        LOG.log(Level.INFO, "readTimeOut [" + this.readTimeOut + "],connTimeOut [{" + this.connTimeOut
                        + "}],website [{" + this.webSite + "}]");

//        new Thread(new UpdateCheckThread()).start();
    }

    /**
     * 从配置文件里读取app_id,app_key 和超时时间
     * 
     * @Title: EyekeyHttp
     */
    public EyeKeyHttp() {
        this.appId = "";//Configuration.getString("app_id");
        if (isEmpty(appId)) {
            throw new EyeKeyException("app_id is null!");
        }
        this.appKey = "";//Configuration.getString("app_key");
        if (isEmpty(appKey)) {
            throw new EyeKeyException("app_key is null!");
        }
        this.readTimeOut = 10000;
//        if (Configuration.getInt("readTimeOut") > 0) {
//            this.readTimeOut = Configuration.getInt("readTimeOut");
//        }
        this.connTimeOut = 10000;
//        if (Configuration.getInt("connTimeOut") > 0) {
//            this.connTimeOut = Configuration.getInt("connTimeOut");
//        }
        this.webSite = "";
//        if (Configuration.getString("website") != null && Configuration.getString("website").length() > 0) {
//            this.webSite = Configuration.getString("website");
//        }

//        new Thread(new UpdateCheckThread()).start();
    }

    /**
     * 获得连接超时时间
     * 
     * @return
     * @Since 2015年1月6日
     */
    public int getConnTimeOut() {
        return connTimeOut;
    }

    /**
     * 设置连接超时时间
     * 
     * @param connTimeOut
     * @Since 2015年1月6日
     */
    public void setConnTimeOut(int connTimeOut) {
        this.connTimeOut = connTimeOut;
    }

    /**
     * 获得读取超时时间
     * 
     * @return
     * @Since 2015年1月6日
     */
    public int getReadTimeOut() {
        return readTimeOut;
    }

    /**
     * 设置读取超时时间
     * 
     * @param readTimeOut
     * @Since 2015年1月6日
     */
    public void setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

    public static JSONObject httpGet(String url, Map<String, String> params) {
        HttpURLConnection conn = null;
        try {
            URL connUrl = new URL(webSite + url + "?" + packageParams(params));
            conn = (HttpURLConnection) connUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(connTimeOut);
            conn.setReadTimeout(readTimeOut);
            conn.addRequestProperty("app_id", appId);
            conn.addRequestProperty("app_key", appKey);
            conn.addRequestProperty("sdk_version", MANIFESTConfig.getVersion());
            conn.setDoOutput(true);
            conn.setDoInput(true);
            JSONObject jObject = null;
            String errString = null;
            try {
                if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                    jObject = JSONObject.parseObject(readString(conn.getInputStream()));
                } else {
                    LOG.log(Level.INFO, "response code [" + conn.getResponseCode() + "]");
                    if (conn.getErrorStream() != null) {
                        errString = readString(conn.getErrorStream());
                        LOG.log(Level.INFO, errString);
                        jObject = JSONObject.parseObject(errString);
                    }
                }
            } catch (JSONException e) {
                if (errString != null) {
                    throw new EyeKeyException(errString);
                } else {
                    throw new EyeKeyException(e);
                }
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
        // HttpGet httpGet = new HttpGet(webSite + url + "?" +
        // packageParams(params));
        // CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // try {
        // HttpResponse response = httpClient.execute(httpGet);
        // JSONObject jObject = null;
        // try {
        // if (HttpURLConnection.HTTP_OK ==
        // response.getStatusLine().getStatusCode()) {
        // jObject =
        // JSONObject.parseObject(readString(response.getEntity().getContent()));
        // } else {
        // jObject =
        // JSONObject.parseObject(readString(response.getEntity().getContent()));
        // }
        // } catch (JSONException e) {
        // e.printStackTrace();
        // }
        //
        // return jObject;
        // } catch (ClientProtocolException e) {
        // e.printStackTrace();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
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

    public static String packageParams(Map<String, String> params) {
        StringBuffer buffer = new StringBuffer();
        int i = 1;
        int size = params.size();
        for (Entry<String, String> entry : params.entrySet()) {
            buffer.append(entry.getKey());
            buffer.append("=");
            try {
				buffer.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if (i < size) {
                buffer.append("&");
            }
            i++;
        }

        return buffer.toString();
    }

    private static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        if (str.equals("")) {
            return true;
        }

        return false;
    }

    private static JSONObject httpPost(String url, HttpEntity entity) {
        HttpURLConnection conn = null;
        try {
            URL connUrl = new URL(webSite + url);
            conn = (HttpURLConnection) connUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(connTimeOut);
            conn.setReadTimeout(readTimeOut);
            conn.addRequestProperty("app_id", appId);
            conn.addRequestProperty("app_key", appKey);
            conn.addRequestProperty("sdk_version", MANIFESTConfig.getVersion());
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty(entity.getContentType().getName(), entity.getContentType().getValue());
            entity.writeTo(conn.getOutputStream());
            JSONObject jObject = null;
            String errString = null;
            try {
                if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                    jObject = JSONObject.parseObject(readString(conn.getInputStream()));
                } else {
                    LOG.log(Level.INFO, "response code [" + conn.getResponseCode() + "]");
                    if (conn.getErrorStream() != null) {
                        errString = readString(conn.getErrorStream());
                        LOG.log(Level.INFO, errString);
                        jObject = JSONObject.parseObject(errString);
                    }
                }
            } catch (JSONException e) {
                if (errString != null) {
                    throw new EyeKeyException(errString);
                } else {
                    throw new EyeKeyException(e);
                }
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

    // private JSONObject httpPost(String url, HttpEntity entity) {
    // HttpPost httpPost = new HttpPost(webSite + url);
    // CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    // httpPost.setEntity(entity);
    // try {
    // HttpResponse response = httpClient.execute(httpPost);
    // JSONObject jObject = null;
    // try {
    // if (HttpURLConnection.HTTP_OK ==
    // response.getStatusLine().getStatusCode()) {
    // jObject =
    // JSONObject.parseObject(readString(response.getEntity().getContent()));
    // } else {
    // jObject =
    // JSONObject.parseObject(readString(response.getEntity().getContent()));
    // }
    // } catch (JSONException e) {
    // e.printStackTrace();
    // }
    //
    // return jObject;
    // } catch (ClientProtocolException e) {
    // e.printStackTrace();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // return null;
    // }

    /**
     * 图片检测
     * 
     * @param imgUrl 图片url地址
     * @param mode 检测模式（normal or oneface)，可以为null
     * @param tip 可以为null
     * @return 结果为JSONObject
     * @Since 2015年1月6日
     */
    public JSONObject checkingImgUrl(String imgUrl, String tip) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("app_id", appId);
        builder.addTextBody("app_key", appKey);
        builder.addTextBody("url", imgUrl);
        if (!isEmpty(tip)) {
            builder.addTextBody("tip", tip);
        }
        return httpPost("/face/Check/checking", builder.build());
    }

    /**
     * 图片检测
     * 
     * @param img 图片base64编码
     * @param mode 检测模式（normal or oneface)，可以为null
     * @param tip 可以为null
     * @return 结果为JSONObject
     * @Since 2015年1月6日
     */
    public static JSONObject checkingImgB64(String img, String tip) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("app_id", appId);
        builder.addTextBody("app_key", appKey);
        builder.addTextBody("img", img);
        if (!isEmpty(tip)) {
            builder.addTextBody("tip", tip);
        }
        return httpPost("/face/Check/checking", builder.build());
    }

    /**
     * 检测给定人脸(Face)相应的面部轮廓，五官等关键点的位置
     * 
     * @param faceId 待检测人脸id
     * @param type 关键点个数(49p or 29p or 5p)
     * @return 结果为JSONObject
     * @Since 2015年1月6日
     */
    public JSONObject checkMark(String faceId, String type) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("face_id", faceId);
        if (!isEmpty(type)) {
            params.put("type", type);
        }

        return httpGet("/face/Check/check_mark", params);
    }

    /**
     * 计算两个Face的相似性以及五官可信度
     * 
     * @param faceId1 待比对face_id1
     * @param faceId2 待比对face_id2
     * @return 结果为JSONObject
     * @Since 2015年1月6日
     */
    public JSONObject matchCompare(String faceId1, String faceId2) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("face_id1", faceId1);
        params.put("face_id2", faceId2);

        return httpGet("/face/Match/match_compare", params);
    }

    /**
     * 给定一个Face和一个People_id，返回是否是同一个人的判断以及可信度
     * 
     * @param faceId 待比对face_id
     * @param peopleId 待比对people_id
     * @return 结果为JSONObject
     * @Since 2015年1月6日
     */
    public JSONObject matchVerifyById(String faceId, String peopleId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("face_id", faceId);
        params.put("people_id", peopleId);

        return httpGet("/face/Match/match_verify", params);
    }

    /**
     * 给定一个Face和一个People_name，返回是否是同一个人的判断以及可信度
     * 
     * @param faceId 待比对face_id
     * @param peopleName 待比对people_name
     * @return 结果为JSONObject
     * @Since 2015年1月6日
     */
    public JSONObject matchVerifyByName(String faceId, String peopleName) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("face_id", faceId);
        params.put("people_name", peopleName);

        return httpGet("/face/Match/match_verify", params);
    }

    /**
     * 给定一个Face和一个Facegather_id，在Facegather内搜索最相似的Face
     * 
     * @param faceId 待搜索face_id
     * @param faceGatherId 待搜索facegather_id
     * @param count 结果数
     * @return 结果为JSONObject
     * @Since 2015年1月6日
     */
    public JSONObject matchSearchById(String faceId, String faceGatherId, int count) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("face_id", faceId);
        params.put("facegather_id", faceGatherId);
        if (count <= 0) {
            count = 3;
        }
        params.put("count", String.valueOf(count));

        return httpGet("/face/Match/match_search", params);
    }

    /**
     * 给定一个Face和一个Facegather_id，在Facegather内搜索最相似的Face
     * 
     * @param faceId 待搜索face_id
     * @param faceGatherName 待搜索facegather_name
     * @param count 结果数,小于0时默认为3
     * @return 结果为JSONObject
     * @Since 2015年1月6日
     */
    public JSONObject matchSearchByName(String faceId, String faceGatherName, int count) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("face_id", faceId);
        params.put("facegather_name", faceGatherName);
        if (count <= 0) {
            count = 3;
        }
        params.put("count", String.valueOf(count));

        return httpGet("/face/Match/match_search", params);
    }

    /**
     * 对于一个待查询的Face列表（或者对于给定的Image中所有的Face），在一个Crowd_id中查询最相似的People
     * 
     * @param faceId 待搜索face_id
     * @param crowdId 待搜索crowd_id
     * @return 结果为JSONObject
     * @Since 2015年1月6日
     */
    public JSONObject matchIdentifyById(String faceId, String crowdId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("face_id", faceId);
        params.put("crowd_id", crowdId);

        return httpGet("/face/Match/match_identify", params);
    }

    /**
     * 对于一个待查询的Face列表（或者对于给定的Image中所有的Face），在一个Crowd_id中查询最相似的People
     * 
     * @param faceId 待搜索face_id
     * @param crowdName 待搜索crowd_name
     * @return 结果为JSONObject
     * @Since 2015年1月6日
     */
    public JSONObject matchIdentifyByName(String faceId, String crowdName) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("face_id", faceId);
        params.put("crowd_name", crowdName);

        return httpGet("/face/Match/match_identify", params);
    }

    /**
     * 创建一个facegather
     * 
     * @param faceGatherName facegather名,可为null
     * @param tip 可以为null
     * @param faceIds face_id列表，可以为null
     * @return 结果为JSONObject
     * @Since 2015年1月6日
     */
    public JSONObject createFaceGather(String faceGatherName, String tip, String... faceIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        if (!isEmpty(faceGatherName)) {
            params.put("facegather_name", faceGatherName);
        }
        if (!isEmpty(tip)) {
            params.put("tip", tip);
        }
        if (faceIds != null) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < faceIds.length; i++) {
                builder.append(faceIds[i]);
                if (i < faceIds.length - 1) {
                    builder.append(",");
                }
            }
            if (!isEmpty(builder.toString())) {
                params.put("face_id", builder.toString());
            }
        }

        return httpGet("/face/FaceGather/facegather_create", params);
    }

    /**
     * 通过gather_id删除facegather
     * 
     * @param faceGatherIds 待删除的facegatherid
     * @return 结果为JSONObject
     * @Since 2015年1月6日
     */
    public JSONObject deleteFaceGatherById(String... faceGatherIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < faceGatherIds.length; i++) {
            builder.append(faceGatherIds[i]);
            if (i < faceGatherIds.length - 1) {
                builder.append(",");
            }
        }
        params.put("facegather_id", builder.toString());

        return httpGet("/face/FaceGather/facegather_delete", params);
    }

    /**
     * 通过gather_name删除facegather
     * 
     * @param faceGatherNames 待删除的facegathernames
     * @return 结果为JSONObject
     * @Since 2015年1月6日
     */
    public JSONObject deleteFaceGatherByName(String... faceGatherNames) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < faceGatherNames.length; i++) {
            builder.append(faceGatherNames[i]);
            if (i < faceGatherNames.length - 1) {
                builder.append(",");
            }
        }
        params.put("facegather_name", builder.toString());

        return httpGet("/face/FaceGather/facegather_delete", params);
    }

    /**
     * 将一组Face加入到一个facegather中,通过facegather_id
     * 
     * @param faceGatherId
     * @param faceIds
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject addFaceToFaceGatherById(String faceGatherId, String... faceIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("facegather_id", faceGatherId);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < faceIds.length; i++) {
            builder.append(faceIds[i]);
            if (i < faceIds.length - 1) {
                builder.append(",");
            }
        }
        params.put("face_id", builder.toString());

        return httpGet("/face/FaceGather/facegather_addface", params);
    }

    /**
     * 将一组Face加入到一个facegather中,通过facegather_name
     * 
     * @param faceGatherName
     * @param faceIds
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject addFaceToFaceGatherByName(String faceGatherName, String... faceIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("facegather_name", faceGatherName);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < faceIds.length; i++) {
            builder.append(faceIds[i]);
            if (i < faceIds.length - 1) {
                builder.append(",");
            }
        }
        params.put("face_id", builder.toString());

        return httpGet("/face/FaceGather/facegather_addface", params);
    }

    /**
     * 删除facegather中的一个或多个Face,通过facegather_id
     * 
     * @param faceGatherId
     * @param faceIds
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject removeFaceFromFaceGatherById(String faceGatherId, String... faceIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("facegather_id", faceGatherId);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < faceIds.length; i++) {
            builder.append(faceIds[i]);
            if (i < faceIds.length - 1) {
                builder.append(",");
            }
        }
        params.put("face_id", builder.toString());

        return httpGet("/face/FaceGather/facegather_removeface", params);
    }

    /**
     * 删除facegather中的一个或多个Face,通过facegather_name
     * 
     * @param faceGatherName
     * @param faceIds
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject removeFaceFromFaceGatherByName(String faceGatherName, String... faceIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("facegather_name", faceGatherName);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < faceIds.length; i++) {
            builder.append(faceIds[i]);
            if (i < faceIds.length - 1) {
                builder.append(",");
            }
        }
        params.put("face_id", builder.toString());

        return httpGet("/face/FaceGather/facegather_removeface", params);
    }

    /**
     * 设置facegather的name和tip,通过facegather_id
     * 
     * @param faceGatherId
     * @param name 可以为空
     * @param tip 可以为空
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject setFaceGatherById(String faceGatherId, String name, String tip) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("facegather_id", faceGatherId);
        if (!isEmpty(name)) {
            params.put("name", name);
        }
        if (!isEmpty(tip)) {
            params.put("tip", tip);
        }

        return httpGet("/face/FaceGather/facegather_set", params);
    }

    /**
     * 设置facegather的name和tip,通过facegather_name
     * 
     * @param faceGatherName
     * @param name 可以为空
     * @param tip 可以为空
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject setFaceGatherByName(String faceGatherName, String name, String tip) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("facegather_name", faceGatherName);
        if (!isEmpty(name)) {
            params.put("name", name);
        }
        if (!isEmpty(tip)) {
            params.put("tip", tip);
        }

        return httpGet("/face/FaceGather/facegather_set", params);
    }

    /**
     * 获取一个facegather的信息, 包括name, id, tip, 相关的face等信息
     * 
     * @param faceGatherId
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject getFaceGatherById(String faceGatherId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("facegather_id", faceGatherId);

        return httpGet("/face/FaceGather/facegather_get", params);
    }

    /**
     * 获取一个facegather的信息, 包括name, id, tip, 相关的face等信息
     * 
     * @param faceGatherName
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject getFaceGatherByName(String faceGatherName) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("facegather_name", faceGatherName);

        return httpGet("/face/FaceGather/facegather_get", params);
    }

    /**
     * 创建一个People
     * 
     * @param peopleName 可以为空
     * @param tip 可以为空
     * @param crowdId 可以为空
     * @param faceIds 可以为空
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject createPeopleById(String peopleName, String tip, String crowdId, String... faceIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        if (!isEmpty(tip)) {
            params.put("tip", tip);
        }
        if (!isEmpty(peopleName)) {
            params.put("people_name", peopleName);
        }
        if (!isEmpty(crowdId)) {
            params.put("crowd_id", crowdId);
        }
        if (faceIds != null) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < faceIds.length; i++) {
                builder.append(faceIds[i]);
                if (i < faceIds.length - 1) {
                    builder.append(",");
                }
            }
            if (!isEmpty(builder.toString())) {
                params.put("face_id", builder.toString());
            }
        }

        return httpGet("/People/people_create", params);
    }

    /**
     * 创建一个People
     * 
     * @param peopleName 可以为空
     * @param tip 可以为空
     * @param crowdName 可以为空
     * @param faceIds 可以为空
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject createPeopleByName(String peopleName, String tip, String crowdName, String... faceIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        if (!isEmpty(tip)) {
            params.put("tip", tip);
        }
        if (!isEmpty(peopleName)) {
            params.put("people_name", peopleName);
        }
        if (!isEmpty(crowdName)) {
            params.put("crowd_name", crowdName);
        }
        if (faceIds != null) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < faceIds.length; i++) {
                builder.append(faceIds[i]);
                if (i < faceIds.length - 1) {
                    builder.append(",");
                }
            }
            if (!isEmpty(builder.toString())) {
                params.put("face_id", builder.toString());
            }
        }

        return httpGet("/People/people_create", params);
    }

    public JSONObject createPeopleByNameIris(String peopleName, String tip, String crowdName, String... irisIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        if (!isEmpty(tip)) {
            params.put("tip", tip);
        }
        if (!isEmpty(peopleName)) {
            params.put("people_name", peopleName);
        }
        if (!isEmpty(crowdName)) {
            params.put("crowd_name", crowdName);
        }
        if (irisIds != null) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < irisIds.length; i++) {
                builder.append(irisIds[i]);
                if (i < irisIds.length - 1) {
                    builder.append(",");
                }
            }
            if (!isEmpty(builder.toString())) {
                params.put("iris_id", builder.toString());
            }
        }

        return httpGet("/People/people_create", params);
    }

    /**
     * 删除一个people,通过people_id
     * 
     * @param peopleId
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject deletePeopleById(String peopleId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("people_id", peopleId);

        return httpGet("/People/people_delete", params);
    }

    /**
     * 删除一个people,通过people_name
     * 
     * @param peopleName
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject deletePeopleByName(String peopleName) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("people_name", peopleName);

        return httpGet("/People/people_delete", params);
    }

    /**
     * 将一组Face加入到一个People中,通过people_id
     * 
     * @param peopleId
     * @param faceIds
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject addFaceToPeopleById(String peopleId, String... faceIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("people_id", peopleId);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < faceIds.length; i++) {
            builder.append(faceIds[i]);
            if (i < faceIds.length - 1) {
                builder.append(",");
            }
        }
        params.put("face_id", builder.toString());

        return httpGet("/People/people_add", params);
    }

    /**
     * 将一组Face加入到一个People中,通过people_id
     * 
     * @param peopleId
     * @param faceIds
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject addIrisToPeopleById(String peopleId, String... irisIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("people_id", peopleId);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < irisIds.length; i++) {
            builder.append(irisIds[i]);
            if (i < irisIds.length - 1) {
                builder.append(",");
            }
        }
        params.put("iris_id", builder.toString());

        return httpGet("/People/people_add", params);
    }

    /**
     * 将一组Face加入到一个People中,通过people_name
     * 
     * @param peopleName
     * @param faceIds
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject addIrisToPeopleByName(String peopleName, String... irisIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("people_name", peopleName);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < irisIds.length; i++) {
            builder.append(irisIds[i]);
            if (i < irisIds.length - 1) {
                builder.append(",");
            }
        }
        params.put("iris_id", builder.toString());

        return httpGet("/People/people_add", params);
    }

    /**
     * 将一组Face加入到一个People中,通过people_name
     * 
     * @param peopleName
     * @param faceIds
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject addFaceToPeopleByName(String peopleName, String... faceIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("people_name", peopleName);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < faceIds.length; i++) {
            builder.append(faceIds[i]);
            if (i < faceIds.length - 1) {
                builder.append(",");
            }
        }
        params.put("face_id", builder.toString());

        return httpGet("/People/people_add", params);
    }

    /**
     * 删除People中的一个或多个Face,通过people_id
     * 
     * @param peopleId
     * @param faceIds
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject removeFaceFromPeopleById(String peopleId, String... faceIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("people_id", peopleId);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < faceIds.length; i++) {
            builder.append(faceIds[i]);
            if (i < faceIds.length - 1) {
                builder.append(",");
            }
        }
        params.put("face_id", builder.toString());

        return httpGet("/People/people_remove", params);
    }

    /**
     * 的一个或多个Face,通过people_name
     * 
     * @param peopleName
     * @param faceIds
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject removeFaceFromPeopleByName(String peopleName, String... faceIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("people_name", peopleName);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < faceIds.length; i++) {
            builder.append(faceIds[i]);
            if (i < faceIds.length - 1) {
                builder.append(",");
            }
        }
        params.put("face_id", builder.toString());

        return httpGet("/People/people_remove", params);
    }

    /**
     * 设置People的name和tip,通过people_id
     * 
     * @param peopleId
     * @param name 可以为空
     * @param tip 可以为空
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject setPeopleById(String peopleId, String name, String tip) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("people_id", peopleId);
        if (!isEmpty(name)) {
            params.put("name", name);
        }
        if (!isEmpty(tip)) {
            params.put("tip", tip);
        }

        return httpGet("/People/people_set", params);
    }

    /**
     * 设置People的name和tip,通过people_name
     * 
     * @param peopleName
     * @param name 可以为空
     * @param tip 可以为空
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject setPeopleByName(String peopleName, String name, String tip) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("people_name", peopleName);
        if (!isEmpty(name)) {
            params.put("name", name);
        }
        if (!isEmpty(tip)) {
            params.put("tip", tip);
        }

        return httpGet("/People/people_set", params);
    }

    /**
     * 获取一个People的信息, 包括name, id, tip, 相关的face, 以及crowds等信息,通过people_id
     * 
     * @param peopleId
     * @param type
     * @return
     * @Since 2015年8月24日
     */
    public JSONObject getPeopleById(String peopleId,String type) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("people_id", peopleId);
        if (!isEmpty(type)) {
        	params.put("type", type);
        }
        return httpGet("/People/people_get", params);
    }

    /**
     * 获取一个People的信息, 包括name, id, tip, 相关的face, 以及crowds等信息,通过people_name
     * 
     * @param peopleName
     * @param type
     * @return
     * @Since 2016年8月24日
     */
    public JSONObject getPeopleByName(String peopleName,String type) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("people_name", peopleName);
        if (!isEmpty(type)) {
        	params.put("type", type);
        }
        
        return httpGet("/People/people_get", params);
    }

    /**
     * 创建一个Crowd
     * 
     * @param crowdName 可以为空，返回系统创建的
     * @param tip 可以为空
     * @param peopleIds 可以为空
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject createCrowdById(String crowdName, String tip, String... peopleIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        if (!isEmpty(crowdName)) {
            params.put("crowd_name", crowdName);
        }
        if (!isEmpty(tip)) {
            params.put("tip", tip);
        }
        if (peopleIds != null) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < peopleIds.length; i++) {
                builder.append(peopleIds[i]);
                if (i < peopleIds.length - 1) {
                    builder.append(",");
                }
            }
            if (!isEmpty(builder.toString())) {
                params.put("people_id", builder.toString());
            }
        }

        return httpGet("/Crowd/crowd_create", params);
    }

    /**
     * 创建一个Crowd
     * 
     * @param crowdName 可以为空，返回系统创建的
     * @param tip 可以为空
     * @param peopleNames 可以为空
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject createCrowdByName(String crowdName, String tip, String... peopleNames) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        if (!isEmpty(crowdName)) {
            params.put("crowd_name", crowdName);
        }
        if (!isEmpty(tip)) {
            params.put("tip", tip);
        }
        if (peopleNames != null) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < peopleNames.length; i++) {
                builder.append(peopleNames[i]);
                if (i < peopleNames.length - 1) {
                    builder.append(",");
                }
            }
            if (!isEmpty(builder.toString())) {
                params.put("people_name", builder.toString());
            }
        }

        return httpGet("/Crowd/crowd_create", params);
    }

    /**
     * 删除一组Crowd
     * 
     * @param crowdId
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject deleteCrowdById(String... crowdIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < crowdIds.length; i++) {
            builder.append(crowdIds[i]);
            if (i < crowdIds.length - 1) {
                builder.append(",");
            }
        }
        params.put("crowd_id", builder.toString());

        return httpGet("/Crowd/crowd_delete", params);
    }

    /**
     * 删除一组Crowd
     * 
     * @param crowdNames
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject deleteCrowdByName(String... crowdNames) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < crowdNames.length; i++) {
            builder.append(crowdNames[i]);
            if (i < crowdNames.length - 1) {
                builder.append(",");
            }
        }
        params.put("crowd_name", builder.toString());

        return httpGet("/Crowd/crowd_delete", params);
    }

    /**
     * 将一组People加入到一个Crowd,crowdId和crowdName选一个
     * 
     * @param crowdId
     * @param crowdName
     * @param peopleIds
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject addPeopleToCrowdById(String crowdId, String crowdName, String... peopleIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        if (!isEmpty(crowdId)) {
            params.put("crowd_id", crowdId);
        }
        if (!isEmpty(crowdName)) {
            params.put("crowd_name", crowdName);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < peopleIds.length; i++) {
            builder.append(peopleIds[i]);
            if (i < peopleIds.length - 1) {
                builder.append(",");
            }
        }
        params.put("people_id", builder.toString());

        return httpGet("/Crowd/crowd_add", params);
    }

    /**
     * 将一组People加入到一个Crowd,crowdId和crowdName选一个
     * 
     * @param crowdId
     * @param crowdName
     * @param peopleNames
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject addPeopleToCrowdByName(String crowdId, String crowdName, String... peopleNames) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        if (!isEmpty(crowdId)) {
            params.put("crowd_id", crowdId);
        }
        if (!isEmpty(crowdName)) {
            params.put("crowd_name", crowdName);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < peopleNames.length; i++) {
            builder.append(peopleNames[i]);
            if (i < peopleNames.length - 1) {
                builder.append(",");
            }
        }
        params.put("people_name", builder.toString());

        return httpGet("/Crowd/crowd_add", params);
    }

    /**
     * 从Crowd中删除一组People,crowdId和crowdName选一个
     * 
     * @param crowdId
     * @param crowdName
     * @param peopleIds
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject removePeopleToCrowdById(String crowdId, String crowdName, String... peopleIds) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        if (!isEmpty(crowdId)) {
            params.put("crowd_id", crowdId);
        }
        if (!isEmpty(crowdName)) {
            params.put("crowd_name", crowdName);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < peopleIds.length; i++) {
            builder.append(peopleIds[i]);
            if (i < peopleIds.length - 1) {
                builder.append(",");
            }
        }
        params.put("people_id", builder.toString());

        return httpGet("/Crowd/crowd_remove", params);
    }

    /**
     * 从Crowd中删除一组People,crowdId和crowdName选一个
     * 
     * @param crowdId
     * @param crowdName
     * @param peopleNames
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject removePeopleToCrowdByName(String crowdId, String crowdName, String... peopleNames) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        if (!isEmpty(crowdId)) {
            params.put("crowd_id", crowdId);
        }
        if (!isEmpty(crowdName)) {
            params.put("crowd_name", crowdName);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < peopleNames.length; i++) {
            builder.append(peopleNames[i]);
            if (i < peopleNames.length - 1) {
                builder.append(",");
            }
        }
        params.put("people_name", builder.toString());

        return httpGet("/Crowd/crowd_remove", params);
    }

    /**
     * 获取Crowd的信息，包括Crowd中的People列表，Crowd的tip等信息,通过crowd_id
     * 
     * @param crowdId
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject getCrowdById(String crowdId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("crowd_id", crowdId);

        return httpGet("/Crowd/crowd_get", params);
    }

    /**
     * 获取Crowd的信息，包括Crowd中的People列表，Crowd的tip等信息,通过crowd_name
     * 
     * @param crowdName
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject getCrowdByName(String crowdName) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("crowd_name", crowdName);

        return httpGet("/Crowd/crowd_get", params);
    }

    /**
     * 获取该App相关的信息
     * 
     * @return
     * @Since 2015年1月6日
     */
    public JSONObject getAppInfo() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);

        return httpGet("/Info/get_appinfo", params);
    }

    class FingerSDK {

    }

    /**
     * 指纹检测 检测图片(Image)中的指纹(Fingerprint)，目前支持单张指纹检测
     * 
     * @param img
     * @param location
     * @return
     */
    public JSONObject fpt_checkingImgB64(String img1, String img2, String img3, String location) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("app_id", appId);
        builder.addTextBody("app_key", appKey);
        builder.addTextBody("img", img1 + "," + img2 + "," + img3);
        builder.addTextBody("location", location);
        return httpPost("/finger/Check/checking", builder.build());
    }

    /**
     * 计算两个Fingerprint的相似度，分值百分制。
     * 
     * @param fingerprintId1
     * @param fingerprintId2
     * @return
     */
    public JSONObject fpt_matchCompare(String fingerprintId1, String fingerprintId2) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("fingerprint_id1", fingerprintId1);
        params.put("fingerprint_id2", fingerprintId2);

        return httpGet("/finger/Match/match_compare", params);
    }

    /**
     * 上传一个Fingerprint和一个People，判断是否为同一个人并返回相关参考分值<br>
     * img 与features至少一个不能为空，people_name和peopleID至少一个不能为空
     * 
     * @param img
     * @param features
     * @param peopleId
     * @param peopleName
     * @return
     */
    public JSONObject fpt_matchVerify(String img, String features, String peopleId, String peopleName) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("app_id", appId);
        builder.addTextBody("app_key", appKey);
        builder.addTextBody("img", img);
        builder.addTextBody("features", features);
        builder.addTextBody("people_id", peopleId);
        builder.addTextBody("people_name", peopleName);
        return httpPost("/finger/Match/match_verify", builder.build());
    }

    /**
     * 上传一个指纹图片（或者指纹特征），在一个Crowd中查询出指纹列表中最相似的People。<br>
     * img 与features至少一个不能为空，crowd_name和crowd_id至少一个不能为空
     * 
     * @param img
     * @param features
     * @param crowdId
     * @param crowdName
     * @return
     */
    public JSONObject fpt_matchIdentify(String img, String features, String crowdId, String crowdName) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("app_id", appId);
        builder.addTextBody("app_key", appKey);
        builder.addTextBody("img", img);
        builder.addTextBody("features", features);
        builder.addTextBody("crowd_id", crowdId);
        builder.addTextBody("crowd_name", crowdName);
        return httpPost("/finger/Match/match_identify", builder.build());
    }

    /**
     * 检测图片(Image)中的虹膜(Iris)，目前支持单张虹膜、双虹膜检测
     * 
     * @param img
     * @param location
     * @param mode
     * @param tip
     * @return
     */
    public static JSONObject iris_checkingImgB64(String img, String location, String tip) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("app_id", appId);
        builder.addTextBody("app_key", appKey);
        builder.addTextBody("img", img);
        builder.addTextBody("location", location);
        if (!isEmpty(tip)) {
            builder.addTextBody("tip", tip);
        }
        return httpPost("/iris/Check/checking", builder.build());
    }

    /**
     * 比较两个Iris的相似度，分值百分制。
     * 
     * @param irisId1
     * @param irisId2
     * @return
     */
    public JSONObject iris_matchCompare(String irisId1, String irisId2) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", appId);
        params.put("app_key", appKey);
        params.put("iris_id1", irisId1);
        params.put("iris_id2", irisId2);
        return httpGet("/iris/Match/match_compare", params);
    }

    /**
     * 上传一个Iris和一个People，判断是否为同一个人<br>
     * img与features至少一个不能为空，people_name与people_id至少一个不能为空<br>
     * 1、如果要使用图片比对，features可以null或者""<br>
     * 2、people_id 与people_name可只传一个，另一个可传 null或者""(空字符串)
     * 
     * @param img
     * @param features
     * @param peopleId
     * @param peopleName
     * @return
     */
    public JSONObject iris_matchVerify(String img, String features, String peopleId, String peopleName) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("app_id", appId);
        builder.addTextBody("app_key", appKey);
        builder.addTextBody("img", img);
        builder.addTextBody("img", img == null ? "" : img);
        builder.addTextBody("features", features == null ? "" : features);
        builder.addTextBody("people_id", peopleId == null ? "" : peopleId);
        builder.addTextBody("people_name", peopleName == null ? "" : peopleName);
        return httpPost("/iris/Match/match_verify", builder.build());
    }

    /**
     * 指定一个虹膜图片或者虹膜特征，在一个Crowd中查询出虹膜列表中最相似的People<br>
     * img与features至少一个不能为空，people_name与people_id至少一个不能为空<br>
     * 1、如果要使用图片比对，features可以null或者""<br>
     * 2、crowd_id 与crowd_name可只传一个，另一个可传 null或者""(空字符串)
     * 
     * @param img
     * @param features
     * @param crowdId
     * @param crowdName
     * @return
     */
    public JSONObject iris_matchIdentify(String img, String features, String crowdId, String crowdName) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("app_id", appId);
        builder.addTextBody("app_key", appKey);
        builder.addTextBody("img", img == null ? "" : img);
        builder.addTextBody("features", features == null ? "" : features);
        builder.addTextBody("crowd_id", crowdId == null ? "" : crowdId);
        builder.addTextBody("crowd_name", crowdName == null ? "" : crowdName);
        return httpPost("/iris/Match/match_identify", builder.build());
    }
    
    
    
    public static void main(String[] args) {
    	EyeKeyHttp ek=new EyeKeyHttp();
		System.out.println(ek.checkingImgUrl("http://www.eyekey.com/images/demo/FanBingbing/1.jpg", null));
	}
}
