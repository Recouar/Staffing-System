package com.hxy.test;

import com.hxy.dao.EmployeeDao;
import com.hxy.dao.impl.EmployeeDaoImpl;
import com.hxy.entity.Employee;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @BelongsProject: myJava
 * @BelongsPackage: com.hxy.test
 * @Author: 胡晓洋
 * @CreateTime: 2019-11-28 18:05
 * @Description: 员工操作系统测试类
 */
public class Test {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println();
            System.out.println("*************欢迎进入员工管理系统************");
            System.out.println("\t1.查看所有员工");
            System.out.println("\t2.查看指定编号的员工");
            System.out.println("\t3.添加员工");
            System.out.println("\t4.删除员工");
            System.out.println("\t5.通过职位跟部门编号进行筛选");
            System.out.println("\t6.修改员工信息");
            System.out.println("\t0.退出");
            System.out.println("*****************************************");
            System.out.println("请选择菜单：");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    findAll();
                    break;
                case 2:
                    findById();
                    break;
                case 3:
                    save();
                    break;
                case 4:
                   delete();
                    break;
               case 5:
                    find();
                    break;
                case 6:
                    upDate();
                    break;

                case 0:
                    System.out.println("谢谢使用");
                    return;
                default:
                    System.out.println("输入有误");
            }
            System.out.println("按回车键结束...................");
            sc.nextLine();
            sc.nextLine();
        }while(true);
    }

    private static void upDate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要修改员工的职位");
        String job = sc.next();
        System.out.println("请输入你要修改员工的薪水");
        Double sal = sc.nextDouble();
        System.out.println("请输入你要修改员工的编号");
        int empno = sc.nextInt();
        Employee emp = new Employee(empno,job,sal);
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        int n  = employeeDao.update(emp);
        if(n>0){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
        }
    }

    private static void find() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要筛选的条件- deptno");
        int deptno = sc.nextInt();
        System.out.println("请输入你要筛选的条件-job");
        String job = sc.next();
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        List<Employee> list = employeeDao.find(deptno,job);
        if(list==null){
            System.out.println("嘛也没有");
        }else {
            System.out.println("员工编号\t员工姓名\t员工职位\t上级编号\t入职时间\t\t员工薪水\t员工补贴\t所属部门");
            for (Employee emp : list) {
                System.out.println(emp.getEmpno() + "\t\t" + emp.getEname() + "\t\t" + emp.getJob() + "\t\t" + emp.getMgr() + "\t\t" + emp.getHiredate()
                        + "\t\t" + emp.getSal() + "\t\t" + emp.getComm()+"\t\t"+emp.getDeptno());
            }
        }
    }

    private static void delete() {
        System.out.println("请输入您要删除员工的编号");
        Scanner sc = new Scanner(System.in);
        int empno = sc.nextInt();
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        int n = employeeDao.delete(empno);
        if(n>0){
            System.out.println("删除成功");
        }else{
            System.out.println("没有你说的这个人");
        }
    }

    private static void save()  {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要添加员工的姓名");
        String ename = sc.next();
        System.out.println("请输入你要添加员工的职位");
        String  job = sc.next();
        System.out.println("请输入你要添加员工的上级编号");
        int mgr = sc.nextInt();
        System.out.println("请输入你要添加员工的入职时间");
        String str = sc.next();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hiredate = null;
        try {
             hiredate = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("请输入你要添加员工的薪水");
        double sal = sc.nextInt();
        System.out.println("请输入你要添加员工的补贴");
        double comm = sc.nextInt();
        System.out.println("请输入你要添加员工的部门");
        int deptno = sc.nextInt();
        Employee emp = new Employee( ename,job,mgr, new java.sql.Date(hiredate.getTime()),  sal, comm,deptno);
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        int n = employeeDao.save(emp);
        if(n>0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
            return;
        }

    }

    public static void findAll(){
        EmployeeDao employeeDao= new EmployeeDaoImpl();
        List<Employee> emplist = employeeDao.findAll();
        if(emplist.size()==0){
            System.out.println("一个员工都没有");
        }else{
            System.out.println("员工编号\t员工姓名\t员工职位\t上级编号\t入职时间\t\t员工薪水\t员工补贴\t所属部门");
            for(Employee emp:emplist)
                if(emp.getEmpno()==7844){
                    System.out.println(emp.getEmpno()+"\t\t"+emp.getEname()+"\t\t"+emp.getJob()+"\t"+emp.getMgr()+"\t\t"+emp.getHiredate()
                            +"\t\t"+emp.getSal()+"\t\t"+emp.getComm()
                            +"\t\t"+emp.getDeptno());
                }else if("SALESMAN".equals(emp.getJob())){
                    System.out.println(emp.getEmpno()+"\t\t"+emp.getEname()+"\t\t"+emp.getJob()+"\t"+emp.getMgr()+"\t\t"+emp.getHiredate()
                            +"\t\t"+emp.getSal()+"\t\t"+emp.getComm()+"\t\t"+emp.getDeptno());
                }else if("KING".equals(emp.getEname())){
                    System.out.println(emp.getEmpno()+"\t\t"+emp.getEname()+"\t\t"+emp.getJob()+"\t"+emp.getMgr()+"\t\t"+emp.getHiredate()
                            +"\t\t"+emp.getSal()+"\t\t"+emp.getComm()+"\t\t"+emp.getDeptno());
                }else{
                    System.out.println(emp.getEmpno()+"\t\t"+emp.getEname()+"\t\t"+emp.getJob()+"\t\t"+emp.getMgr()+"\t\t"+emp.getHiredate()
                            +"\t\t"+emp.getSal()+"\t\t"+emp.getComm()+"\t\t"+emp.getDeptno());
                }

        }
    }
    public static void findById(){
        System.out.println("请输入您要查找员工的编号");
        Scanner sc = new Scanner(System.in);
        int empno = sc.nextInt();
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        Employee emp = employeeDao.findById(empno);
        if(emp==null){
            System.out.println("查无此人");
        }else{
            System.out.println("员工编号\t员工姓名\t员工职位\t上级编号\t入职时间\t\t员工薪水\t员工补贴\t所属部门");
            System.out.println(emp.getEmpno()+"\t\t"+emp.getEname()+"\t\t"+emp.getJob()+"\t\t"+emp.getMgr()+"\t\t"+emp.getHiredate()
                    +"\t\t"+emp.getSal()+"\t\t"+emp.getComm()+"\t\t"+emp.getDeptno());
        }
    }
}
