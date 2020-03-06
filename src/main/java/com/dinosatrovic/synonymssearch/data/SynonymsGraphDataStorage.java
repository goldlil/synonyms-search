package com.dinosatrovic.synonymssearch.data;

import com.dinosatrovic.synonymssearch.controller.model.SynonymsInput;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SynonymsGraphDataStorage implements SynonymsRepository {

    private Map<String, HashSet<String>> graph = new HashMap<>();

    private void addWord(String word) {
        graph.put(word, new HashSet<>());
    }

    private void addSynonym(String word, String synonym) {
        if(!graph.containsKey(word))
            this.addWord(word);

        if(!graph.containsKey(synonym))
            this.addWord(synonym);

        graph.get(word).add(synonym);
        graph.get(synonym).add(word);
    }

    private List<String> searchWithDepth(String word, Integer depth) {
        Set<String> visitedNodes = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();
        queue.add(word);

        Set<String> firstNodeVertex= graph.get(word);
        if(firstNodeVertex == null)
            return Collections.emptyList();


        for (int i = 1, currentDepth = 0, nextDepthIteration = 0, currentDepthIteration = 1; !queue.isEmpty(); i++) {
            String nodeValue = queue.removeFirst();
            if(visitedNodes.contains(nodeValue))
                continue;

            visitedNodes.add(nodeValue);

            Set<String> neighboursList = graph.get(nodeValue);

            for(String neighbourNodeValue : neighboursList) {
                if(!visitedNodes.contains(neighbourNodeValue)) {
                    queue.add(neighbourNodeValue);
                    nextDepthIteration++;
                }
            }

            if(depth != null && i == currentDepthIteration) {
                i = 0;
                currentDepthIteration = nextDepthIteration;
                nextDepthIteration = 0;
                if(currentDepth++ == depth)
                    break;
            }
        }
        visitedNodes.remove(word);
        return new ArrayList<>(visitedNodes);
    }

    @Override
    public List<String> getSynonymsByWord(String word, Integer depth) {
        return searchWithDepth(word, depth);
    }

    @Override
    public void saveSynonyms(String word, List<String> synonyms) {
        for(String synonym : synonyms) {
            addSynonym(word, synonym);
        }
    }
}
