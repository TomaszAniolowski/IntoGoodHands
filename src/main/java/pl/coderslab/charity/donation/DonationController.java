package pl.coderslab.charity.donation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.institution.InstitutionService;

import javax.validation.Valid;

@Controller
@RequestMapping("/donations")
public class DonationController {
    private DonationService donationService;
    private CategoryService categoryService;
    private InstitutionService institutionService;

    public DonationController(DonationService donationService, CategoryService categoryService, InstitutionService institutionService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("institutions", institutionService.getAll());
        return "form";
    }

    @PostMapping("/form")
    public String validateForm(@Valid Donation donation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            model.addAttribute("institutions", institutionService.getAll());
            return "form";
        }

        donationService.save(donation);
        return "form-confirmation";
    }
}
