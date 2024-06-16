package com.wenbo.shorturl.modle;

import com.baomidou.mybatisplus.annotation.TableField;
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

	@TableField("lurl")
	private String lUrl;

	@TableField("surl")
	private String sUrl;

	private LocalDateTime gmtCreate;
}
