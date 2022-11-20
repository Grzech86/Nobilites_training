package parking.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import parking.domain.database.car.Car;
import parking.domain.database.car.CarId;
import parking.domain.database.car.Plate;
import parking.domain.database.user.Identifier;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, CarId> {


    List<Car> findAllByPlate(Plate plate);

    boolean existsByPlate(Plate plate);

    @Transactional
    void deleteByPlate(Plate plate);

    @Transactional
    void deleteByIdentifier(Identifier identifier);

//    void deleteAllByUserIdentifier(UserIdentifier userIdentifier);
}
