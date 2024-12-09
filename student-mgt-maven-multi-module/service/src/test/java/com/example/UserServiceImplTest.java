package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class UserServiceImplTest {

    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    public void testGetUser() {
        long userId = 1L;
        String result = userService.getUser(userId);

        assertEquals("Ahurein", result, "The returned user name should be 'Ahurein'");
    }

    @Test
    public void testGetAllUsers() {
        List<String> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("Ahurein"));
        assertTrue(result.contains("Eben"));
    }
}