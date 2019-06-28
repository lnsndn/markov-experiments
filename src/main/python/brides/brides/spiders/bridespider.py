# -*- coding: utf-8 -*-

from scrapy.spiders import SitemapSpider
import json
from brides.items import BridesItem
import re


class BrideSpider(SitemapSpider):
    name = 'brides'
    sitemap_urls = ['http://www.brides.com/sitemap.xml?year=2016']
    sitemap_rules = [
        ('/story/', 'parse_story')
    ]

    def parse_story(self, response):
        script_texts = response.xpath("//script[@type='application/ld+json']/text()").extract()
        for script_text in script_texts:
            self.body_to_file(self.parse_script_text(script_text))

    def parse(self, response):
        pass

    def body_to_file(self, body_string):
        with open('brides.txt', 'a') as f:
            for line in body_string.split('\n'):
                f.write(line.encode('utf-8') + '\n')

    def parse_script_text(self, script_text):
        article = json.loads(script_text)
        body = article['articleBody']
        #the order below is important, [#iframe: ...] goes away before links are turned into text
        body = re.sub(r'\[#[^:]*:[^\]]*\]', '', body) # md images,instagrams, etc are [#stuff: morestuff], not needed
        body = re.sub(r'\[([^\]]+)\]\([^\)]*\)(\{.*\})?', r'\1', body) # markdown links are [stuff](link){optional}, keep
        body = re.sub(r'__.*__', r'\\n\\n', body) # md __headings__ not needed
        body = re.sub(r'[|*]*', '', body) # some ugly chars we don't want
        body = re.sub(r'\\n#+.*?\\n', r'\\n\\n', body) # headings removed
        body = re.sub(r'\(\d+x\d+\)', '', body) # remove (610x344) type junk
        lines = []
        for line in re.split(r'\\n(\\n)+', body):
            line = re.sub(r'\\n',' ', line)
            line = re.sub(r'\\','', line)
            if not re.match(r'^\s*$', line):
                lines.append(line)
        body = '\n'.join([u'BEGIN HERE %s END' % x for x in lines])
        return body
