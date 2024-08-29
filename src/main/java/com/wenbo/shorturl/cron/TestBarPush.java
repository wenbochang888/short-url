package com.wenbo.shorturl.cron;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.wenbo.shorturl.utils.GsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Slf4j
@Service
public class TestBarPush {

	public static void main(String[] args) {
		int dayOfMonth = LocalDate.now().getDayOfMonth();
		System.out.println(dayOfMonth);

	}

	@Autowired
	private RestTemplate restTemplate;

	@Scheduled(cron = "0 0 7 * * ?")
	public void cron() {
		// wenbo
		postUrl("PCNjVYaRvRGoaoqAnyGkPi");

		// 鸡腿
		postUrl("PJFPc8UerojeqV8VJej7JU");
	}


	public void postUrl(String deviceKey) {
		String url = "https://api.day.app/push";

		// 设置请求Header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("device_key", deviceKey);
		jsonObject.addProperty("title", "我亲爱的小鸡腿");
		jsonObject.addProperty("sound", "wenbo.caf");
		jsonObject.addProperty("icon", "https://images.cnblogs.com/cnblogs_com/wenbochang/1203185/o_240829142118_IMG_4358.jpg");
		jsonObject.addProperty("body", getBodyStr(getWeather()));

		HttpEntity<String> requestEntity = new HttpEntity<>(jsonObject.toString(), headers);


		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		String result = responseEntity.getBody();
		log.info("requestEntity = {}, result = {}", GsonUtil.toJson(requestEntity), result);
	}

	private String getBodyStr(Weather weather) {
		StringBuilder sb = new StringBuilder();
		sb.append("今天是").append(weather.getWeek() + "。").append("最" + weather.getHigh()).append("，最" + weather.getLow())
				.append("。").append(weather.getNotice()).append("。\n").append(getWord());

		return sb.toString();
	}


	public Weather getWeather() {
		String url = "http://t.weather.itboy.net/api/weather/city/101280601";
		String forObject = restTemplate.getForObject(url, String.class);
		JsonObject jsonObject = GsonUtil.fromJson(forObject, JsonObject.class);
		log.info("getWeather = {}", jsonObject);
		JsonObject data = GsonUtil.getAsJsonObject(jsonObject, "data");
		if (data == null) {
			return null;
		}

		int dayOfMonth = LocalDate.now().getDayOfMonth();
		JsonArray array = data.get("forecast").getAsJsonArray();
		for (JsonElement jsonElement : array) {
			Weather weather = GsonUtil.fromJson(jsonElement, Weather.class);
			log.info("weather = {}", weather);

			if (dayOfMonth == NumberUtils.toInt( weather.getDate())) {
				return weather;
			}
		}

		return null;
	}

	public String getWord() {
		String url = "https://api.lovelive.tools/api/SweetNothings";
		String forObject = restTemplate.getForObject(url, String.class);
		return forObject;
	}

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Weather {
	private String date;

	private String week;

	private String high;

	private String low;

	private String notice;
}