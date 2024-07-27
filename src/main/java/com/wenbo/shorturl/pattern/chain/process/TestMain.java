package com.wenbo.shorturl.pattern.chain.process;

import com.wenbo.shorturl.pattern.Cat;
import com.wenbo.shorturl.pattern.Dog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class TestMain {

	// 可以使用factory来配合使用
	@Autowired
	private CatProcess catProcess;

	@Autowired
	private DogProcess dogProcess;

	@PostConstruct
	public void init() {
		TxnContext context = new TxnContext();
		context.setAnimal(new Cat());

		catProcess.process(context);

		log.info("================================================================================");

		context.setAnimal(new Dog());
		dogProcess.process(context);
	}

}
