package pl.coderslab.charity.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.donation.DonationRepository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CategoryService {

    void save(Category category);

    void removeCategory(Category category);

    Category findByCategoryId(Long id);

    List<Category> getAll();

    Map<String, Integer> getCategoriesAndDonationsQuantityMap();

}
