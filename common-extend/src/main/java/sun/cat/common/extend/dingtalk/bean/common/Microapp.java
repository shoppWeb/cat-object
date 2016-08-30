package sun.cat.common.extend.dingtalk.bean.common;

/**
 * 微应用
 * Created by 小小工作者 on 2016/4/21.
 */
public class Microapp extends Err {
    private String appIcon;//微应用的图标。需要调用上传接口将图标上传到钉钉服务器后获取到的mediaId
    private String appName;//微应用的名称。长度限制为1~10个字符
    private String appDesc;//微应用的描述。长度限制为1~20个字符
    private String homepageUrl;//	微应用的移动端主页，必须以http开头或https开头
    private String pcHomepageUrl;//微应用的PC端主页，必须以http开头或https开头，如果不为空则必须与homepageUrl的域名一致
    private String ompLink;//微应用的OA后台管理主页，必须以http开头或https开头


    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public void setHomepageUrl(String homepageUrl) {
        this.homepageUrl = homepageUrl;
    }

    public String getPcHomepageUrl() {
        return pcHomepageUrl;
    }

    public void setPcHomepageUrl(String pcHomepageUrl) {
        this.pcHomepageUrl = pcHomepageUrl;
    }

    public String getOmpLink() {
        return ompLink;
    }

    public void setOmpLink(String ompLink) {
        this.ompLink = ompLink;
    }
}
