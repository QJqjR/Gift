package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101000653738";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCIR+Dq0YhWnmAvecFr3zxQiBOPVT3p3mnIKvArnvj8N8uQ6SgURKI9LKHCo7HR4fwG1vR1tjG+Vo38qkWPbpHc8e6uA1Zkpf0YPbjlH1HPdT8+idjZ/gH7lm93VoJpLs7IZcNU0QtBoYO2CS2PI/y7QRwLlYRbrzO8yImUcXIvGW7LF72N1z7e2toQdCOISRgfbYcr1a61KkIcl1O/mx2zjd33T0wFBmUuA6B8hmOodfO9eTxHmXVvgvgALPsbqkIvCY57QkRpkDs8+eSVJfsLyvU7jGVZr32ncFwnT36rGup7nrD470rOqogwUo04Cib5udUUgjCkdGVFBbFQAdrVAgMBAAECggEAVGiw1sCRQZU23jD7rGTIZtjhxJV3k7M+scxmlZ4ReabVXxwKxwL7ugg66OOa7/SYtWPOZlpC1TGnICgWjZBeqArxWraWfCCTcsBIFSIg+e4VDhczSVIayEmVIMVkYOGtElJ1ecNAxZXUjpuArHgNwa3CQSqKoH8T/In5LQhXQPQGwpQ2+D41qxmjN5nP15mjZVmka1vo/Q95huEbc1hVdD/NrYdbyawIAiXY37ei3HlabYY8fOhL4PmBRMEAG9pTVe6fTwdOn5xSR/1edCA1iePq50LfEElt5sSWwOdF4SslOdQyGu6e31e63EXDBzkFjvDPdpAp2IMJSmLoCZQiwQKBgQDSA/Uhmt4eEs3MKjbBB1h6hdSMQdlkvcDEH4j6p29BZN5tpyGRU2D0r5otIAIcuLxdxMRU8zs9F5ZfMveWogClqcipm+Tv2580rBpjEhW1gGShHJ9wJjH26qJ7nHnmonLIa72CwMPbGE86lvxvv1xJgBayVVCq4LzBa8Vp2EoftwKBgQCmHtrN65MGGVmNVin2uf5FAqj2cUyVSX8lQGUDGAvrKYS+OtsHLLP1md89cTMH8d4r4BTNekdcG1inV9cDn/D1HWiEa68UVZENMtSRPnSc3a4K/nKegKFcDYOGTBU5IN4tvF7VutbQmHJYrGdf4w9Col0EBlW+0qvSlarsEYYB0wKBgEI3GzegM3354ccIXEEaBsyCm5QpQtifd0Keyrp0xgAxRGwAMPY3+q7LGuo1WFUj7cgGvPwnwDqv0Cpt0GA+7cNNt8W6MEFr1lNuG9e2oP+b3p5pft+GO2Slb9tXZ5vPBi+owJSUIGxYysonRqoZBIjsCLvraEaoGvtE9nY7yosbAoGAFf/KDDEhVFJxGJWLkyAIe00h4RkaAj8sdltzxRgfI/JooOSccvPJlUHeXX2rO2cdfAP93URPRPReJjMsx08cEunlGHxGQFQglHXhzqZNvSRiUxRlS3ZPaChl/Zf/y4P9yGLE0Fsg7Zjys6mJOxqPv8xOsyS/oDi4BnhJnV+otn8CgYEArISFroOxP4AmJzuiLd97KZVAlhrkd6DcFLEM/jLIiKUAB8AU/gkoftRkOFFvmw/PzZPkEoF4HmgRijm9l7dXj7h2HRpxD6IUytYeOtKU4+XjNOYSnoWPKEKCzAVNr69YGIHdmyvCGU6g5woHOrbS88sSTKfB6yEq+Q8tTXpZ6tg=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAove7sTACTEx850kBrwPcql+lND+WPmkkKoQzYTRE0dyJm1/A6XXaM3X3WsbuGhSdawsb9vvE4Eb1K/1TuF3Q0v+3ajc7mThbA3snAu2EqNtYMCzzfFGMGBmzG011KAGlInP3yoAqJZgxNU4RklvGv2tS2JqyTNmiY1yqDjWtpK6kKWMejxTNxxyF3/22E7siewlGzc1HLkc4hnNMpwKVrL1SMODj2ARu+THnZBBPseZZthM1nLYkU6wAc1K3x65lFj4BTdLDkxF6cJJRiAfTuhJ3DTyDYWm+/3HYbeXKZHALbh0upKYrpUek72+D8AM7X+GBJSLd0T3cled1sTkAhwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/TradePayDemo/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = " http://tuj2ys.natappfree.cc/Gift/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
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

