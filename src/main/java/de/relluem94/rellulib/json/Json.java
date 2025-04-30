package de.relluem94.rellulib.json;

import java.util.List;

import de.relluem94.rellulib.stores.DoubleStore;
import de.relluem94.rellulib.utils.TypeUtils;

public class Json {

    private final String json;

    public Json(List<DoubleStore<?,?>> json) {
        this.json = convert(json);
    }

    public String getJson() {
        return json;
    }

    public static String toJson(List<DoubleStore<?,?>> json) {
        return convert(json);
    }

    private static String convert(List<DoubleStore<?,?>> json) {
        StringBuilder out = new StringBuilder("[{");
        int i = 0;
        for (DoubleStore<?,?> ds : json) {
            i++;
            out.append(searchArray(ds));
            if (ds != null && json.size() != i) {
                out.append(",");
            }
        }
        return out + "}]";
    }

    private static int dragon_helper = 0;

    private static String searchArray(DoubleStore<?,?> ds) {
        StringBuilder out = new StringBuilder();
        if (ds == null) {
            return out.toString();
        }

        if (ds.getSecondValue() instanceof List<?> list) {
            out.append("\"").append(ds.getValue()).append("\":{");
            boolean firstInArray = true;
            for (Object o : list) {
                if (o instanceof DoubleStore<?, ?> listDs) {
                    if(listDs.getSecondValue() instanceof DoubleStore<?,?>){
                        out.append("\"").append(listDs.getValue()).append("\":{").append(searchArray((DoubleStore<?, ?>) listDs.getSecondValue())).append("}");
                    }
                    else{
                        firstInArray = appendComma(firstInArray, out);
                        appendDoubleStore(listDs, out);
                    }
                }
                else{
                    firstInArray = appendComma(firstInArray, out);
                    appendType(o, out);
                }
            }
            out.append("}");
        } else if (ds.getSecondValue() instanceof DoubleStore) {
            dragon_helper++;
            if (dragon_helper == 1) {
                out.append("\"").append(ds.getValue()).append(searchArray((DoubleStore<?,?>) ds.getSecondValue()));
            } else {
                out.append("\":{\"").append(ds.getValue()).append(searchArray((DoubleStore<?,?>) ds.getSecondValue())).append("}");
            }

        } else {
            appendDoubleStore(ds, out);
            if (dragon_helper != 0) {
                out.append("}");
            }

            dragon_helper = 0;
        }

        return out.toString();
    }

    private static void appendDoubleStore(DoubleStore<?, ?> ds, StringBuilder out) {
        if (TypeUtils.isInt("" + ds.getValue())) {
            out.append(ds.getValue()).append(":");
        } else {
            if (dragon_helper != 0) {
                out.append("\":{\"").append(ds.getValue()).append("\":");
            } else {
                out.append("\"").append(ds.getValue()).append("\":");
            }
        }

        appendType(ds.getSecondValue(), out);
    }

    private static void appendType(Object o, StringBuilder out) {
        if (TypeUtils.isInt("" + o)
                || TypeUtils.isFloat("" + o)
                || o instanceof Boolean) {
            out.append(o);
        } else if (o instanceof Integer[] in) {
            out.append("[");
            int b = 0;
            for (int a : in) {
                b++;
                out.append(a);
                if (in.length != b) {
                    out.append(",");
                }
            }
            out.append("]");
        } else if (o instanceof int[] in) {
            out.append("[");
            int b = 0;
            for (int a : in) {
                b++;
                out.append(a);
                if (in.length != b) {
                    out.append(",");
                }
            }
            out.append("]");
        } else if (o instanceof Float[] fl) {
            out.append("[");
            int b = 0;
            for (float a : fl) {
                b++;
                out.append(a);
                if (fl.length != b) {
                    out.append(",");
                }
            }
            out.append("]");
        } else if (o instanceof String[] st) {
            out.append("[");
            int b = 0;
            for (String a : st) {
                b++;

                if (dragon_helper != 0) {
                    out.append(a).append("\"");
                } else {
                    out.append("\"").append(a).append("\"");
                }

                if (st.length != b) {
                    out.append(",");
                }
            }
            out.append("]");
        } else {
            out.append("\"").append(o).append("\"");
        }
    }

    private static boolean appendComma(boolean firstInArray, StringBuilder out){
        if (!firstInArray) {
            out.append(",");
        }
        return false;
    }
}
