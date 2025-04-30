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
            out.append("\"").append(ds.getValue()).append("\": {");
            boolean firstInArray = true;
            for (Object o : list) {
                if (o instanceof DoubleStore<?, ?> listDs) {
                    if(listDs.getSecondValue() instanceof DoubleStore<?,?>){
                        out.append(listDs.getValue()).append(searchArray((DoubleStore<?, ?>) listDs.getSecondValue()));
                    }
                    else{
                        if(firstInArray){
                            firstInArray = false;
                        }
                        else{
                            out.append(",");
                        }
                        appendTypes(listDs, out);
                    }
                }
            }
            out.append("}");
        } else if (ds.getSecondValue() instanceof DoubleStore) {
            dragon_helper++;
            if (dragon_helper == 1) {
                out.append("\"").append(ds.getValue()).append(searchArray((DoubleStore<?,?>) ds.getSecondValue()));
            } else {
                out.append("\": {\"").append(ds.getValue()).append(searchArray((DoubleStore<?,?>) ds.getSecondValue())).append("}");
            }

        } else {
            appendTypes(ds, out);
            if (dragon_helper != 0) {
                out.append("}");
            }

            dragon_helper = 0;
        }

        return out.toString();
    }

    private static void appendTypes(DoubleStore<?, ?> ds, StringBuilder out) {
        if (TypeUtils.isInt((String) ds.getValue())) {
            out.append(ds.getValue()).append(":");
        } else {
            if (dragon_helper != 0) {
                out.append("\": {\"").append(ds.getValue()).append("\":");
            } else {
                out.append("\"").append(ds.getValue()).append("\":");
            }
        }

        if (TypeUtils.isInt("" + ds.getSecondValue())
                || TypeUtils.isFloat("" + ds.getSecondValue())
                || ds.getSecondValue() instanceof Boolean) {
            out.append(ds.getSecondValue());
        } else if (ds.getSecondValue() instanceof Integer[] in) {
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
        } else if (ds.getSecondValue() instanceof int[] in) {
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
        } else if (ds.getSecondValue() instanceof Float[] fl) {
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
        } else if (ds.getSecondValue() instanceof String[] st) {
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
            out.append("\"").append(ds.getSecondValue()).append("\"");
        }
    }
}
