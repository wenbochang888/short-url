package com.wenbo.shorturl.pattern.chain.process;

import com.wenbo.shorturl.pattern.chain.CatHandler;
import com.wenbo.shorturl.pattern.chain.DogHandler;
import com.wenbo.shorturl.pattern.chain.Handler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DogProcess extends AbstractProcess {

	@Override
	public void process(TxnContext context) {
		Handler handler = getHandler();
		if (handler != null) {
			handler.handler(context.getAnimal());
		}
	}

	@Override
	@PostConstruct
	public void getHandlerList() {
		// 可以从数据库中 也可以从其他地方去获取

		// 1. dog
		Handler dogHandler = new DogHandler();
		// 2. cat
		Handler catHandler = new CatHandler();

		// 3. 设置下一个handler
		dogHandler.setNextHandler(catHandler);

		setHandler(dogHandler);
	}
}
