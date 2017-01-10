package pavel.words;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {

	public static void printMap(Map<String, Integer> map) {
		for (Entry<String, Integer> entry : map.entrySet()) {
			FileWriter2.writeLog(entry.getValue() + " " + entry.getKey());
		}		
	}

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {

        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
    
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Storage storage = new Storage();
		TextProcessor.ExtractWordsFromSubs(Constants.file, storage);
		System.out.println("Total: " + storage.getTotalWordsAmount());
		System.out.println("Unique: " + storage.getUniqueWordsAmount() + "\n");
		printMap(sortByComparator(storage.getLhm(), false));
	}
}
