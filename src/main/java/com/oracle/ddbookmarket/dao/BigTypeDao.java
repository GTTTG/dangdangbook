package com.oracle.ddbookmarket.dao;

import java.util.List;

import com.oracle.ddbookmarket.model.BigType;

public interface BigTypeDao {

	boolean save(String name);

	List<BigType> findAll();

}
