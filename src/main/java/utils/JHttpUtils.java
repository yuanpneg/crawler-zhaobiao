package utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class JHttpUtils {

    public static String sendPost(String url,String postBody,String charset,String cookie) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);

            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            // 设置通用的请求属性
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            conn.setRequestProperty("Charset", charset);
            if (!(cookie.equals("")||cookie.equals(null))){
                conn.setRequestProperty("Cookie",cookie);
            }

            conn.connect();


            //建立输入流，向指向的URL传入参数
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(postBody);
            dos.flush();
            dos.close();

            //获得响应状态
            int resultCode = conn.getResponseCode();

//            System.out.println(resultCode);

            if (HttpURLConnection.HTTP_OK == resultCode) {
                StringBuffer sb = new StringBuffer();
                String readLine = new String();
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "gbk"));
                while ((readLine = responseReader.readLine()) != null) {
                    sb.append(readLine).append("\n");
                }
                responseReader.close();
//                System.out.println(sb.toString());
                return sb.toString();
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        } finally {
        }
        return null;
    }
}
