package pl.coderslab.charity.category;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CategoryService {

    void save(Category category);

    void removeCategory(Category category);

    Optional<Category> findByCategoryId(Long id);

    List<Category> getAll();

    Map<String, Integer> getCategoriesAndDonationsQuantityMap();

}
