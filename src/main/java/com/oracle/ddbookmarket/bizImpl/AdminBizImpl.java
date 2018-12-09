package com.oracle.ddbookmarket.bizImpl;

import com.oracle.ddbookmarket.biz.AdminBiz;
import com.oracle.ddbookmarket.dao.AdminDao;
import com.oracle.ddbookmarket.daoImpl.AdminDaojdbcImpl;
import com.oracle.ddbookmarket.model.Admin;

public class AdminBizImpl implements AdminBiz {
    @Override
    public boolean findAdmin(Admin admin) {
        AdminDao adminDao=new AdminDaojdbcImpl();
        return adminDao.findAdmin(admin);
    }
}
