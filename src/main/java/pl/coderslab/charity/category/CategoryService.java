package pl.coderslab.charity.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.donation.DonationRepository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CategoryService {

    private CategoryRepository categoryRepository;
    private DonationRepository donationRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, DonationRepository donationRepository) {
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
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
