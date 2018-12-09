package com.oracle.ddbookmarket.bizImpl;

import java.util.List;

import com.oracle.ddbookmarket.biz.BigTypeBiz;
import com.oracle.ddbookmarket.dao.BigTypeDao;
import com.oracle.ddbookmarket.daoImpl.BigTypeDaojdbcImpl;
import com.oracle.ddbookmarket.model.BigType;

public class BigTypeBizImpl implements BigTypeBiz {

	@Override
	public boolean save(String name) {
		BigTypeDao bigTypeDao=new BigTypeDaojdbcImpl();
		return bigTypeDao.save(name);
	}

	@Override
	public List<BigType> findAllBigtype() {
		BigTypeDao bigTypeDao=new BigTypeDaojdbcImpl();
		return bigTypeDao.findAll();
	}

}
