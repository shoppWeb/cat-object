package sun.cat.common.extend.dtgrid.model;

/**
 * 排序model
 * Created by Administrator on 2015/10/12 0012.
 */
public class SortModel {
    /**
     * 字段
     */
    private String field;

    /**
     * 排序逻辑
     */
    private String logic;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
    }
}
