package de.relluem94.rellulib.json;

import de.relluem94.rellulib.FixedSizeList;
import de.relluem94.rellulib.stores.DoubleStore;
import de.relluem94.rellulib.utils.FileUtils;
import de.relluem94.rellulib.utils.InfoUtils;
import de.relluem94.rellulib.utils.LogUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;


class JsonTest {
    @Test
    public void jsonTest() {
        FixedSizeList<DoubleStore<?,?>> stores = new FixedSizeList<>(12);
        stores.set(0, new DoubleStore<>("Firstname", "Elon"));
        stores.set(1, new DoubleStore<>("Lastname", "Musk"));
        stores.set(2, new DoubleStore<>("Age", 42));
        stores.set(3, new DoubleStore<>("Married", false));
        stores.set(4, new DoubleStore<>("Size", 69.420F));
        String[] lang = {"Deutsch", "Englisch", "Java", "PHP", "MySQL"};
        Integer[] num = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] num2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Float[] num3 = {0.1f, 1.2f, 2.3f, 3.4f, 4.5f, 5.6f, 6.0f, 7f, 81.331f, 9.001f};
        stores.set(5, new DoubleStore<>("Languages", lang));
        stores.set(6, new DoubleStore<>("Numbers", num));
        stores.set(7, new DoubleStore<>("Numbers2", num2));
        stores.set(8, new DoubleStore<>("Numbers3", num3));
        stores.set(9, new DoubleStore<>("TestDoubleStores", new DoubleStore<>("Test2", new DoubleStore<>("Test3", num))));
        stores.set(10, new DoubleStore<>("TestListNumbers", List.of(new DoubleStore<>("Test1", num), new DoubleStore<>("Test2", num))));
        stores.set(11, new DoubleStore<>("TestListsMixed", List.of(new DoubleStore<>("Test1", num3), new DoubleStore<>("Test2", num), new DoubleStore<>("Test3", "something else"), new DoubleStore<>("Test4", true))));
        try {
            FileUtils.writeDoubleStoreTextFile(new File(InfoUtils.userHome() + "/RelluLib.txt"), stores);
            Json json = new Json(FileUtils.readDoubleStoreTextFile(new File(InfoUtils.userHome() + "/RelluLib.txt").getAbsolutePath(), Charset.defaultCharset()));
            String jsonString = Json.toJson(FileUtils.readDoubleStoreTextFile(new File(InfoUtils.userHome() + "/RelluLib.txt").getAbsolutePath(), Charset.defaultCharset()));

            String hardcodedJsonString = "[{\"Firstname\":\"Elon\",\"Lastname\":\"Musk\",\"Age\":42,\"Married\":false,\"Size\":69.42,\"Languages\":[\"Deutsch\",\"Englisch\",\"Java\",\"PHP\",\"MySQL\"],\"Numbers\":[0,1,2,3,4,5,6,7,8,9],\"Numbers2\":[0,1,2,3,4,5,6,7,8,9],\"Numbers3\":[0.1,1.2,2.3,3.4,4.5,5.6,6.0,7.0,81.331,9.001],\"TestDoubleStores\": {\"Test2\": {\"Test3\":[0,1,2,3,4,5,6,7,8,9]}},\"TestListNumbers\": {\"Test1\":[0,1,2,3,4,5,6,7,8,9],\"Test2\":[0,1,2,3,4,5,6,7,8,9]},\"TestListsMixed\": {\"Test1\":[0.1,1.2,2.3,3.4,4.5,5.6,6.0,7.0,81.331,9.001],\"Test2\":[0,1,2,3,4,5,6,7,8,9],\"Test3\":\"something else\",\"Test4\":true}}]";

            Assertions.assertEquals(json.getJson(), jsonString);
            Assertions.assertEquals(hardcodedJsonString, Json.toJson(stores));

            LogUtils.test(jsonString);
        } catch (IOException e) {
            LogUtils.error(e.getMessage());
        }
        LogUtils.log(Json.toJson(stores));
    }

    @Test
    public void jsonTestOnlyOneEntry() {
        FixedSizeList<DoubleStore<?,?>> stores = new FixedSizeList<>(1);
        stores.set(0, new DoubleStore<>("Firstname", "Elon"));

        String jsonString = Json.toJson(stores);
        Assertions.assertNotNull(jsonString);
        Assertions.assertEquals("[{\"Firstname\":\"Elon\"}]", jsonString);
        LogUtils.info(jsonString);
    }

    @Test
    public void jsonTestOnlyOneEntryIsNull() {
        FixedSizeList<DoubleStore<?,?>> stores = new FixedSizeList<>(1);
        stores.set(0, null);

        String jsonString = Json.toJson(stores);
        Assertions.assertNotNull(jsonString);
        Assertions.assertEquals("[{}]", jsonString);
        LogUtils.info(jsonString);
    }

    @Test
    public void jsonTestTwoEntries() {
        FixedSizeList<DoubleStore<?,?>> stores = new FixedSizeList<>(2);
        stores.set(0, new DoubleStore<>("Firstname", "Elon"));
        stores.set(1, new DoubleStore<>("Lastname", "Musk"));

        String jsonString = Json.toJson(stores);
        Assertions.assertNotNull(jsonString);
        Assertions.assertEquals("[{\"Firstname\":\"Elon\",\"Lastname\":\"Musk\"}]", jsonString);
        LogUtils.info(jsonString);
    }

    @Test
    public void jsonTestOnlyOneEntryInteger() {
        FixedSizeList<DoubleStore<?,?>> stores = new FixedSizeList<>(1);
        stores.set(0, new DoubleStore<>(1994, "Birthyear"));

        String jsonString = Json.toJson(stores);
        Assertions.assertNotNull(jsonString);
        Assertions.assertEquals("[{1994:\"Birthyear\"}]", jsonString);
        LogUtils.info(jsonString);
    }
}