package com.wenbo.shorturl.pattern.chain.process;

import com.wenbo.shorturl.pattern.chain.Handler;
import org.springframework.stereotype.Service;

@Service
public class CatProcess extends AbstractProcess {

	@Override
	public void process(TxnContext context) {
		Handler handler = getHandler();
		if (handler != null) {
			handler.handler(context.getAnimal());
		}
	}

	//@Override
	//@PostConstruct
	//public void getHandlerList() {
	//	// 可以从数据库中 也可以从其他地方去获取
	//
	//	// 1. cat
	//	Handler catHandler = new CatHandler();
	//	// 2. dog
	//	Handler dogHandler = new DogHandler();
	//	// 3. 设置下一个handler
	//	catHandler.setNextHandler(dogHandler);
	//
	//	setHandler(catHandler);
	//}
}
