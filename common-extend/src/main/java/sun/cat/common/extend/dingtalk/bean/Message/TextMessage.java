package sun.cat.common.extend.dingtalk.bean.Message;

/**
 *  文本消息
 * Created by 小小工作者 on 2016/4/21.
 */
public class TextMessage extends Message {

    private Text text;//消息内容

    public TextMessage( String content) {
        super("text");
        this.text = new Text();
        this.text.setContent(content);
    }

    public class Text{
        private String content;//消息内容

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }


    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
