package pl.coderslab.charity.variableEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VariableEntityServiceImpl implements VariableEntityService {

    private DonationService donationService;
    private InstitutionService institutionService;
    private UserService userService;
    private CategoryService categoryService;

    @Autowired
    public VariableEntityServiceImpl(DonationService donationService, InstitutionService institutionService, UserService userService, CategoryService categoryService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public VariableEntity getObject(String instance, Long id) throws ClassNotFoundException, EntityNotFoundException {

        switch (instance) {
            case "USER":
                if (id == null)
                    return new User();
                else {
                    return userService.findByUserId(id).orElseThrow(() -> new EntityNotFoundException("No " + instance + " in database with id " + id));
                }
            case "CATEGORY":
                if (id == null)
                    return new Category();
                else {
                    return categoryService.findByCategoryId(id).orElseThrow(() -> new EntityNotFoundException("No " + instance + " in database with id " + id));
                }
            case "INSTITUTION":
                if (id == null)
                    return new Institution();
                else {
                    return institutionService.findByInstitutionId(id).orElseThrow(() -> new EntityNotFoundException("No " + instance + " in database with id " + id));
                }
            case "DONATION":
                if (id == null)
                    return new Donation();
                else {
                    return donationService.findByDonationId(id).orElseThrow(() -> new EntityNotFoundException("No " + instance + " in database with id " + id));
                }
            default:
                throw new ClassNotFoundException("Can't get object of other type than User, Category, Institution and Donation.");
        }
    }

    @Override
    public void saveObject(VariableEntity dataObject) throws ClassNotFoundException {
        if (dataObject instanceof User) {
            userService.saveUser((User) dataObject);
        } else if (dataObject instanceof Category) {
            categoryService.save((Category) dataObject);
        } else if (dataObject instanceof Institution) {
            institutionService.save((Institution) dataObject);
        } else if (dataObject instanceof Donation) {
            donationService.save((Donation) dataObject);
        } else {
            throw new ClassNotFoundException("Can't save object of other type than User, Category, Institution and Donation.");
        }
    }

    @Override
    public List<? extends VariableEntity> getAllObjects(String instance, String mode) throws ClassNotFoundException {
        switch (instance) {
            case "USERS":
                if ("ord".equals(mode))
                    return userService.getUsers();
                else if ("adm".equals(mode))
                    return userService.getAdmins();
                else
                    throw new ClassNotFoundException("Can't find role of user.");
            case "CATEGORIES":
                return categoryService.getAll();
            case "INSTITUTIONS":
                return institutionService.getAll();
            case "DONATIONS":
                return donationService.getAll();
            default:
                throw new ClassNotFoundException("Can't get object of other type than User, Category, Institution and Donation.");
        }
    }

    @Override
    public void removeObject(String instance, Long id) throws ClassNotFoundException, EntityNotFoundException {
        switch (instance) {
            case "USER":
                User user = userService.findByUserId(id).orElseThrow(() -> new EntityNotFoundException("No " + instance + " in database with id " + id));
                userService.removeUser(user);
                break;
            case "CATEGORY":
                Category category = categoryService.findByCategoryId(id).orElseThrow(() -> new EntityNotFoundException("No " + instance + " in database with id " + id));
                categoryService.removeCategory(category);
                break;
            case "INSTITUTION":
                Institution institution = institutionService.findByInstitutionId(id).orElseThrow(() -> new EntityNotFoundException("No " + instance + " in database with id " + id));
                institutionService.removeInstitution(institution);
                break;
            case "DONATION":
                Donation donation = donationService.findByDonationId(id).orElseThrow(() -> new EntityNotFoundException("No " + instance + " in database with id " + id));
                donationService.removeDonation(donation);
                break;
            default:
                throw new ClassNotFoundException("Can't remove object of other type than User, Category, Institution and Donation.");
        }

    }
}
