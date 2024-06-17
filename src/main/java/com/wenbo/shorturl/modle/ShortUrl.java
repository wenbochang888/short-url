package com.wenbo.shorturl.modle;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("short_url")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShortUrl {

	@TableId(value = "id",type = IdType.AUTO)
	private Long id;

	@TableField("lurl")
	private String lUrl;

	@TableField("surl")
	private String sUrl;

	private LocalDateTime gmtCreate;


	public ShortUrl(String lUrl, String sUrl) {
		this.lUrl = lUrl;
		this.sUrl = sUrl;
	}
}
