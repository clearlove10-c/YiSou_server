package com.smlz.yisounews.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;


public class ImageUtil {

    /**
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     * @param file base64编码字符串
     * @param path 图片路径-具体到文件
     * @return
     */
    public static String generateImage(String file, String path) {
        // 解密
        try {
            // 图片分类路径+图片名+图片后缀
            String imgClassPath = path.concat(UUID.randomUUID().toString()).concat(".jpg");
            // 解密
//			Base64.Decoder decoder = Base64.getDecoder();
            Base64.Decoder decoder = Base64.getMimeDecoder();

            // 去掉base64前缀 data:image/jpeg;base64,
            file = file.substring(file.indexOf(",", 1) + 1, file.length());
            byte[] b = decoder.decode(file);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            // 保存图片
            OutputStream out = new FileOutputStream("./tempPics/".concat(imgClassPath));
            try {
                out.write(b);
                out.flush();
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 返回图片的相对路径 = 图片分类路径+图片名+图片后缀
            return imgClassPath;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * TODO:将byte数组以Base64方式编码为字符串
     * @param bytes 待编码的byte数组
     * @return 编码后的字符串
     * */
    public static String encode(byte[] bytes){
        return new String(Base64.getUrlEncoder().encode(bytes), StandardCharsets.ISO_8859_1);
    }
    /**
     * TODO:将以Base64方式编码的字符串解码为byte数组
     * @param encodeStr 待解码的字符串
     * @return 解码后的byte数组
     * @throws IOException
     * */
    public static byte[] decode(String encodeStr) throws IOException{
        byte[] bt = null;
        Base64.Decoder decoder = Base64.getUrlDecoder();
        bt = decoder.decode(encodeStr);
        return bt;
    }

    /**
     * TODO:将图片以Base64方式编码为字符串
     * @param file 图片
     * @return 编码后的字符串
     * @throws IOException
     * */
    public static String encodeImage(File file) throws IOException{
        FileInputStream fis = new FileInputStream(file);
        byte[] rs = new byte[fis.available()];
        fis.read(rs);
        fis.close();
        return encode(rs);
    }
}
