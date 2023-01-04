package ua.nure.coworkinghelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.coworkinghelper.model.CoWorking;

public interface CoWorkingRepository extends JpaRepository<CoWorking, Long> {
}
