package pl.coderslab.charity.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DonationService {

    private DonationRepository donationRepository;

    @Autowired
    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public List<Donation> getAll() {
        return donationRepository.findAll();
    }

    public void save(Donation donation){
        donationRepository.save(donation);
    }

    public Integer getAllBagsQuantity() {
        return (Integer) ifNull(donationRepository.sumAllBagsQuantity(), 0);
    }

    public Long getAllDonationsQuantity(){
        return donationRepository.count();
    }

    public Integer getThisMonthDonationsQuantity() {
        String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String currentMonth = getCurrentMonth();
        return  donationRepository.getThisMonthDonationsQuantity(currentYear + "-" + currentMonth);
    }

    private Object ifNull(Object basic, Object alternative) {
        if (basic != null)
            return basic;
        else
            return alternative;
    }

    private String getCurrentMonth() {
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;

        if(currentMonth < 10)
            return "0" + currentMonth;
        else
            return String.valueOf(currentMonth);
    }
}
