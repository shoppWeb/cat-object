<#--
    function :  Service接口模板
    author   :  小小工作者
    date     :   2016-06-12 14:34
 -->
package sun.cat.service;
import sun.cat.entity.${entity.entityName};

public interface I${entity.entityName?substring(0)}Service {

public int deleteByPrimaryKey(${entity.entityIdType} id)throws Exception;

public int insert(${entity.entityName} entityName)throws Exception;

public int insertSelective(${entity.entityName} entityName)throws Exception;

public ${entity.entityName} selectByPrimaryKey(${entity.entityIdType} id)throws Exception;

public int updateByPrimaryKeySelective(${entity.entityName} entityName)throws Exception;

public int updateByPrimaryKey(${entity.entityName} entityName)throws Exception;
}