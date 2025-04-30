package de.relluem94.rellulib.json;

import java.util.List;

import de.relluem94.rellulib.stores.DoubleStore;
import de.relluem94.rellulib.utils.TypeUtils;

public class Json {

    private static class ArraySearchResult {
        private int round;
        private String output;

        public ArraySearchResult(int round, String output){
            this.round = round;
            this.output = output;
        }

        public int getRound() {
            return round;
        }

        public void setRound(int round) {
            this.round = round;
        }

        public String getOutput() {
            return output;
        }

        public void setOutput(String output) {
            this.output = output;
        }

        @Override
        public String toString() {
            return output;
        }
    }

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
            ArraySearchResult arraySearchResult = searchArray(new ArraySearchResult(0, ""), ds);
            out.append(arraySearchResult.getOutput());
            if (ds != null && json.size() != i) {
                out.append(",");
            }
        }
        return out + "}]";
    }

    private static ArraySearchResult searchArray(ArraySearchResult arraySearchResult, DoubleStore<?,?> ds) {
        StringBuilder out = new StringBuilder();
        if (ds == null) {
            return new ArraySearchResult(0, "");
        }

        if (ds.getSecondValue() instanceof List<?> list) {
            out.append("\"").append(ds.getValue()).append("\":{");
            boolean firstInArray = true;
            for (Object o : list) {
                if (o instanceof DoubleStore<?, ?> listDs) {
                    if(listDs.getSecondValue() instanceof DoubleStore<?,?>){
                        out.append("\"").append(listDs.getValue()).append("\":{").append(searchArray(arraySearchResult, (DoubleStore<?, ?>) listDs.getSecondValue())).append("}");
                    }
                    else{
                        firstInArray = appendComma(firstInArray, out);
                        appendDoubleStore(listDs, out, arraySearchResult.getRound());
                    }
                }
                else{
                    firstInArray = appendComma(firstInArray, out);
                    appendType(o, out);
                }
            }
            out.append("}");
        } else if (ds.getSecondValue() instanceof DoubleStore) {
            arraySearchResult.setRound(arraySearchResult.getRound()+1);
            if (arraySearchResult.getRound() == 1) {
                out.append("\"").append(ds.getValue()).append(searchArray(arraySearchResult, (DoubleStore<?,?>) ds.getSecondValue()));
            } else {
                out.append("\":{\"").append(ds.getValue()).append(searchArray(arraySearchResult, (DoubleStore<?,?>) ds.getSecondValue())).append("}");
            }

        } else {
            appendDoubleStore(ds, out, arraySearchResult.getRound());
            if (arraySearchResult.getRound() != 0) {
                out.append("}");
            }

            arraySearchResult.setRound(0);
        }

        System.out.println("pre " + arraySearchResult.output);
        arraySearchResult.setOutput(out.toString());
        System.out.println("post " + arraySearchResult.output);
        System.out.println(out);
        return arraySearchResult;
    }

    private static void appendDoubleStore(DoubleStore<?, ?> ds, StringBuilder out, int round) {
        if (TypeUtils.isInt("" + ds.getValue())) {
            out.append(ds.getValue()).append(":");
        } else {
            if (round != 0) {
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
                out.append("\"").append(a).append("\"");
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