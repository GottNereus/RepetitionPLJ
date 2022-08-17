package kyklos.org;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.management.InstanceNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@Transactional
public class KyklopService {

    @Autowired
    private final KyklopRepo kRepo;

    public KyklopService(KyklopRepo kRepo) {
        this.kRepo = kRepo;
    }

    //GetAll

    public List<Kyklop> findAll() {
        log.info("Get everything in author table");
        List<Kyklop> kyklopList = new ArrayList<>();
        Iterable<Kyklop> kyklops = kRepo.findAll();
        kyklops.forEach(kyklopList::add);
        return kyklopList;
    }

    // endpoint 1 get

    public Kyklop getKyklop(Integer id) throws InstanceNotFoundException{
        return (kRepo.findById(id).orElseThrow(() -> new InstanceNotFoundException("Kyklop with id"+id+"doesn't exist")));

    }

    // endpoint 2 put

    public void putKyklop(Kyklop kyklop, int id) {
        if (!(kRepo.existsById(id))) {
            log.info("Kyklop was added");
            kRepo.save(kyklop);
        } else {
            log.info("Kyklop was updated");
            kRepo.save(kyklop);       }
    }

    // endpoint 3 post

    public void postKyklop(Kyklop kyklop) {
        if (!(kRepo.existsById(kyklop.getId()))) {
            log.info("Kyklop was added");
            kRepo.save(kyklop);
        } else {
            log.warn("User already exists");
        }
    }
        // endpoint 4 delete

        public void deleteKyklop(Integer id) throws InstanceNotFoundException {
            kRepo.findById(id).orElseThrow(() -> new InstanceNotFoundException("Kyklop with id"+id+"doesn't exist"));
            log.info("Kyklop was deleted");
            kRepo.deleteById(id);
        }
    }

