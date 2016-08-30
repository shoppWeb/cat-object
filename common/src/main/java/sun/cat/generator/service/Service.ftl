<#--
    function :  Service接口模板
    author   :  小小工作者
    date     :   2016-06-12 14:34
 -->
package sun.cat.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sun.cat.entity.${entity.entityName};
import sun.cat.dao.${entity.entityName}Mapper;
import org.springframework.stereotype.Service;
import sun.cat.service.I${entity.entityName?cap_first}Service;

@Service
public class ${entity.entityName?cap_first}Service implements I${entity.entityName?cap_first}Service{
    static Logger logger = LogManager.getLogger();
    @Autowired
    private ${entity.entityName}Mapper ${entity.entityName?uncap_first}Mapper;

    /**
     * 删除${entity.entityName}数据
     *  author : 小小工作者
     *  date : ${.now}
     */
    @Override
    public int deleteByPrimaryKey(${entity.entityIdType} id) throws Exception {
       return ${entity.entityName?uncap_first}Mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(${entity.entityName} entityName) throws Exception{
        return ${entity.entityName?uncap_first}Mapper.insert(entityName);
    }

    @Override
    public int insertSelective(${entity.entityName} entityName) throws Exception{
        return ${entity.entityName?uncap_first}Mapper.insertSelective(entityName);
    }

    @Override
    public ${entity.entityName} selectByPrimaryKey(${entity.entityIdType} id) throws Exception{
            return ${entity.entityName?uncap_first}Mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(${entity.entityName} entityName) throws Exception{
        return ${entity.entityName?uncap_first}Mapper.updateByPrimaryKeySelective(entityName);
    }

    @Override
    public int updateByPrimaryKey(${entity.entityName} entityName)throws Exception{
        return ${entity.entityName?uncap_first}Mapper.updateByPrimaryKey(entityName);
    }


}
