package sun.cat.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import sun.cat.entity.RootUser;
import sun.cat.dao.RootUserMapper;


import sun.cat.service.IRootUserService;
@Service
public class RootUserService implements IRootUserService{

    @Autowired
    private RootUserMapper rootUserMapper;

    /**
     * 删除RootUser数据
     *  author : 小小工作者
     *  date : 2016-6-12 16:18:34
     */
    @Override
    public int deleteByPrimaryKey(java.lang.Integer id) throws Exception {
       return rootUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RootUser entityName) throws Exception{
        return rootUserMapper.insert(entityName);
    }

    @Override
    public int insertSelective(RootUser entityName) throws Exception{
        return rootUserMapper.insertSelective(entityName);
    }

    @Override
    public RootUser selectByPrimaryKey(java.lang.Integer id) throws Exception{
            return rootUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RootUser entityName) throws Exception{
        return rootUserMapper.updateByPrimaryKeySelective(entityName);
    }

    @Override
    public int updateByPrimaryKey(RootUser entityName)throws Exception{
        return rootUserMapper.updateByPrimaryKey(entityName);
    }


}
