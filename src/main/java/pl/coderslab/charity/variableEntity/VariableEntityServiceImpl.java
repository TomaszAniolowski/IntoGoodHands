package pl.coderslab.charity.variableEntity;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserService;

import javax.transaction.Transactional;

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
    public VariableEntity getObject(String instance, Long id) throws NotFoundException {
        VariableEntity entity = null;
        switch (instance) {
            case "USER":
                if (id == null)
                    entity = new User();
                else {
                    entity = userService.findByUserId(id);
                }
                break;
            case "CATEGORY":
                if (id == null)
                    entity = new Category();
                else {
                    entity = categoryService.findByCategoryId(id);
                }
                break;
            case "INSTITUTION":
                if (id == null)
                    entity = new Institution();
                else {
                    entity = institutionService.findByInstitutionId(id);
                }
                break;
            case "DONATION":
                if (id == null)
                    entity = new Donation();
                else {
                    entity = donationService.findByDonationId(id);
                }
                break;
        }

        if (entity == null)
            throw new NotFoundException("Given object id or type is incorrect");
        else
            return entity;
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
    public void removeObject(VariableEntity dataObject) throws ClassNotFoundException {
        if (dataObject instanceof User) {
            userService.removeUser((User) dataObject);
        } else if (dataObject instanceof Category) {
            categoryService.removeCategory((Category) dataObject);
        } else if (dataObject instanceof Institution) {
            institutionService.removeInstitution((Institution) dataObject);
        } else if (dataObject instanceof Donation) {
            donationService.removeDonation((Donation) dataObject);
        } else {
            throw new ClassNotFoundException("Can't remove object of other type than User, Category, Institution and Donation.");
        }
    }
}
