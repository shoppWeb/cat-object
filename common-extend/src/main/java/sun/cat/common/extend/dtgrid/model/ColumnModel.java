package sun.cat.common.extend.dtgrid.model;

import java.util.Map;

/**
 * 列属性model
 * Created by Administrator on 2015/10/12 0012.
 */
public class ColumnModel {

    /**
     * 编号
     * 定义列编号，该编号将会对应数据中的字段名称，如果是自定义解析内容列，该属性可以随意填写，注意列与列之间该属性不能重复。
     */
    private String id;

    /**
     * 是否参与高级查询
     */
    private String search;

    /**
     * 是否作为导出列导出[default:true]
     * 是否可以执行导出，当该参数为false时，导出时将不会作为可选项进行导出。
     注意：export为javascript的保留关键字，使用时请尽量按此方式定义：'export':false，不推荐直接写为：export:false。
     参数类型：布尔类型（boolean）
     默认值：true
     */
    private boolean export = true;

    /**
     * 是否作为打印列打印[default:true]
     * 是否可以执行打印，当该参数为false时，打印时将不会作为可选项进行打印。
     参数类型：布尔类型（boolean）
     默认值：true
     */
    private boolean print = true;

    /**
     * 是否作为扩展列隐藏备用[default:true(对于自定义的复选或相关操作内容，请设置为false以免数据冲突)]
     * 是否在扩展列中显示该内容。由于我们的响应式是基于样式控制的，所以表样显示的数据内容和隐藏起来的内容是独立实现的，也就是说每一个单元格的内容实际上被展示了两份，只不过DT Grid会通过程序和配置来控制是在表样中显示还是在扩展中显示，这就导致有些自定义解析的内容会产生获取错误，比如一个自定义解析的单元格中显示的是复杂条件复选框，通过check属性无法直接完成，只能通过自定义解析器resolution属性来自定解析，这就导致每一个复选框会解析出两个复选框，以dom的方式获取选中内容会发生错误，所以我们提供了extra属性，当extra属性为false时，扩展中将不会显示该内容，避免获取数据时发生的错误。
     参数类型：布尔类型（boolean）
     */
    private boolean extra = true;

    /**
     * 显示的列名
     * 列名，可以随意填写
     */
    private String title;

    /**
     * 数据类型
     * 列展示数据的类型，可选类型如下：
     string：字符型数据
     date：日期型数据
     number：数值型数据
     参数类型：弱枚举类型，可选值：string，date，number
     */
    private String type;

    /**
     * 格式化
     * 内容格式化属性，该属性仅当列类型为date或number时有效。
     对于date类型数据来说，格式化脚本如下：
     y：年
     M：月
     d：日
     h：小时
     m：分钟
     s：秒
     S：毫秒
     典型的示例如下：yyyy-MM-dd hh:mm:ss.S，显示的内容为：2014-10-21 09:50:33.386。
     对于number类型数据来说，格式化脚本如下：
     #：如果顶位为0则不显示该内容
     0：无论如何都会显示，没有数据的情况下补全0
     典型的示例如下：#,###.00，显示的内容为：12,345,678.90。
     注意：format属性是影响打印和导出功能的，使用了format参数格式的内容在打印和导出时也会按照此格式进行解析。
     */
    private String format;

    /**
     * 原始数据类型
     * 列展示数据的原始类型，该参数主要用于格式化时判断原始数据的类型，目前仅当type参数为date时有效。当该属性不填写的时候，我们默认认为您传递的数据是JS的日期对象来进行格式化处理。可选类型如下：
     time_stamp_s：秒级时间戳
     time_stamp_ms：毫秒级时间戳
     string：字符串类型
     参数类型：弱枚举类型，可选值：time_stamp_s，time_stamp_ms，string
     */
    private String otype;

    /**
     * 原始格式
     * 配合otype参数使用，当otype参数为string时，我们需要您告知该日期字符串的格式，该格式就是在此标识的，脚本格式同format中日期的脚本格式相同。
     参数类型：字符串（string）
     */
    private String oformat;

    /**
     * 码表映射，用于高级查询及显示
     * 用于码表信息的扩展。目前很多大型系统都提供码表机制，通过缓存（对于J2EE项目来说通常存储在application域中）来存储码表信息，调用时可以实时调用，避免频繁访问数据库，并且也能很好的规划码表信息，避免通用信息解析混乱。
     DT Grid通过此参数可以实现码表的解析，如性别，1表示男，2表示女，那么获取数据时直接获取1或2即可，在此属性中设置一个JSON对象，以性别为例格式则为：{1:'男', 2:'女'}，则我们在显示表样是将会显示男或女。
     注意：如果码表中并没有解析的内容，如上述示例，传递的内容为3，那么我们将原样显示3。
     注意：码表信息是影响打印和导出的，在打印和导出过程中也会对码表信息进行解析。
     参数类型：对象（Object）
     */
    private Map<String, Object> codeTable;

    /**
     * 列样式
     * 定义列内容的样式，样式遵循标准css规范。
     参数类型：字符串（string）
     */
    private String columnStyle;

    /**
     * 列样式表
     * 定义列内容的样式表，使用自定义的class定义样式。
     参数类型：字符串（string）
     */
    private String columnClass;

    /**
     * 列头样式
     * 定义列标题的样式，样式遵循标准css规范。
     参数类型：字符串（string）
     */
    private String headerStyle;

    /**
     * 列头样式表
     * 定义列标题的样式表，使用自定义的class定义样式。
     参数类型：字符串（string）
     */
    private String headerClass;

