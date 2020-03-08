package com.dinosatrovic.synonymssearch.controller;

import com.dinosatrovic.synonymssearch.api.SynonymsSearchApi;
import com.dinosatrovic.synonymssearch.model.SynonymsInput;
import com.dinosatrovic.synonymssearch.service.SynonymsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SynonymsController implements SynonymsSearchApi {

    private SynonymsService synonymsService;

    @Autowired
    SynonymsController(SynonymsService synonymsService) {
        this.synonymsService = synonymsService;
    }

    public ResponseEntity<Void> addSynonyms(SynonymsInput synonymsInput) {
        synonymsService.addSynonyms(synonymsInput.getWord(), synonymsInput.getSynonyms());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> addSynonymsCollection(List<SynonymsInput> synonymsInput) {
        synonymsService.addSynonyms(synonymsInput);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<List<String>> findSynonyms(String word, Integer depth) {
        return ResponseEntity.ok(synonymsService.searchSynonyms(word, depth));
    }
}
