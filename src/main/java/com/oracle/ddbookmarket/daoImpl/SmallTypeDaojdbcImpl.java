package com.oracle.ddbookmarket.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oracle.ddbookmarket.dao.SmallTypeDao;
import com.oracle.ddbookmarket.model.SmallType;
import com.oracle.ddbookmarket.util.DBUtile;

public class SmallTypeDaojdbcImpl implements SmallTypeDao {

	@Override
	public boolean save(SmallType smallType) {
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBUtile.getConnection();
			pst=conn.prepareStatement("insert into t_small_type values(default,?,?)");
			pst.setString(1, smallType.getName());
			pst.setInt(2, smallType.getBid());
			int ps=pst.executeUpdate();
			if(ps>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DBUtile.fiee(pst, conn);
		}
		return false;
	}

	@Override
	public List<SmallType> findAllSmalltype(int bd) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rst=null;
		
		try {
			conn=DBUtile.getConnection();
			stmt=conn.createStatement();
			String sql="select * from t_small_type where bid="+bd;
			rst=stmt.executeQuery(sql);
			List<SmallType>ls=new ArrayList<>();
			while(rst.next()) {
				SmallType st=new SmallType();
				st.setId(rst.getInt("id"));
				st.setName(rst.getString("name"));
				st.setBid(rst.getInt("bid"));
				ls.add(st);
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
	public int findBidByid(int sid) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rst=null;
		try{
		conn=DBUtile.getConnection();
		stmt=conn.createStatement();
		String sql="select bid from t_small_type where id="+sid;
		rst=stmt.executeQuery(sql);

		if(rst.next()) {
			return rst.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		DBUtile.fiee(rst, stmt, conn);
	}
		return 0;
	}


}
