package com.dinosatrovic.synonymssearch.controller.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
public class SynonymsInput {

    private String word;
    private List<String> synonyms;
}
