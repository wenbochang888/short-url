package com.wenbo.shorturl.controller;

import com.wenbo.shorturl.dao.ShortUrlDAO;
import com.wenbo.shorturl.modle.ShortUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class BasicController {

	@Autowired
	private ShortUrlDAO shortUrlDAO;

	@RequestMapping("")
	@ResponseBody
	public String hello() {
		return "Hello World";
	}


	@RequestMapping("/test/mysql")
	@ResponseBody
	public String testMysql() {

		List<ShortUrl> shortUrls = shortUrlDAO.selectList(null);
		log.info("shortUrls = {}", shortUrls);


		return "Hello World";
	}

}

