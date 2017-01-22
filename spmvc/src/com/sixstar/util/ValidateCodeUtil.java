package com.sixstar.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

/**
 * 生成验证码
 * @author lifei
 *
 */
public class ValidateCodeUtil {
    
    private static Random random = new Random();
    private static final String CODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    public static BufferedImage getValidateCode(HttpServletRequest request) {
       
        int width = 80, height = 38;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D grap = bufferedImage.createGraphics();
        grap.setColor(gerRandomColor(160, 230));
        grap.fillRect(0, 0, width, height);
        String code = "";
        grap.setFont(new Font("宋体", Font.BOLD, 20));
        int x = 10;
        char temp;
        addRandLines(grap, width, height);
        for (int i = 0 ; i < 4 ; i++) {
            temp = CODE.charAt(random.nextInt(36));
            code += temp;
            grap.setColor(gerRandomColor(80, 150));
            double degree = random.nextInt(20)%30*Math.PI/180;
            grap.rotate(degree, x, 25);
            grap.drawString(temp+"", x, 25);
            grap.rotate(-degree, x, 25);
            x+=20;
        }
        // 将验证码保存到什么位置？
        request.getSession().setAttribute("validateCode", code);
        return bufferedImage;
    }
    
    private static void addRandLines(Graphics2D grap, int width, int height) {
        for (int i = 0; i < 10 ; i++) {
            grap.setColor(gerRandomColor(200, 250));
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            grap.drawLine(x1, y1, x2, y2);
        }
    }
    
    private static Color gerRandomColor(int start, int end) {
        int red = random.nextInt(end-start) + random.nextInt(end-start);
        int green = start + random.nextInt(end-start);
        int blue = start + random.nextInt(end-start);
        return new Color(red, green, blue);
    }
}
