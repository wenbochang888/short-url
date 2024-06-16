package com.wenbo.shorturl.controller;

import com.wenbo.shorturl.dao.ShortUrlDAO;
import com.wenbo.shorturl.modle.ShortUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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


	@RequestMapping("/test/mysql/select")
	@ResponseBody
	public String testMysqlSelect() {

		List<ShortUrl> shortUrls = shortUrlDAO.selectList(null);
		log.info("shortUrls = {}", shortUrls);

		return "Hello World";
	}

	@RequestMapping("/test/mysql/insert")
	@ResponseBody
	public String testMysqlInsert() {

		ShortUrl url = new ShortUrl("longurl", "sUrl12350");

		try {
			shortUrlDAO.insert(url);
		} catch (DuplicateKeyException e) {
			log.error("插入数据库失败 e = {}", e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage());
		}


		return "Hello World";
	}


}



















