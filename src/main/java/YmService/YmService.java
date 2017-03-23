package YmService;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import java.io.*;

/**
 * 车辆信息三要素(亿美)
 * Created by nzm on 2017/3/21.
 */
public class YmService {

    private Logger LOGGER = Logger.getLogger(YmService.class);

    /**
     * 获取token的方法
     *
     * @return token
     * @throws Exception
     */
    public String getToken() throws Exception {
        String endpoint = "http://opensdk.emay.cn:9080/HD_GetAccess_Token.asmx";

        Call call;
        Service service = new Service();
        try {
            call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endpoint));
            call.setOperationName(new QName("http://tempuri.org/", "GetACCESS_TOKEN"));
            call.addParameter(new QName("http://tempuri.org/", "AppID"), org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName("http://tempuri.org/", "AppSecret"), org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName("http://tempuri.org/", "Key"), org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
            call.setUseSOAPAction(true);
            call.setSOAPActionURI("http://tempuri.org/GetACCESS_TOKEN");

            String AppID = "BBAE7EA8W9598W43A0W83BDW26C58CF01FDE";
            String AppSecret = "20A778A9LB435L4A7DL8C2ALE774155294C3";
            String Key = "824100B9HD93AH44A3HB656H9BFA2C1941FB";
            String token = (String) call.invoke(new Object[]{AppID, AppSecret, Key});
            System.err.println("数据源返回token" + token);
            JsonObject resultToken = new JsonParser().parse(token).getAsJsonObject();
            if (resultToken.has("access_token")) {
                return resultToken.get("access_token").getAsString();
            } else {
                LOGGER.error(resultToken);
                return null;
            }
        } catch (Exception exception) {
            throw new Exception("获取token出错");
        }
    }

    @Test
    public void getToken1() {
        try {
            System.out.println(getToken());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 车辆信息三要素
     */
    @Test
    public void test() {
        String endpoint = "http://opensdk.emay.cn:9080/HD_ZX/HD_OperatorService.asmx";

        Call call;
        Service service = new Service();
        try {
            call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endpoint));
            call.setOperationName(new QName("http://tempuri.org/", "GetIdnamevrpQueryAttribute"));
            call.addParameter(new QName("http://tempuri.org/", "idcode"), org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName("http://tempuri.org/", "name"), org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName("http://tempuri.org/", "vrp"), org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName("http://tempuri.org/", "ACCESS_TOKEN"), org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
            call.setUseSOAPAction(true);
            call.setSOAPActionURI("http://tempuri.org/GetIdnamevrpQueryAttribute");

            String name = "曹金平";
            String idcode = "142601197709251039";
            String vrp = "晋BPS282";
            String ACCESS_TOKEN = "68986921F0D043059C2A35FF851F43779259EEC1B92F8B55B647958CCF2FFA2B5806F432D5024CB186A922BF451C7F77";

            Long statr = System.currentTimeMillis();
            String result = (String) call.invoke(new Object[]{idcode, name, vrp, ACCESS_TOKEN});
            System.out.println("耗时：" + (System.currentTimeMillis() - statr));
            System.err.println("数据源返回" + result);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 信贷整合
     */

    public String xdzh(String Phone) {
        String endpoint = "http://opensdk.emay.cn:9080/MADE_API/emda_xdzh.asmx";

        Call call;
        Service service = new Service();
        try {
            call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endpoint));
            call.setOperationName(new QName("http://tempuri.org/", "GetEmda_xdzh"));
            call.addParameter(new QName("http://tempuri.org/", "Phone"), org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName("http://tempuri.org/", "cycle"), org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName("http://tempuri.org/", "ACCESS_TOKEN"), org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName("http://tempuri.org/", "Platform"), org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
            call.setUseSOAPAction(true);
            call.setSOAPActionURI("http://tempuri.org/GetEmda_xdzh");

            //时间段（1,3,6,9,12，24）月
            String cycle = "24";
            //token
            String ACCESS_TOKEN = "0691D1B1093A44CBBEE3F618858023E3E1A6A885AE54EE4B8254076B8E7AEF1073343DE2D7BE4194A6F906F85240C926";
            //平台类型（0 全部；1 银行；2 非银行）
            String Platform = "0";

            String result = (String) call.invoke(new Object[]{Phone, cycle, ACCESS_TOKEN, Platform});
            System.out.println(result);
            return result;

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "报错了~~！！！";
    }

    @Test
    public void tt() {
        try {
            File file = new File("D:\\1.txt");
            File file1 = new File("D:\\2.txt");
            FileReader read = new FileReader(file);
            FileWriter Writer = new FileWriter(file1, true);

            BufferedWriter haveDataBuffer = new BufferedWriter(Writer);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt;
            while ((lineTxt = bufferedReader.readLine()) != null) {

                long start = System.currentTimeMillis();
                System.out.println();
                String res = xdzh(lineTxt);
                haveDataBuffer.write(res + "\r\n" + "\r\n");
                LOGGER.info("耗时" + (System.currentTimeMillis() - start) + "毫秒");
                System.out.println("---------------------------");
            }
            haveDataBuffer.flush();
            haveDataBuffer.close();
            Writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("身份证验证数据源验证异常");
        }
    }
}
