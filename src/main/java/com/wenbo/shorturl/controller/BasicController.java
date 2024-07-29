package com.wenbo.shorturl.controller;

import com.wenbo.shorturl.dao.ShortUrlDAO;
import com.wenbo.shorturl.modle.ShortUrl;
import com.wenbo.shorturl.pattern.CommonRequestParam;
import com.wenbo.shorturl.pattern.command.AbstractCommand.CommandInvoker;
import com.wenbo.shorturl.service.ShortUrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
public class BasicController {

	@Autowired
	private ShortUrlDAO shortUrlDAO;

	@Autowired
	private ShortUrService shortUrService;

	@GetMapping("/")
	public String index(@RequestParam(value = "longUrl", required = false) String longUrl,
						@RequestParam(value = "shortUrl", required = false) String shortUrl,
						Model model) {
		model.addAttribute("longUrl", longUrl);
		model.addAttribute("shortUrl", shortUrl);
		return "index";
	}

	@GetMapping("/short/{url}")
	public void index(@PathVariable(value = "url") String url, HttpServletResponse response) throws Exception {
		String longUrl = shortUrService.getLongUrl(url);
		if (longUrl != null) {
			response.sendRedirect(longUrl);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Autowired
	private CommandInvoker processInvoker;


	@ResponseBody
	@RequestMapping("/base/get")
	public String getLongUrl2(String shortUrl) {
		CommonRequestParam request = new CommonRequestParam(shortUrl);
		return processInvoker.executeCommand(request, () -> shortUrService.getLongUrl(request.getCommon()));
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



















