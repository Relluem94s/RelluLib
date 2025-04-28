package de.relluem94.rellulib.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class LogUtils {

    public LogUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Prints an Object with a line break <br>
     *
     * @param o Object to log
     */
    public static void log(Object o) {
        if (LOG_UTILS_LEVEL_OTHER) {
            System.out.println("\t\t" + o);
            log(o, true);
        }
    }

    /**
     * Prints an Object without a line break <br>
     *
     * @param o Object to log
     */
    public static void logInLine(Object o) {
        if (LOG_UTILS_LEVEL_OTHER) {
            System.out.print(o);
        }
    }

    /**
     *
     * Prints an Object with a line break if should log is true<br>
     *
     * @param o Object to log
     * @param shouldLog boolean if should log
     */
    public static void debug(Object o, boolean shouldLog) {
        if (LOG_UTILS_LEVEL_OTHER) {
            if (shouldLog) {
                System.out.println("\t\t" + o);
            }
        }
    }

    /**
     *
     * Prints an Object without a line break if should log is true<br>
     *
     * @param o Object to log
     * @param shouldLog boolean if should log
     */
    public static void debugInLine(Object o, boolean shouldLog) {
        if (LOG_UTILS_LEVEL_OTHER) {
            if (shouldLog) {
                log(o, false);
            }
        }
    }

    /**
     *
     * Prints an Object with a line break and [ERROR] <br>
     *
     * @param o Object to log
     */
    public static void error(Object o) {
        if (LOG_UTILS_LEVEL_ERROR) {
            log("[ERROR]\t\t" + o, true);
        }

    }

    /**
     *
     * Prints an Object with a line break and [INFO] <br>
     *
     * @param o Object to log
     */
    public static void info(Object o) {
        if (LOG_UTILS_LEVEL_INFO) {
            log("[INFO]\t\t" + o, true);
        }

    }

    /**
     *
     * Prints an Object with a line break and [TEST] <br>
     *
     * @param o Object to log
     */
    public static void test(Object o) {
        if (LOG_UTILS_LEVEL_TEST) {
            log("[TEST]\t\t" + o, true);
        }

    }

    /**
     *
     * Prints an Object with a line break and [WARNING] <br>
     *
     * @param o Object to log
     */
    public static void warning(Object o) {
        if (LOG_UTILS_LEVEL_WARNING) {
            log("[WARNING]\t" + o, true);
        }
    }

    /**
     *
     * Prints an Object without a line break <br>
     *
     * @param o Object to log
     */
    public static void line(Object o) {
        if (LOG_UTILS_LEVEL_OTHER) {
            log(o, false);
        }
    }

    /**
     * Prints a List of Objects with a line break
     *
     * @param input List of Objects
     */
    public static void list(List<?> input) {
        if (LOG_UTILS_LEVEL_OTHER) {
            for (Object o : input) {
                log(o);
            }
        }
    }

    /**
     * Prints an Array of Integers with a line break
     *
     * @param input Array of Integers
     */
    public static void intArray(Integer[] input) {
        if (LOG_UTILS_LEVEL_OTHER) {
            for (int i : input) {
                log(i);
            }
        }
    }

    /**
     * Prints an Array of Strings with an line break
     *
     * @param input Array of Strings
     */
    public static void stringArray(String[] input) {
        if (LOG_UTILS_LEVEL_OTHER) {
            for (String s : input) {
                log(s);
            }
        }
    }

    private static void log(Object o, boolean line) {
        if (line) {
            System.out.println(o);
        } else {
            System.out.print(o);
        }

        if (LOG_UTILS_LOG_IN_FILE) {
            try (PrintWriter out = new PrintWriter(new FileOutputStream(LOG_UTILS_LOGFILE + "/RelluLib.log", true))) {
                if (line) {
                    out.println(o);
                } else {
                    out.print(o);
                }

            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static boolean LOG_UTILS_LEVEL_OTHER = true;
    private static final boolean LOG_UTILS_LEVEL_TEST = true;
    private static boolean LOG_UTILS_LEVEL_INFO = true;
    private static boolean LOG_UTILS_LEVEL_WARNING = true;
    private static boolean LOG_UTILS_LEVEL_ERROR = true;
    private static boolean LOG_UTILS_LOG_IN_FILE = false;
    private static String LOG_UTILS_LOGFILE = ".";

    /**
     * Sets Log Level for Info
     *
     * @param enable boolean
     */
    public static void setInfo(boolean enable) {
        LogUtils.LOG_UTILS_LEVEL_INFO = enable;
    }

    /**
     * Gets Log Level for Info
     *
     * @return boolean level enabled
     */
    public static boolean getInfo() {
        return LogUtils.LOG_UTILS_LEVEL_WARNING;
    }

    /**
     * Sets Log Level for Warning
     *
     * @param enable boolean
     */
    public static void setWarning(boolean enable) {
        LogUtils.LOG_UTILS_LEVEL_WARNING = enable;
    }

    /**
     * Gets Log Level for Warning
     *
     * @return boolean level enabled
     */
    public static boolean getWarning() {
        return LogUtils.LOG_UTILS_LEVEL_WARNING;
    }

    /**
     * Sets Log Level for Error
     *
     * @param enable boolean
     */
    public static void setError(boolean enable) {
        LogUtils.LOG_UTILS_LEVEL_ERROR = enable;
    }

    /**
     * Gets Log Level for Error
     *
     * @return boolean level enabled
     */
    public static boolean getError() {
        return LogUtils.LOG_UTILS_LEVEL_ERROR;
    }

    /**
     * Sets Log Level for Other
     *
     * @param enable boolean
     */
    public static void setOther(boolean enable) {
        LogUtils.LOG_UTILS_LEVEL_OTHER = enable;
    }

    /**
     * Gets Log Level for Other
     *
     * @return boolean level enabled
     */
    public static boolean getOther() {
        return LogUtils.LOG_UTILS_LEVEL_OTHER;
    }

    public static boolean getLog2File() {
        return LOG_UTILS_LOG_IN_FILE;
    }

    public static void setLog2File(boolean enable) {
        LOG_UTILS_LOG_IN_FILE = enable;
    }

    public static String getLogFilePath() {
        return LOG_UTILS_LOGFILE;
    }

    public static void setLogFilePath(String path) {
        LOG_UTILS_LOGFILE = path;
    }
}
