package com.gvp.quickgen.commands;

import picocli.CommandLine;

import java.util.UUID;

@CommandLine.Command(name = "uuid", mixinStandardHelpOptions = true)
public class UudiGeneratorCommand implements Runnable {

    @CommandLine.Parameters(arity = "0..1", description = "The amount of uuids to be generated")
    private int amount;

    @Override
    public void run() {
        int amount = validateAmount(this.amount);
        for (int i = 0; i < amount; i++) {
            UUID uuid = generateUuid();
            printUuidInformation(uuid);
        }
    }

    private void printUuidInformation(UUID uuid) {
        System.out.println(uuid.toString());
    }

    private UUID generateUuid() {
        return UUID.randomUUID();
    }

    private int validateAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Only positive numbers are allowed");
        }

        if (amount == 0) {
            return 1;
        }

        return amount;
    }
}
