package com.dotdash.recruiting.bookreview.console;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.cli.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class console implements CommandLineRunner {
    private static final String APP_URL = "http://localhost:8080/book/search?query=%s&page=%s&searchBy=%s&sortBy=%s";
    @Override
    public void run(String... args) throws Exception {
        var options = getOptions();
        var values = getOptionValues(options, args);
        searchBasedOnOptionValues(values);
    }

    private void searchBasedOnOptionValues(CommandLine values) {
        var searchQuery = values.getOptionValue("s");
        var sortOrder = values.getOptionValue("sort");
        var pageNumber = values.getOptionValue("p");

        sortOrder = sortOrder == null ? "" : sortOrder;
        pageNumber = pageNumber == null ? "" : pageNumber;
        var fullUrl = String.format(APP_URL, searchQuery, pageNumber, "all", sortOrder);
        System.out.println(fullUrl);
        var restTemplate = new RestTemplate();
        var response = restTemplate.getForEntity(fullUrl, String.class);
        var results = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        try {
            var prettyResults = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
            System.out.println(prettyResults);
        } catch (JsonProcessingException e) {
            System.out.println(results);
        }
        System.exit(1);
    }

    private CommandLine getOptionValues(Options options, String[] args) {
        var parser = new DefaultParser();
        var formatter = new HelpFormatter();
        var cmd = new CommandLine.Builder().build();
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("help")) {
                formatter.printHelp("java -jar app.jar [OPTIONS]", options);
                System.exit(1);
            }
            if (cmd.hasOption("host")) {
                System.out.println("");
                System.exit(1);
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("java -jar app.jar [OPTIONS]", options);

            System.exit(1);
        }

        return cmd;
    }

    public Options getOptions() {
        var options = new Options();

        var searchOption = new Option("s", "search", true, "search query string");
        searchOption.setRequired(true);
        options.addOption(searchOption);

        var sortOption = new Option(null, "sort", true, "sort order string");
        options.addOption(sortOption);

        var pageOption = new Option("p", true, "page number");
        options.addOption(pageOption);

        var hostOption = new Option("h", "host", false, "hostname");
        options.addOption(hostOption);

        var helpOption = new Option(null, "help", false, "display help message");
        options.addOption(helpOption);

        return options;
    }
}
