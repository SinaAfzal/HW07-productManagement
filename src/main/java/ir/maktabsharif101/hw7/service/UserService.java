package ir.maktabsharif101.hw7.service;

import ir.maktabsharif101.hw7.entities.User;
import ir.maktabsharif101.hw7.repository.UserRepository;

import java.sql.SQLException;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) throws SQLException {
        if (userRepository.doesExist(user.getUserName()) || userRepository.mailDoesExist(user.getEmail())) {
            System.out.println("username or email already exist on database!");
            return null;
        } else {
            User returnedUser = userRepository.save(user);
            if (returnedUser != null) {
                System.out.println("ID=" + returnedUser.getId() + " is assigned to the user and it is stored on the database!");
                return returnedUser;
            } else {
                System.out.println("OOPS! Something went wrong!");
                return null;
            }
        }
    }

    public User login(String username, String password) throws SQLException {
        User loggedInUser = userRepository.login(username, password);
        if (loggedInUser != null) {
            System.out.println("Welcome " + loggedInUser.getFullName() + "!");
            return loggedInUser;
        } else {
            System.out.println("Invalid username or password!");
            return null;
        }
    }


}
