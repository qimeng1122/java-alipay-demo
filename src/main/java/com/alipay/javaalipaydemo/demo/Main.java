package com.alipay.javaalipaydemo.demo;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.BaseClient.Config;
import com.alipay.easysdk.payment.common.models.AlipayTradeCreateResponse;

public class Main {
    public static void main(String[] args) {
        // 1. 设置参数（全局只需设置一次）
        Factory.setOptions(getOptions());
        try {
            // 2. 发起API调用（以支付能力下的统一收单交易创建接口为例）
            AlipayTradeCreateResponse response = Factory.Payment.Common().create("Apple iPhone11 128G",
                    "2234567890", "5799.00", "qprqel8542@sandbox.com");
            // 3. 处理响应或异常
            if ("10000".equals(response.code)) {
                System.out.println("调用成功");
            } else {
                System.err.println("调用失败，原因：" + response.msg + "，" + response.subMsg);
            }
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static Config getOptions() {
        Config config = new Config();
        config.protocol = "https";
        //config.gatewayHost = "openapi.alipay.com";
        config.gatewayHost = "openapi.alipaydev.com";
        config.signType = "RSA2";

        config.appId = "2016102300745824";

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCn6Vfr+UwLOHetRb+Ee9KWdgAXxNCGZfjzhYuWRDB9oyzJvZF9n6Rz1ii6/T2LCMFblqUgSdRqE6+8NXXrY95J6BcAr9riQu/oaltmMthJ3h0IPMZrNtsDhhsJ6K1fCUgCUaJaFPmShzOUVYC9d46FGAgGWAU57L//oJhFPl8ptPX+egUvlnl1u+3KJYqOWtnvOYWKwyzEowYa8rmWNnuGKYATZ3cuMToIRNvQ0DAcGkSh41tH9K+sFtqO0XIMs7lngU4nEOBomATOMI2pfY3COnC+ncwLcxqkKAxGgpxZGZB/sAMoEWchbVHuYTgs477I1LXtKG/vAb2/OvnzrES5AgMBAAECggEBAITf/Tm5wFmox9wQs+wNaKq0moI0t6eDjqx3Y+z37T+fH1ROdZHqAWPBd93xhQA/X3QxFF8mzbdb+NeBnt013LSzu3BJuXcP7XYIpGeNmOdro6HfJk21zT9OopsGzBvG5nsfStDqhE1WPJUHzKcis1/TkpYilZAvMAkEdztkjj4brqDEDeXNIQMczkwjVT8b0NPHusDcx6Hl79SNWSQW9nTaRjpUjcAICVtKjewjk8ybqRx5rDsbnanJeCQracJylaxDe9JWr06MHs/KVVTzKXkvXq0v1WF/OKeH4AP7cUi98CYhqyBSLV7jTT+LEN4kL0oe29VLaKEXJn+3m+GnXfkCgYEA9+yC8DmCEhomGn0DFFmOaMMpIxK1E+h1sL2RzFcXi1cs/PzIZTORtaY6rUv8xKrwEVb6opgwQeGiQY7a0m8RiMCU0zgjIbsvO3wF3228qdNCCqPYJLMAFs079/xQhG54wFOUVrErT6yLrlIuaAPE58Dc3McLBCyPJg51I1BKxb8CgYEArWGXmmMGoz4EuXXwWkt/8QftrkGP3eOfepxZOlKjUuNj4VEmk+f5rKpddXpYCQsbbD7cZbIbmRL97qyGQbhx9oeBFBWB4ZmE9JUBvAJGMdWiOUgGQW03vZqZeDp2OeVwbKON+r4NYpb8sE1EeQVySzUdG1KQYobFNNgfnBbDQ4cCgYEAs2Fi0+EoJWTaEOxGoCC0E0SxrPjKH59gGALxTMHu/RiUKm/TprWLRb0CU4TZXIq5rDIcoiwvLlvNBFYSKZPHQgTlKXZCpRNEd2ui7CgAC3gZHo2xw8vn0peoPiNHNqqfn7hZaehCiCE152j6vrOLcotuwicZ52k+IbDwPB7KGw0CgYBWKFQE1Ua8IhqI6tGonTIT3aO/3zaVxgI/FKjGccBVdUfx9Lid8T3CDtrogQZ0X/+dl9M/+fT66VQkobkJuZVwM7/x8gobyIUoYU9+VQb6xrWZj3AEXTjA0K2I8ExF0v0+DzyDqKPQJUWmeo8XMJyuQNOY+ztiqqQe8XMrxqf7ywKBgC4YV8FkFioNbWUJ5r8on+I82yBCY9p0F+ysXw6iIcc3sQ1HKZl4JZ4S5luWGXWrknBG8vjeFHGus3zPS72egCqzUDrgKS6WvZRqedMdGJ/M5LVtDfUbbAIfxz7NEsEpEz2cPg0oLHI9WcsaPk0rTuEaTGn2LyGPEz8RdYMhomt2";

        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
//        config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
//        config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->";
//        config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";

        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
         config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlWcdMunZbmLThBUqY8yuN1cee7QgbC7tPeaVAIKe30g4jNDHcmfU+rPyMtNfVnpBnwDtzRRmzaQgRTFEZATu0Ht2CpSKaYBMGffUPrRvh3h1fjZSStOLBUUAVS1qThx5XQrLMzIe5QTGg1nQJzs8B7dL0d3yyA2dwJVVe0YSl5GZnHzG9fRB80uP0HbRwHzrtILIeqsM4l8D2zsRa//UUFbbi6ce0hQLZARx2b8/tUeotHPRJN6wR4tbBvVN9ZqZgaDILEvADMDw1S6nukW4r7XRLazaxzoUBsFw3rMX4hOJn/r25Gnsj8beBaNUOitqRAXbZgDyIRskxF17aM9nZQIDAQAB";

        return config;
    }
}
