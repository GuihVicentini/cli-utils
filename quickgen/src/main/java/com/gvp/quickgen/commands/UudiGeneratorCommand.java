package com.gvp.quickgen.commands;

import picocli.CommandLine;

@CommandLine.Command(name = "uuid", mixinStandardHelpOptions = true)
public class UudiGeneratorCommand implements Runnable {

    @CommandLine.Parameters(arity = "0..1", description = "The amount of uuids to be generated")
    private int amount;

    @Override
    public void run() {
        System.out.println("some random uuid");
    }
}
