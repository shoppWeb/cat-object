package sun.cat.common.extend.dingtalk.bean.Message;

/**
 * 图片消息
 * Created by 小小工作者 on 2016/4/21.
 */
public class ImageMessage extends Message {

    private Image image;//消息内容

    public ImageMessage( String mediaId) {
        super("image");
        this.image = new Image();
        this.image.setMedia_id(mediaId);
    }

    public class Image{
        private String media_id;//图片媒体文件id，可以调用上传媒体文件接口获取。建议宽600像素 x 400像素，宽高比3：2

        public String getMedia_id() {
            return media_id;
        }

        public void setMedia_id(String media_id) {
            this.media_id = media_id;
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
