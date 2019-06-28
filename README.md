# markov-experiments

A simple generator of bridal article content, using web scraping and markov chains.

## Dependencies

* Python 2.7
* Java 1.8
* Maven

## Functionality

The project has two separate parts: 

1) python scrapy script scraping brides.com articles for text (see the python scrapy documentation)
2) a java app for generating rudimentary markov chains of bridal nonsense

The basic functionality of the markov chain generation:

- An input file contains lines of text starting with "BEGIN HERE" and ending with "END", sourced via the scraping script
- Go through word by word and store each word combo (for example "their" and "ceremony") as a unique key, and the third following word in an array, for example:
```
their, ceremony: [was, came, never, was]
Mike, took: [notes, the, the, his, the]
```
- Naturally, the "BEGIN HERE" combo will be followed by all possible starting words, so start building a sentence by randomizing a first word from its array
- Let's say the random word was "The", next do the same thing for "HERE The"
- Continue until you reach the word "END"
- Probability will have made sure you ended up with combinations that were more likely to occur together

## Usage

Run the scrapy python script. 
(Not sure if this part actually works anymore though, brides.com has probably changed layout since the script was written).
```
pip install -r requirements.txt
scrapy runspider bridespider.py
```
Then run the java main part which will print the resulting sentence.

```
mvn exec:java
```

## Example output

Most output is brain-hemmorhage-inducing nonsence, but sometimes you will get something like this (from actual runs):

* _The bride wore a Sy Devore white linen jacket with cream cheese frosting._
* _The upcoming John Legend-produced historical rom-com about Barack and Michelle will have to work a lace handkerchief and butterfly pin that belonged to Genevieve's great, great grandmother._
* _No matter how much you painstakingly plan, animals tend to start talking about your soon-to-be husband._
* _Blake was down on one knee... Yes, you heard correctly. Llamas. The day of my life._
