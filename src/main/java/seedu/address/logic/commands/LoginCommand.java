package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import seedu.address.model.exception.InvalidPasswordException;
import seedu.address.model.exception.InvalidUsernameException;
import seedu.address.model.exception.MultipleLoginException;

import seedu.address.commons.core.Messages;

import seedu.address.logic.commands.exceptions.CommandException;

/**
* Logs the user into contactHeRo.
*/
public class LoginCommand extends Command {
    public static final String COMMAND_WORD = "login";
    public static final String COMMAND_SYNTAX = COMMAND_WORD + " "
                + PREFIX_USERNAME + " "
                + PREFIX_PASSWORD;
    public static final String MESSAGE_USAGE = COMMAND_WORD + "Logs the user into contactHero."
                + "Parameters: "
                + PREFIX_USERNAME + "USERNAME "
                + PREFIX_PASSWORD + "PASSWORD\n"
                + "Example: " + COMMAND_WORD + " "
                + PREFIX_USERNAME + "JohnDoe "
                + PREFIX_PASSWORD + "98765432 ";
    public static final String MESSAGE_SUCCESS = "You have successfully login as %1$s";
    public static final String MESSAGE_MULTIPLE_LOGIN = "You have already login.";

    private final String username;
    private final String password;

    public LoginCommand(String inputUsername, String inputPassword) {
        requireAllNonNull(inputUsername, inputPassword);
        this.username = inputUsername;
        this.password = inputPassword;
    }

    @Override
    public CommandResult execute() throws CommandException {
        requireNonNull(model);
        try {
            model.login(username, password);
            return new CommandResult(String.format(MESSAGE_SUCCESS, username));
        } catch (InvalidUsernameException iue) {
            throw new CommandException(Messages.MESSAGE_INVALID_USERNAME);
        } catch (InvalidPasswordException ipe) {
            throw new CommandException(Messages.MESSAGE_INVALID_PASSWORD);
        } catch (MultipleLoginException mle) {
            throw new CommandException(MESSAGE_MULTIPLE_LOGIN);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LoginCommand) // instanceof handles nulls
                && this.username.equals(((LoginCommand) other).username)
                && password.equals(((LoginCommand) other).password);
    }

}
