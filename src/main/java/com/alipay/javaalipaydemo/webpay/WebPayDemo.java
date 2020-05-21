package com.alipay.javaalipaydemo.webpay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class WebPayDemo {

    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private AlipayTradePagePayRequest alipayRequest;

    /**
     * 统一收单下单并支付页面接口测试
     * @param httpRequest
     * @param httpResponse
     * @throws IOException
     */
    @RequestMapping("/test")
    public void test(HttpServletRequest httpRequest,
                     HttpServletResponse httpResponse) throws IOException {

        //设置支付成功跳转页面，展示下单详情
        alipayRequest.setReturnUrl("http://localhost:8080/returnUrl");
        //支付成功异步通知消息，返回success
        alipayRequest.setNotifyUrl("http://ea27b74c.ngrok.io/notifyUrl"); //在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101011\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":88.88," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }" +
                "  }"); //填充业务参数
        String form = "";
        try {
            AlipayTradePagePayResponse response = alipayClient.pageExecute(alipayRequest);
            if (response.isSuccess()) {
                form = response.getBody();  //调用SDK生成表单
                System.out.println("统一下单接口调用成功");
            } else {
                System.out.println("统一下单接口调用失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + "UTF-8");
        httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    /**
     * 异步通知
     * @param request
     * @return
     */
    @RequestMapping("/notifyUrl")
    @ResponseBody
    public String notifyUrl(HttpServletRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        try {
            //这里拿到支付宝通知数据
            Map<String, String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                // 乱码解决，这段代码在出现乱码时使用
                // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
            //打印数据看看
            System.out.println("数据："+ new Gson().toJson(requestParams));
            //获取其中一个值看看
            String out_trade_no = (String) params.get("out_trade_no");
            System.out.println("订单号：" + out_trade_no);
        } catch(Exception e){
            System.out.println(e);
        }
        System.out.println("收到返回消息");
        return "success";
    }

    /**
     * 支付成功后返回商家页面
     * @param request
     * @param name
     * @return
     */
    @RequestMapping("/returnUrl")
    public String returnUrl(HttpServletRequest request, @RequestParam(value = "name", required = false, defaultValue = "您订购的商品下单成功，我们将尽快安排发货") String name) {

        Map<String, Object> params = new HashMap<>();
        try {
            //这里拿到支付宝回调数据
            Map<String, String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String names = (String) iter.next();
                String[] values = (String[]) requestParams.get(names);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                // 乱码解决，这段代码在出现乱码时使用
                // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(names, valueStr);
            }
            //打印数据看看
            System.out.println("数据："+ new Gson().toJson(requestParams));
            //获取其中一个值看看
            String out_trade_no = (String) params.get("out_trade_no");
            System.out.println("订单号：" + out_trade_no);
        } catch(Exception e){
            System.out.println(e);
        }

        System.out.println("返回页面被调用");
        request.setAttribute("name", name);
        return "hello";
    }


}
