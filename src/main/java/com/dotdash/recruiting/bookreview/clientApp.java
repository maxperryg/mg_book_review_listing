package com.dotdash.recruiting.bookreview;

import com.dotdash.recruiting.bookreview.entity.dto.BookDto;
import com.dotdash.recruiting.bookreview.entity.dto.CollectionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.cli.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetAddress;

public class clientApp {
    private static final String COMMAND_LINE_OPTION_SHORT_SEARCH = "s";
    private static final String COMMAND_LINE_OPTION_SHORT_PAGE = "p";
    private static final String COMMAND_LINE_OPTION_SHORT_HOST = "h";

    private static final String COMMAND_LINE_OPTION_LONG_SEARCH = "search";
    private static final String COMMAND_LINE_OPTION_LONG_SORT = "sort";
    private static final String COMMAND_LINE_OPTION_LONG_HOST = "host";
    private static final String COMMAND_LINE_OPTION_LONG_HELP = "help";

    private static final String COMMAND_LINE_OPTION_DEFAULT_SORT = "";
    private static final String COMMAND_LINE_OPTION_DEFAULT_PAGE = "";

    private static final String COMMAND_LINE_OPTION_DESCRIPTION_SEARCH = "search term(s)";
    private static final String COMMAND_LINE_OPTION_DESCRIPTION_SORT = "field to sort by (title or author)";
    private static final String COMMAND_LINE_OPTION_DESCRIPTION_PAGE = "page number to display";
    private static final String COMMAND_LINE_OPTION_DESCRIPTION_HOST = "host name of the server";
    private static final String COMMAND_LINE_OPTION_DESCRIPTION_HELP = "display help message";

    private static final String COMMAND_LINE_HELP_MESSAGE = "java -jar app.jar [OPTIONS]";

    private static final String APP_URL = "http://localhost:8080/book/search?query=%s&page=%s&searchBy=%s&sortBy=%s";

    public static void main(String[] args)  {
        try {
            var options = getOptions();
            var values = getOptionValues(options, args);
            searchBasedOnOptionValues(values);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        System.exit(1);
    }

    private static void searchBasedOnOptionValues(CommandLine values) {
        var searchQuery = values.getOptionValue(COMMAND_LINE_OPTION_SHORT_SEARCH);
        var sortOrder = values.getOptionValue(COMMAND_LINE_OPTION_LONG_SORT, COMMAND_LINE_OPTION_DEFAULT_SORT);
        var pageNumber = values.getOptionValue(COMMAND_LINE_OPTION_SHORT_PAGE, COMMAND_LINE_OPTION_DEFAULT_PAGE);

        var fullUrl = String.format(APP_URL, searchQuery, pageNumber, "all", sortOrder);
        System.out.println(fullUrl);

        var restTemplate = new RestTemplate();
        var response = restTemplate.exchange(fullUrl, HttpMethod.GET, null, new ParameterizedTypeReference<CollectionDto<BookDto>>() {});
        var results = response.getBody();
        var mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(System.out, results);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(1);
    }

    private static CommandLine getOptionValues(Options options, String[] args) {
        var parser = new DefaultParser();
        var formatter = new HelpFormatter();
        var cmd = new CommandLine.Builder().build();
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            formatter.printHelp(COMMAND_LINE_HELP_MESSAGE, options);
            System.exit(1);
        }

        if (cmd.hasOption(COMMAND_LINE_OPTION_LONG_HELP)) {
            formatter.printHelp(COMMAND_LINE_HELP_MESSAGE, options);
            System.exit(1);
        }
        if (cmd.hasOption(COMMAND_LINE_OPTION_LONG_HOST)) {
            try {
                InetAddress localHost = InetAddress.getLocalHost();
                System.out.println("Hostname: " + localHost.getHostName());
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
            System.exit(1);
        }

        return cmd;
    }

    private static Options getOptions() {
        var options = new Options();

        var searchOption = new Option(COMMAND_LINE_OPTION_SHORT_SEARCH, COMMAND_LINE_OPTION_LONG_SEARCH, true, COMMAND_LINE_OPTION_DESCRIPTION_SEARCH);
        searchOption.setRequired(true);
        options.addOption(searchOption);

        var sortOption = new Option(null, COMMAND_LINE_OPTION_LONG_SORT, true, COMMAND_LINE_OPTION_DESCRIPTION_SORT);
        options.addOption(sortOption);

        var pageOption = new Option(COMMAND_LINE_OPTION_SHORT_PAGE, true, COMMAND_LINE_OPTION_DESCRIPTION_PAGE);
        options.addOption(pageOption);

        var hostOption = new Option(COMMAND_LINE_OPTION_SHORT_HOST, COMMAND_LINE_OPTION_LONG_HOST, false, COMMAND_LINE_OPTION_DESCRIPTION_HOST);
        options.addOption(hostOption);

        var helpOption = new Option(null, COMMAND_LINE_OPTION_LONG_HELP, false, COMMAND_LINE_OPTION_DESCRIPTION_HELP);
        options.addOption(helpOption);

        return options;
    }
}
