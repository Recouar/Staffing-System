package com.hxy.dao;

import com.hxy.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    /**
     * 查询所有员工
     * @return
     */
    public List<Employee> findAll();

    /**
     * 查询指定员工的编号
     * @param empNo
     * @return
     */
    public Employee findById (int empNo);

    /**
     * 修改员工信息
     * @param emp
     * @return
     */
    public int update(Employee emp);
    /**
     * 查询符合条件的员工
     * @param deptno
     * @param job
     * @return
     */
    public List<Employee> find(int deptno,String job);

    /**
     * 修改员工信息
     * @param emp
     * @return
     */
    public int save(Employee emp) ;

    /**
     * 删除指定编号的员工
     * @param empNo
     * @return
     */
    public int delete(int empNo);
}
