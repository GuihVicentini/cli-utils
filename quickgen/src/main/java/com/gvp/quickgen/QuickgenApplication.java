package com.gvp.quickgen;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;

@SpringBootApplication
@RequiredArgsConstructor
public class QuickgenApplication implements CommandLineRunner, ExitCodeGenerator {

	private final CommandLine.IFactory iFactory;
	private final QuickgenCommand quickgenCommand;
	private int exitCode;

	@Override
	public void run(String... args) throws Exception {
		exitCode = new CommandLine(quickgenCommand, iFactory).execute(args);
	}

	@Override
	public int getExitCode() {
		return exitCode;
	}
	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(QuickgenApplication.class, args)));
	}

}