    /**
     * 彻底隐藏
     * 是否彻底隐藏，当hide设置为true时，将在表样解析时不会被显示，在dom中也不会被查找得到。该属性主要用于一些比如主键之类的编号型数据的处理，使用hide属性将其隐藏，调用的时候通过方法获取数据取得编号使用。
     参数类型：布尔类型（boolean）
     */
    private boolean hide = false;

    /**
     * 隐藏类别
     * 该属性主要用于响应式表格的配置，用于配置在哪些终端下进行隐藏，提供的属性如下：
     xs：在小屏幕下隐藏，主要用于手机终端，触发宽度：小于768像素
     sm：在较小屏幕下隐藏，主要用于平板终端，触发宽度：介于768像素到992像素之间
     md：在中等屏幕下隐藏，主要用于分辨率较低的电脑终端：介于992像素到1200像素之间
     lg：在大屏幕下隐藏，主要用于大分辨率的电脑终端：大于1200像素
     DT Grid的响应式是完全基于Bootstrap组件的，并且基于visible-lg、visible-md、visible-sm、visible-xs四个css样式完成响应式的解析，如果您需要调整各种触发终端的参数，您可以直接修改Bootstrap中这四个样式对应的media内容。
     多个参数使用|方式分割，如：md|sm|xs，表示该列仅在大屏幕下显示。
     参数类型：字符串（string）
     默认值：空字符串，即所有终端全部显示。
     */
    private String hideType;

    /**
     * 快速查询
     * 是否启用快速查询，如果启用快速查询，在点击查询按钮显示的面板中将显示该列的快速查询表单内容。
     参数类型：布尔类型（boolean）
     */
    private boolean fastQuery;

    /**
     * 快速查询类别
     * 启用快速查询的类型，类型可选参数为：
     eq：equals，等于
     ne：not equals，不等于
     lk：like，模糊匹配
     ll：left like，左侧模糊匹配
     rl：right like，右侧模糊匹配
     gt：great than，大于
     ge：great than equals，大于等于
     lt：less than，小于
     le：less than equals，小于等于
     range：range，范围，通常用于时间或数值
     参数类型：弱枚举类型，可选值：eq、ne、lk、ll、rl、gt、ge、lt、le、range
     默认值：空字符串
     */
    private String fastQueryType;

    /**
     * 高级查询
     * 是否启用高级查询，当该参数为false时，高级查询将不会作为可选项进行过滤或排序。
     参数类型：布尔类型（boolean）
     默认值：true
     */
    private boolean advanceQuery;

    /**
     * 回调方法，参数：record value
     * 自定义解析函数。通过匿名方法（或外层预置方法）的方式定义，示例代码如下：
     * resolution:function(value, record, column, grid, dataNo, columnNo){
     var content = '';
     if(value==1){
     content += '<span style="background:#00a2ca;padding:2px 10px;color:white;">Male</span>';
     }else{
     content += '<span style="background:#c447ae;padding:2px 10px;color:white;">Female</span>';
     }
     return content;
     }

     resolution:function(value, record, column, grid, dataNo, columnNo){
     var content = '';
     content += '<button class="btn btn-xs btn-default" onclick="alert(\'Edit User: '+record.user_name+'\');"><i class="fa fa-edit"></i>&nbsp;&nbsp;Edit</button>';
     content += '&nbsp;&nbsp;';
     content += '<button class="btn btn-xs btn-danger" onclick="alert(\'Delete User: '+record.user_name+'\');"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;Delete</button>';
     return content;
     }
     自定义解析通常用于自定义显示的内容或在当前单元格中生成一些操作按钮。
     方法中将传递以下参数：
     value：当前单元格的值
     record：记录集，可以直接获取该行的数据
     column：列对象
     grid：表格对象
     dataNo：数据编号，从0开始
     columnNo：列编号，从0开始
     参数类型：方法（function）
     默认值：null
     */
    private String resolution;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public boolean isExport() {
        return export;
    }

    public void setExport(boolean export) {
        this.export = export;
    }

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getOtype() {
        return otype;
    }

    public void setOtype(String otype) {
        this.otype = otype;
    }

    public String getOformat() {
        return oformat;
    }

    public void setOformat(String oformat) {
        this.oformat = oformat;
    }

    public Map<String, Object> getCodeTable() {
        return codeTable;
    }

    public void setCodeTable(Map<String, Object> codeTable) {
        this.codeTable = codeTable;
    }

    public String getColumnStyle() {
        return columnStyle;
    }

    public void setColumnStyle(String columnStyle) {
        this.columnStyle = columnStyle;
    }

    public String getColumnClass() {
        return columnClass;
    }

    public void setColumnClass(String columnClass) {
        this.columnClass = columnClass;
    }

    public String getHeaderStyle() {
        return headerStyle;
    }

    public void setHeaderStyle(String headerStyle) {
        this.headerStyle = headerStyle;
    }

    public String getHeaderClass() {
        return headerClass;
    }

    public void setHeaderClass(String headerClass) {
        this.headerClass = headerClass;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public String getHideType() {
        return hideType;
    }

    public void setHideType(String hideType) {
        this.hideType = hideType;
    }

    public boolean isFastQuery() {
        return fastQuery;
    }

    public void setFastQuery(boolean fastQuery) {
        this.fastQuery = fastQuery;
    }

    public String getFastQueryType() {
        return fastQueryType;
    }

    public void setFastQueryType(String fastQueryType) {
        this.fastQueryType = fastQueryType;
    }

    public boolean isAdvanceQuery() {
        return advanceQuery;
    }

    public void setAdvanceQuery(boolean advanceQuery) {
        this.advanceQuery = advanceQuery;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

}
