//@@author Jason1im
package seedu.address.model;

/**
 * Represents a user account.
 */
public final class Account {
    private final String DEFAULT_USERNAME = "John";
    private final String DEFAULT_PASSWORD = "Doe123";

    private String username;
    private String password;

    public Account() {
        this.username = DEFAULT_USERNAME;
        this.password = DEFAULT_PASSWORD;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * @param newUsername should not be null
     */
    public void updateUsername(String newUsername) {
        username = newUsername;
    }

    /**
     * @param newPassword should not be null
     */
    public void updatePassword(String newPassword) {
        password = newPassword;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Account)) {
            return false;
        }
        Account otherAccount = (Account) other;
        return otherAccount.getUsername().equals(this.getUsername())
                && otherAccount.getPassword().equals(this.getPassword());
    }

}
