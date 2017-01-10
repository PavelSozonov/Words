package pavel.words;

import java.util.LinkedHashMap;

public class Storage {

	private int totalWordsAmount = 0;
	private int uniqueWordsAmount = 0;
	private LinkedHashMap<String, Integer> lhm = new LinkedHashMap<String, Integer>();
	
	public LinkedHashMap<String, Integer> getLhm() {
		return lhm;
	}

	/*
	 * Possible cases:
	 * 1) There is no word in hash map. Add this word, increase uniqueWordsAmount
	 * 2) There is word in the same form. Increase value of this word in the hash map
	 * 3) There is word in other form. 
	 * 3.1) There is small word, we add capitalized. Increase value of small. 
	 * 3.2) There is capitalized word in the hash map, we add small word. Replace capitalized to small, increase hash map value.
	 */
	public void push(String word) {
		totalWordsAmount++;

		String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1);

		if (capitalizedWord.equals(word)) { // Word begins from capital letter
			Integer value = lhm.get(word);
			if (value == null) { // There is no capitalized word in the storage
				value = lhm.get(word.toLowerCase());
				if (value == null) { // There is no small word in the storage
					uniqueWordsAmount++;
					lhm.put(word, 1);
					//System.out.println(word);
				} else { // There is small word in the storage
					lhm.put(word.toLowerCase(), value + 1);
				}
			} else { // There is capitalized word in the storage
				lhm.put(word, value + 1);
			}
		} else { // Word begins from small letter
			Integer value = lhm.get(word);
			if (value == null) { // There is no small word in the storage
				value = lhm.get(capitalizedWord);
				if (value == null) { // There is no capitalized word in the storage
					uniqueWordsAmount++;
					lhm.put(word, 1);
					//System.out.println(word);
				} else { // There is capitalized word in the storage
					lhm.remove(capitalizedWord);
					lhm.put(word, value + 1);
				}
			} else { // There is small word in the storage
				lhm.put(word, value + 1);
			}
		}
	}

	public int getTotalWordsAmount() {
		return totalWordsAmount;
	}

	public void setTotalWordsAmount(int totalWordsAmount) {
		this.totalWordsAmount = totalWordsAmount;
	}

	public int getUniqueWordsAmount() {
		return uniqueWordsAmount;
	}

	public void setUniqueWordsAmount(int uniqWordsAmount) {
		this.uniqueWordsAmount = uniqWordsAmount;
	}
	
}
