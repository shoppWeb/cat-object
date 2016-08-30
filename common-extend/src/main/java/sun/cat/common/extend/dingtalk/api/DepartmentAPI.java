package sun.cat.common.extend.dingtalk.api;

import com.alibaba.fastjson.JSON;
import sun.cat.common.extend.dingtalk.bean.common.*;
import sun.cat.common.extend.dingtalk.client.LocalHttpClient;
import sun.cat.common.extend.dingtalk.util.JsonUtil;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * 部门管理
 * Created by 小小工作者 on 2016/4/21.
 */
public class DepartmentAPI extends BaseAPI {

    /**
     * 获取部门列表
     * @param accessToken
     * @return
     */
    public static DepartmentList getDepartmentList(String accessToken){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/department/list")
                .addParameter("access_token", accessToken)
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, DepartmentList.class);
    }

    /**
     * 获取部门信息
     * @param accessToken
     * @param id 部门id
     * @return
     */
    public static DepartmentInfo getDepartmentInfo(String accessToken , String id){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/department/get")
                .addParameter("access_token", accessToken)
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, DepartmentInfo.class);
    }

    /**
     * 创建部门
     * @param accessToken
     * @param departmentInfoJson
     * @return
     */
    public static DepartmentInfo createDepartment(String accessToken , String departmentInfoJson){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/department/create")
                .addParameter("access_token", accessToken)
                .setEntity(new StringEntity(departmentInfoJson, Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, DepartmentInfo.class);
    }

    /**
     * 创建部门
     * @param accessToken
     * @param departmentInfo
     * @return
     */
    public static DepartmentInfo createDepartment(String accessToken , DepartmentInfo departmentInfo){
        return createDepartment(accessToken , JsonUtil.toJSONString(departmentInfo));
    }


    /**
     * 更新部门
     * @param accessToken
     * @param departmentInfoJson
     * @return
     */
    public static Err updateDepartment(String accessToken , String departmentInfoJson){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/department/update")
                .addParameter("access_token", accessToken)
                .setEntity(new StringEntity(departmentInfoJson, Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, Err.class);
    }

    /**
     * 更新部门
     * @param accessToken
     * @param departmentInfo
     * @return
     */
    public static Err updateDepartment(String accessToken , DepartmentInfo departmentInfo){
        return updateDepartment(accessToken , JsonUtil.toJSONString(departmentInfo));
    }

    /**
     * 删除部门
     * @param accessToken
     * @param id
     * @return
     */
    public static Err deleteDepartment(String accessToken , String id){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/department/delete")
                .addParameter("access_token", accessToken)
                .addParameter("id", id)
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, DepartmentInfo.class);
    }




    @Test
    public void test(){
        DepartmentList departmentList = DepartmentAPI.getDepartmentList("c4649c04a28b32b9a6fcb34e88c3d989" );
        System.err.print(JSON.toJSONString(departmentList));
    }
}
