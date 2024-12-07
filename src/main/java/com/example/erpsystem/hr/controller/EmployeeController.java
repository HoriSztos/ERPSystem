package com.example.erpsystem.hr.controller;

import com.example.erpsystem.hr.model.Employee;
import com.example.erpsystem.hr.service.EmployeeService;
import com.example.erpsystem.hr.service.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hr/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PositionService positionService;

    public EmployeeController(EmployeeService employeeService, PositionService positionService) {
        this.employeeService = employeeService;
        this.positionService = positionService;
    }

    // 1. Wyświetlenie listy pracowników
    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employee_list";
    }

    // 2. Formularz dodawania nowego pracownika
    @GetMapping("/add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("positions", positionService.getAllPositions());
        return "employee_add";
    }

    // 3. Zapis nowego pracownika
    @PostMapping("/add")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/hr/employees";
    }

    // 4. Formularz edycji pracownika
    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("positions", positionService.getAllPositions());
        return "employee_edit";
    }

    // 5. Aktualizacja pracownika
    @PostMapping("/edit")
    public String updateEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/hr/employees";
    }

    // 6. Usunięcie pracownika
    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/hr/employees";
    }
}
