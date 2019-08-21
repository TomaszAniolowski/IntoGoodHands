package pl.coderslab.charity.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.donation.DonationRepository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    private DonationRepository donationRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, DonationRepository donationRepository) {
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void removeCategory(Category category) {
        categoryRepository.delete(category);
    }

    public Category findByCategoryId(Long id) {
        return categoryRepository.getOne(id);
    }

    public List<Category> getAll(){
        return categoryRepository.getAllByOrderById();
    }

    public Map<String, Integer> getCategoriesAndDonationsQuantityMap(){
        Map<String, Integer> quantities = new HashMap<>();
        categoryRepository.findAll().forEach(c -> quantities.put(c.getName(), donationRepository.countAllByCategories(c)));
        return quantities;
    }

}
