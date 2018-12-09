package com.oracle.ddbookmarket.bizImpl;

import java.util.List;

import com.oracle.ddbookmarket.biz.BookTypeBiz;
import com.oracle.ddbookmarket.dao.BookTypeDao;
import com.oracle.ddbookmarket.daoImpl.BookTypeDaojdbcImpl;
import com.oracle.ddbookmarket.model.BookType;

public class BookTypeBizImpl implements BookTypeBiz {

	@Override
	public boolean save(BookType book) {
		BookTypeDao bookTypeDao=new BookTypeDaojdbcImpl();
		return bookTypeDao.save(book);
	}

	@Override
	public List<BookType> findAll(int currentPage,String name,int sid) {
		BookTypeDao bookTypeDao=new BookTypeDaojdbcImpl();
		return bookTypeDao.findAll(currentPage,name,sid);
	}

	@Override
	public int totaRow(String name,int sid) {
		BookTypeDao bookTypeDao=new BookTypeDaojdbcImpl();
		return bookTypeDao.totaRow(name,sid);
	}

	@Override
	public int dleById(int did) {
		BookTypeDao bookTypeDao=new BookTypeDaojdbcImpl();
		return bookTypeDao.dleById(did);
	}

	@Override
	public BookType findBookByid(int id) {
		BookTypeDao bookTypeDao=new BookTypeDaojdbcImpl();
		return bookTypeDao.findBookByid(id);
	}

	@Override
	public boolean update(BookType book) {
		BookTypeDao bookTypeDao=new BookTypeDaojdbcImpl();

		return bookTypeDao.update(book);
	}


}
