package com.gvb.dupliwise;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;


@SpringBootApplication
@RequiredArgsConstructor
public class DupliwiseApplication implements CommandLineRunner, ExitCodeGenerator {

	private final IFactory iFactory;
	private final DupliwiseCommand dupliwiseCommand;
	private int exitCode;

	@Override
	public void run(String... args) throws Exception {
		exitCode = new CommandLine(dupliwiseCommand, iFactory).execute(args);
	}

	@Override
	public int getExitCode() {
		return exitCode;
	}

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(DupliwiseApplication.class, args)));
	}

}
