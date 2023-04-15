package org.housecallpro.datastore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public final class User {

    @Getter
    private final String email;

    private final String password;

    public char[] getPassword() {
        return password.toCharArray();
    }

}
