package com.wenbo.shorturl.pattern.chain;

import com.wenbo.shorturl.pattern.Animal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractHandler implements Handler {
	private Handler nextHandler;

	@Override
	public boolean handler(Animal animal) {

		try {
			preHandler(animal);
			boolean res = onHandler(animal);
			// 如果return false，则继续执行下一个handler
			if (!res && nextHandler != null) {
				return nextHandler.handler(animal);
			}
		} catch (Exception e) {
			log.error("handler error", e);
		} finally {
			postHandler(animal);
		}
		return true;
	}


	@Override
	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}


	protected abstract boolean onHandler(Animal animal);

	protected void preHandler(Animal animal) {
		log.info("preHandler animal = {}", animal.getType());
	}

	protected void postHandler(Animal animal) {
		log.info("postHandler animal = {}", animal.getType());
	}
}
