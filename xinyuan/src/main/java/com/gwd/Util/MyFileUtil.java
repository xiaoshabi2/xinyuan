package com.gwd.Util;

import org.apache.commons.codec.digest.DigestUtils;

import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class MyFileUtil {

    /*本地部署时改到服务器路径*/
  //  public static final String filePath = "D:\\xinyuan\\wish\\image\\";

    private static final String WISH_IMG_PATH = "//home//xinyuan//wish//img//"; //服务器上的路径
    private static final String AVATAR_IMG_PATH = "//home//xinyuan//user//avatar//"; //服务器上的路径

//    private static final String WISH_IMG_PATH = "D:\\xinyuan\\wish\\image\\";
//    private static final String AVATAR_IMG_PATH = "D:\\xinyuan\\user\\avatar\\";


    public static final int PATH_NUMBER_WISH = 1;
    public static final int PATH_NUMBER_AVATAR = 2;


    public static String InImage(MultipartFile image,int pathNumber) throws IOException {
        String oldFileName = image.getOriginalFilename();
        String md5FileName = null;
        String newFileName = null;
        try {
            md5FileName = DigestUtils.md5Hex(image.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(oldFileName.contains(".")) {
            newFileName = md5FileName+"."+oldFileName.split("\\.")[1];
        }else {
            newFileName = md5FileName;
        }
        File path = null;
        if(pathNumber == PATH_NUMBER_WISH){
             path = new File(WISH_IMG_PATH+newFileName);
        }else if(pathNumber == PATH_NUMBER_AVATAR){
            path = new File(AVATAR_IMG_PATH+newFileName);
        }
        if(!path.exists()){
            path.createNewFile();
            InputStream in = image.getInputStream();
            FileOutputStream out = new FileOutputStream(path);
            byte[] media = new byte[1024];
            int length = in.read(media, 0, 1024);
            while(length  != -1) {
                out.write(media, 0, length);
                length = in.read(media, 0, 1024);
            }
            in.close();
            out.close();
        }
        return newFileName;
    }

    public static void outImage(String fileName,HttpServletResponse response,int pathNumber) throws IOException {
        File file = null;
        if(pathNumber == PATH_NUMBER_WISH){
            file = new File(WISH_IMG_PATH+fileName);
        }else if(pathNumber == PATH_NUMBER_AVATAR){
            file = new File(AVATAR_IMG_PATH+fileName);
        }
        FileInputStream inputStream = null;
        if(file.exists()) {
            inputStream = new FileInputStream(file);
            int i = inputStream.available();
            //byte数组用于存放图片字节数据
            byte[] buff = new byte[i];
            inputStream.read(buff);
            //记得关闭输入流
            inputStream.close();
            //设置发送到客户端的响应内容类型
            response.setContentType("image/*");
            //response.setContentType("text/html; charset=utf-8");
            OutputStream out = response.getOutputStream();
            out.write(buff);
            //关闭响应输出流
            out.close();
        }
    }


}
