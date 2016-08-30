package sun.cat.common.extend.dingtalk.bean.common;

/**
 * 创建微应用返回值
 * Created by 小小工作者 on 2016/4/21.
 */
public class MicroappCreateResponse extends Err {
   private int id;//创建的微应用id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
