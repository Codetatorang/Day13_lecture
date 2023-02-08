package sg.edu.nus.iss.day13_lecture.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import sg.edu.nus.iss.day13_lecture.model.Employee;
import sg.edu.nus.iss.day13_lecture.repository.EmployeeRepo;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepo empRepo;

    @GetMapping("/home")
    public String employeeListPage(Model model){
        List<Employee> employees = empRepo.findAll();
        model.addAttribute("employees",employees);
        return "employeesList";
    }
    @GetMapping("/addnew")
    public String addPage(Model model){
        Employee emp = new Employee();
        model.addAttribute("employee",emp);
        return "employeeadd";
    }

    @PostMapping("/addnew")
    public String addEmployee(@Valid  @ModelAttribute("employee")Employee employeeForm, BindingResult result, Model model)throws FileNotFoundException{
        if(result.hasErrors()){
            return "employeeadd";
        }
        Boolean bRes = false;
        bRes = empRepo.save(employeeForm);
        return "redirect:/employees/home";
    }

    @GetMapping("/deleteEmployee/{email}")
    public String deleteEmployee(@PathVariable("email") String email){
        Employee emp = empRepo.findByEmailId(email);
        Boolean bRes = empRepo.delete(emp);
        
        return "redirect:/employees/home";
    }

    @GetMapping("/updateEmployee/{email}")
    public String updateEmployee(@PathVariable("email") String email, Model model){
        Employee emp = empRepo.findByEmailId(email);
        model.addAttribute("employee",emp);
        // Boolean bRes = empRepo.delete(emp);

        return "employeeupdate";
    }

    @PostMapping("/updateEmp")
    public String updateEmployeeProcess(@ModelAttribute("employee") Employee emp, BindingResult result, Model model){
        if(result.hasErrors()){
            return "employeeupdate";
        }
        empRepo.update(emp);
        return "redirect:/employees/home";
    }
}
