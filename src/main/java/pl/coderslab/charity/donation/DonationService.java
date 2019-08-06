package pl.coderslab.charity.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DonationService {

    private DonationRepository donationRepository;

    @Autowired
    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public Integer getAllBagsQuantity() {
        return (Integer) ifNull(donationRepository.sumAllBagsQuantity(), 0);
    }

    private Object ifNull(Object basic, Object alternative) {
        if (basic != null)
            return basic;
        else
            return alternative;
    }
}
