package org.housecallpro.datastore;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j(topic = "UsersFetcher")
class UsersFetcher {

    private static final String RESOURCE_PATH = "datastore/test-users.json";

    private static UsersFetcher loader;

    @Getter
    private final List<User> users;

    /**
     * It should be fetched from services like [AWS cognito], [GCP Secret Manager] or etc.
     * but for the sake of example it will be simple json file loaded from resources (not stored in repository)
     */
    @SneakyThrows
    private UsersFetcher() {
        log.info("fetching test users from [GCS] bucket");
        String testUsersDefinitionFilePath = getPathToTestUsersDefinitionFile();
        String testUsersJsonString = new String(Files.readAllBytes(Paths.get(testUsersDefinitionFilePath)));
        users = new Gson().fromJson(testUsersJsonString, new TypeToken<ArrayList<User>>() {
        }.getType());
    }

    synchronized static UsersFetcher fetchUsers() {
        if (loader == null) {
            loader = new UsersFetcher();
        }
        return loader;
    }

    private String getPathToTestUsersDefinitionFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(RESOURCE_PATH);
        if (resource == null) {
            throw new NoSuchElementException(
                    String.format("Fetch definition file form private GCS bucket [gs://qa-resources/test-users.json]" +
                            "and place it to src/integration-test/resource/[%s]", RESOURCE_PATH));
        }
        return new File(resource.getFile()).getAbsolutePath();
    }

}
