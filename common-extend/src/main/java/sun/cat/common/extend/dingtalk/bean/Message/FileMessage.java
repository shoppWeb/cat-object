package sun.cat.common.extend.dingtalk.bean.Message;

/**
 * 文件消息
 * Created by 小小工作者 on 2016/4/21.
 */
public class FileMessage extends Message {

    private File file;//消息内容

    public FileMessage(String mediaId) {
        super("file");
        this.file = new File();
        this.file.setMedia_id(mediaId);
    }

    public class File{
        private String media_id;//媒体文件id，可以调用上传媒体文件接口获取。10MB

        public String getMedia_id() {
            return media_id;
        }

        public void setMedia_id(String media_id) {
            this.media_id = media_id;
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
