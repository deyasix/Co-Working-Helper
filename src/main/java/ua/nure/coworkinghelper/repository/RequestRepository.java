package ua.nure.coworkinghelper.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.coworkinghelper.model.Customer;
import ua.nure.coworkinghelper.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
