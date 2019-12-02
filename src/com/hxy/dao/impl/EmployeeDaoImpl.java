package com.hxy.dao.impl;

import com.hxy.dao.EmployeeDao;
import com.hxy.entity.Employee;
import com.hxy.util.DBUtil;

import java.util.List;

/**
 * @BelongsProject: myJava
 * @BelongsPackage: com.hxy.dao.impl
 * @Author: 胡晓洋
 * @CreateTime: 2019-11-28 18:08
 * @Description:
 */
public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public List<Employee> findAll() {
   /*     Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Employee> emplist =new ArrayList<Employee>() ;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String sql ="select * from emp";
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                int empNo = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date hireDate = rs.getDate("hireDate");
                double sal = rs.getDouble("sal");
                double comm = rs.getDouble("comm");
                int deptNo = rs.getInt("deptno");
                Employee emp = new Employee( empNo,  ename,  job,  mgr,  hireDate,  sal,  comm, deptNo);
                emplist.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(conn,stmt,rs);
        }

        return emplist;*/
        String sql ="select * from emp";
        Object params[]={};
        return DBUtil.executeQuery(sql,params,Employee.class);
    }

    @Override
    public Employee findById(int empno) {
        /*Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Employee emp = null;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String sql ="select * from emp where empno = empNo";
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                 empNo = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date hireDate = rs.getDate("hireDate");
                double sal = rs.getDouble("sal");
                double comm = rs.getDouble("comm");
                int deptNo = rs.getInt("deptno");
                emp = new Employee( empNo,  ename,  job,  mgr,  hireDate,  sal,  comm, deptNo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(conn,stmt,rs);
        }

        return emp;*/
        String sql ="select * from emp where empno = ?";
        Object params[] = {empno};
        List<Employee> list =  DBUtil.executeQuery(sql,params,Employee.class);
        if(list.size()==0){
            return null;
        }else{
            return list.get(0);
        }
    }

    @Override
    public List<Employee> find(int deptno, String job) {
        String sql ="select * from emp where job = ? and deptno = ?";
        Object params[] = {job,deptno};
        List<Employee> list =  DBUtil.executeQuery(sql,params,Employee.class);
        if(list.size()==0){
            return null;
        }else{
            return list;
        }
        /*Connection conn = null;
        //Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Employee> emplist = new ArrayList<Employee>();
        try {
            conn = DBUtil.getConnection();
            String sql ="select * from emp where job = ? and deptno = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,job);
            pstmt.setInt(2,deptno);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int empNo = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job1 = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date hireDate = rs.getDate("hireDate");
                double sal = rs.getDouble("sal");
                double comm = rs.getDouble("comm");
                int deptNo = rs.getInt("deptno");
                Employee emp = new Employee( empNo,  ename,  job1,  mgr,  hireDate,  sal,  comm, deptNo);
                emplist.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(conn,pstmt,rs);
        }

        return emplist;*/
    }

    @Override
    public int save(Employee emp) {
        String sql ="insert into emp values (null,?,?, ?,?,?,?,?)";
        Object[] params = new Object[]{emp.getEname(),emp.getJob(),
                emp.getMgr(),new java.sql.Date(emp.getHiredate().getTime()),emp.getSal(),emp.getComm(),emp.getDeptno()};
       return DBUtil.executeUpdate(sql,params);
    }

    @Override
    public int delete(int empNo) {
        return DBUtil.executeUpdate("delete from emp where empno = ?",new Object[]{empNo});
    }
    public int update(Employee emp){
        String sql ="update emp set job = ? , sal = ? where empno =?";
        Object[] params =new Object[]{emp.getJob(),emp.getSal(),emp.getEmpno()};
        return DBUtil.executeUpdate(sql,params);
    }
}
