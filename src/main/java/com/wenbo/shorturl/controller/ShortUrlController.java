package com.wenbo.shorturl.controller;

import com.wenbo.shorturl.service.ShortUrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("url")
public class ShortUrlController {

	@Autowired
	private ShortUrService shortUrService;

	@RequestMapping("/generate")
	public String generateShortUrl(String longUrl, Model model) {
		String shortUrl = shortUrService.generateShortUrl(longUrl);
		log.info("longUrl = {}, shortUrl = {}", longUrl, shortUrl);

		model.addAttribute("result", "Short URL: <a href='" + shortUrl + "' target='_blank'>" + shortUrl + "</a>");
		model.addAttribute("longUrl", longUrl);
		model.addAttribute("shortUrl", shortUrl);

		return "result";
	}

	@RequestMapping("/get")
	public String getLongUrl(String shortUrl, Model model) {
		String longUrl = shortUrService.getLongUrl(shortUrl);
		log.info("shortUrl = {}, longUrl = {}", shortUrl, longUrl);

		model.addAttribute("result", "Long URL: <a href='" + longUrl + "' target='_blank'>" + longUrl + "</a>");
		model.addAttribute("longUrl", longUrl);
		model.addAttribute("shortUrl", shortUrl);

		return "result";
	}
}