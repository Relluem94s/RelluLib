package de.relluem94.rellulib.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class InfoUtilsTest {

    private InfoUtils.EnvironmentProvider envProvider;

    @BeforeEach
    public void setUp() {
        envProvider = Mockito.mock(InfoUtils.EnvironmentProvider.class);
    }

    @AfterEach
    public void tearDown() {
        System.clearProperty("PROCESSOR_ARCHITEW6432");
        InfoUtils.setEnvironmentProvider(InfoUtils.envProvider);
    }

    @Test
    void checkForUtilityClass(){
        Assertions.assertThrows(IllegalStateException.class, InfoUtils::new);
    }

    @Test
    void osName() {
        assertNotNull(InfoUtils.osName());
    }

    @Test
    void osVersion() {
        assertNotNull(InfoUtils.osVersion());
    }

    @Test
    void osArch() {
        assertNotNull(InfoUtils.osArch());
    }

    @Test
    void cpuIdentifier() {
        Mockito.when(envProvider.getEnv("PROCESSOR_IDENTIFIER")).thenReturn(null);
        assertEquals("", InfoUtils.cpuIdentifier());
        Mockito.when(envProvider.getEnv("PROCESSOR_IDENTIFIER")).thenReturn("Intel64");
        InfoUtils.setEnvironmentProvider(envProvider);
        Mockito.when(envProvider.getEnv("PROCESSOR_IDENTIFIER")).thenReturn(null);
        assertEquals("", InfoUtils.cpuIdentifier());
        Mockito.when(envProvider.getEnv("PROCESSOR_IDENTIFIER")).thenReturn("Intel64");
        assertEquals("Intel64", InfoUtils.cpuIdentifier());
    }

    @Test
    void cpuArch() {
        Mockito.when(envProvider.getEnv("PROCESSOR_ARCHITECTURE")).thenReturn(null);
        Mockito.when(envProvider.getProperty("PROCESSOR_ARCHITEW6432")).thenReturn(null);
        assertEquals("", InfoUtils.cpuArch());
        Mockito.when(envProvider.getEnv("PROCESSOR_ARCHITECTURE")).thenReturn("x86_64");
        InfoUtils.setEnvironmentProvider(envProvider);
        Mockito.when(envProvider.getEnv("PROCESSOR_ARCHITECTURE")).thenReturn(null);
        Mockito.when(envProvider.getProperty("PROCESSOR_ARCHITEW6432")).thenReturn(null);
        assertEquals("", InfoUtils.cpuArch());
        Mockito.when(envProvider.getEnv("PROCESSOR_ARCHITECTURE")).thenReturn("x86_64");
        assertEquals("x86_64", InfoUtils.cpuArch());
        Mockito.when(envProvider.getEnv("PROCESSOR_ARCHITECTURE")).thenReturn(null);
        Mockito.when(envProvider.getProperty("PROCESSOR_ARCHITEW6432")).thenReturn("amd64");
        assertEquals("amd64", InfoUtils.cpuArch());
    }

    @Test
    void cpuNumberOfCores() {
        Mockito.when(envProvider.getEnv("NUMBER_OF_PROCESSORS")).thenReturn(null);
        String expected = String.valueOf(Runtime.getRuntime().availableProcessors());
        assertEquals(expected, InfoUtils.cpuNumberOfCores());
        Mockito.when(envProvider.getEnv("NUMBER_OF_PROCESSORS")).thenReturn("8");
        InfoUtils.setEnvironmentProvider(envProvider);
        Mockito.when(envProvider.getEnv("NUMBER_OF_PROCESSORS")).thenReturn(null);
        expected = String.valueOf(Runtime.getRuntime().availableProcessors());
        assertEquals(expected, InfoUtils.cpuNumberOfCores());
        Mockito.when(envProvider.getEnv("NUMBER_OF_PROCESSORS")).thenReturn("8");
        assertEquals("8", InfoUtils.cpuNumberOfCores());
    }

    @Test
    void javaName() {
        assertNotNull(InfoUtils.javaName());
    }

    @Test
    void javaVersion() {
        assertNotNull(InfoUtils.javaVersion());
    }

    @Test
    void userHome() {
        assertNotNull(InfoUtils.userHome());
    }
}