package com.wenbo.shorturl.pattern.chain;

import com.wenbo.shorturl.pattern.Animal;

public interface Handler {

	boolean handler(Animal animal);


	void setNextHandler(Handler nextHandler);
}
