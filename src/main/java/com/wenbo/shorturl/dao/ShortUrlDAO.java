package com.wenbo.shorturl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenbo.shorturl.modle.ShortUrl;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlDAO extends BaseMapper<ShortUrl> {

}
