package com.gvp.quickgen;

import com.gvp.quickgen.commands.UudiGeneratorCommand;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "quickgen",
        version = "1.0.0",
        mixinStandardHelpOptions = true,
        subcommands = {
                UudiGeneratorCommand.class
        }
)
@Component
public class QuickgenCommand implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 0;
    }
}
