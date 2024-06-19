package com.wenbo.shorturl.controller;

import com.wenbo.shorturl.service.ShortUrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("url")
public class ShortUrlController {

	@Autowired
	private ShortUrService shortUrService;


	@RequestMapping("/generate")
	@ResponseBody
	public String generateShortUrl(String longUrl) {
		String shortUrl = shortUrService.generateShortUrl(longUrl);
		log.info("longUrl = {}, shortUrl = {}", longUrl, shortUrl);
		return shortUrl;
	}

	@RequestMapping("/get")
	@ResponseBody
	public String getLongUrl(String shortUrl) {
		String longUrl = shortUrService.getLongUrl(shortUrl);
		log.info("shortUrl = {}, longUrl = {}", shortUrl, longUrl);
		return longUrl;
	}
}