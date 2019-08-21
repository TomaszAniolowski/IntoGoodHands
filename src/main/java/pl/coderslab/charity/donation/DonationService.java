package pl.coderslab.charity.donation;

import java.util.List;
import java.util.Optional;


public interface DonationService {

    Optional<Donation> findByDonationId(Long id);

    List<Donation> getAll();

    void removeDonation(Donation donation);

    void save(Donation donation);

    Integer getAllBagsQuantity();

    Long getAllDonationsQuantity();

     Integer getThisMonthDonationsQuantity();
}
