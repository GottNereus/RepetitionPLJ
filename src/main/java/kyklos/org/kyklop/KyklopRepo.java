package kyklos.org.kyklop;

import kyklos.org.kyklop.Kyklop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KyklopRepo extends JpaRepository<Kyklop,Integer> {

    Kyklop findKyklosByVulgo (String vulgo);

}
