# Crowdsourced Reduction!

Based on [Budget-Optimal Task Allocation for Reliable Crowdsourcing Systems](http://arxiv.org/abs/1110.3564) by Karger et al.

## Usage

Make sure you've downloaded and restored your project's Mongo Database dump.

### Annotation Match
Right now the algorithim is only measuring if a particular annotation is present or not on a classification. Specify which annotation you want to match by passing it in like so 

        "{:<annotation key> \"<annotation value>\"}"

For example in Disk Detective I want to match on an annotation of classified_as "good", so I'll pass as the third argument. 

        "{:classified_as \"good\"}"

The quote marks are important, so your shell treats that as one argument. Let me know if you have trouble with that. 

### OS X

* Install [homebrew](http://brew.sh)
* In a terminal `brew install leiningen`
* Clone the project `git clone https://github.com/edpaget/algo-zoo`
* `cd` into the directory and run `lein run -m zoo-algos.mongo <database name> <iterations> <annotation match>` (Note: If you don't have Java installed you'll be prompted to at this step)
* Sit back and wait (A really long time)

### Ubuntu

* `sudo apt-get install openjdk-7-jdk`
* Install [Leiningen](https://github.com/technomancy/leiningen#Installation)
* Clone the project `git clone https://github.com/edpaget/algo-zoo`
* `cd` into the directory and run `lein run -m zoo-algos.mongo <database name> <iterations> <annotation match>`
* Sit back and wait (A really long time)

## License

Distributed under the Apache Public License v2.0
