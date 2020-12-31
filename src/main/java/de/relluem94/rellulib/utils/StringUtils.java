package de.relluem94.rellulib.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtils {

    /**
     *
     * @param object
     * @return object as String
     */
    public static String toString(Object object) {
        return "" + object;
    }

    /**
     *
     * @param input String Array
     * @return appends every String in one line
     */
    public static String toString(String[] input) {
        return implode(0, input);
    }

    /**
     * 
     * @param start int where to start in the args param
     * @param input String[] array of strings to implode
     * @return String with all words with offset of start
     */
    public static String implode(int start, String[] input) {
        String message = "";
        for (int i = start; input.length > i; i++) {
            if (input[i] == null) {
                break;
            }
            message += input[i] + " ";
        }
        return message;
    }
    
    /**
     *
     * @param input List Object
     * @return appends every Object in one line
     */
    public static String toString(List<Object> input) {
        String output = "";
        for (int i = 0; i < input.size(); i++) {
            output = output + " " + input.get(i);
        }
        return output;
    }

    /**
     *
     * [&lt;3] [NINJA] [PFEIL] [PFEIL2] [TICK] [X] [STAR] [PUNKT] <br> [BLUME] [XD]
     * [ACHTUNG] [MAIL] [STERN] [SUIT] [+] [KREIS] <br>
     * [SONNE] [RTM] [COPY] [OMEGA] [LAMBDA] [RUND] [^^] [TM]  <br>
     * <br>
     * etc..
     * @param message String to replace Symbols from
     * @return String with unicode chars
     */
    public static String replaceSymbols(String message) {
        for (Map.Entry pair : symbols.entrySet()) {
            message = message.replace((String) pair.getKey(), (String) pair.getValue());
        }
        return message;
    }
    
    
    private static Map<String, String> symbols = new HashMap<>();
    static {
        Map<String, String> staticMap = new HashMap<>();
        staticMap.put("[~]", "\u0020");
        staticMap.put("[DICK]", "\u2593");
        staticMap.put("[STROKES]", "\u2591");
        staticMap.put("[PICKAXE]", "\u26CF");
        staticMap.put("[HOT]", "\u2668");
        staticMap.put("[SKULL]", "\u2620");
        staticMap.put("[:)]", "\u263A");
        staticMap.put("[:C]", "\u2639");
        staticMap.put("[ATLANTIS]", "\u212B");
        staticMap.put("[:D]", "\u1CE9");
        staticMap.put("[CAM]", "\u1CC0");
        staticMap.put("[CROSS]", "\u16ED");
        staticMap.put("[<3]", "\u2764");
        staticMap.put("[NINJA]", "             ");
        staticMap.put("[ARROW]", "\u279c");
        staticMap.put("[TICK]", "\u2714");
        staticMap.put("[X]", "\u2716");
        staticMap.put("[STAR]", "\u2605");
        staticMap.put("[DOT]", "\u25Cf");
        staticMap.put("[FLOWER]", "\u273f");
        staticMap.put("[XD]", "\u263b");
        staticMap.put("[ATTENTION]", "\u26a0");
        staticMap.put("[MAIL]", "\u2709");
        staticMap.put("[ARROW2]", "\u27a4");
        staticMap.put("[STAR2]", "\u2730");
        staticMap.put("[SUIT]", "\u2666");
        staticMap.put("[+]", "\u2726");
        staticMap.put("[CIRCLE]", "\u25CF");
        staticMap.put("[SUN]", "\u2739");
        staticMap.put("[RTM]", "\u00AE");
        staticMap.put("[COPY]", "\u00A9");
        staticMap.put("[OMEGA]", "\u03A9");
        staticMap.put("[LAMBDA]", "\u03BB");
        staticMap.put("[ROUND]", "\u058D");
        staticMap.put("[^^]", "\u0E05(\u0E4F.\u0E4F)\u0E05");
        staticMap.put("[TM]", "\u2122");
        staticMap.put("[No]", "\u2116");
        staticMap.put("[H]", "\u210B");
        staticMap.put("[h]", "\u210C");
        staticMap.put("[L]", "\u2112");
        staticMap.put("[l]", "\u2113");
        staticMap.put("[R]", "\u211C");
        staticMap.put("[B]", "\u212C");
        staticMap.put("[E]", "\u2130");
        staticMap.put("[e]", "\u212F");
        staticMap.put("[F]", "\u2131");
        staticMap.put("[M]", "\u2133");
        staticMap.put("[1]", "\u2160");
        staticMap.put("[2]", "\u2161");
        staticMap.put("[3]", "\u2162");
        staticMap.put("[4]", "\u2163");
        staticMap.put("[5]", "\u2164");
        staticMap.put("[6]", "\u2165");
        staticMap.put("[7]", "\u2166");
        staticMap.put("[8]", "\u2167");
        staticMap.put("[9]", "\u2168");
        staticMap.put("[10]", "\u2169");
        staticMap.put("[11]", "\u216A");
        staticMap.put("[12]", "\u216B");
        staticMap.put("[RELOAD]", "\u21BA");
        staticMap.put("[>]", "\u00BB");
        staticMap.put("[<]", "\u00AB");
        staticMap.put("[1/2]", "\u00BD");
        staticMap.put("[1/4]", "\u00BC");
        staticMap.put("[B|]", "\u16D4\u16DA");
        staticMap.put("[xD]", "\u16DD\u16A6");
        symbols = Collections.unmodifiableMap(staticMap);
    }
    
}
