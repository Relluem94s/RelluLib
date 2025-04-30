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


class JsonTest {
    @Test
    public void jsonTest() {
        FixedSizeList<DoubleStore<?,?>> stores = new FixedSizeList<>(10);
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
        stores.set(9, new DoubleStore<>("Test", new DoubleStore<>("Test2", new DoubleStore<>("Test3", num))));
        try {
            FileUtils.writeDoubleStoreTextFile(new File(InfoUtils.userHome() + "/RelluLib.txt"), stores);
            Json json = new Json(FileUtils.readDoubleStoreTextFile(new File(InfoUtils.userHome() + "/RelluLib.txt").getAbsolutePath(), Charset.defaultCharset()));
            String jsonString = Json.toJson(FileUtils.readDoubleStoreTextFile(new File(InfoUtils.userHome() + "/RelluLib.txt").getAbsolutePath(), Charset.defaultCharset()));

            Assertions.assertEquals(json.getJson(), jsonString);

            LogUtils.test(jsonString);
        } catch (IOException e) {
            LogUtils.error(e.getMessage());
        }
        LogUtils.log(Json.toJson(stores));
    }
}