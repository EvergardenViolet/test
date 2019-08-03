package com.xueliang.crud.controller;

import com.xueliang.crud.dao.DepartmentDao;
import com.xueliang.crud.dao.EmployeeDao;
import com.xueliang.crud.entities.Department;
import com.xueliang.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps",all);
        System.out.println("===========");
        return "emp/list";
    }

//    查出所有的部门在页面显示
    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        employeeDao.save(employee);
//       redirect:重定向  forward: 转发
        return "redirect:/emps";
    }

    /**
     * RedirectAttributesModelMap extends ModelMap  ,可以在重定向时保留参数
     *
     * */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,RedirectAttributesModelMap modelMap,Model model){
        Employee employee = employeeDao.get(id);
//        modelMap.addFlashAttribute("em",employee);
        model.addAttribute("em",employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    @PutMapping("/emp")
    public String UpdateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String DeleteEmployee(@PathVariable("id") Integer id){
         employeeDao.delete(id);
        System.out.println(id);
         return "redirect:/emps";
    }
}
