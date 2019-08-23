package pl.coderslab.charity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.variableEntity.VariableEntity;
import pl.coderslab.charity.variableEntity.VariableEntityService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private DonationService donationService;
    private InstitutionService institutionService;
    private UserService userService;
    private CategoryService categoryService;
    private VariableEntityService variableEntityService;

    @ModelAttribute("username")
    private String getUsername(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser == null)
            return null;

        return currentUser.getUser().getUsername();
    }

    @Autowired
    public AdminController(DonationService donationService, InstitutionService institutionService, UserService userService, CategoryService categoryService, VariableEntityService variableEntityService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.variableEntityService = variableEntityService;
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

    @RequestMapping("/exit")
    public String exitFromAdminPanel(HttpSession session) {
        session.removeAttribute("CHECKED_ADMIN_RIGHTS");
        return "/";
    }

    @RequestMapping("/{typeOfEntity:users|categories|institutions|donations}")
    public String displayVariableEntityList(Model model, @PathVariable String typeOfEntity, @RequestParam(required = false) Optional<String> role, HttpServletResponse response, HttpSession session) throws IOException {
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking

//        if (checkRights(session)) {
        try {
            model.addAttribute(typeOfEntity, variableEntityService.getAllObjects(typeOfEntity.toUpperCase(), role.orElse(null)));
            if(typeOfEntity.equals("users"))
                model.addAttribute("role", role.get());

            return "admin/" + typeOfEntity;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //TODO: message from users locale (get it from context)
            session.setAttribute("actualErrorMessage", e.getMessage());
            response.sendError(500);
            return null;
        }
//        } else {
//            return "redirect:/";
//        }

    }

    @GetMapping("/{typeOfEntity:user|category|institution|donation}/form")
    public String displayVariableEntityForm(Model model, @RequestParam(required = false) Long id, HttpServletResponse response, HttpSession session, @PathVariable String typeOfEntity) throws IOException {
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking
//        if (checkRights(session)) {
        try {
            VariableEntity instanceOfEntity = variableEntityService.getObject(typeOfEntity.toUpperCase(), id);
            model.addAttribute(typeOfEntity, instanceOfEntity);
            if (instanceOfEntity instanceof Donation) {
                model.addAttribute("institutions", institutionService.getAll());
                model.addAttribute("categories", categoryService.getAll());
            }

            return "admin/form/" + typeOfEntity;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(404);
            return null;
        } catch (EntityNotFoundException n) {
            n.printStackTrace();
            session.setAttribute("actualErrorMessage", n.getMessage());
            response.sendError(500);
            return null;
        }
//        } else {
//            return "redirect:/";
//        }
    }

    @RequestMapping("/{typeOfEntity:user|category|institution|donation}/rmv")
    public String removeObject(@PathVariable String typeOfEntity, @RequestParam Long id, HttpServletResponse response, HttpSession session) throws IOException {
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking
//        if (checkRights(session)) {
        try {
            variableEntityService.removeObject(typeOfEntity.toUpperCase(), id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(404);
            return null;
        } catch (EntityNotFoundException n) {
            n.printStackTrace();
            session.setAttribute("actualErrorMessage", n.getMessage());
            response.sendError(500);
            return null;
        }
//        } else {
//            return "redirect:/";
//        }
        return "redirect:/admin/" + typeOfEntity + "/list";
    }

    @PostMapping("/user/form")
    public String saveUser(HttpServletResponse response,
                           HttpSession session,
                           @Valid User user,
                           BindingResult result) throws IOException {
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking
//        if (checkRights(session)) {
        if (result.hasErrors())
            return "admin/user/form";

        boolean effect = saveObject(user, session);

        if (effect)
            return "redirect:/admin/desk";
        else {
            response.sendError(500);
            return null;
        }
//        } else {
//            return "redirect:/";
//        }
    }

    @PostMapping("/category/form")
    public String saveCategory(HttpServletResponse response,
                               HttpSession session,
                               @Valid Category category,
                               BindingResult result) throws IOException {
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking
//        if (checkRights(session)) {
        if (result.hasErrors())
            return "admin/category/form";

        boolean effect = saveObject(category, session);

        if (effect)
            return "redirect:/admin/desk";
        else {
            response.sendError(500);
            return null;
        }
//        } else {
//            return "redirect:/";
//        }
    }

    @PostMapping("/institution/form")
    public String saveInstitution(HttpServletResponse response,
                                  HttpSession session,
                                  @Valid Institution institution,
                                  BindingResult result) throws IOException {
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking
//        if (checkRights(session)) {
        if (result.hasErrors())
            return "admin/institution/form";

        boolean effect = saveObject(institution, session);

        if (effect)
            return "redirect:/admin/desk";
        else {
            response.sendError(500);
            return null;
        }
//        } else {
//            return "redirect:/";
//        }
    }

    @PostMapping("/donation/form")
    public String saveDonation(HttpServletResponse response,
                               HttpSession session,
                               @Valid Donation donation,
                               BindingResult result) throws IOException {
        //TODO: uncomment CHECKED_ADMIN_RIGHTS checking
//        if (checkRights(session)) {
        if (result.hasErrors())
            return "admin/donation/form";

        boolean effect = saveObject(donation, session);

        if (effect)
            return "redirect:/admin/desk";
        else {
            response.sendError(505);
            return null;
        }
//        } else {
//            return "redirect:/";
//        }
    }

   @GetMapping("/mk/{role}")
   public String makeAnAdmin(@RequestParam Long id, HttpSession session, HttpServletResponse response, @PathVariable String role) throws IOException {

       try {
           userService.makeRole(id, role);
       } catch (Exception e) {
           e.printStackTrace();
           session.setAttribute("actualErrorMessage", e.getMessage());
           response.sendError(500);
       }

       return "redirect:/admin/users?role=" + role;
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

    private boolean saveObject(VariableEntity variableEntity, HttpSession session) {
        try {
            variableEntityService.saveObject(variableEntity);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            session.setAttribute("actualErrorMessage", e.getMessage());
            return false;
        }
    }

}
