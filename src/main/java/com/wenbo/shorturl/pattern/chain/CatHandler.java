package com.wenbo.shorturl.pattern.chain;

import com.wenbo.shorturl.pattern.Animal;
import com.wenbo.shorturl.pattern.Cat;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatHandler extends AbstractHandler {

	@Override
	protected boolean onHandler(Animal animal) {
		animal.speak();
		if (animal instanceof Cat) {
			return true;
		}
		return false;
	}
}
