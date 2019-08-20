package pl.coderslab.charity.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.InstitutionService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private DonationService donationService;
    private InstitutionService institutionService;
    private UserService userService;
    private CategoryService categoryService;

    @ModelAttribute("username")
    private String getUsername(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser == null)
            return null;

        return currentUser.getUser().getUsername();
    }

    public AdminController(DonationService donationService, InstitutionService institutionService, UserService userService, CategoryService categoryService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/desk")
    public String displayAdminDesk(Model model, HttpSession session){
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking
//        Boolean allowed = (Boolean)session.getAttribute("CHECKED_ADMIN_RIGHTS");
//        if (allowed == null || !allowed) {
//            SecurityContextHolder.clearContext();
//            session.invalidate();
//            return "redirect:/";
//        }

        model.addAttribute("monthDonationsQuantity", donationService.getThisMonthDonationsQuantity());
        model.addAttribute("allDonationsQuantity", donationService.getAllDonationsQuantity());
        model.addAttribute("allBagsQuantity", donationService.getAllBagsQuantity());
        model.addAttribute("allInstitutionsQuantity", institutionService.countAll());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("categoriesAndDonationsMap", categoryService.getCategoriesAndDonationsQuantityMap());
        return "admin/index";
    }
}
