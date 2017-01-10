package pavel.words;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class FileWriter2 {

	public static void writeLog(String message) {
		writeLog(0, message);
	}

	public static void writeLog(int nestingLevel, String message) {
		String indent = "";
		if (nestingLevel > 0) {
			for (int i = 0; i < nestingLevel; i++) { indent += "    "; }
		}
		String filePath = Constants.outputFile;
		try {
			FileWriter fw = new FileWriter(filePath, true);
	        fw.write(indent + message);
            fw.append('\n');  
            fw.flush();
            fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String dateTimeToString() {
		long curTime = System.currentTimeMillis();
		String curStringDate = new SimpleDateFormat("dd.MM.yyyy_HH-mm-ss-SSS").format(curTime); 
		return curStringDate;
	}
	
	public static void writeJson(String json) {
		String filePath = "/home/pavel/Projects/JSONs/CMemvit_" + dateTimeToString() + ".json";

		try {
			FileWriter fileWriter = new FileWriter(filePath, false);
			fileWriter.write(json);
			fileWriter.flush();
            fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
