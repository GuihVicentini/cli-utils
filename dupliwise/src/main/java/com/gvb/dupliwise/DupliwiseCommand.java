package com.gvb.dupliwise;


import com.gvb.dupliwise.commands.ListCommand;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "dupliwise",
        version = "1.0.0",
        mixinStandardHelpOptions = true,
        subcommands = {
             ListCommand.class
        }
)
@Component
public class DupliwiseCommand implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 0;
    }
}
