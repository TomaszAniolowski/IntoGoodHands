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
import java.util.List;

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
//        if (checkRights(session)) {
            model.addAttribute("monthDonationsQuantity", donationService.getThisMonthDonationsQuantity());
            model.addAttribute("allDonationsQuantity", donationService.getAllDonationsQuantity());
            model.addAttribute("allBagsQuantity", donationService.getAllBagsQuantity());
            model.addAttribute("allInstitutionsQuantity", institutionService.countAll());
            model.addAttribute("categories", categoryService.getAll());
            model.addAttribute("categoriesAndDonationsMap", categoryService.getCategoriesAndDonationsQuantityMap());
            return "admin/index";
//        } else {
//            return "redirect:/";
//        }
    }

    @RequestMapping("/inst")
    public String displayInstitutionList(Model model, HttpSession session) {
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking

//        if (checkRights(session)) {
            model.addAttribute("institutions", institutionService.getAll());
            return "admin/institutions";
//        } else {
//            return "redirect:/";
//        }

    }

    @RequestMapping("/cat")
    public String displayCategoryList(Model model, HttpSession session) {
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking
//        if (checkRights(session)) {
            model.addAttribute("categories", categoryService.getAll());
            return "admin/categories";
//        } else {
//            return "redirect:/";
//        }

    }

    @RequestMapping("/don")
    public String displayDonationList(Model model, HttpSession session) {
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking
//        if (checkRights(session)) {
            model.addAttribute("donations", donationService.getAll());
            return "admin/donations";
//        } else {
//            return "redirect:/";
//        }

    }

    @RequestMapping("/users/{role}")
    public String displayInstitutionList(Model model, @PathVariable String role, HttpServletResponse response, HttpSession session) throws IOException {
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking
//        if (checkRights(session)) {
            List<User> users = getUsers(role);
            if (users == null) {
                response.sendError(404);
                return null;
            } else {
                model.addAttribute("users", users);
                model.addAttribute("role", role);
                return "admin/users";
            }
//        } else {
//            return "redirect:/";
//        }
    }


    private boolean checkRights(HttpSession session) {
        Boolean allowed = (Boolean) session.getAttribute("CHECKED_ADMIN_RIGHTS");
        if (allowed == null || !allowed) {
            SecurityContextHolder.clearContext();
            session.invalidate();
            return false;
        }

        return true;
    }

    private List<User> getUsers(String role) {
        if (role.equals("adm"))
            return userService.getAdmins();
        else if (role.equals("ord"))
            return userService.getUsers();
        else {
            return null;
        }
    }

}
