package com.tianxingshuke.utils.okhttp;

import com.squareup.okhttp.*;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

public class OkHttpUtils {

    private static Logger LOG = Logger.getLogger(OkHttpUtils.class);
    private static OkHttpClient client = new OkHttpClient();
    public static final MediaType STRING = MediaType.parse("text/x-markdown; charset=utf-8");

    static {
        //超时配置
        client.setConnectTimeout(30, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);
    }

    /**
     * post方法    htpps协议   String参数
     *
     * @return
     */
    public static String postString(String url, String params) {
        String result = null;
        Long start = System.currentTimeMillis();
        try {
            RequestBody formBody = RequestBody.create(STRING, params);
            Request request = new Request.Builder().url(url).post(formBody).build();

            Response response;
            response = getConnection().newCall(request).execute();
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
     * 访问https证书验证
     *
     * @return
     * @throws Exception
     */
    public static OkHttpClient getConnection() throws Exception {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");//SSL
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
}
