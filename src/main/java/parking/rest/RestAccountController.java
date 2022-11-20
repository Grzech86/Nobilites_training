package parking.rest;

import org.springframework.web.bind.annotation.*;
import pl.nobilites.parking.controllers.AccountController;
import pl.nobilites.parking.domain.rest.*;

@RestController
@RequestMapping("accounts")
public class RestAccountController {

    private final AccountController accountController;

    // constructor
    public RestAccountController(AccountController accountController) {
        this.accountController = accountController;
    }

    @PutMapping("/create")
    public AccountCreateResponse create(@RequestBody AccountCreateRequest accountCreateRequest) {

        try {
            final var localResult = accountController.createNewAccount(accountCreateRequest.firstName(),
                    accountCreateRequest.lastName(),
                    accountCreateRequest.emailAddress(),
                    accountCreateRequest.phoneNumber(),
                    accountCreateRequest.plates());

            if (localResult.equals(AccountCreationResult.ACCOUNT_CREATED)) {
                return new AccountCreateResponse.AccountCreatedSuccessfully();

            } else if (localResult.equals(AccountCreationResult.ACCOUNT_ALREADY_EXIST)) {
                return new AccountCreateResponse.AccountNotCreated("Account already exists.");

            } else if (localResult.equals(AccountCreationResult.INCORRECT_EMAIL_ADDRESS)) {
                return new AccountCreateResponse.AccountNotCreated("Incorrect e-mail address.");

            } else {
                return new AccountCreateResponse.AccountNotCreated("Account not created because of unknown reason.");
            }

        } catch (UnsupportedOperationException e) {
            return new AccountCreateResponse.AccountNotCreated("Unsupported operation exception");
        }

    }

//    @PostMapping("/update-plates")
//    public AccountPlatesUpdateResponse updatePlates(@RequestBody AccountPlatesUpdateRequest accountPlatesUpdateRequest) {
//        try {
//            accountController.updateAccountPlates(accountPlatesUpdateRequest.userIdentifier(), accountPlatesUpdateRequest.plates());
//            return new AccountPlatesUpdateResponse.AccountPlatesUpdatedSuccessfully();
//        } catch (UnsupportedOperationException e) {
//            return new AccountPlatesUpdateResponse.AccountPlatesNotUpdated();
//        }
//    }

    @PostMapping("/plates/delete")
    public PlatesModificationResponse deletePlate(@RequestBody PlatesDeleteRequest platesDeleteRequest) {
        try {
            final var localResult = accountController.deletePlate(platesDeleteRequest.plates());
            if (localResult.equals(PlatesModificationResult.MODIFICATION_SUCCEED)) {
                return new PlatesModificationResponse.PlatesModificationSucceed("Plates deleted successfully.");
            } else {
                return new PlatesModificationResponse.PlatesModificationFail("Plates not found.");
            }

        } catch (UnsupportedOperationException e) {
            return new PlatesModificationResponse.PlatesModificationFail("Unsupported operation.");
        }
    }
    @PostMapping("/plates/add")
    public PlatesModificationResponse addPlates(@RequestBody PlatesAddRequest platesAddRequest) {
        try {
            final var localResult = accountController.addPlates(platesAddRequest.identifier(), platesAddRequest.plates());
            if (localResult.equals(PlatesModificationResult.MODIFICATION_SUCCEED)) {
                return new PlatesModificationResponse.PlatesModificationSucceed("Plates added.");
            } else {
                return new PlatesModificationResponse.PlatesModificationFail("Plates not added.");
            }

        } catch (UnsupportedOperationException e) {
            return new PlatesModificationResponse.PlatesModificationFail("Unsupported operation.");
        }
    }
    @PostMapping("/plates/clear")
    public PlatesModificationResponse clearPlates(@RequestBody PlatesClearRequest platesClearRequest) {
        try {
            final var localResult = accountController.addPlates(platesClearRequest.identifier());
            if (localResult.equals(PlatesModificationResult.MODIFICATION_SUCCEED)) {
                return new PlatesModificationResponse.PlatesModificationSucceed("All plates deleted");
            } else {
                return new PlatesModificationResponse.PlatesModificationFail("Plates not deleted");
            }

        } catch (UnsupportedOperationException e) {
            return new PlatesModificationResponse.PlatesModificationFail("Unsupported operation.");
        }
    }


    @DeleteMapping("/delete")
    public AccountDeleteResponse delete(@RequestBody AccountDeleteRequest accountDeleteRequest) {
        try {
            final var localResult = accountController.deleteExistingAccount(accountDeleteRequest.identifier());
            if (localResult.equals(AccountDeleteResult.ACCOUNT_DELETED)) {

                accountController.deleteExistingAccount(accountDeleteRequest.identifier());
                return new AccountDeleteResponse.AccountDeletedSuccessfully("Account deleted.");

            } else {
                return new AccountDeleteResponse.AccountNotDeleted("Account to delete not found.");
            }

        } catch (UnsupportedOperationException e) {
            return new AccountDeleteResponse.AccountNotDeleted("Account not deleted.");
        }
    }
}
