package com.dinosatrovic.synonymssearch.controller;

import com.dinosatrovic.synonymssearch.controller.model.SynonymsInput;
import com.dinosatrovic.synonymssearch.service.SynonymsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("synonyms")
public class SynonymsController {

    private SynonymsService synonymsService;

    @Autowired
    SynonymsController(SynonymsService synonymsService) {
        this.synonymsService = synonymsService;
    }

    @PostMapping(value = "/add-collection", produces = "application/json")
    public ResponseEntity<Void> addSynonyms(@RequestBody List<SynonymsInput> synonymsInput) {
        synonymsService.addSynonyms(synonymsInput);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<Void> addSynonyms(@RequestBody SynonymsInput synonymsInput) {
        synonymsService.addSynonyms(synonymsInput.getWord(), synonymsInput.getSynonyms());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<String>> findSynonyms(@RequestParam(value = "word") String word, @RequestParam(value = "depth", required = false) Integer depth) {
        return ResponseEntity.ok(synonymsService.searchSynonyms(word, depth));
    }

}
