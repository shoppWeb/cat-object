package sun.cat.common.extend.dingtalk.bean.Message;

import java.util.List;

/**
 *  OA消息
 * Created by 小小工作者 on 2016/4/21.
 */
public class OAMessage extends Message {

    private OA oa;//消息内容

    public OAMessage(OA oa) {
        super("oa");
        this.oa = oa ;
    }

    protected class OA{
        private String message_url;//客户端点击消息时跳转到的H5地址
        private String pc_message_url;//PC端点击消息时跳转到的URL地址
        private Head head;//消息头部内容
        private Body body;//消息体

        public String getMessage_url() {
            return message_url;
        }

        public void setMessage_url(String message_url) {
            this.message_url = message_url;
        }

        public String getPc_message_url() {
            return pc_message_url;
        }

        public void setPc_message_url(String pc_message_url) {
            this.pc_message_url = pc_message_url;
        }

        public Head getHead() {
            return head;
        }

        public void setHead(Head head) {
            this.head = head;
        }

        public Body getBody() {
            return body;
        }

        public void setBody(Body body) {
            this.body = body;
        }

        class Head{
            private String bgcolor;//消息头部的背景颜色。长度限制为8个英文字符，其中前2为表示透明度，后6位表示颜色值。不要添加0x
            private String text;//消息的头部标题（仅适用于发送普通场景）

            public String getBgcolor() {
                return bgcolor;
            }

            public void setBgcolor(String bgcolor) {
                this.bgcolor = bgcolor;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public Head(String bgcolor, String text) {
                this.bgcolor = bgcolor;
                this.text = text;
            }
        }


        public class Body{
            private String title;//消息体的标题
            private String content;//消息体的内容，最多显示3行
            private String image;//消息体中的图片media_id
            private String file_count;//自定义的附件数目。此数字仅供显示，钉钉不作验证
            private String author;//自定义的作者名字
            private Rich rich;//单行富文本信息
            private List<Form> form;//消息体的表单，最多显示6个，超过会被隐藏

            public Body(String title, String content, String image, String file_count, String author, Rich rich, List<Form> form) {
                this.title = title;
                this.content = content;
                this.image = image;
                this.file_count = file_count;
                this.author = author;
                this.rich = rich;
                this.form = form;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getFile_count() {
                return file_count;
            }

            public void setFile_count(String file_count) {
                this.file_count = file_count;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public Rich getRich() {
                return rich;
            }

            public void setRich(Rich rich) {
                this.rich = rich;
            }

            public List<Form> getForm() {
                return form;
            }

            public void setForm(List<Form> form) {
                this.form = form;
            }

            public class Form{
                private String key;//消息体的关键字
                private String value;//消息体的关键字对应的值
            }


            public class Rich{
                private String num;//单行富文本信息的数目
                private String unit;//单行富文本信息的单位

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public Rich(String num, String unit) {
                    this.num = num;
                    this.unit = unit;
                }
            }
        }

    }
}
