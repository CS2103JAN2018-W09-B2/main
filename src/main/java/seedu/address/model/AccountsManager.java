//@@author Jason1im
package seedu.address.model;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.exception.InvalidPasswordException;
import seedu.address.model.exception.InvalidUsernameException;


/**
 * Represents a database of registered user accounts
 */
public class AccountsManager {
    private final Account account;


    public AccountsManager() {
        account = new Account();
    }

    private boolean checkUsername(String username, Account account) {
        return account.getUsername().equals(username);
    }

    private boolean checkPassword(String password, Account account) {
        return account.getPassword().equals(password);
    }

    /**
     * Updates the username of the account.
     * @throws InvalidUsernameException if the username is already in use
     */
    public void updateUsername(String inputUsername) throws InvalidUsernameException {
        requireAllNonNull(inputUsername);
        if (checkUsername(inputUsername, account)) {
            throw new InvalidUsernameException();
        } else {
            account.updateUsername(inputUsername);
        }
    }

    /**
     * Updates the password of the account.
     * @throws InvalidPasswordException
     */
    public void updatePassword(String oldPassword, String newPassword)
            throws InvalidPasswordException {
        requireAllNonNull(oldPassword, newPassword);
        if (!checkPassword(oldPassword, account)) {
            throw new InvalidPasswordException();
        } else {
            account.updatePassword(newPassword);
        }
    }

    public void resetPassword() { account.resetPassword(); }

    /**
     * Checks for validity of username and password.
     * @return the user account that matches the inputs.
     * @throws InvalidUsernameException if the input username cannot be found
     * @throws InvalidPasswordException if the input password does not match with the username
     */
    public Account login(String inputUsername, String inputPassword)
            throws InvalidUsernameException, InvalidPasswordException {

        requireAllNonNull(inputUsername, inputPassword);
        if (!checkUsername(inputUsername, account)) {
            throw new InvalidUsernameException();
        } else if (!checkPassword(inputPassword, account)) {
            throw new InvalidPasswordException();
        } else {
            return account;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AccountsManager // instanceof handles nulls
                && this.account.equals(((AccountsManager) other).account));
    }

    @Override
    public int hashCode() {
        return Objects.hash(account);
    }
}
