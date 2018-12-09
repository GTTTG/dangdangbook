package com.oracle.ddbookmarket.daoImpl;

import com.oracle.ddbookmarket.dao.AdminDao;
import com.oracle.ddbookmarket.model.Admin;
import com.oracle.ddbookmarket.util.DBUtile;
import com.oracle.ddbookmarket.util.MD5Util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDaojdbcImpl implements AdminDao {
    @Override
    public boolean findAdmin(Admin admin) {
        Connection conn=null;
       PreparedStatement stmt=null;
        ResultSet rst=null;

        try {
            conn= DBUtile.getConnection();
          /*  String sql="select * from t_admin where name=? and pwd=? ";
           stmt=conn.prepareStatement(sql);
           stmt.setString(1,admin.getName());
           stmt.setString(2, MD5Util.getEncryptedPwd(admin.getPwd()));*/
            String sql="select pwd from t_admin where name=? ";
            stmt=conn.prepareStatement(sql);
            stmt.setString(1,admin.getName());
            rst=stmt.executeQuery();

            if(rst.next()) {
                String dbPwd=rst.getString(1);
               return MD5Util.validPasswd(admin.getPwd(),dbPwd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtile.fiee(rst, stmt, conn);
        }
        return false;
    }
}
