package de.relluem94.rellulib.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     *
     * @param object Object
     * @return object as String
     */
    @Contract(pure = true)
    public static @NotNull String toString(Object object) {
        return "" + object;
    }

    /**
     *
     * @param input String Array
     * @return appends every String in one line
     */
    public static @NotNull String toString(String[] input) {
        return implode(0, input);
    }

    /**
     *
     * @param start int where to start in the args param
     * @param input String[] array of strings to implode
     * @return String with all words with offset of start
     */
    public static @NotNull String implode(int start, String @NotNull [] input) {
        StringBuilder message = new StringBuilder();
        for (int i = start; input.length > i; i++) {
            if (input[i] == null) {
                break;
            }
            message.append(input[i]).append(" ");
        }
        return message.toString();
    }

    /**
     *
     * @param input List Object
     * @return appends every Object in one line
     */
    public static @NotNull String toString(@NotNull List<Object> input) {
        StringBuilder output = new StringBuilder();
        for (Object o : input) {
            output.append(" ").append(o);
        }
        return output.toString();
    }

    /**
     *
     * [&lt;3] [NINJA] [PFEIL] [PFEIL2] [TICK] [X] [STAR] [PUNKT] <br> [BLUME]
     * [XD] [ACHTUNG] [MAIL] [STERN] [SUIT] [+] [KREIS] <br>
     * [SONNE] [RTM] [COPY] [OMEGA] [LAMBDA] [RUND] [^^] [TM]  <br>
     * <br>
     * etc..
     *
     * @param message String to replace Symbols from
     * @return String with Unicode chars
     */
    public static String replaceSymbols(String message) {
        for (Map.Entry<String, String> pair : symbols.entrySet()) {
            message = message.replace(pair.getKey(), pair.getValue());
        }
        return message;
    }

    private static final Map<String, String> symbols;

    static {
        symbols = Map.<String, String>ofEntries(
                Map.entry("[~]", "\u0020"), 
                Map.entry("[DICK]", "\u2593"), 
                Map.entry("[STROKES]", "\u2591"), 
                Map.entry("[PICKAXE]", "\u26CF"), 
                Map.entry("[HOT]", "\u2668"), 
                Map.entry("[SKULL]", "\u2620"), 
                Map.entry("[:)]", "\u263A"), 
                Map.entry("[:C]", "\u2639"), 
                Map.entry("[ATLANTIS]", "\u212B"), 
                Map.entry("[:D]", "\u1CE9"), 
                Map.entry("[CAM]", "\u1CC0"), 
                Map.entry("[CROSS]", "\u16ED"), 
                Map.entry("[<3]", "\u2764"), 
                Map.entry("[NINJA]", "             "), 
                Map.entry("[ARROW]", "\u279c"), 
                Map.entry("[TICK]", "\u2714"), 
                Map.entry("[X]", "\u2716"), 
                Map.entry("[STAR]", "\u2605"), 
                Map.entry("[DOT]", "\u25Cf"), 
                Map.entry("[FLOWER]", "\u273f"), 
                Map.entry("[XD]", "\u263b"), 
                Map.entry("[ATTENTION]", "\u26a0"), 
                Map.entry("[MAIL]", "\u2709"), 
                Map.entry("[ARROW2]", "\u27a4"), 
                Map.entry("[STAR2]", "\u2730"), 
                Map.entry("[SUIT]", "\u2666"), 
                Map.entry("[+]", "\u2726"), 
                Map.entry("[CIRCLE]", "\u25CF"), 
                Map.entry("[SUN]", "\u2739"), 
                Map.entry("[RTM]", "\u00AE"), 
                Map.entry("[COPY]", "\u00A9"), 
                Map.entry("[OMEGA]", "\u03A9"), 
                Map.entry("[LAMBDA]", "\u03BB"), 
                Map.entry("[ROUND]", "\u058D"), 
                Map.entry("[^^]", "\u0E05(\u0E4F.\u0E4F)\u0E05"), 
                Map.entry("[TM]", "\u2122"), 
                Map.entry("[No]", "\u2116"), 
                Map.entry("[H]", "\u210B"), 
                Map.entry("[h]", "\u210C"), 
                Map.entry("[L]", "\u2112"), 
                Map.entry("[l]", "\u2113"), 
                Map.entry("[R]", "\u211C"), 
                Map.entry("[B]", "\u212C"), 
                Map.entry("[E]", "\u2130"),
                Map.entry("[e]", "\u212F"),
                Map.entry("[F]", "\u2131"),
                Map.entry("[M]", "\u2133"),
                Map.entry("[1]", "\u2160"),
                Map.entry("[2]", "\u2161"),
                Map.entry("[3]", "\u2162"),
                Map.entry("[4]", "\u2163"),
                Map.entry("[5]", "\u2164"),
                Map.entry("[6]", "\u2165"),
                Map.entry("[7]", "\u2166"),
                Map.entry("[8]", "\u2167"),
                Map.entry("[9]", "\u2168"),
                Map.entry("[10]", "\u2169"),
                Map.entry("[11]", "\u216A"),
                Map.entry("[12]", "\u216B"),
                Map.entry("[RELOAD]", "\u21BA"),
                Map.entry("[>]", "\u00BB"),
                Map.entry("[<]", "\u00AB"),
                Map.entry("[1/2]", "\u00BD"),
                Map.entry("[1/4]", "\u00BC"),
                Map.entry("[B|]", "\u16D4\u16DA"),
                Map.entry("[xD]", "\u16DD\u16A6"));
    }
}