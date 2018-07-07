package edu.csuft.yunyun.spider;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImgFile {
	//图片的路径
    String imagePath;
    /**
     * 
     * @param src
     *            图片的位置和路径
     */
    public ImgFile(String src) {
        imagePath = src;
      //建立一个HTTP连接，使用输入流获得数据，使用输出流写入磁盘
        HttpURLConnection conn = null;
        InputStream in = null;
        FileOutputStream out = null;
        
        try {
            conn = (HttpURLConnection) new URL(imagePath).openConnection();
            //读取数据
            in = conn.getInputStream();
            String uu = "D:\\代码\\SpiderDouban\\img";
            //获得图片的名字
            int index = imagePath.lastIndexOf('/');
            String file = imagePath.substring(index + 1);
            file = uu + file;
            //创建输出流，写入
            out = new FileOutputStream(file);
            
            byte[] buf = new byte[1024 + 16];
            int size;
            while(-1 != (size = in.read(buf))) {
                out.write(buf, 0, size);
            }
            //下载完成
            String name = Thread.currentThread().getName();
                      
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //不论是否发生异常都会执行的
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
            if(conn != null) {
                conn.disconnect();
            }
        }


    }



}
