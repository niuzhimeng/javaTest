package OkHttp;

import com.squareup.okhttp.*;
import com.squareup.okhttp.Request.Builder;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Created by jacobdong on 15/12/31.
 */
public class OkHttpUtils {

    private static Logger LOG = Logger.getLogger(OkHttpUtils.class);
    private static OkHttpClient client = new OkHttpClient();
    private static OkHttpClient clientNoLimit = new OkHttpClient();//没有超时时间限制
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType STRING = MediaType.parse("text/x-markdown; charset=utf-8");

    static {
        //超时配置
        client.setConnectTimeout(30, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);
    }

    /**
     * 普通的get方法
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        LOG.info("请求URL 为: ==>" + url);
        return getMethod(url);
    }

    /**
     * get方法访问https证书
     *
     * @param url
     * @return
     */
    public static String getByHttps(String url) throws Exception {
        client = getConnection();
        return getMethod(url);
    }

    private static String getMethod(String url) {
        Long start = System.currentTimeMillis();
        String result = null;
        Request request = new Builder().url(url).build();
        try {
            result = client.newCall(request).execute().body().string();
        } catch (IOException e) {
            LOG.error("调用异常:url:" + url);
            e.printStackTrace();
        } finally {
            Long end = System.currentTimeMillis();
            LOG.info("开始时间：" + start + " 结束时间：" + end + " 此次调用时长为" + (end - start));
        }
        return result;
    }

    /**
     * post方法（以get的url方式提交参数）
     *
     * @param url
     * @return
     */
    public static String post(String url) {
//        LOG.info("POST 地址为" + url);
        Long start = System.currentTimeMillis();
        String result = "";
        try {
            RequestBody formBody = new FormEncodingBuilder().build();
            Request request = new Builder().url(url).post(formBody).build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
                //System.out.println(result);
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("调用异常:url:" + url);
        } finally {
            Long end = System.currentTimeMillis();
            LOG.info("开始时间：" + start + " 结束时间：" + end + " 此次调用时长为" + (end - start));
        }
        return result;
    }


    /**
     * post方法（以form形式提交参数）
     *
     * @param url
     * @param param
     * @return
     */
    public static String post(String url, Map<String, String> param) {
        LOG.info("POST 地址为" + url);
        Long start = System.currentTimeMillis();
        String result = "";
        try {
            RequestBody formBody = getFormEncodingBuilder(param).build();
            Request request = new Builder().url(url).post(formBody).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("调用异常:url:" + url);
        } finally {
            Long end = System.currentTimeMillis();
            LOG.info("开始时间：" + start + " 结束时间：" + end + " 此次调用时长为" + (end - start));
        }
        return result;
    }


    /**
     * 没有超时时间限制的post请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String postNoLimit(String url, Map<String, String> param) {
        String result = "";
        try {
            RequestBody formBody = getFormEncodingBuilder(param).build();
            Request request = new Builder().url(url).post(formBody).build();
            Response response = clientNoLimit.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * psot方法访问https证书(以form形式提交参数)
     *
     * @param url
     * @param param
     * @param formEncodingBuilder
     * @return
     * @throws Exception
     */
    public static String post(String url, Map<String, String> param, FormEncodingBuilder formEncodingBuilder) {
        String result = "";
        Long start = System.currentTimeMillis();
        try {
            OkHttpClient client = getConnection();
            RequestBody formBody = getFormEncodingBuilder(param).build();
            Request request = new Builder().url(url).post(formBody).build();

            Response response;
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("调用异常:url:" + url);
        } finally {
            Long end = System.currentTimeMillis();
            LOG.info("开始时间：" + start + " 结束时间：" + end + " 此次调用时长为" + (end - start));
        }
        return result;
    }


    /**
     * post方法    htpps协议   json参数
     *
     * @param url
     * @param json
     * @return
     */
    public static String post(String url, String json) {
        String result = "";
        Long start = System.currentTimeMillis();
        try {
            OkHttpClient client = getConnection();
            RequestBody formBody = RequestBody.create(JSON, json);
            Request request = new Builder().url(url).post(formBody).build();

            Response response;
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("调用异常:url:" + url);
        } finally {
            Long end = System.currentTimeMillis();
            LOG.info("开始时间：" + start + " 结束时间：" + end + " 此次调用时长为" + (end - start));
        }
        return result;
    }

