package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.CurrentUser;


@Controller
public class HomeController {

    private InstitutionService institutionService;
    private DonationService donationService;

    @Autowired
    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @ModelAttribute("username")
    private String getUsername(@AuthenticationPrincipal CurrentUser customUser) {
        if (customUser == null)
            return "";

        return customUser.getUser().getUsername();
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("institutions", institutionService.getAll());
        model.addAttribute("institutionsQuantity", institutionService.countAll());
        model.addAttribute("bagsQuantity", donationService.getAllBagsQuantity());
        return "index";
    }
}
