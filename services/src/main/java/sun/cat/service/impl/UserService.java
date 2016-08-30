package sun.cat.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import sun.cat.entity.User;
import sun.cat.dao.UserMapper;


import sun.cat.service.IUserService;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 删除User数据
     *  author : 小小工作者
     *  date : 2016-6-12 16:25:47
     */
    @Override
    public int deleteByPrimaryKey(java.lang.Integer id) throws Exception {
       return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User entityName) throws Exception{
        return userMapper.insert(entityName);
    }

    @Override
    public int insertSelective(User entityName) throws Exception{
        return userMapper.insertSelective(entityName);
    }

    @Override
    public User selectByPrimaryKey(java.lang.Integer id) throws Exception{
            return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User entityName) throws Exception{
        return userMapper.updateByPrimaryKeySelective(entityName);
    }

    @Override
    public int updateByPrimaryKey(User entityName)throws Exception{
        return userMapper.updateByPrimaryKey(entityName);
    }
}
