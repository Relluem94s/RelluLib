package de.relluem94.rellulib.utils;

public class InfoUtils {

    private InfoUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     *
     * @return Operating System Name
     */
    public static String osName() {
        return System.getProperty("os.name");
    }

    /**
     *
     * @return Operating System Version
     */
    public static String osVersion() {
        return System.getProperty("os.version");
    }

    /**
     *
     * @return Operating System Architecture
     */
    public static String osArch() {
        return System.getProperty("os.arch");
    }

    /**
     *
     * @return CPU Identifer
     */
    public static String cpuIndentifier() {
        return System.getenv("PROCESSOR_IDENTIFIER");
    }

    /**
     *
     * @return CPU Architecture
     */
    public static String cpuArch() {
        return System.getenv("PROCESSOR_ARCHITECTURE");
    }

    /**
     *
     * @return CPU Number of Cores
     */
    public static String cpuNumberOfCores() {
        return System.getenv("NUMBER_OF_PROCESSORS");
    }

    /**
     *
     * @return Java Name
     */
    public static String javaName() {
        return System.getProperty("java.vm.name");
    }

    /**
     *
     * @return Java Version
     */
    public static String javaVersion() {
        return System.getProperty("java.vm.version");
    }

    /**
     *
     * @return User Home Directory
     */
    public static String userHome() {
        return System.getProperty("user.home");
    }
}
