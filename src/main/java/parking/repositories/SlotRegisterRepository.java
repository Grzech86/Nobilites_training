package parking.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import parking.domain.database.car.Plate;
import parking.domain.database.parking.SlotRegister;

import java.util.Optional;

@Repository
public interface SlotRegisterRepository extends CrudRepository<SlotRegister, Integer> {
    Optional<SlotRegister> findByPlate(Plate plate);

    boolean existsByPlate(Plate plate);
}
