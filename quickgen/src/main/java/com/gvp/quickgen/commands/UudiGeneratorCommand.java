package com.gvp.quickgen.commands;

import picocli.CommandLine;

import java.util.UUID;

@CommandLine.Command(name = "uuid", mixinStandardHelpOptions = true)
public class UudiGeneratorCommand implements Runnable {

    @CommandLine.Parameters(arity = "0..1", description = "The amount of uuids to be generated")
    private int amount;

    @CommandLine.Option(names = {"-U", "--upper"}, description = "Print all letters of the uuid in uppercase")
    private boolean upperCase;

    @CommandLine.Option(names = {"-d", "--details"}, description = "Print all letters of the uuid in uppercase")
    private boolean details;

    @Override
    public void run() {
        int amount = validateAmount(this.amount);
        for (int i = 0; i < amount; i++) {
            UUID uuid = generateUuid();
            printUuidInformation(uuid);
        }
    }

    private void printUuidInformation(UUID uuid) {
        String uuidValue = upperCase ? uuid.toString().toUpperCase() : uuid.toString();
        String output = details ? addDetails(uuidValue, uuid) : uuidValue;
        System.out.println(output);
    }

    private String addDetails(String uuidValue, UUID uuid) {
        return String.format("Value: %s, Version: %s, Variant: %s%n", uuidValue, uuid.version(), uuid.variant());
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
