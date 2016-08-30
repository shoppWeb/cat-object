package sun.cat.common.extend.dingtalk.bean.Message;

/**
 *  link消息
 * Created by 小小工作者 on 2016/4/21.
 */
public class LinkMessage extends Message {

    private Link link;//消息内容

    public LinkMessage( Link link) {
        super("link");
        this.link = link;
    }

    public class Link{
        private String title;//消息标题
        private String text;//消息描述
        private String pic_url;//图片媒体文件id，可以调用上传媒体文件接口获取
        private String message_url;//消息点击链接地址

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getMessage_url() {
            return message_url;
        }

        public void setMessage_url(String message_url) {
            this.message_url = message_url;
        }
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
