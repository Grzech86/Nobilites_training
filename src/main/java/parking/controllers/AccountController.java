package parking.controllers;

import org.springframework.stereotype.Controller;
import parking.domain.database.car.Car;
import parking.domain.database.car.Plate;
import parking.domain.database.user.Account;
import parking.domain.database.user.Identifier;
import parking.domain.rest.AccountCreationResult;
import parking.domain.rest.AccountDeleteResult;
import parking.domain.rest.PlatesModificationResult;
import parking.repositories.AccountRepository;
import parking.repositories.CarRepository;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

@Controller
public class AccountController {

    final AccountRepository accountRepository;

    private final CarRepository carRepository;

    public AccountController(AccountRepository accountRepository, CarRepository carRepository) {
        this.accountRepository = accountRepository;
        this.carRepository = carRepository;
    }

    public long amountOfAccounts() {
        return accountRepository.count();
    }

    public AccountCreationResult createNewAccount(String firstName, String lastName, String emailAddress, Integer phoneNumber, String... plates) {
        /* adding a new account to the register
         * field emailAddress can't be empty string and has to have a proper email address construction
         * field/s plates is allowed to be empty, be separate String or few Strings
         */
        final var userIdentifier = createUserIdentifier(emailAddress);
        if (accountRepository.existsById(userIdentifier)) {
            return AccountCreationResult.ACCOUNT_ALREADY_EXIST;

        } else if (!isEmailAddressCorrect(emailAddress)) {
            return AccountCreationResult.INCORRECT_EMAIL_ADDRESS;

        } else {
            accountRepository.save(
                    new Account(userIdentifier,
                            firstName,
                            lastName,
                            emailAddress,
                            phoneNumber
                    )
            );
            carRepository.saveAll(Arrays.stream(plates).map(plate -> new Car(userIdentifier, new Plate(plate))).toList());
            return AccountCreationResult.ACCOUNT_CREATED;
        }
    }

    private static Identifier createUserIdentifier(String emailAddress) {
        return new Identifier(emailAddress);
    }

    public AccountDeleteResult deleteExistingAccount(Identifier identifier) {
        try {
            accountRepository.deleteById(identifier);
            return AccountDeleteResult.ACCOUNT_DELETED;
        } catch (Exception ex) {
            return AccountDeleteResult.ACCOUNT_NOT_FOUND;
        }
    }

    public PlatesModificationResult deletePlate(String plate) {
        try {
            carRepository.deleteByPlate(new Plate(plate));
            return PlatesModificationResult.MODIFICATION_SUCCEED;
        } catch (Exception ex) {
            return PlatesModificationResult.MODIFICATION_FAILED;
        }
    }

    public PlatesModificationResult addPlates(Identifier identifier, String... plate) {
        carRepository.saveAll(
                Arrays.stream(plate)
                        .map(Plate::new)
                        .map(p -> new Car(identifier, p))
                        .toList()
        );
        return PlatesModificationResult.MODIFICATION_SUCCEED;
//        return PlatesModificationResult.MODIFICATION_FAILED;
    }


    public Optional<Identifier> findByEmail(String email) {
        final var userIdentifier = createUserIdentifier(email);
        return accountRepository.findById(userIdentifier).map(Account::getIdentifier);
    }

    /**
     * method findBy accepts three possible inputs
     * - String as email address
     * - String as car plate
     * - UserIdentifier
     */


    public Optional<Account> findBy(String userIdAsString) {
        final var userIdentifier = createUserIdentifier(userIdAsString);

        return accountRepository.findById(userIdentifier);
    }

    public Optional<Account> findBy(Plate plate) {
        return carRepository.findAllByPlate(plate)
                .stream()
                .findFirst()
                .map(Car::getIdentifier)
                .flatMap(accountRepository::findById);
    }

    public Optional<Account> findBy(Identifier identifier) {
        return accountRepository.findById(identifier);
    }

    public boolean isEmailAddressCorrect(String emailAddress) {
        String regex = "^(.+)@(.+).(.+)$";

        Pattern pattern = Pattern.compile(regex);
        final var matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
