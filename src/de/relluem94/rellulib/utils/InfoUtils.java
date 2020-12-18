package de.relluem94.rellulib.utils;

public class InfoUtils {

    /**
     *
     * @return Operating System Name
     */
    public static String OSname() {
        return System.getProperty("os.name");
    }

    /**
     *
     * @return Operating System Version
     */
    public static String OSversion() {
        return System.getProperty("os.version");
    }

    /**
     *
     * @return Operating System Architecture
     */
    public static String OsArch() {
        return System.getProperty("os.arch");
    }

    /**
     *
     * @return CPU Identifer
     */
    public static String CPU_Identifer() {
        return System.getenv("PROCESSOR_IDENTIFIER");
    }

    /**
     *
     * @return CPU Architecture
     */
    public static String CPU_Arch() {
        return System.getenv("PROCESSOR_ARCHITECTURE");
    }

    /**
     *
     * @return CPU Number of Cores
     */
    public static String CPU_NumberOfCores() {
        return System.getenv("NUMBER_OF_PROCESSORS");
    }

    /**
     *
     * @return Java Name
     */
    public static String JAVA_Name() {
        return System.getProperty("java.vm.name");
    }

    /**
     *
     * @return Java Version
     */
    public static String JAVA_Version() {
        return System.getProperty("java.vm.version");
    }

    /**
     *
     * @return User Home Directory
     */
    public static String USER_home() {
        return System.getProperty("user.home");
    }
}
