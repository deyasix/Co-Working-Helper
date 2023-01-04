package ua.nure.coworkinghelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.coworkinghelper.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
