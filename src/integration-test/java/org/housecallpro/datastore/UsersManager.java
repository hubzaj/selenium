package org.housecallpro.datastore;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class UsersManager {

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

    public synchronized void releaseUser(User user) {
        if (USERS.contains(user)) {
            availableUsers.add(user);
        } else {
            log.warn("Unable to release user [{}]", user);
        }
        log.info("Released user [{}]", user);
    }


    private synchronized Optional<User> getFirstAvailableUser() {
        if (availableUsers.isEmpty()) {
            return Optional.empty();
        }
        User user = availableUsers.iterator().next();
        availableUsers.remove(user);
        log.info("Reserved user [{}]", user);
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
