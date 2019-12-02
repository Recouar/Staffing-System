package com.hxy.entity;

import java.sql.Date;

/**
 * @BelongsProject: myJava
 * @BelongsPackage: com.hxy.entity
 * @Author: 胡晓洋
 * @CreateTime: 2019-11-28 18:02
 * @Description: 员工类
 */
public class Employee {
    private Integer empno;      //员工编号
    private String ename;   //员工姓名
    private String job;     //员工职位
    private Integer mgr;        //上级编号
    private Date hiredate;  //入职时间
    private Double sal;     //员工薪水
    private Double comm;    //员工补贴
    private Integer deptno;     //部门编号

    public Employee() {
    }



    public Employee(Integer empno, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public Employee(Integer empno, String job, Double sal) {
        this.empno = empno;
        this.job = job;
        this.sal = sal;
    }

    public Employee(String ename, String job, int mgr, Date date, double sal, double comm,int deptno) {
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = date;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hireDate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptNo=" + deptno +
                '}';
    }
}
