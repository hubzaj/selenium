package org.housecallpro.resource;

import org.housecallpro.datastore.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class UsersManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersManager.class);

    private static final int USER_AVAILABILITY_TIMEOUT_IN_SECONDS = 30;
    private static final int USER_AVAILABILITY_POOLING_IN_SECONDS = 1;
    private static final int MILLISECONDS_IN_SECOND = 1000;

    private static final Set<User> USERS = new HashSet<>(UsersFetcher.fetchUsers().getUsers());

    private static UsersManager manager;

    private Set<User> availableUsers;

    private UsersManager() {
        this.availableUsers = new HashSet<>(USERS);
    }

    public static UsersManager getUserManager() {
        if (manager == null) {
            manager = new UsersManager();
        }
        return manager;
    }

    public User reserveUser() {
        final long timeout = new Date().getTime() + USER_AVAILABILITY_TIMEOUT_IN_SECONDS * MILLISECONDS_IN_SECOND;
        while (timeout > new Date().getTime()) {
            Optional<User> availableUser = getFirstAvailableUser();
            if (availableUser.isPresent()) {
                return availableUser.get();
            }
            wait(USER_AVAILABILITY_POOLING_IN_SECONDS);
        }
        throw new NoSuchElementException("There is no available user to get");
    }

    public synchronized void releaseUsers(Collection<User> users) {
        for (User user : users) {
            if (USERS.contains(user)) {
                availableUsers.add(user);
            } else {
                LOGGER.warn("Unable to release user [{}]", user);
            }
            LOGGER.info("Released user [{}]", user);
        }
    }


    private synchronized Optional<User> getFirstAvailableUser() {
        if (availableUsers.isEmpty()) {
            return Optional.empty();
        }
        User user = availableUsers.iterator().next();
        availableUsers.remove(user);
        LOGGER.info("Reserved user [{}]", user);
        return Optional.of(user);
    }

    private void wait(int seconds) {
        try {
            Thread.sleep((long) seconds * MILLISECONDS_IN_SECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
