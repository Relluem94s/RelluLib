package de.relluem94.rellulib.utils;

import java.util.List;

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
        String output = "";
        for (int i = 0; i < input.length; i++) {
            output = output + " " + input[i];
        }
        return output;
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
     * [<3] [NINJA] [PFEIL] [PFEIL2] [TICK] [X] [STAR] [PUNKT] <br> [BLUME] [XD]
     * [ACHTUNG] [MAIL] [STERN] [SUIT] [+] [KREIS] <br>
     * [SONNE] [RTM] [COPY] [OMEGA] [LAMBDA] [RUND] [^^] [TM]  <br>
     * <br>
     * etc..
     */
    public static String symbole(String sym) {
        sym = sym.replaceAll("[<3]", "\u2764");
        sym = sym.replaceAll("[NINJA]", "             ");
        sym = sym.replaceAll("[PFEIL2]", "\u279c");
        sym = sym.replaceAll("[TICK]", "\u2714");
        sym = sym.replaceAll("[X]", "\u2716");
        sym = sym.replaceAll("[STAR]", "\u2605");
        sym = sym.replaceAll("[PUNKT]", "\u25Cf");
        sym = sym.replaceAll("[BLUME]", "\u273f");
        sym = sym.replaceAll("[XD]", "\u263b");
        sym = sym.replaceAll("[ACHTUNG]", "\u26a0");
        sym = sym.replaceAll("[MAIL]", "\u2709");
        sym = sym.replaceAll("[PFEIL]", "\u27a4");
        sym = sym.replaceAll("[STERN]", "\u2730");
        sym = sym.replaceAll("[SUIT]", "\u2666");
        sym = sym.replaceAll("[+]", "\u2726");
        sym = sym.replaceAll("[KREIS]", "\u25CF");
        sym = sym.replaceAll("[SONNE]", "\u2739");
        sym = sym.replaceAll("[RTM]", "\u00AE");
        sym = sym.replaceAll("[COPY]", "\u00A9");
        sym = sym.replaceAll("[OMEGA]", "\u03A9");
        sym = sym.replaceAll("[LAMBDA]", "\u03BB");
        sym = sym.replaceAll("[RUND]", "\u058D");
        sym = sym.replaceAll("[^^]", "\u0E05(\u0E4F.\u0E4F)\u0E05");
        sym = sym.replaceAll("[TM]", "\u2122");
        sym = sym.replaceAll("[No]", "\u2116");
        sym = sym.replaceAll("[H]", "\u210B");
        sym = sym.replaceAll("[h]", "\u210C");
        sym = sym.replaceAll("[L]", "\u2112");
        sym = sym.replaceAll("[l]", "\u2113");
        sym = sym.replaceAll("[R]", "\u211C");
        sym = sym.replaceAll("[B]", "\u212C");
        sym = sym.replaceAll("[E]", "\u2130");
        sym = sym.replaceAll("[e]", "\u212F");
        sym = sym.replaceAll("[F]", "\u2131");
        sym = sym.replaceAll("[M]", "\u2133");
        sym = sym.replaceAll("[1]", "\u2160");
        sym = sym.replaceAll("[2]", "\u2161");
        sym = sym.replaceAll("[3]", "\u2162");
        sym = sym.replaceAll("[4]", "\u2163");
        sym = sym.replaceAll("[5]", "\u2164");
        sym = sym.replaceAll("[6]", "\u2165");
        sym = sym.replaceAll("[7]", "\u2166");
        sym = sym.replaceAll("[8]", "\u2167");
        sym = sym.replaceAll("[9]", "\u2168");
        sym = sym.replaceAll("[10]", "\u2169");
        sym = sym.replaceAll("[11]", "\u216A");
        sym = sym.replaceAll("[12]", "\u216B");
        sym = sym.replaceAll("[RELOAD]", "\u21BA");
        sym = sym.replaceAll("[>]", "\u00BB");
        sym = sym.replaceAll("[<]", "\u00AB");
        sym = sym.replaceAll("[1/2]", "\u00BD");
        sym = sym.replaceAll("[1/4]", "\u00BC");
        sym = sym.replaceAll("[B|]", "\u16D4\u16DA");
        sym = sym.replaceAll("[xD]", "\u16DD\u16A6");
        sym = sym.replaceAll("[CROSS]", "\u16ED");
        sym = sym.replaceAll("[CAM]", "\u1CC0");
        sym = sym.replaceAll("[:D]", "\u1CE9");
        sym = sym.replaceAll("[ATLANTIS]", "\u212B");
        sym = sym.replaceAll("[:C]", "\u2639");
        sym = sym.replaceAll("[:)]", "\u263A");
        sym = sym.replaceAll("[SKULL]", "\u2620");
        sym = sym.replaceAll("[HOT]", "\u2668");
        sym = sym.replaceAll("[PICKAXE]", "\u26CF");
        sym = sym.replaceAll("[STRICHE]", "\u2591");
        sym = sym.replaceAll("[DICK]", "\u2593");
        sym = sym.replaceAll("_", "\u0020");
        sym = sym.replaceAll("~", "_");

        return sym;
    }
}
