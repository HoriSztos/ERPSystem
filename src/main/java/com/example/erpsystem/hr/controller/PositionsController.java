package com.example.erpsystem.hr.controller;

import com.example.erpsystem.hr.model.Position;
import com.example.erpsystem.hr.service.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hr/positions")
public class PositionsController {

    private final PositionService positionService;

    public PositionsController(PositionService positionService) {
        this.positionService = positionService;
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
        positionService.deletePosition(id);
        return "redirect:/hr/positions";
    }
}
