package com.example.erpsystem.pricing;

import com.example.erpsystem.pricing.model.Pricing;
import com.example.erpsystem.pricing.service.PricingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/pricing")
public class PricingController {

    private final PricingService pricingService;

    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping("/form")
    public String showPricingFormWithRecords(Model model) {
        model.addAttribute("components", new Pricing());
        model.addAttribute("pricings", pricingService.findAll());
        return "pricing_form";
    }
    @GetMapping("/list")
    public String showPricingList(Model model) {
        List<Pricing> pricingList = pricingService.findAll();
        model.addAttribute("pricingList", pricingList);
        return "pricing_list";
    }

    @GetMapping("/form/load/{id}")
    public String loadPricing(@PathVariable Long id, Model model) {
        Pricing pricing = pricingService.findById(id);
        model.addAttribute("components", pricing);
        return "pricing_form";
    }

    @PostMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        pricingService.deleteById(id);
        return "redirect:/pricing/list";
    }


    @PostMapping("/calculate")
    public String calculatePricing(
            @ModelAttribute Pricing pricing,
            Model model) {

        // Obliczenie wartości roboczogodziny
        BigDecimal manHourValue = pricingService.calculateManHourValue(pricing.getManHours(),
                pricing.getBusinessOperating()
                        .add(pricing.getEmployees())
                        .add(pricing.getEquipment())
                        .add(pricing.getProducts())
                        .add(pricing.getTraining())
                        .add(pricing.getIncome())
                        .add(pricing.getTaxes()));

        model.addAttribute("components", pricing);
        model.addAttribute("manHourValue", manHourValue);
        return "pricing_result";
    }

    @PostMapping("/save")
    public String saveComponents(
            @ModelAttribute Pricing pricing,
            Model model) {

        // Zapis komponentów
        pricingService.saveComponents(pricing);

        // Dodanie komunikatu o sukcesie do modelu
        model.addAttribute("successMessage", "Dane zostały zapisane pomyślnie!");

        // Ponowne obliczenie wartości roboczogodziny (jeśli potrzebne do wyświetlenia wyników)
        BigDecimal manHourValue = pricingService.calculateManHourValue(pricing.getManHours(),
                pricing.getBusinessOperating()
                        .add(pricing.getEmployees())
                        .add(pricing.getEquipment())
                        .add(pricing.getProducts())
                        .add(pricing.getTraining())
                        .add(pricing.getIncome())
                        .add(pricing.getTaxes()));

        model.addAttribute("components", pricing);
        model.addAttribute("manHourValue", manHourValue);

        // Pozostanie na stronie wyników
        return "pricing_result";
    }
}
