package sun.cat.utils.file;

/**
 * 文件保存返回model
 * Created by 小小工作者 on 2015/12/19 0019.
 */
public class SaveFileReturnModel {
    private boolean marker = false;//返回标志位
    private String fileFullPath;//文件全路径
    private String fileName;//文件名
    private String filePath;//文件路径

    public boolean isMarker() {
        return marker;
    }

    public void setMarker(boolean marker) {
        this.marker = marker;
    }

    public String getFileFullPath() {
        return fileFullPath;
    }

    public void setFileFullPath(String fileFullPath) {
        this.fileFullPath = fileFullPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public SaveFileReturnModel() {
    }

    public SaveFileReturnModel(boolean marker) {
        this.marker = marker;
    }

    public SaveFileReturnModel(boolean marker, String fileFullPath, String fileName, String filePath) {
        this.marker = marker;
        this.fileFullPath = fileFullPath;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "SaveFileReturnModel{" +
                "marker=" + marker +
                ", fileFullPath='" + fileFullPath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
