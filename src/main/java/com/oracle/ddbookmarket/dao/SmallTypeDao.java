package com.oracle.ddbookmarket.dao;

import java.util.List;

import com.oracle.ddbookmarket.model.SmallType;

public interface SmallTypeDao {

	boolean save(SmallType smallType);




	List<SmallType> findAllSmalltype(int bd);

    int findBidByid(int sid);
}
