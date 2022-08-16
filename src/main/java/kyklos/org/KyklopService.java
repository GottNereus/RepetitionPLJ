package kyklos.org;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Log4j2
@Transactional
public class KyklopService {

    @Autowired
    private final KyklopRepo kRepo;

    public KyklopService(KyklopRepo kRepo) {
        this.kRepo = kRepo;
    }

        // endpoint 1 get

        // endpoint 2 put

        // endpoint 3 post

    public void postKyklop(Kyklop kyklop) {
        if (!(kRepo.existsById(kyklop.getId()))) {
            log.info("Kyklop was added");
            kRepo.save(kyklop);
        } else {
            log.warn("User already exists");
        }

        // endpoint 4 delete

    }
}
