package com.example.erpsystem.treatments;

import com.example.erpsystem.treatments.model.Treatment;
import com.example.erpsystem.treatments.repository.TreatmentRepository;
import com.example.erpsystem.treatments.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/treatments")
public class TreatmentsController {

    @Autowired
    private TreatmentRepository TreatmentRepository;
    @Autowired
    private TreatmentService treatmentService;

    @GetMapping
    public String listTreatments(Model model) {
        model.addAttribute("treatments", treatmentService.getAllServices());
        return "treatments";
    }


    @GetMapping("/add")
    public String addTreatmentForm(Model model) {
        model.addAttribute("treatment", new Treatment());
        return "treatments_add";
    }

    @PostMapping("/add")
    public String saveTreatment(@ModelAttribute Treatment treatment) {
        treatmentService.saveService(treatment);
        return "redirect:/treatments";
    }

    @GetMapping("/edit/{id}")
    public String editTreatmentForm(@PathVariable Long id, Model model) {
        Treatment treatment = treatmentService.getServiceByID(id);
        model.addAttribute("treatment", treatment);
        return "treatments_edit";
    }

    @PostMapping("/edit")
    public String updateTreatment(@ModelAttribute Treatment treatment) {
        treatmentService.saveService(treatment);
        return "redirect:/treatments";
    }
    @PostMapping("/delete/{id}")
    public String deleteTreatment(@PathVariable Long id) {
        treatmentService.deleteService(id);
        return "redirect:/treatments";
    }

}
