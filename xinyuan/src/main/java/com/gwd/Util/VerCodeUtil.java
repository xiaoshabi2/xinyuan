package com.gwd.Util;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class VerCodeUtil {

    public static  void CreateImage(HttpServletRequest request, HttpServletResponse response,String randomNum) throws IOException {
        //在内存中生成图片
        BufferedImage bufferedImage = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
        //获取到这张图片
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        //设置背景色为白色
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0, 0, 80, 20);
        //设置图片的字体和颜色
        graphics2D.setFont(new Font(null, Font.BOLD, 20));
        graphics2D.setColor(Color.BLUE);
        //往这张图片上写数据,横坐标是0，纵坐标是20
        graphics2D.drawString(randomNum, 0, 20);
        //将随机数存进Session域中
//        request.getSession().setAttribute("randomNum", randomNum);
//        //控制浏览器不缓存该图片
//        response.setHeader("Expires", "-1");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setHeader("Pragma", "no-cache");
        //通知浏览器以图片的方式打开
        response.setHeader("Content-type", "image/jpeg");
        //把图片写给浏览器
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
    }


    //生成6位随机数
    public static String CreateRandomNum() {
        Random random = new Random();
        //生成0-6位的随机数
        int num = random.nextInt(999999);
        //验证码的数位全都要6位数，于是将该随机数转换成字符串，不够位数就添加
        String randomNum = String.valueOf(num);
        //使用StringBuffer来拼凑字符串
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 6 - randomNum.length(); i++) {
            stringBuffer.append("0");
        }
        return stringBuffer.append(randomNum).toString();
    }

    public static String GetRandomString(int length) { //length表示生成字符串的长度
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
