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
                Map.entry("[~]", " "),
                Map.entry("[DICK]", "▓"),
                Map.entry("[STROKES]", "░"),
                Map.entry("[PICKAXE]", "⛏"),
                Map.entry("[HOT]", "♨"),
                Map.entry("[SKULL]", "☠"),
                Map.entry("[:)]", "☺"),
                Map.entry("[:C]", "☹"),
                Map.entry("[ATLANTIS]", "Å"),
                Map.entry("[:D]", "ᳩ"),
                Map.entry("[CAM]", "᳀"),
                Map.entry("[CROSS]", "᛭"),
                Map.entry("[<3]", "❤"),
                Map.entry("[NINJA]", "             "), 
                Map.entry("[ARROW]", "➜"),
                Map.entry("[TICK]", "✔"),
                Map.entry("[X]", "✖"),
                Map.entry("[STAR]", "★"),
                Map.entry("[DOT]", "●"),
                Map.entry("[FLOWER]", "✿"),
                Map.entry("[XD]", "☻"),
                Map.entry("[ATTENTION]", "⚠"),
                Map.entry("[MAIL]", "✉"),
                Map.entry("[ARROW2]", "➤"),
                Map.entry("[STAR2]", "✰"),
                Map.entry("[SUIT]", "♦"),
                Map.entry("[+]", "✦"),
                Map.entry("[CIRCLE]", "●"),
                Map.entry("[SUN]", "✹"),
                Map.entry("[RTM]", "®"),
                Map.entry("[COPY]", "©"),
                Map.entry("[OMEGA]", "Ω"),
                Map.entry("[LAMBDA]", "λ"),
                Map.entry("[ROUND]", "֍"),
                Map.entry("[^^]", "ฅ(๏.๏)ฅ"),
                Map.entry("[TM]", "™"),
                Map.entry("[No]", "№"),
                Map.entry("[H]", "ℋ"),
                Map.entry("[h]", "ℌ"),
                Map.entry("[L]", "ℒ"),
                Map.entry("[l]", "ℓ"),
                Map.entry("[R]", "ℜ"),
                Map.entry("[B]", "ℬ"),
                Map.entry("[E]", "ℰ"),
                Map.entry("[e]", "ℯ"),
                Map.entry("[F]", "ℱ"),
                Map.entry("[M]", "ℳ"),
                Map.entry("[1]", "Ⅰ"),
                Map.entry("[2]", "Ⅱ"),
                Map.entry("[3]", "Ⅲ"),
                Map.entry("[4]", "Ⅳ"),
                Map.entry("[5]", "Ⅴ"),
                Map.entry("[6]", "Ⅵ"),
                Map.entry("[7]", "Ⅶ"),
                Map.entry("[8]", "Ⅷ"),
                Map.entry("[9]", "Ⅸ"),
                Map.entry("[10]", "Ⅹ"),
                Map.entry("[11]", "Ⅺ"),
                Map.entry("[12]", "Ⅻ"),
                Map.entry("[RELOAD]", "↺"),
                Map.entry("[>]", "»"),
                Map.entry("[<]", "«"),
                Map.entry("[1/2]", "½"),
                Map.entry("[1/4]", "¼"),
                Map.entry("[B|]", "ᛔᛚ"),
                Map.entry("[xD]", "ᛝᚦ"));
    }
}