package com.oracle.ddbookmarket.bizImpl;

import java.util.List;

import com.oracle.ddbookmarket.biz.SmallTypeBiz;
import com.oracle.ddbookmarket.dao.SmallTypeDao;
import com.oracle.ddbookmarket.daoImpl.SmallTypeDaojdbcImpl;
import com.oracle.ddbookmarket.model.SmallType;

public class SmallTypeBizImpl implements SmallTypeBiz {

	@Override
	public boolean save(SmallType smallType) {
		SmallTypeDao smallTypeDao=new SmallTypeDaojdbcImpl();
		return smallTypeDao.save(smallType);
	}

	@Override
	public List<SmallType> findAllSmalltype(int bd) {
		SmallTypeDao smallTypeDao=new SmallTypeDaojdbcImpl();
		return smallTypeDao.findAllSmalltype(bd);
	}

	@Override
	public int findBidByid(int sid) {
		SmallTypeDao smallTypeDao=new SmallTypeDaojdbcImpl();
		return  smallTypeDao.findBidByid(sid);
	}


}
