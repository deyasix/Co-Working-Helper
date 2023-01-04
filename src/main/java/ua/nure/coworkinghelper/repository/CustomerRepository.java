package ua.nure.coworkinghelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.coworkinghelper.model.Customer;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
