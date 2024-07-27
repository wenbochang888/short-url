package com.wenbo.shorturl.pattern.chain.process;

import com.wenbo.shorturl.pattern.chain.CatHandler;
import com.wenbo.shorturl.pattern.chain.DogHandler;
import com.wenbo.shorturl.pattern.chain.Handler;

import javax.annotation.PostConstruct;

public abstract class AbstractProcess implements BaseProcess {
	private Handler handler = null;

	@PostConstruct
	public void getHandlerList() {
		// 可以从数据库中 也可以从其他地方去获取

		// 1. cat
		Handler catHandler = new CatHandler();
		// 2. dog
		Handler dogHandler = new DogHandler();
		// 3. 设置下一个handler
		catHandler.setNextHandler(dogHandler);

		setHandler(catHandler);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
