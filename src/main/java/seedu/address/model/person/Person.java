package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 * Two persons can have the same profilePicture
 */
public class Person implements ReadOnlyPerson {

    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final ProfilePicture profilePicture;

    private ObjectProperty<Name> nameProperty;
    private ObjectProperty<Phone> phoneProperty;
    private ObjectProperty<Email> emailProperty;
    private ObjectProperty<Address> addressProperty;

    private final UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, ProfilePicture profilePicture, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.nameProperty = new SimpleObjectProperty<>(name);
        this.phone = phone;
        this.phoneProperty = new SimpleObjectProperty<>(phone);
        this.email = email;
        this.emailProperty = new SimpleObjectProperty<>(email);
        this.address = address;
        this.addressProperty = new SimpleObjectProperty<>(address);
        if (profilePicture == null) {
            this.profilePicture = new ProfilePicture();
        } else {
            this.profilePicture = profilePicture;
        }
        this.tags = new UniqueTagList(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags.toSet());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Person)) {
            return false;
        }
        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(this.getName())
                && otherPerson.getPhone().equals(this.getPhone())
                && otherPerson.getEmail().equals(this.getEmail())
                && otherPerson.getAddress().equals(this.getAddress())
                && otherPerson.getProfilePicture().equals(this.getProfilePicture());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, profilePicture, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Profile Picture: ")
                .append(getProfilePicture())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

    public ObjectProperty<Name> nameProperty() {
        return nameProperty;
    }

    public ObjectProperty<Phone> phoneProperty() {
        return phoneProperty;
    }

    public ObjectProperty<Email> emailProperty() {
        return emailProperty;
    }

    public ObjectProperty<Address> addressProperty() {
        return addressProperty;
    }

}
