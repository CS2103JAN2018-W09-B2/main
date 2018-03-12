package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ProfilePicture {
    private static  final String SPECIAL_CHARACTERS = "!#$%&'*+/=?`{|}~^.-";
    public static final String MESSAGE_PROFILEPICTURE_CONSTRAINTS =
            "Person names should only contain alphanumeric characters and spaces, and it should not be blank";

    // alphanumeric and special characters
    public static final String PROFILE_PICTURE_VALIDATION_REGEX = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param profilePicture A valid image path.
     */
    public ProfilePicture(String profilePicture) {
        if (profilePicture != null) {
            checkArgument(isValidProfilePicture(profilePicture), MESSAGE_PROFILEPICTURE_CONSTRAINTS);
        }
        this.value = profilePicture;
    }

    /**
     * Returns if a given string is a valid person email.
     */
    public static boolean isValidProfilePicture(String test) {
        return test.matches(PROFILE_PICTURE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProfilePicture // instanceof handles nulls
                && this.value.equals(((Email) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
