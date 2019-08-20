package pl.coderslab.charity.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.InstitutionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    public String displayAdminDesk(Model model, HttpSession session) {
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

    @RequestMapping("/inst")
    public String displayInstitutionList(Model model) {
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking
//        Boolean allowed = (Boolean)session.getAttribute("CHECKED_ADMIN_RIGHTS");
//        if (allowed == null || !allowed) {
//            SecurityContextHolder.clearContext();
//            session.invalidate();
//            return "redirect:/";
//        }

        model.addAttribute("institutions", institutionService.getAll());
        return "admin/institutions";
    }

    @RequestMapping("/cat")
    public String displayCategoryList(Model model) {
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking
//        Boolean allowed = (Boolean)session.getAttribute("CHECKED_ADMIN_RIGHTS");
//        if (allowed == null || !allowed) {
//            SecurityContextHolder.clearContext();
//            session.invalidate();
//            return "redirect:/";
//        }

        model.addAttribute("categories", categoryService.getAll());
        return "admin/categories";
    }

    @RequestMapping("/don")
    public String displayDonationList(Model model) {
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking
//        Boolean allowed = (Boolean)session.getAttribute("CHECKED_ADMIN_RIGHTS");
//        if (allowed == null || !allowed) {
//            SecurityContextHolder.clearContext();
//            session.invalidate();
//            return "redirect:/";
//        }

        model.addAttribute("donations", donationService.getAll());
        return "admin/donations";
    }

    @RequestMapping("/users/{role}")
    public String displayInstitutionList(Model model, @PathVariable String role, HttpServletResponse response) throws IOException {
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking
//        Boolean allowed = (Boolean)session.getAttribute("CHECKED_ADMIN_RIGHTS");
//        if (allowed == null || !allowed) {
//            SecurityContextHolder.clearContext();
//            session.invalidate();
//            return "redirect:/";
//        }

        if (role.equals("adm"))
            model.addAttribute("users", userService.getAdmins());
        else if (role.equals("ord"))
            model.addAttribute("users", userService.getUsers());
        else {
            response.sendError(404);
            return null;
        }

        model.addAttribute("role", role);

        return "admin/users";
    }

}
