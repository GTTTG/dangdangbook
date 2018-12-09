package com.oracle.ddbookmarket.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oracle.ddbookmarket.dao.BigTypeDao;
import com.oracle.ddbookmarket.model.BigType;
import com.oracle.ddbookmarket.util.DBUtile;

public class BigTypeDaojdbcImpl implements BigTypeDao {

	@Override
	public boolean save(String name) {
		Connection conn=null;
		PreparedStatement pst=null;
			try {
				conn=DBUtile.getConnection();
				String sql="insert into t_big_type values(default,?)";
				pst=conn.prepareStatement(sql);
				pst.setString(1, name);
				int ret =pst.executeUpdate();
				if(ret>0) {
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
	public List<BigType> findAll() {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rst=null;
		
		try {
			conn=DBUtile.getConnection();
			stmt=conn.createStatement();
			String sql="select * from t_big_type";
			rst=stmt.executeQuery(sql);
			List<BigType>ls=new ArrayList<>();
			while(rst.next()) {
				BigType bt=new BigType();
				bt.setId(rst.getInt("id"));
				bt.setName(rst.getString("name"));
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

}
