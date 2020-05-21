package com.alipay.javaalipaydemo.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlipayClientConfig {

    @Bean
    public AlipayClient defaultAlipayClient() {
        //支付宝网关
        String url = "https://openapi.alipaydev.com/gateway.do";
        //应用ID
        String appId = "2016102300745824";
        //应用私钥
        String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCn6Vfr+UwLOHetRb+Ee9KWdgAXxNCGZfjzhYuWRDB9oyzJvZF9n6Rz1ii6/T2LCMFblqUgSdRqE6+8NXXrY95J6BcAr9riQu/oaltmMthJ3h0IPMZrNtsDhhsJ6K1fCUgCUaJaFPmShzOUVYC9d46FGAgGWAU57L//oJhFPl8ptPX+egUvlnl1u+3KJYqOWtnvOYWKwyzEowYa8rmWNnuGKYATZ3cuMToIRNvQ0DAcGkSh41tH9K+sFtqO0XIMs7lngU4nEOBomATOMI2pfY3COnC+ncwLcxqkKAxGgpxZGZB/sAMoEWchbVHuYTgs477I1LXtKG/vAb2/OvnzrES5AgMBAAECggEBAITf/Tm5wFmox9wQs+wNaKq0moI0t6eDjqx3Y+z37T+fH1ROdZHqAWPBd93xhQA/X3QxFF8mzbdb+NeBnt013LSzu3BJuXcP7XYIpGeNmOdro6HfJk21zT9OopsGzBvG5nsfStDqhE1WPJUHzKcis1/TkpYilZAvMAkEdztkjj4brqDEDeXNIQMczkwjVT8b0NPHusDcx6Hl79SNWSQW9nTaRjpUjcAICVtKjewjk8ybqRx5rDsbnanJeCQracJylaxDe9JWr06MHs/KVVTzKXkvXq0v1WF/OKeH4AP7cUi98CYhqyBSLV7jTT+LEN4kL0oe29VLaKEXJn+3m+GnXfkCgYEA9+yC8DmCEhomGn0DFFmOaMMpIxK1E+h1sL2RzFcXi1cs/PzIZTORtaY6rUv8xKrwEVb6opgwQeGiQY7a0m8RiMCU0zgjIbsvO3wF3228qdNCCqPYJLMAFs079/xQhG54wFOUVrErT6yLrlIuaAPE58Dc3McLBCyPJg51I1BKxb8CgYEArWGXmmMGoz4EuXXwWkt/8QftrkGP3eOfepxZOlKjUuNj4VEmk+f5rKpddXpYCQsbbD7cZbIbmRL97qyGQbhx9oeBFBWB4ZmE9JUBvAJGMdWiOUgGQW03vZqZeDp2OeVwbKON+r4NYpb8sE1EeQVySzUdG1KQYobFNNgfnBbDQ4cCgYEAs2Fi0+EoJWTaEOxGoCC0E0SxrPjKH59gGALxTMHu/RiUKm/TprWLRb0CU4TZXIq5rDIcoiwvLlvNBFYSKZPHQgTlKXZCpRNEd2ui7CgAC3gZHo2xw8vn0peoPiNHNqqfn7hZaehCiCE152j6vrOLcotuwicZ52k+IbDwPB7KGw0CgYBWKFQE1Ua8IhqI6tGonTIT3aO/3zaVxgI/FKjGccBVdUfx9Lid8T3CDtrogQZ0X/+dl9M/+fT66VQkobkJuZVwM7/x8gobyIUoYU9+VQb6xrWZj3AEXTjA0K2I8ExF0v0+DzyDqKPQJUWmeo8XMJyuQNOY+ztiqqQe8XMrxqf7ywKBgC4YV8FkFioNbWUJ5r8on+I82yBCY9p0F+ysXw6iIcc3sQ1HKZl4JZ4S5luWGXWrknBG8vjeFHGus3zPS72egCqzUDrgKS6WvZRqedMdGJ/M5LVtDfUbbAIfxz7NEsEpEz2cPg0oLHI9WcsaPk0rTuEaTGn2LyGPEz8RdYMhomt2";
        //格式
        String FORMAT = "json";
        //编码
        String CHARSET = "UTF-8";
        //支付宝公钥
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlWcdMunZbmLThBUqY8yuN1cee7QgbC7tPeaVAIKe30g4jNDHcmfU+rPyMtNfVnpBnwDtzRRmzaQgRTFEZATu0Ht2CpSKaYBMGffUPrRvh3h1fjZSStOLBUUAVS1qThx5XQrLMzIe5QTGg1nQJzs8B7dL0d3yyA2dwJVVe0YSl5GZnHzG9fRB80uP0HbRwHzrtILIeqsM4l8D2zsRa//UUFbbi6ce0hQLZARx2b8/tUeotHPRJN6wR4tbBvVN9ZqZgaDILEvADMDw1S6nukW4r7XRLazaxzoUBsFw3rMX4hOJn/r25Gnsj8beBaNUOitqRAXbZgDyIRskxF17aM9nZQIDAQAB";
        //商户生成签名字符串所使用的签名算法类型
        String SIGN_TYPE = "RSA2";
        //获得初始化的AlipayClient
        return new DefaultAlipayClient(url, appId, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
    }

    @Bean
    public AlipayTradePagePayRequest getAlipayRequest() {
        //创建API对应的request
        return new AlipayTradePagePayRequest();
    }
}
