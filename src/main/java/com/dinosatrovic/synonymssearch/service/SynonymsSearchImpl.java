package com.dinosatrovic.synonymssearch.service;

import com.dinosatrovic.synonymssearch.controller.model.SynonymsInput;
import com.dinosatrovic.synonymssearch.data.SynonymsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SynonymsSearchImpl implements SynonymsService {

    SynonymsRepository synonymsRepository;

    @Autowired
    SynonymsSearchImpl(SynonymsRepository synonymsRepository) {
        this.synonymsRepository = synonymsRepository;
    }

    @Override
    public void addSynonyms(String word, List<String> synonyms) {
        synonymsRepository.saveSynonyms(word, synonyms);
    }

    @Override
    public List<String> searchSynonyms(String word, Integer depth) {
        return synonymsRepository.getSynonymsByWord(word, depth);
    }

    @Override
    public void addSynonyms(List<SynonymsInput> synonymsInput) {
       for(SynonymsInput singleSynonymsInput : synonymsInput) {
           synonymsRepository.saveSynonyms(singleSynonymsInput.getWord(), singleSynonymsInput.getSynonyms());
       }
    }
}
