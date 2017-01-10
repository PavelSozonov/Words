package pavel.words;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class TextProcessor {
	
	private static String removeEscapeSequence(String line) {
		if (!line.contains("\\")) return line;
		int index = line.indexOf('\\');
		return line.substring(0, index);
	}
	
	public static void ExtractWordsFromSubs(String filename, Storage storage) throws FileNotFoundException, IOException {

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;

			//boolean serviceInfoRead = false;
			boolean serviceInfoRead = true;
			while ((line = br.readLine()) != null) {
				
				if (serviceInfoRead) {
					LinkedList<String> words = TextProcessor.parse(line);
					for (String word : words) storage.push(word);
				}
				
				if (line.equals(Constants.titlesHeader)) serviceInfoRead = true;
			}	
		}
	}
	
	private static LinkedList<String> parse(String line) {
		String parser = line;
		parser = parser.replace('[', ' ');
		parser = parser.replace(']', ' ');
		parser = parser.replaceAll("[(),.?<>&+:;!\"_]", " ");
		parser = parser.replaceAll(" - ", " ");
		Scanner scanner = new Scanner(parser);
		LinkedList<String> wordList = new LinkedList<String>();
		while (scanner.hasNext()) {
			String word = scanner.next();
			word = removeEscapeSequence(word);
			if (!word.matches(".*[0123456789].*") && !word.equals("")) {
				wordList.add(word);
			}
		}
		return wordList;
	}
			
}
