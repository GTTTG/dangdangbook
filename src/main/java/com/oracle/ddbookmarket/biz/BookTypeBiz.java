package com.oracle.ddbookmarket.biz;

import java.util.List;

import com.oracle.ddbookmarket.model.BookType;

public interface BookTypeBiz {

	boolean save(BookType book);

	List<BookType> findAll(int currentPage, String name, int sid);

	int totaRow(String name, int sid);

	int dleById(int did);


    BookType findBookByid(int id);

	boolean update(BookType book);
}
