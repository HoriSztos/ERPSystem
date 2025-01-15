package com.example.erpsystem.hr.controller;

import com.example.erpsystem.hr.model.Position;
import com.example.erpsystem.hr.service.EmployeeService;
import com.example.erpsystem.hr.service.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hr/positions")
public class PositionsController {

    private final PositionService positionService;
    private final EmployeeService employeeService;

    public PositionsController(PositionService positionService, EmployeeService employeeService) {
        this.positionService = positionService;
        this.employeeService = employeeService;
    }

    // 1. Wyświetlenie listy stanowisk
    @GetMapping
    public String listPositions(Model model) {
        List<Position> positions = positionService.getAllPositions();
        model.addAttribute("positions", positions);
        return "positions";
    }

    // 2. Formularz dodawania nowego stanowiska
    @GetMapping("/add")
    public String addPositionForm(Model model) {
        model.addAttribute("position", new Position());
        return "positions_add";
    }

    // 3. Zapis nowego stanowiska
    @PostMapping("/add")
    public String savePosition(@ModelAttribute Position position) {
        positionService.savePosition(position);
        return "redirect:/hr/positions";
    }

    // 4. Formularz edycji stanowiska
    @GetMapping("/edit/{id}")
    public String editPositionForm(@PathVariable Long id, Model model) {
        Position position = positionService.getPositionById(id);
        model.addAttribute("position", position);
        return "positions_edit";
    }

    // 5. Aktualizacja stanowiska
    @PostMapping("/edit")
    public String updatePosition(@ModelAttribute Position position) {
        positionService.savePosition(position);
        return "redirect:/hr/positions";
    }

    // 6. Usunięcie stanowiska
    @PostMapping("/delete/{id}")
    public String deletePosition(@PathVariable Long id) {
        try {
            // Zaktualizowanie pracowników, aby usunąć powiązanie z pozycją
            employeeService.updateEmployeesPosition(id);

            // Usunięcie pozycji
            positionService.deletePosition(id);
            return "redirect:/hr/positions"; // Przekierowanie na stronę z listą pozycji
        } catch (Exception e) {
            e.printStackTrace(); // Dla debugowania
            return "error"; // Strona błędu
        }
    }

}
