package de.relluem94.rellulib.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import de.relluem94.rellulib.FixedSizeList;
import de.relluem94.rellulib.json.Json;
import de.relluem94.rellulib.stores.DoubleStore;
import de.relluem94.rellulib.utils.FileUtils;
import de.relluem94.rellulib.utils.LogUtils;

public class TestClass2 {
	
	public static void main(String[] args){
		FixedSizeList<DoubleStore> stores = new FixedSizeList<DoubleStore>(5);
		stores.set(0, new DoubleStore("Firstname","Johannes"));
		stores.set(1, new DoubleStore("Lastname","Müller"));
		stores.set(2, new DoubleStore("Age",22));
		stores.set(3, new DoubleStore("Married",false));
		stores.set(4, new DoubleStore("Size",1.94F));
		try {
			FileUtils.writeDoubleStoreTextFile(new File("C:/Users/Rellu/RelluEngine2/test.txt"), stores);
			LogUtils.log(Json.toJson(FileUtils.readDoubleStoreTextFile(new File("C:/Users/Rellu/RelluEngine2/test.txt").getAbsolutePath(), Charset.forName("UTF-8"))).replaceAll("\r", "").replaceAll("\n", ""));
		} catch (IOException e) {
			e.printStackTrace();
		}
		LogUtils.log(Json.toJson(stores));
	}
}
