package com.wenbo.shorturl.pattern.chain;

import com.wenbo.shorturl.pattern.Animal;
import com.wenbo.shorturl.pattern.Dog;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DogHandler extends AbstractHandler {

	@Override
	protected boolean onHandler(Animal animal) {
		animal.speak();
		if (animal instanceof Dog) {
			return true;
		}
		return false;
	}
}
