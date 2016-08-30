package sun.cat.datasource;

import org.aspectj.lang.JoinPoint;

public class DataSourceInterceptor {

	public void setdataSourceybboa(JoinPoint jp) {
		DatabaseContextHolder.setCustomerType("dataSourceybboa");
		System.out.println("daoOne8789------------============_++++++++");
	}
	
	public void setdataSourceybb(JoinPoint jp) {
		DatabaseContextHolder.setCustomerType("dataSourceybb");
		System.out.println("daoTwo8789------------============_++++++++");
	}

}