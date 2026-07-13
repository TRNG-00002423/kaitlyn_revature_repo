package com.revature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.revature.User;

/**
 * An alternate UserService class with no dependencies.
 */
public class UserService {

    List<User> users = new ArrayList<>();
    long curID = 1L;

    User createUser(String name, String email) {
        if (findByEmail(email) != null) {
            throw new DuplicateUserException("User with email " + email + " already exists.");
        }
        if (name == null) {
            throw new IllegalArgumentException("name can not be null");
        }
        if (!isEmail(email)) {
            throw new IllegalArgumentException("invalid email");
        }
        User newUser = new User(curID, name, email);
        users.add(newUser);
        curID++;
        return newUser;
    }

    User findByEmail(String email) {
        for (User user : users) {
            if (email.equals(user.getEmail())) {
                return user;
            }
        }
        return null;
    }

    public User getUser(Long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new UserNotFoundException("User with id " + id + " not found");
    }

    // Custom exceptions
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateUserException extends RuntimeException {
        public DuplicateUserException(String message) {
            super(message);
        }
    }

    public void updateUser(User user) {
        User localUser = getUser(user.getId());
        if (user.getName() == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (!isEmail(user.getEmail())) {
            throw new IllegalArgumentException("invalid email");
        }
        localUser.setName(user.getName());
        localUser.setEmail(user.getEmail());
    }

    public void deleteUser(Long id) {
        User toRemove = getUser(id);
        users.remove(toRemove);
    }

    private static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    private static boolean isEmail(String emailAddress) {
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return patternMatches(emailAddress, emailRegex);
    }
}
