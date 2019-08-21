package pl.coderslab.charity.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


public interface DonationService {

    Donation findByDonationId(Long id);

    List<Donation> getAll();

    void removeDonation(Donation donation);

    void save(Donation donation);

    Integer getAllBagsQuantity();

    Long getAllDonationsQuantity();

     Integer getThisMonthDonationsQuantity();
}
