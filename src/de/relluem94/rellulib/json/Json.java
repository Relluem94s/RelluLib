package de.relluem94.rellulib.json;

import java.util.List;

import de.relluem94.rellulib.stores.DoubleStore;
import de.relluem94.rellulib.utils.TypeUtils;

public class Json {

    private String json;

    public Json(List<DoubleStore> json) {
        this.json = convert(json);
    }

    public String getJson() {
        return json;
    }

    public static String toJson(List<DoubleStore> json) {
        return convert(json);
    }

    private static String convert(List<DoubleStore> json) {
        String out = "[{";
        int i = 0;
        for (DoubleStore ds : json) {
            i++;
            out = out + searcharray(ds);
            if (ds != null && json.size() != i) {
                out = out + ",";
            }
        }
        if (out.charAt(out.length() - 1) == ',') {
            out = out.substring(0, out.length() - 1);
        }
        return out + "}]";
    }

    private static int dragon_helper = 0;

    private static String searcharray(DoubleStore ds) {
        String out = "";
        if (ds != null) {

            if (ds.getSecondValue() instanceof DoubleStore) {
                dragon_helper++;
                if (dragon_helper == 1) {
                    out = out + "\"" + ds.getValue() + searcharray((DoubleStore) ds.getSecondValue());
                } else {
                    out = out + " => " + ds.getValue() + searcharray((DoubleStore) ds.getSecondValue());
                }

            } else {

                if (TypeUtils.isInt((String) ds.getValue())) {
                    out = out + ds.getValue() + ":";
                } else {
                    if (dragon_helper != 0) {
                        out = out + " => " + ds.getValue() + "\":";
                    } else {
                        out = out + "\"" + ds.getValue() + "\":";
                    }
                }
                if (TypeUtils.isInt("" + ds.getSecondValue())
                        || TypeUtils.isFloat("" + ds.getSecondValue())
                        || ds.getSecondValue() instanceof Boolean) {
                    out = out + ds.getSecondValue();
                } else if (ds.getSecondValue() instanceof Integer[]) {
                    out = out + "[";
                    Integer[] in = (Integer[]) ds.getSecondValue();
                    int b = 0;
                    for (int a : in) {
                        b++;
                        out = out + a;
                        if (in.length != b) {
                            out = out + ",";
                        }
                    }
                    out = out + "]";
                } else if (ds.getSecondValue() instanceof int[]) {
                    out = out + "[";
                    int[] in = (int[]) ds.getSecondValue();
                    int b = 0;
                    for (int a : in) {
                        b++;
                        out = out + a;
                        if (in.length != b) {
                            out = out + ",";
                        }
                    }
                    out = out + "]";
                } else if (ds.getSecondValue() instanceof Float[]) {
                    out = out + "[";
                    Float[] fl = (Float[]) ds.getSecondValue();
                    int b = 0;
                    for (float a : fl) {
                        b++;
                        out = out + a;
                        if (fl.length != b) {
                            out = out + ",";
                        }
                    }
                    out = out + "]";
                } else if (ds.getSecondValue() instanceof String[]) {
                    out = out + "[";
                    String[] st = (String[]) ds.getSecondValue();
                    int b = 0;
                    for (String a : st) {
                        b++;

                        if (dragon_helper != 0) {
                            out = out + a + "\"";
                        } else {
                            out = out + "\"" + a + "\"";
                        }

                        if (st.length != b) {
                            out = out + ",";
                        }
                    }
                    out = out + "]";
                } else {
                    out = out + "\"" + ds.getSecondValue() + "\"";
                }

                dragon_helper = 0;
            }
        }
        return out;
    }
}
