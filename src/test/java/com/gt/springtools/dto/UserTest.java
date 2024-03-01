package com.gt.springtools.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private static final User userA = new User("8108e27f-301c-4c2a-8592-de2dd34402e8",
            "Mary",
            "5551234",
            "mary@email.com",
            "A Street",
            "Bronx",
            "NY",
            "NY",
            null,
            null);

    private static final User userB = new User("8108e27f-301c-4c2a-8592-de2dd34402e8",
            "John",
            "5551234",
            "john@email.com",
            "B Street",
            "Bronx",
            "NY",
            "NY",
            null,
            null);

    @Test
    void testToString() {
        String userToString = "User (8108e27f-301c-4c2a-8592-de2dd34402e8, Mary, 5551234, mary@email.com, A Street, " +
                "Bronx, NY, NY, null, null)";
        assertThat(userToString).isEqualTo(userA.toString());
    }

    @Test
    void testEquals() {
        assertThat(userA).isNotEqualTo(userB);
    }

    @Test
    void testHashCode() {
        assertThat(userA.hashCode()).isNotEqualTo(userB.hashCode());
    }


}