package server;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
	String currentLang;
	
	@SuppressWarnings("rawtypes")
	Map<String, Map> dictionary = new HashMap<>();
	public Dictionary() {
		this.currentLang = "EN->KH";
		Map<String, String> enWords = new HashMap<>();
		enWords.put("apple", "ផ្លែប៉ោម");
		enWords.put("book", "សៀវភៅ");
		this.dictionary.put("EN->KH", enWords);
		Map<String, String> khWords = new HashMap<>();
		khWords.put("ផ្លែប៉ោម", "apple");
		khWords.put("សៀវភៅ", "book");
		this.dictionary.put("KH->EN", khWords);
	}
	
	public String getLang() {
		return this.currentLang;
	}
	public void switchLang() {
		if(this.currentLang.equals("EN->KH")) {
			this.currentLang = "KH->EN";
		} else {
			this.currentLang = "EN->KH";		
		}
	}
	
	public String search(String keyword) {
		@SuppressWarnings("unchecked")
		Map<String, String> words =  this.dictionary.get(this.currentLang);
		String word = words.get(keyword);
		if(word == null) {
			if(this.currentLang.equals("EN->KH")) {
				return "Not found";
			} else {
				return "មិនមានក្នុងវចនានុក្រម";
			}
		} else {
			return word;
		}
	}
}
