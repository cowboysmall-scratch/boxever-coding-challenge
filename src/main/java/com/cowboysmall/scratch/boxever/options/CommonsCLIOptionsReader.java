package com.cowboysmall.scratch.boxever.options;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import org.springframework.stereotype.Component;

import static org.apache.commons.cli.Option.builder;

@Component
public class CommonsCLIOptionsReader implements OptionsReader {

    @Override
    public Options getOptions(String... args) {

        org.apache.commons.cli.Options options = createOptions();

        try {

            CommandLine cmd = new DefaultParser().parse(options, args);

            if (cmd.getOptions().length == 0 || cmd.hasOption("h"))
                printHelp(options);
            else
                return new Options(
                        cmd.getOptionValue("s"),
                        cmd.getOptionValue("d"),
                        cmd.hasOption("directed")
                );

        } catch (ParseException e) {

            System.out.printf("%s%n%n", e.getMessage());
            printHelp(options);
        }

        return new Options();
    }


    //_________________________________________________________________________

    private org.apache.commons.cli.Options createOptions() {

        return new org.apache.commons.cli.Options()
                .addOption(
                        builder()
                                .longOpt("directed")
                                .hasArg(false)
                                .desc("construct a directed graph")
                                .optionalArg(true)
                                .build()
                )
                .addOption(
                        builder("s")
                                .longOpt("source")
                                .hasArg()
                                .desc("the source")
                                .build()
                )
                .addOption(
                        builder("d")
                                .longOpt("destination")
                                .hasArg()
                                .desc("the destination")
                                .build()
                )
                .addOption(
                        builder("h")
                                .longOpt("help")
                                .hasArg(false)
                                .desc("help information")
                                .build()
                );
    }

    private void printHelp(org.apache.commons.cli.Options options) {

        new HelpFormatter()
                .printHelp(
                        "java -jar /path/to/boxever-coding-challenge.jar OPTIONS\n\n",
                        options
                );
    }
}
