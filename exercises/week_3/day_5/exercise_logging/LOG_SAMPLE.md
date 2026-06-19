# Log sample

<!-- Paste ~10 log lines + 2 sentences on threshold change (see Friday README). -->


## Logger level: DEBUG
2026-06-19 10:34:51,910 INFO  [com.pair.a.words.WordFrequencyApp] Vocabulary: [and, collections, java, lambdas, maps, more, queues, sets]
2026-06-19 10:34:51,912 DEBUG [com.pair.a.words.WordFrequencyApp] Counts:
2026-06-19 10:34:51,912 DEBUG [com.pair.a.words.WordFrequencyApp] java: 3
2026-06-19 10:34:51,912 DEBUG [com.pair.a.words.WordFrequencyApp] maps: 2
2026-06-19 10:34:51,912 DEBUG [com.pair.a.words.WordFrequencyApp] sets: 2
2026-06-19 10:34:51,912 DEBUG [com.pair.a.words.WordFrequencyApp] collections: 1
2026-06-19 10:34:51,912 DEBUG [com.pair.a.words.WordFrequencyApp] queues: 1
2026-06-19 10:34:51,912 DEBUG [com.pair.a.words.WordFrequencyApp] and: 2
2026-06-19 10:34:51,912 DEBUG [com.pair.a.words.WordFrequencyApp] more: 1
2026-06-19 10:34:51,912 DEBUG [com.pair.a.words.WordFrequencyApp] lambdas: 1
2026-06-19 10:34:51,913 INFO  [com.pair.a.words.WordFrequencyApp] Top N words (N=3)
2026-06-19 10:34:51,913 INFO  [com.pair.a.words.WordFrequencyApp] java=3
2026-06-19 10:34:51,913 INFO  [com.pair.a.words.WordFrequencyApp] maps=2
2026-06-19 10:34:51,913 INFO  [com.pair.a.words.WordFrequencyApp] sets=2
2026-06-19 10:34:51,913 INFO  [com.pair.a.words.WordFrequencyApp] First dictionary entry: and
2026-06-19 10:34:51,913 INFO  [com.pair.a.words.WordFrequencyApp] Last dictionary entry: sets

## Logger level: INFO
2026-06-19 10:37:58,729 INFO  [com.pair.a.words.WordFrequencyApp] Vocabulary: [and, collections, java, lambdas, maps, more, queues, sets]
2026-06-19 10:37:58,732 INFO  [com.pair.a.words.WordFrequencyApp] Top N words (N=3)
2026-06-19 10:37:58,732 INFO  [com.pair.a.words.WordFrequencyApp] java=3
2026-06-19 10:37:58,732 INFO  [com.pair.a.words.WordFrequencyApp] maps=2
2026-06-19 10:37:58,732 INFO  [com.pair.a.words.WordFrequencyApp] sets=2
2026-06-19 10:37:58,732 INFO  [com.pair.a.words.WordFrequencyApp] First dictionary entry: and
2026-06-19 10:37:58,732 INFO  [com.pair.a.words.WordFrequencyApp] Last dictionary entry: sets

## What changed
When the root logging level changes, the loggers will stop displaying lower logger levels. In this case, this means that changing the level of the logger from DEBUG to INFO will stop the logger from outputting the finer details of the loops.