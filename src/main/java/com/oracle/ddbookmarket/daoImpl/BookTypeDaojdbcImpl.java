package com.oracle.ddbookmarket.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oracle.ddbookmarket.dao.BookTypeDao;
import com.oracle.ddbookmarket.model.BookType;
import com.oracle.ddbookmarket.model.PageConstant;
import com.oracle.ddbookmarket.util.DBUtile;

public class BookTypeDaojdbcImpl implements BookTypeDao {

	@Override
	public boolean save(BookType book) {
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBUtile.getConnection();
			pst=conn.prepareStatement("insert into t_book values(default,?,?,?,?,?,?,?,?)");
			pst.setString(1, book.getName());
			pst.setDouble(2, book.getPrice());
			pst.setString(3, book.getAuthor());
			pst.setString(4, book.getCbs());
			pst.setDate(5, new Date(book.getCbDate().getTime()));
			pst.setString(6,book.getDescn());
			pst.setInt(7, book.getSid());
			pst.setString(8, book.getPhoto());
			int ps=pst.executeUpdate();
			if(ps>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtile.fiee(pst, conn);
		}
		return false;
	}

	@Override
	public List<BookType> findAll(int currentPage,String name,int sid) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rst=null;
		
		try {
			conn=DBUtile.getConnection();
			stmt=conn.createStatement();
			String sql="select * from t_book  where 1=1";
			if(name!=null&&!name.equals("")) {
				sql+=" and name like '%"+name+"%' ";
			}
			if (sid!=-1) {
				sql+=" and sid="+sid;
			}
			sql+="  limit "+((currentPage-1)*PageConstant.PAGE_SIZE+1-1)+","+PageConstant.PAGE_SIZE;
			rst=stmt.executeQuery(sql);
			List<BookType>ls=new ArrayList<>();
			while(rst.next()) {
				BookType bt=new BookType();
				bt.setId(rst.getInt("id"));
				bt.setName(rst.getString("name"));
				bt.setPrice(rst.getDouble("price"));
				bt.setAuthor(rst.getString("author"));
				bt.setCbs(rst.getString("cbs"));
				bt.setCbDate(rst.getDate("cbDate"));
				bt.setDescn(rst.getString("descn"));
				bt.setSid(rst.getInt("sid"));
				bt.setPhoto(rst.getString("photo"));
				ls.add(bt);
			}
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtile.fiee(rst, stmt, conn);
		}
		return null;
	}


	@Override
	public int totaRow(String name, int sid) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rst=null;
		
		try {
			conn=DBUtile.getConnection();
			stmt=conn.createStatement();
			String sql="select count(*) from t_book where 1=1";
			if(name!=null&&!name.equals("")) {
				sql+=" and name like '%"+name+"%' ";
			}
			if (sid!=-1) {
				sql+=" and sid="+sid;
			}
			rst=stmt.executeQuery(sql);
			if(rst.next()) {
				System.out.println(rst.getInt(1)+"--------------");
				return rst.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtile.fiee(rst, stmt, conn);
		}
		return 0;
	}

	@Override
	public int dleById(int did) {
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBUtile.getConnection();
			pst=conn.prepareStatement("delete from t_book where id="+did);
			int ps=pst.executeUpdate();
				return ps;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtile.fiee(pst, conn);
		}
		return 0;
	}

	@Override
	public BookType findBookByid(int id) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rst=null;

		try {
			conn=DBUtile.getConnection();
			stmt=conn.createStatement();
			String sql="select * from t_book  where id="+id;


			rst=stmt.executeQuery(sql);

			if(rst.next()) {
				BookType bt=new BookType();
				bt.setId(rst.getInt("id"));
				bt.setName(rst.getString("name"));
				bt.setPrice(rst.getDouble("price"));
				bt.setAuthor(rst.getString("author"));
				bt.setCbs(rst.getString("cbs"));
				bt.setCbDate(rst.getDate("cbDate"));
				bt.setDescn(rst.getString("descn"));
				bt.setSid(rst.getInt("sid"));
				bt.setPhoto(rst.getString("photo"));
				return  bt;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtile.fiee(rst, stmt, conn);
		}
		return null;
	}

	@Override
	public boolean update(BookType book) {
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBUtile.getConnection();
			if(book.getPhoto()==null){//用户没有选择文件 表示不想修改图片
				pst=conn.prepareStatement("update t_book set name=?,price=?,author=?,cbs=?,cbDate=?,descn=?,sid=? where id=?");
				pst.setString(1, book.getName());
				pst.setDouble(2, book.getPrice());
				pst.setString(3, book.getAuthor());
				pst.setString(4, book.getCbs());
				pst.setDate(5, new Date(book.getCbDate().getTime()));
				pst.setString(6,book.getDescn());
				pst.setInt(7, book.getSid());
				pst.setInt(8,book.getId());

			}else{
				pst=conn.prepareStatement("update t_book set name=?,price=?,author=?,cbs=?,cbDate=?,descn=?,sid=?,photo=? where id=?");
				pst.setString(1, book.getName());
				pst.setDouble(2, book.getPrice());
				pst.setString(3, book.getAuthor());
				pst.setString(4, book.getCbs());
				pst.setDate(5, new Date(book.getCbDate().getTime()));
				pst.setString(6,book.getDescn());
				pst.setInt(7, book.getSid());
				pst.setString(8, book.getPhoto());
				pst.setInt(9,book.getId());
			}

			int ps=pst.executeUpdate();
			if(ps>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtile.fiee(pst, conn);
		}
		return false;
	}

}
