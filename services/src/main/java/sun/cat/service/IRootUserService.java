package sun.cat.service;
import sun.cat.entity.RootUser;

public interface IRootUserService {

public int deleteByPrimaryKey(java.lang.Integer id)throws Exception;

public int insert(RootUser entityName)throws Exception;

public int insertSelective(RootUser entityName)throws Exception;

public RootUser selectByPrimaryKey(java.lang.Integer id)throws Exception;

public int updateByPrimaryKeySelective(RootUser entityName)throws Exception;

public int updateByPrimaryKey(RootUser entityName)throws Exception;
}