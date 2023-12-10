package com.project06_employee_management_crud.controller;


import com.project06_employee_management_crud.enitity.Employee;
import com.project06_employee_management_crud.service.EmpService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    private final EmpService empService;

    public HomeController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Employee> employeeList = empService.getAllEmp();
        model.addAttribute("employeeList", employeeList);
        return "index";
    }

    @GetMapping("/loadEmpSave")
    public String loadEmpSave(){
        return "emp-save";
    }

    @GetMapping("/EditEmp/{id}")
    public String editEmp(@PathVariable("id") int id, Model model) {
        System.out.println(id);
        Employee employee = empService.getEmpById(id);
        model.addAttribute("employee", employee);

        return "edit_emp";
    }

    @PostMapping("/saveEmp")
    public String saveEmp(@ModelAttribute Employee employee, HttpSession session ){
        Employee savedEmployee = empService.saveEmp(employee);
        if (savedEmployee != null){
            System.out.println("Saved");
            session.setAttribute("msg", "Registered Successfully");
        }
        else session.setAttribute("msg", "Employee Not Saved");
        return "redirect:/loadEmpSave";
    }


    @PostMapping("/updateEmpDetais")
    public String updateEmpDetais(@ModelAttribute Employee employee, HttpSession session ){
        Employee updatedEmployee = empService.saveEmp(employee);
        if (updatedEmployee != null){
            System.out.println("Saved");
            session.setAttribute("msg", "Updated Successfully");
        }
        else session.setAttribute("msg", "Employee Not Updated");
        return "redirect:/";
    }
    @GetMapping("/deleteEmp/{id}")
    public String deleteEmpl(@PathVariable("id") int id, HttpSession session ) {


        if (empService.deleteEmp(id)) {
            session.setAttribute("msg", "Deleted Successfully");
        }
        else session.setAttribute("msg", "Employee Not Deleted");



        return "redirect:/";
    }

}
