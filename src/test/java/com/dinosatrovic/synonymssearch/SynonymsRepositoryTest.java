package com.dinosatrovic.synonymssearch;

import com.dinosatrovic.synonymssearch.data.SynonymsGraphDataStorage;
import com.dinosatrovic.synonymssearch.data.SynonymsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SynonymsRepositoryTest {

    private SynonymsRepository synonymsRepository;

    @BeforeEach
    public void init() {
        synonymsRepository = new SynonymsGraphDataStorage();
    }

    @Test
    public void findSynonyms_depthIsOne() {
        synonymsRepository.saveSynonyms("dummy", Arrays.asList("dummy1", "dummy2", "dummy3"));
        synonymsRepository.saveSynonyms("dummy3", Arrays.asList("dummy5", "dummy6"));
        synonymsRepository.saveSynonyms("dummy", Arrays.asList("dummy1"));

        List<String> result = synonymsRepository.getSynonymsByWord("dummy", 1);

        Assertions.assertEquals(Arrays.asList("dummy1", "dummy2", "dummy3"), result);
    }

    @Test
    public void findSynonyms_depthIsTwo() {
        synonymsRepository.saveSynonyms("dummy", Arrays.asList("dummy1", "dummy2", "dummy3"));
        synonymsRepository.saveSynonyms("dummy3", Arrays.asList("dummy5", "dummy6"));
        synonymsRepository.saveSynonyms("dummy2", Arrays.asList("dummy7", "dummy8"));
        synonymsRepository.saveSynonyms("dummy5", Arrays.asList("dummy9", "dummy10"));


        List<String> result = synonymsRepository.getSynonymsByWord("dummy", 2);
        Assertions.assertEquals(Arrays.asList("dummy1", "dummy2", "dummy3", "dummy5", "dummy6", "dummy7", "dummy8" ),
                result);
    }

    @Test
    public void findSynonyms_depthIsThree() {
        synonymsRepository.saveSynonyms("dummy", Arrays.asList("dummy1", "dummy2", "dummy3"));
        synonymsRepository.saveSynonyms("dummy3", Arrays.asList("dummy5", "dummy6"));
        synonymsRepository.saveSynonyms("dummy2", Arrays.asList("dummy7", "dummy8"));
        synonymsRepository.saveSynonyms("dummy5", Arrays.asList("dummy9", "dummy10"));


        List<String> result = synonymsRepository.getSynonymsByWord("dummy", 3);
        Assertions.assertEquals(Arrays.asList("dummy1", "dummy2", "dummy3", "dummy5", "dummy6", "dummy7", "dummy8",
                "dummy9", "dummy10" ), result);
    }

}
