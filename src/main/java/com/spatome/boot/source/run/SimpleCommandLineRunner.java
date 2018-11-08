package com.spatome.boot.source.run;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleCommandLineRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("(启动流程)commandLine runner");
		for (String string : args) {
			System.out.println(string);
		}
	}

}
