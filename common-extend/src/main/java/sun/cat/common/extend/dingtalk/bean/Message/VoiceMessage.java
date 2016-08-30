package sun.cat.common.extend.dingtalk.bean.Message;

/**
 * 音频消息
 * Created by 小小工作者 on 2016/4/21.
 */
public class VoiceMessage extends Message {

    private Voice voice;//消息内容

    public VoiceMessage(String mediaId , String duration) {
        super("voice");
        this.voice = new Voice();
        this.voice.setMedia_id(mediaId);
        this.voice.setDuration(duration);
    }

    public class Voice{
        private String media_id;//语音媒体文件id，可以调用上传媒体文件接口获取。2MB，播放长度不超过60s，AMR格式
        private String duration;//正整数，小于60，表示音频时长
        public String getMedia_id() {
            return media_id;
        }

        public void setMedia_id(String media_id) {
            this.media_id = media_id;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}
