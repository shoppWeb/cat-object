package sun.cat.dao;

import sun.cat.entity.RootUser;

public interface RootUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RootUser record);

    int insertSelective(RootUser record);

    RootUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RootUser record);

    int updateByPrimaryKey(RootUser record);
}