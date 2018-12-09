package com.oracle.ddbookmarket.biz;

import java.util.List;

import com.oracle.ddbookmarket.model.BigType;

public interface BigTypeBiz {


	boolean save(String name);

	List<BigType> findAllBigtype();




}
