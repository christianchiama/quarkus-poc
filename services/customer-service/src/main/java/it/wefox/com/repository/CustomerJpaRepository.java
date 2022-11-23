package it.wefox.com.repository;

/**
 * @author Christian Chiama
 * --
 */
import it.wefox.com.domain.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CustomerJpaRepository extends PagingAndSortingRepository<Customer, String> {

    Customer insert(String name, String address, LocalDate birthDate);

    int update(String id, String name, String address, LocalDate birthDate);

}

