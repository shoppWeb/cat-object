package sun.cat.common.extend.dtgrid.model;

import java.util.List;
import java.util.Map;

/**
 * 交互式分页查询model
 * Created by Administrator on 2015/10/12 0012.
 */
public class PagerModel<T> {

    /**
     * 是否出错
     * 数据是否加载成功，若查询SQL出现错误或高级查询中配置错误，则该返回值为false，正确查询则为true
     */
    private boolean isSuccess;

    /**
     * 每页显示条数
     */
    private int pageSize;

    /**
     * 开始记录数
     */
    private int startRecord;

    /**
     * 当前页数
     */
    private int nowPage;

    /**
     * 记录总数
     */
    private int recordCount;

    /**
     * 总页数
     */
    private int pageCount;

    /**
     * 参数列表
     */
    private Map<String, Object> parameters;

    /**
     * 快速查询参数列表
     */
    private Map<String, Object> fastQueryParameters;

    /**
     * 高级查询列表
     */
    private List<ConditionModel> advanceQueryConditionModels;

    /**
     * 高级排序列表
     */
    private List<SortModel> advanceQuerySortModels;

    /**
     * 显示数据集
     */
    private List<T> exhibitDatas;

    /**
     * 是否导出：1-是，0-否
     */
    private boolean isExport;

    /**
     * 导出类型，支持excel、pdf、txt、cvs
     */
    private String exportType;

    /**
     * 导出文件名
     */
    private String exportFileName;

    /**
     * 导出列
     */
    private List<ColumnModel> exportColumns;

    /**
     * 全部数据导出
     */
    private boolean exportAllData;

    /**
     * 导出数据是否已被加工
     */
    private boolean exportDataIsProcessed;

    /**
     * 导出数据
     */
    private List<Map<String, Object>> exportDatas;

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Map<String, Object> getFastQueryParameters() {
        return fastQueryParameters;
    }

    public void setFastQueryParameters(Map<String, Object> fastQueryParameters) {
        this.fastQueryParameters = fastQueryParameters;
    }

    public List<ConditionModel> getAdvanceQueryConditionModels() {
        return advanceQueryConditionModels;
    }

    public void setAdvanceQueryConditionModels(List<ConditionModel> advanceQueryConditionModels) {
        this.advanceQueryConditionModels = advanceQueryConditionModels;
    }

    public List<SortModel> getAdvanceQuerySortModels() {
        return advanceQuerySortModels;
    }

    public void setAdvanceQuerySortModels(List<SortModel> advanceQuerySortModels) {
        this.advanceQuerySortModels = advanceQuerySortModels;
    }

    public List<T> getExhibitDatas() {
        return exhibitDatas;
    }

    public void setExhibitDatas(List<T> exhibitDatas) {
        this.exhibitDatas = exhibitDatas;
    }

    public boolean getIsExport() {
        return isExport;
    }

    public void setIsExport(boolean isExport) {
        this.isExport = isExport;
    }

    public String getExportType() {
        return exportType;
    }

    public void setExportType(String exportType) {
        this.exportType = exportType;
    }

    public String getExportFileName() {
        return exportFileName;
    }

    public void setExportFileName(String exportFileName) {
        this.exportFileName = exportFileName;
    }

    public List<ColumnModel> getExportColumns() {
        return exportColumns;
    }

    public void setExportColumns(List<ColumnModel> exportColumns) {
        this.exportColumns = exportColumns;
    }

    public boolean getExportAllData() {
        return exportAllData;
    }

    public void setExportAllData(boolean exportAllData) {
        this.exportAllData = exportAllData;
    }

    public boolean getExportDataIsProcessed() {
        return exportDataIsProcessed;
    }

    public void setExportDataIsProcessed(boolean exportDataIsProcessed) {
        this.exportDataIsProcessed = exportDataIsProcessed;
    }

    public List<Map<String, Object>> getExportDatas() {
        return exportDatas;
    }

    public void setExportDatas(List<Map<String, Object>> exportDatas) {
        this.exportDatas = exportDatas;
    }

    public PagerModel() {
        /**
         *    "exportAllData": false,
         "exportColumns": [],
         "exportDataIsProcessed": false,
         "exportDatas": [],
         "exportFileName": "",
         "exportType": "",
         "fastQueryParameters": {},
         "isExport": false,
         "isSuccess": true,
         "nowPage": 1,
         "pageCount": 1,
         "pageSize": 10,
         "parameters": {
         "like_user_code_or_user_name": "user_10734"
         },
         "recordCount": 1,
         "startRecord": 0
         */
        this.isSuccess = true;//是否出错
        this.exportDataIsProcessed = false;
        this.exportColumns=null;
        this.exportFileName="gridDownload";//下载文件名
        this.isExport = false;//用于判断前台的交互信息是否为导出的动作
        this.nowPage = 1;
        this.pageSize = 100;
        this.recordCount = 1;
        this.startRecord = 0 ;


    }
}
