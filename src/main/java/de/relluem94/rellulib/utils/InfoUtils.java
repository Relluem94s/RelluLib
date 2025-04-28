package de.relluem94.rellulib.utils;

public class InfoUtils {
    public InfoUtils() {
        throw new IllegalStateException("Utility class");
    }

    static EnvironmentProvider envProvider = new SystemEnvironmentProvider();

    static void setEnvironmentProvider(EnvironmentProvider provider) {
        envProvider = provider;
    }

    interface EnvironmentProvider {
        String getEnv(String key);
        String getProperty(String key);
    }

    static class SystemEnvironmentProvider implements EnvironmentProvider {
        @Override
        public String getEnv(String key) {
            return System.getenv(key);
        }

        @Override
        public String getProperty(String key) {
            return System.getProperty(key);
        }
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
     * @return CPU Identifier
     */
    public static String cpuIdentifier() {
        return envProvider.getEnv("PROCESSOR_IDENTIFIER") == null ? "" : envProvider.getEnv("PROCESSOR_IDENTIFIER");
    }

    /**
     *
     * @return CPU Architecture
     */
    public static String cpuArch() {
        return envProvider.getEnv("PROCESSOR_ARCHITECTURE") == null ? (envProvider.getProperty("PROCESSOR_ARCHITEW6432") == null? "" : envProvider.getProperty("PROCESSOR_ARCHITEW6432")) : envProvider.getEnv("PROCESSOR_ARCHITECTURE");
    }

    /**
     *
     * @return CPU Number of Cores
     */
    public static String cpuNumberOfCores() {
        return envProvider.getEnv("NUMBER_OF_PROCESSORS") == null ? Runtime.getRuntime().availableProcessors() + "" : envProvider.getEnv("NUMBER_OF_PROCESSORS");
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
