package sun.cat.utils.file;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 下载文件
 * Created by 小小工作者 on 2015/12/19 0019.
 */
public class DownloadFile {
    static Logger logger = LogManager.getLogger();



    public static HttpServletResponse DownloadFile(HttpServletResponse response , String fileName , String fileRootPath){
        FileInputStream in = null;
        try {
//            fileName = new String(fileName ,"UTF-8");

            //通过文件名找出文件的所在目录
            String path = UploadFile.makePath(fileName, fileRootPath);
            //得到要下载的文件
            File file = new File(path + File.separator + fileName);
            //如果文件不存在
            if(!file.exists()){
                logger.error("您要下载的资源已被删除！！");
                return response;
            }

            //处理文件名
            String realname = fileName.substring(fileName.lastIndexOf("_")+1);
            //设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
            in = new FileInputStream(file);
            //创建输出流
            OutputStream out = response.getOutputStream();
            //创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            //循环将输入流中的内容读取到缓冲区当中
            while((len=in.read(buffer))>0) {
                //输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            //关闭文件输入流
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //关闭输出流
            //out.close();
            return response;
        }
    }


    /**
     * @Method: findFileSavePathByFileName
     * @Description: 通过文件名和存储上传文件根目录找出要下载的文件的所在路径
     * @param filename 要下载的文件名
     * @param saveRootPath 上传文件保存的根目录，也就是/WEB-INF/upload目录
     * @return 要下载的文件的存储目录
     */

    public static String findFileSavePathByFileName(String filename,String saveRootPath){
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        String dir = saveRootPath + File.separator + dir1 + File.separator + dir2;  //upload\2\3  upload\3\5
        File file = new File(dir);
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
}
