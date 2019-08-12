package pl.coderslab.charity.donation;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.CurrentUser;

import javax.validation.Valid;

@Controller
@RequestMapping("/donations")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class DonationController {
    private DonationService donationService;
    private CategoryService categoryService;
    private InstitutionService institutionService;

    public DonationController(DonationService donationService, CategoryService categoryService, InstitutionService institutionService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
    }

    @ModelAttribute("username")
    private String getUsername(@AuthenticationPrincipal CurrentUser customUser) {
        if (customUser == null)
            return "";

        return customUser.getUser().getUsername();
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
