package parking.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import parking.domain.SlotStatus;
import parking.domain.database.parking.ParkingStatus;

@Repository
public interface ParkingStatusRepository extends CrudRepository <ParkingStatus, Integer>{

    Iterable<ParkingStatus> findBySlotStatus(SlotStatus slotStatus);

    boolean existsByNumberAndSlotStatus(int number, SlotStatus occupied);
}
