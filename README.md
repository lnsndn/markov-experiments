# markov-experiments

A project that has two separate parts: 

1) python scrapy script scraping brides.com articles for text (see the python scrapy documentation)
2) a java app for generating very rudimentary markov chains of bridal nonsense

## Usage

Run the scrapy python script, place contents in the java resources folder. 
(Not sure if this part actually works anymore though, it's been a while).
```
scrapy runspider bridespider.py
```
Then run the java main method which will print the resulting sentence.

## Example output

Most output is brain-hemmorhage-inducing nonsence, but sometimes you will get something like this (from actual runs):

* _The bride wore a Sy Devore white linen jacket with cream cheese frosting._
* _The upcoming John Legend-produced historical rom-com about Barack and Michelle will have to work a lace handkerchief and butterfly pin that belonged to Genevieve's great, great grandmother._
* _No matter how much you painstakingly plan, animals tend to start talking about your soon-to-be husband._
* _Blake was down on one knee... Yes, you heard correctly. Llamas. The day of my life._
