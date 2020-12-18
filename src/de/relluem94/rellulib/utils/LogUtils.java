package de.relluem94.rellulib.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class LogUtils {

    /**
     * Prints an Object with an line break <br>
     *
     * @param o Object to log
     */
    public static void log(Object o) {
        if (LOGUTILS_LEVEL_OTHER) {
            System.out.println("\t\t" + o);
            log(o, true);
        }
    }

    /**
     * Prints an Object without an line break <br>
     *
     * @param o Object to log
     */
    public static void logInLine(Object o) {
        if (LOGUTILS_LEVEL_OTHER) {
            System.out.print(o);
        }
    }

    /**
     *
     * Prints an Object with an line break if should log is true<br>
     *
     * @param o Object to log
     * @param shouldlog boolean if should log
     */
    public static void debug(Object o, boolean shouldlog) {
        if (LOGUTILS_LEVEL_OTHER) {
            if (shouldlog) {
                System.out.println("\t\t" + o);
            }
        }
    }

    /**
     *
     * Prints an Object without an line break if should log is true<br>
     *
     * @param o Object to log
     * @param shouldlog boolean if should log
     */
    public static void debugInLine(Object o, boolean shouldlog) {
        if (LOGUTILS_LEVEL_OTHER) {
            if (shouldlog) {
                log(o, false);
            }
        }
    }

    /**
     *
     * Prints an Object with an line break and [ERROR] <br>
     *
     * @param o Object to log
     */
    public static void error(Object o) {
        if (LOGUTILS_LEVEL_ERROR) {
            log("[ERROR]\t\t" + o, true);
        }

    }

    /**
     *
     * Prints an Object with an line break and [INFO] <br>
     *
     * @param o Object to log
     */
    public static void info(Object o) {
        if (LOGUTILS_LEVEL_INFO) {
            log("[INFO]\t\t" + o, true);
        }

    }

    /**
     *
     * Prints an Object with an line break and [WARNING] <br>
     *
     * @param o Object to log
     */
    public static void warning(Object o) {
        if (LOGUTILS_LEVEL_WARNING) {
            log("[WARNING]\t" + o, true);
        }
    }

    /**
     *
     * Prints an Object without an line break <br>
     *
     * @param o Object to log
     */
    public static void line(Object o) {
        if (LOGUTILS_LEVEL_OTHER) {
            log(o, false);
        }
    }

    /**
     * Prints an List of Objects with an line break
     *
     * @param input List of Objects
     */
    public static void list(List<?> input) {
        if (LOGUTILS_LEVEL_OTHER) {
            for (Object o : input) {
                log(o);
            }
        }
    }

    /**
     * Prints an Array of Integers with an line break
     *
     * @param input Array of Integers
     */
    public static void intArray(Integer[] input) {
        if (LOGUTILS_LEVEL_OTHER) {
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
        if (LOGUTILS_LEVEL_OTHER) {
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

        if (LOGUTILS_LOG_IN_FILE) {
            try ( PrintWriter out = new PrintWriter(new FileOutputStream(LOGUTILS_LOGFILE + "/RelluLib.log", true))) {
                if (line) {
                    out.println(o);
                } else {
                    out.print(o);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean LOGUTILS_LEVEL_OTHER = true;
    private static boolean LOGUTILS_LEVEL_INFO = true;
    private static boolean LOGUTILS_LEVEL_WARNING = true;
    private static boolean LOGUTILS_LEVEL_ERROR = true;
    private static boolean LOGUTILS_LOG_IN_FILE = false;
    private static String LOGUTILS_LOGFILE = ".";

    /**
     * Sets Log Level for Info
     *
     * @param enable
     */
    public static void setInfo(boolean enable) {
        LogUtils.LOGUTILS_LEVEL_INFO = enable;
    }

    /**
     * Gets Log Level for Info
     */
    public static boolean getInfo() {
        return LogUtils.LOGUTILS_LEVEL_WARNING;
    }

    /**
     * Sets Log Level for Warning
     *
     * @param enable
     */
    public static void setWarning(boolean enable) {
        LogUtils.LOGUTILS_LEVEL_WARNING = enable;
    }

    /**
     * Gets Log Level for Warning
     */
    public static boolean getWarning() {
        return LogUtils.LOGUTILS_LEVEL_WARNING;
    }

    /**
     * Sets Log Level for Error
     *
     * @param enable
     */
    public static void setError(boolean enable) {
        LogUtils.LOGUTILS_LEVEL_ERROR = enable;
    }

    /**
     * Gets Log Level for Error
     */
    public static boolean getError() {
        return LogUtils.LOGUTILS_LEVEL_ERROR;
    }

    /**
     * Sets Log Level for Other
     *
     * @param enable
     */
    public static void setOther(boolean enable) {
        LogUtils.LOGUTILS_LEVEL_OTHER = enable;
    }

    /**
     * Gets Log Level for Other
     */
    public static boolean getOther() {
        return LogUtils.LOGUTILS_LEVEL_OTHER;
    }

    public static boolean getLog2File() {
        return LOGUTILS_LOG_IN_FILE;
    }

    public static void setLog2File(boolean enable) {
        LOGUTILS_LOG_IN_FILE = enable;
    }

    public static String getLogFilePath() {
        return LOGUTILS_LOGFILE;
    }

    public static void setLogFilePath(String path) {
        LOGUTILS_LOGFILE = path;
    }
}
