package com.week3day4.partner_a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Partner A — word counts + sorted unique words.
 * See ../../README.md
 */
public class WordFrequencyApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(WordFrequencyApp.class);
    static final String SAMPLE = """
            Java collections maps sets queues lambdas
            Java maps and sets and more Java
            """;
    static final int N = 3;

    static void incrementToken(Map<String, Integer> counts, String token) {
        if (counts.containsKey(token)) {
            counts.put(token, counts.get(token) + 1);
        } else {
            counts.put(token, 1);

        }
    }

    public static void main(String[] args) {
        Map<String, Integer> counts = new HashMap<>();
        // : tokenize SAMPLE, populate counts (lower-case tokens)
        String[] tokens = SAMPLE.split("[ \n\t]");
        List<String> tokensList = new ArrayList<>(Arrays.asList(tokens));

        tokensList = tokensList.stream()
                .map(String::toLowerCase)
                .map(token -> token.replaceAll("[^a-zA-Z ]", ""))
                .filter(token -> !token.isEmpty())
                .collect(Collectors.toList());

        if (tokensList.isEmpty()) {
            LOGGER.warn("empty token list");
        }

        for (String token : tokensList) {
            incrementToken(counts, token);
        }

        TreeSet<String> vocabulary = new TreeSet<String>(counts.keySet());
        LOGGER.info("Vocabulary");
        LOGGER.info(vocabulary.toString());

        LOGGER.info("Counts");
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            LOGGER.debug(key + ": " + value);
        }

        Comparator<Map.Entry<String, Integer>> ascendingCount = Map.Entry.<String, Integer>comparingByValue()
                .reversed();
        List<Map.Entry<String, Integer>> sortedList = counts.entrySet().stream()
                .sorted(ascendingCount).collect(Collectors.toList());
        LOGGER.info("Top N words (N=" + N + ")");
        for (int i = 0; i < N; i++) {
            LOGGER.info(sortedList.get(i).toString());
        }

        LOGGER.info("Dictionary ends");
        LOGGER.info("First dictionary entry: " + vocabulary.pollFirst());
        LOGGER.info("Last dictionary entry: " + vocabulary.pollLast());
    }
}