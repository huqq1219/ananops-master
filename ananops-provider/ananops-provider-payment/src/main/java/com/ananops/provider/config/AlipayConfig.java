package com.ananops.provider.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2016101600702395";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCRjuQR8DcZMis5UKFf2xtqnq5P/3So1dTkGdI57QpMmolG25klh0fM6ASx14ZXQlfe8vq9r4lQFCo4RKK1s46eBn8HqJxTGzvz/g518uh7cQLr/qxEjMuogNqECUY0amvN7n2K0v4qFD/R06jQ91Lp3777Bt5JkVVzVAU9mNIOHvhG+OQ8IER63a0AAKNacQaVUIZx8YbZpqWBvjuXlXwyF4xb8J0L4nZGI/Rsj/zNHHcVQ1cFKaooI2luMajfxXy01+ENtO7B49ecedcBtbykFwaD6QA3gHpPnwh4lmj5Oxw2Fu26HIv1kb87pdTNRHZE4KyndDYzJvH80DuHdT0DAgMBAAECggEAFZjaBj9aLYbgGvT3vL1J6Bv7SStiJ9ryZ5XdN3MQYCVd0QGsS4EzChgy+iA8cp4JY68xvQtamGMlkIu+iK9HrfLQzlYaO8qCrsU3aBDOpeIyyMlUzicF4M6dnZGytPxFWpzUS9DanWu7vGpnVfocd08Xiy+Ns+S4hRAmZulR4X5FlGZVtu6yJp4104HGrl2fCASn45VdeKNpzBQIXj/+cWaphkQBUv4cowY2GXpiuzMmKnZ54PgvneI7LIw6PqNUUT/qqEkaWEMHDG8JLmHkSYS7PIYFBK0iAZMhgJ3mWb3BiOuTagwy6viDfSZJWcdk7JeebNke77Z6/ImUOO9/IQKBgQDFKckxzWnZVifLOkM3O5LM5D+bnPhHyj37ZwZihl1LXqb4yusw/ISEbfy+E25qE6OaKaYkeqEvD8G9lUaMCrZ4XR9574P1kMq5cSw+hUvDsVDPBoWhqD8Ooq390saaA2xaycBOsOsTxxZ+TIAtjB7TmCw3B23a5t2WbOftXV+VtwKBgQC8/sFHuUI8qy1kn4YvJPfHLi2rHX8rGsI3VW5VDuSqGi01ZekbABzFDydJu5R0/IC5masxtpooKiEyJS0Q9mY8+3c4djvbJL264RzL1pSoFqgvPivVEE38nHvcG+BDcEa3+v/XBCYSKVYe+1DeU/otkv7v36RSbihuVSGtZomzFQKBgQC1l3PoO8I224RRSLspvuPJdJwlLiD6tgn6Z/xYeFqrFWWCFhsihiFje43iXzE94BK55YxRYuTb3I5bl4Zfa2p+V36rJvAKF66vt83BU7Hhbon385DWwIg7aZgJBL3IkkiVIebOU2JgV+iesMUJrlmiFzD6CgyjJimG542BcJOd5wKBgQCU1d8qd7e1BW1DD+nhWLCUuEYYuqr6LCGE6A9yhr8HQoQVyELyE32UpqFfDI0WL//DqkPKa64tMThhQCukbxcGsVYrLdlndCDCeiNuV3dMhENAqSplqWhxJl7YcU+EbHgUzWMLVmYQ6WK8FViTLfkYSOnhx/KlQ8EtWneiEd0hHQKBgCeioHfgBOLD0MlcJ9j3i1dc9J4RJWs/RSnssuGV4n9xaFJ81tbNkuXqyHFuvbJa84oaHfaIO5+JgJAmWGtucKVrpnT94SBOQLbrHSvXTTujDml4Qhc0UC+bxeu0U9yI/MvSofknJu4UrrEpECTraRw0fINlqnVYiIOgTyQ6khv+";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt/97jB8L1BSgG0FlezZXCWiVFa4lDMIMWgbFh4nZJLa2hhUWS67daReWTEG8HRxFeK47PU/XSBIt1U6p32+3Q05bYgNvjGdrc4GmVZhhrLW5XD9tNhpJ/aKkFLVmIdE1kkxuqlOm5NZf8v4YTH2v1RCXYmcAJlvx5PWZPvRwqDHYw6ZtAn1H3CTkX3tgDWUltr7ebGYGWEwlB/mthOFDdnaJk5lX4MuxZuy/lpdS9vw6TEzfSbcVHBoYvG5ssYBLujikMhb5bu56nNNx/xCunFhHkBkVB9XRYALpuBt9saf0usOxEdFVIhSC464libfICA8OmuFWQEshAL+fXfucvQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

