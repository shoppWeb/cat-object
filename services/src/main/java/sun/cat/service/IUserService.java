package sun.cat.service;
import sun.cat.entity.User;

public interface IUserService {

public int deleteByPrimaryKey(java.lang.Integer id)throws Exception;

public int insert(User entityName)throws Exception;

public int insertSelective(User entityName)throws Exception;

public User selectByPrimaryKey(java.lang.Integer id)throws Exception;

public int updateByPrimaryKeySelective(User entityName)throws Exception;

public int updateByPrimaryKey(User entityName)throws Exception;
}