package ua.nure.coworkinghelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.coworkinghelper.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
