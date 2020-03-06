package com.dinosatrovic.synonymssearch.service;

import com.dinosatrovic.synonymssearch.controller.model.SynonymsInput;

import java.util.List;

public interface SynonymsService {

    List<String> searchSynonyms(String word, Integer depth);
    void addSynonyms(String word, List<String> synonyms);
    void addSynonyms(List<SynonymsInput> synonymsInput);
}
