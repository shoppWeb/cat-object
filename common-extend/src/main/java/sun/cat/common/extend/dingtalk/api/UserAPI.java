package sun.cat.common.extend.dingtalk.api;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import sun.cat.common.extend.dingtalk.bean.common.*;
import sun.cat.common.extend.dingtalk.client.LocalHttpClient;
import sun.cat.common.extend.dingtalk.util.JsonUtil;

import java.nio.charset.Charset;

/**
 * 用户API
 * Created by 小小工作者 on 2016/4/21.
 */
public class UserAPI extends BaseAPI {
    /**
     *获取用户信息
     * @param accessToken
     * @param userid	员工在企业内的UserID，企业用来唯一标识用户的字段。
     * @return
     */
    public static UserInfo getUserInfo(String accessToken , String userid){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/user/get")
                .addParameter("access_token", accessToken)
                .addParameter("userid", userid)
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, UserInfo.class);
    }

    /**
     * 创建用户
     * @param accessToken
     * @param userInfoJson
     * @return
     */
    /**
    {
     "userid": "zhangsan",
     "name": "张三",
     "orderInDepts" : "{1:10, 2:20}",
     "department": [1, 2],
     "position": "产品经理",
     "mobile": "15913215421",
     "tel" : "010-123333",
     "workPlace" :"",
     "remark" : "",
     "email": "zhangsan@gzdev.com",
     "jobnumber": "111111",
     "isHide": false,
     "isSenior": false,
     "extattr": {
     "爱好":"旅游",
     "年龄":"24"
     }
     }*/
    public static UserInfo createUser(String accessToken , String userInfoJson){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/user/create")
                .addParameter("access_token", accessToken)
                .setEntity(new StringEntity(userInfoJson, Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, UserInfo.class);
    }

    /**
     * 创建用户
     * @param accessToken
     * @param UserInfo
     * @return
     */
    public static UserInfo createUser(String accessToken , UserInfo UserInfo){
        return createUser(accessToken , JsonUtil.toJSONString(UserInfo));
    }


    /**
     * 更新用户
     * @param accessToken
     * @param UserInfoJson
     * {
    "userid": "zhangsan",
    "name": "张三",
    "department": [1, 2],
    "orderInDepts": "{1:10}",
    "position": "产品经理",
    "mobile": "15913215421",
    "tel" : "010-123333",
    "workPlace" :"",
    "remark" : "",
    "email": "zhangsan@gzdev.com",
    "jobnumber": "111111",
    "isHide": false,
    "isSenior": false,
    "extattr": {
    "爱好":"旅游",
    "年龄":"24"
    }
    }
     * @return
     */
    public static Err updateUser(String accessToken , String UserInfoJson){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/user/update")
                .addParameter("access_token", accessToken)
                .setEntity(new StringEntity(UserInfoJson, Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, Err.class);
    }

    /**
     * 更新用户
     * @param accessToken
     * @param userInfo
     * @return
     */
    public static Err updateUser(String accessToken , UserInfo userInfo){
        return updateUser(accessToken , JsonUtil.toJSONString(userInfo));
    }

    /**
     * 删除用户
     * @param accessToken
     * @param id
     * @return
     */
    public static Err deleteUser(String accessToken , String id){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/user/delete")
                .addParameter("access_token", accessToken)
                .addParameter("id", id)
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, Err.class);
    }

    /**
     * 批量删除用户
     * @param accessToken
     * @param id
     * @return
     */
    public static Err batchDeleteUser(String accessToken , String[] id){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/user/delete")
                .addParameter("access_token", accessToken)
                .addParameter("useridlist", id.toString())
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, Err.class);
    }

    /**
     * 获取部门成员
     * @param accessToken
     * @param departmentId 获取的部门id
     * @return
     */
    public static UserList simpleUserList(String accessToken , String departmentId){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/user/simplelist")
                .addParameter("access_token", accessToken)
                .addParameter("department_id", departmentId)
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, UserList.class);
    }

    //获取部门成员（详情）

    public static UserList simpleUserListInfo(String accessToken ,String departmentId){
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(BASE_URI + "/user/list")
                .addParameter("access_token", accessToken)
                .addParameter("department_id", departmentId)
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, UserList.class);
    }


    public static void main(String args[]){
        AccessToken accessToken = TokenAPI.accessToken("ding3dfd0f1168a40f72", "iFEHb5isU4y_EN7_8ncijt-KOf4MkcMbZYtQ19wmbxzZk_y4eKLUFj_1zzL0MhIe");
        DepartmentList departmentList = DepartmentAPI.getDepartmentList(accessToken.getAccess_token());
        System.err.print(JSON.toJSONString(departmentList));

        UserList userList = UserAPI.simpleUserListInfo(accessToken.getAccess_token(), String.valueOf(departmentList.getDepartment().get(0).getId()));
        System.err.print(JSON.toJSONString(userList));


    }
}