    /**
     * psot方法访问https证书(以get的url方式提交参数)
     *
     * @param url
     * @param param
     * @param formEncodingBuilder
     * @return
     * @throws Exception
     */
    public static String postByHttps(String url) {
        String result = "";
        Long start = System.currentTimeMillis();
        try {
            OkHttpClient client = getConnection();
            RequestBody formBody = new FormEncodingBuilder().build();
            Request request = new Builder().url(url).post(formBody).build();

            Response response;
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("调用异常:url:" + url);
        } finally {
            Long end = System.currentTimeMillis();
            LOG.info("开始时间：" + start + " 结束时间：" + end + " 此次调用时长为" + (end - start));
        }
        return result;
    }


    /**
     * post方法 上传图片
     *
     * @param url
     * @param param      (除图片参数以外的参数)
     * @param file
     * @param photoParam 图片参数
     * @return
     */
    public static String post(String url, Map<String, String> param, File file, String photoParam) {
        LOG.info("POST 地址为" + url);
        Long start = System.currentTimeMillis();
        String result = "";
        try {
            MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
            for (Map.Entry<String, String> entry : param.entrySet()) {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                        RequestBody.create(null, entry.getValue()));
            }

            String fileName = file.getName();
            //根据文件名设置contentType
            RequestBody fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + photoParam + "\"; filename=\"" + fileName + "\""), fileBody);

            RequestBody requestBody = builder.build();
            Request request = new Builder().url(url).post(requestBody).build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("调用异常:url:" + url);
        } finally {
            Long end = System.currentTimeMillis();
            LOG.info("开始时间：" + start + " 结束时间：" + end + " 此次调用时长为" + (end - start));
        }
        return result;
    }


    /**
     * post方法提供参数
     *
     * @param param
     * @return
     */
    public static FormEncodingBuilder getFormEncodingBuilder(Map<String, String> param) {
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            formEncodingBuilder.add(entry.getKey(), entry.getValue());
        }
        return formEncodingBuilder;
    }

    /**
     * 访问https证书验证
     *
     * @return
     * @throws Exception
     */
    public static OkHttpClient getConnection() throws Exception {
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");
            ctx.init(new KeyManager[0], new TrustManager[]{new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

            }}, new SecureRandom());
            client.setSslSocketFactory(ctx.getSocketFactory());
            client.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return client;
    }


    private static String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    /**
     * post方法    htpps协议   String参数
     *
     * @param url
     * @param json
     * @return
     */
    public static String postString(String url, String params) {
        String result = "";
        Long start = System.currentTimeMillis();
        try {
            RequestBody formBody = RequestBody.create(STRING, params);
            Request request = new Builder().url(url).post(formBody).build();

            Response response;
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("调用异常:url:" + url);
        } finally {
            Long end = System.currentTimeMillis();
            LOG.info("开始时间：" + start + " 结束时间：" + end + " 此次调用时长为" + (end - start));
        }
        return result;
    }
    /**
     * post方法 访问htpps证书（ String参数）
     * @param url
     * @param String
     * @return
     */
    public static String postByHttpsString(String url, String params) {
        String result = "";
        Long start = System.currentTimeMillis();
        try {
            RequestBody formBody = RequestBody.create(STRING, params);
            Request request = new Builder().url(url).post(formBody).build();
            OkHttpClient client = getConnection();
            Response response;
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("调用异常:url:" + url);
        } finally {
            Long end = System.currentTimeMillis();
            LOG.info("开始时间："+ start +" 结束时间："+ end +" 此次调用时长为" + (end - start));
        }
        return result;
    }

    /**
     * post方法（提交json串和headers）
     *
     * @param url
     * @param param
     * @param headers
     * @return
     */
    public static String postJsonHeader(String url, String json, Map<String, String> headers) {
        String result = "";
        Long start = System.currentTimeMillis();
        try {
            OkHttpClient client = getConnection();
            RequestBody formBody = RequestBody.create(JSON, json);
            Request request = null;
            Builder builder = new Builder();
            for (Map.Entry<String, String> entity : headers.entrySet()) {
                builder.header(entity.getKey(), entity.getValue());
            }
            request = builder.url(url).post(formBody).build();
            Response response;
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("调用异常:url:" + url);
        } finally {
            Long end = System.currentTimeMillis();
            LOG.info("开始时间：" + start + " 结束时间：" + end + " 此次调用时长为" + (end - start));
        }
        return result;
    }
}
