package sg.edu.nus.iss.day13_lecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.day13_lecture.model.Employee;
import sg.edu.nus.iss.day13_lecture.repository.EmployeeRepo;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepo empRepo;

    @GetMapping("/employees")
    public String employeeListPage(Model model){
        List<Employee> employees = empRepo.findAll();
        model.addAttribute("employees",employees);
        return "employeeList";
    }
}
