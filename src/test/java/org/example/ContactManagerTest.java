package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

class ContactManagerTest {

    ContactManager contactManager;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before all");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Before each");
        contactManager = new ContactManager();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After all");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("After each");
    }

    @Disabled
    @Test
    public void shouldCreateTest() {
        contactManager.addContact("Bob", "Smith", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should not create Contact which first name is null")
    public void shouldCreateRunTimeExceptionWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> contactManager.addContact(null, "Bob", "999"));
    }

    @Test
    @EnabledOnOs(value = OS.LINUX)
    public void linuxTest() {
        contactManager.addContact("Bob", "Smith", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisabledOnOs(value = OS.LINUX)
    public void noLinuxTest() {
        contactManager.addContact("Bob", "Smith", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    //@DisplayName("The Assumption test")
    public void assumptionTest() {
        Assumptions.assumeFalse(true);
        contactManager.addContact("Bob", "Smith", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @DisplayName("Repeated test")
    @RepeatedTest(value = 5)
    public void repeatedTest() {
        Assumptions.assumeTrue(true);
        contactManager.addContact("Bob", "Smith", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @DisplayName("Parameterized test")
    @ParameterizedTest()
    @ValueSource(strings = {"9123456789","0123456789","2345678900"})
    public void parameterizedTest(String phoneNumber) {
        contactManager.addContact("Bob", "Smith", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @DisplayName("Parameterized test using MethodSource")
    @ParameterizedTest()
    @MethodSource("phoneNumberList")
    public void parameterizedWithMethodSourceTest(String phoneNumber) {
        contactManager.addContact("Bob", "Smith", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    private static List<String> phoneNumberList() {
        return Arrays.asList("9123456789","0123456789","2345678900");
    }

    @DisplayName("Parameterized CSVSource test")
    @ParameterizedTest()
    @CsvSource("9123456789,0123456789,2345678900")
    public void parameterizedCSVSourceTest(String phoneNumber) {
        contactManager.addContact("Bob", "Smith", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @DisplayName("Parameterized CSVSource test")
    @ParameterizedTest()
    @CsvFileSource(resources = "/data.csv")
    public void parameterizedCSVSourceFileTest(String phoneNumber) {
        contactManager.addContact("Bob", "Smith", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

}