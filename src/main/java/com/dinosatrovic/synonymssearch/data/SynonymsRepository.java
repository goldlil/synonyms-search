package com.dinosatrovic.synonymssearch.data;

import java.util.List;

public interface SynonymsRepository {
    List<String> getSynonymsByWord(String word, Integer depth);
    void saveSynonyms(String word, List<String> synonyms);
}
