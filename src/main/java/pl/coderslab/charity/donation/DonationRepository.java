package pl.coderslab.charity.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.charity.category.Category;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(nativeQuery = true, value = "SELECT SUM(quantity) FROM donations;")
    Integer sumAllBagsQuantity();

    @Query(nativeQuery = true, value = "SELECT COUNT(id) FROM donations WHERE pick_up_date LIKE CONCAT(:month, '%');")
    Integer getThisMonthDonationsQuantity(@Param("month") String month);

    Integer countAllByCategories(Category category);
}
