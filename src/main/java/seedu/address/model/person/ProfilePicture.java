package seedu.address.model.person;

import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a ProfilePicture in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ProfilePicture {
    public static final String MESSAGE_PROFILEPICTURE_CONSTRAINTS =
            "Profile picture name should be a valid image name, and it should end with either jpeg, jpg, png, gif or bmp";
    public static final String MESSAGE_PROFILEPICTURE_NOT_EXISTS =
            "Profile picture does not exists. Please give another profile picture";
    private static final String defaultImg = "file:images/default.png";

    // alphanumeric and special characters
    public static final String PROFILE_PICTURE_VALIDATION_REGEX = "^$|([^\\s]+(\\.(?i)(jpeg|jpg|png|gif|bmp))$)";

    public final String value;
    public final String path;

    /**
     * Constructs an {@code Email}.
     *
     * @param profilePicture A valid image path.
     */
    public ProfilePicture(String... profilePicture) {
        if (profilePicture.length != 0 && profilePicture[0] != null) {
            checkArgument(isValidProfilePicture(profilePicture[0]), MESSAGE_PROFILEPICTURE_CONSTRAINTS);
            checkArgument(hasValidProfilePicture(profilePicture[0]), MESSAGE_PROFILEPICTURE_NOT_EXISTS);
            String temp = new String();
            try {
                temp = new File("images/".concat(profilePicture[0])).toURI().toURL().toExternalForm();
            } catch (MalformedURLException e) {
                temp = defaultImg;
            } finally {
                this.value = profilePicture[0];
                this.path = temp;
            }
        } else {
            this.value = "default.png";
            this.path = defaultImg;
        }
    }

    /**
     * Returns if a given string is a valid person email.
     */
    public static boolean isValidProfilePicture(String test) {
        return test.matches(PROFILE_PICTURE_VALIDATION_REGEX);
    }

    /**
     * Returns if there exists profile picture.
     * @param profilePicture
     * @return
     */
    public static boolean hasValidProfilePicture(String profilePicture) {
        String profilePicturepath = "images/".concat(profilePicture);
        File file = new File(profilePicturepath);
        return file.exists() && !file.isDirectory();
    }

    public Image getImage() {
        return new Image(path);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProfilePicture // instanceof handles nulls
                && this.value.equals(((ProfilePicture) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
