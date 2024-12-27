package com.example.erpsystem.pricing;

import com.example.erpsystem.pricing.model.Pricing;
import com.example.erpsystem.pricing.service.PricingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/pricing")
public class PricingController {

    private final PricingService pricingService;

    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping("/form")
    public String showPricingForm(Model model) {
        model.addAttribute("components", new Pricing());
        return "pricing_form";
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
    public String saveComponents(@ModelAttribute Pricing pricing) {
        pricingService.saveComponents(pricing);
        return "redirect:/pricing/form";
    }
}
