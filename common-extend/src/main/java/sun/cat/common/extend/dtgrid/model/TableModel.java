package sun.cat.common.extend.dtgrid.model;

import java.util.ArrayList;

/**
 * 表格model
 * Created by Administrator on 2015/10/12 0012.
 */
public class TableModel {
    /**
     用于设置表格的编号，如果没有特殊需要可以不设置该属性，表格构建过程中将会自动生成唯一的GUID来处理。
     注意：同一页面中的多个表格中的该属性不能重复。
     参数类型：字符串（string）
     默认值：GUID
     */
    private String id;

    /**
     *用于设置表格的语言，目前提供三种语言：英文、简体中文、繁体中文，默认为英文。
     注意：语言需要引入对应的i18n语言文件。
     参数类型：弱枚举类型，可选值：en，zh-cn，zh-tw，可扩展其他语言
     默认值：en
     */
    private String lang;

    /**
     *是否通过ajax的方式加载数据，如果设置为true，则将通过loadURL的地址获取数据，如果设置为false，则基础数据由datas属性传入。
     参数类型：布尔类型（boolean）
     默认值：true
     */
    private boolean ajaxLoad;

    /**
     * 是否一次全部加载数据。该参数仅在ajaxLoad属性为true时起作用，不过我们并不推荐这种方式，全部加载会影响第一次的执行效率，带来较差的用户体验，而且数据展现有可能并非是实时的。优势在于一次加载，后期的处理效率非常高。
     参数类型：布尔类型（boolean）
     默认值：false
     */
    private boolean loadAll;

    /**
     * ajax加载数据的地址，本属性只有在ajaxLoad参数设置为true时起作用。
     注意：如果是一次全部数据加载模式（即loadAll属性为true），请保证loadURL返回值为JSON数组，如果非全部数据加载模式，则需要返回对应的Pager对象，Pager对象的格式在提供的下载项目中有具体实现。
     参数类型：字符串（string）
     默认值：空字符串
     */
    private String loadURL;

    /**
     * 表格数据，数据格式为JSON数组。该参数仅在ajaxLoad参数设置为false时有效。
     参数类型：JSON数组
     默认值：null
     */
    private Object datas;

    /**
     * 定义表格的全局样式，样式遵循标准css规范。
     参数类型：字符串（string）
     默认值：空字符串
     */
    private String tableStyle;

    /**
     *定义表格的全局样式表，使用自定义的class定义样式。
     参数类型：字符串（string）
     默认值：table table-bordered table-hover table-responsive
     */
    private String tableClass;

    /**
     * DT Grid提供复选功能，设置该属性为true则将会在所有列之前添加一个复选框的列，并在列标题提供全部选择、全部取消功能。该复选框可以使用getCheckedRecords方法来获取选中的内容。
     参数类型：布尔类型（boolean）
     默认值：false
     */
    private boolean check;

    /**
     * 是否显示列标题。
     参数类型：布尔类型（boolean）
     默认值：true
     */
    private boolean showHeader;

    /**
     *表格承载容器对象的id，设置完成后表格将显示在此容器中。
     参数类型：字符串（string）
     默认值：dtGridContainer
     */
    private String girdContainer;

    /**
     * 工具条承载容器对象的id，设置完成后表格的工具条将显示在此容器中。
     参数类型：字符串（string）
     默认值：dtGridToolBarContainer
     */
    private String toolbarContainer;

    /**
     * 工具条可选内容，可选参数如下：
     refresh：刷新表格
     fastQuery：快速查询
     advanceQuery：高级查询
     export：导出
     excel：导出excel文件
     csv：导出csv文件
     pdf：导出pdf文件
     txt：导出txt文件
     print：打印
     多参数采用|符号进行分割，export的典型格式为：export[excel,csv,pdf,txt]，全格式参考：refresh|faseQuery|advanceQuery|export[excel,csv,pdf,txt]|print。
     参数类型：字符串（string）
     默认值：refresh|faseQuery|advanceQuery|export[excel,csv,pdf,txt]|print
     */
    private String tools;

    /**
     * 导出文件的文件名。
     参数类型：字符串（stirng）
     默认值：datas
     */
    private String exportFileName;

    /**
     * 导出文件的响应地址，该属性对ajax动态加载分页模式无效，ajax动态分页加载模式由于需要执行SQL拼接操作，所以是直接封装在loadURL参数中的，具体的实现方式请参考我们提供下载的项目实现。
     参数类型：字符串（string）
     默认值：/dtgrid/export
     */
    private String exportURL;

    /**
     * 每页显示条数。
     参数类型：整数（integer）
     默认值：20
     */
    private Integer pageSize;

    /**
     *每页显示条数的限制。如果该参数为数组，则按照数组中的内容生成下拉框供用户选择每页显示条数，如果该参数为整数，则提供输入框供用户自行输入每页显示条数，此参数中设置的值为限制输入的最大值，如果该参数设置为其他类型，则原样显示每页显示条数，不提供用户自行更改。
     参数类型：数组（array） 或 整数（integer） 或 其他
     默认值：[20, 50, 100]
     */
    private ArrayList pageSizeLimit;

    /**
     * 列对象定义，将上述定义列的JSON数组对象传递到此参数中。
     参数类型：JSON数组
     默认值：null
     */
    private Object columns;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public boolean isAjaxLoad() {
        return ajaxLoad;
    }

    public void setAjaxLoad(boolean ajaxLoad) {
        this.ajaxLoad = ajaxLoad;
    }

    public boolean isLoadAll() {
        return loadAll;
    }

    public void setLoadAll(boolean loadAll) {
        this.loadAll = loadAll;
    }

    public String getLoadURL() {
        return loadURL;
    }

    public void setLoadURL(String loadURL) {
        this.loadURL = loadURL;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

    public String getTableStyle() {
        return tableStyle;
    }

    public void setTableStyle(String tableStyle) {
        this.tableStyle = tableStyle;
    }

    public String getTableClass() {
        return tableClass;
    }

    public void setTableClass(String tableClass) {
        this.tableClass = tableClass;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isShowHeader() {
        return showHeader;
    }

    public void setShowHeader(boolean showHeader) {
        this.showHeader = showHeader;
    }

    public String getGirdContainer() {
        return girdContainer;
    }

    public void setGirdContainer(String girdContainer) {
        this.girdContainer = girdContainer;
    }

    public String getToolbarContainer() {
        return toolbarContainer;
    }

    public void setToolbarContainer(String toolbarContainer) {
        this.toolbarContainer = toolbarContainer;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public String getExportFileName() {
        return exportFileName;
    }

    public void setExportFileName(String exportFileName) {
        this.exportFileName = exportFileName;
    }

    public String getExportURL() {
        return exportURL;
    }

    public void setExportURL(String exportURL) {
        this.exportURL = exportURL;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public ArrayList getPageSizeLimit() {
        return pageSizeLimit;
    }

    public void setPageSizeLimit(ArrayList pageSizeLimit) {
        this.pageSizeLimit = pageSizeLimit;
    }

    public Object getColumns() {
        return columns;
    }

    public void setColumns(Object columns) {
        this.columns = columns;
    }
}
