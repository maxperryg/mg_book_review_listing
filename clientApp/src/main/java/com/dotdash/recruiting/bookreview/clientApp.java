package com.dotdash.recruiting.bookreview;

import com.dotdash.recruiting.bookreview.entity.dto.BookDto;
import com.dotdash.recruiting.bookreview.entity.dto.CollectionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.cli.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    private static final String COMMAND_LINE_HELP_MESSAGE = "java -jar {jar_name}}.jar [OPTIONS]";
    private static final String COMMAND_LINE_HOSTNAME_MESSAGE = "Hostname: %s";
    private static final String COMMAND_LINE_ERROR_MESSAGE = "Something went wrong: %s";
    private static final String COMMAND_LINE_MISSING_OPTION_ERROR_MESSAGE = "Option '%s' is missing";

    private static final String APP_URL = "http://localhost:8080/book/search?query=%s&page=%s&searchBy=%s&sortBy=%s";

    public static void main(String[] args)  {
        try {
            var options = getOptions();
            var values = getOptionValues(options, args);
            searchBasedOnOptionValues(values);
        } catch (Exception e) {
            System.out.println(String.format(COMMAND_LINE_ERROR_MESSAGE, e.getMessage()));
        }
        System.exit(1);
    }

    /**
     * queries the Googreads API for book info based on the specified options
     *
     * @param values option values
     */
    private static void searchBasedOnOptionValues(CommandLine values) {
        // Get option values and default for certain optional values if they're not specified
        var searchQuery = values.getOptionValue(COMMAND_LINE_OPTION_SHORT_SEARCH);
        if (searchQuery == null || searchQuery.isEmpty()) {
            throw new RuntimeException(String.format(COMMAND_LINE_MISSING_OPTION_ERROR_MESSAGE, COMMAND_LINE_OPTION_LONG_SEARCH));
        }
        var sortOrder = values.getOptionValue(COMMAND_LINE_OPTION_LONG_SORT, COMMAND_LINE_OPTION_DEFAULT_SORT);
        var pageNumber = values.getOptionValue(COMMAND_LINE_OPTION_SHORT_PAGE, COMMAND_LINE_OPTION_DEFAULT_PAGE);

        // Construct the URL
        // hardcoded "all" for search fields because it's a field on the API, but not required for the client
        var fullUrl = String.format(APP_URL, searchQuery, pageNumber, "all", sortOrder);

        var restTemplate = new RestTemplate();
        ResponseEntity<CollectionDto<BookDto>> response = null;
        try {
            response = restTemplate.exchange(fullUrl, HttpMethod.GET, null, new ParameterizedTypeReference<CollectionDto<BookDto>>() {});
        } catch (Exception e) {
            throw new RuntimeException(String.format(COMMAND_LINE_ERROR_MESSAGE, e.getMessage()));
        }
        var results = response.getBody();
        var mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(System.out, results);
        } catch (IOException e) {
            throw new RuntimeException(String.format(COMMAND_LINE_ERROR_MESSAGE, e.getMessage()));
        }
    }

    /**
     * Parses the command line arguments based on specified options
     *
     * @param options {@link Options} Object containing option information
     * @param args command line arguments
     *
     * @return {@link CommandLine} Object containing all the parsed option values
     */
    private static CommandLine getOptionValues(Options options, String[] args) {
        var parser = new DefaultParser();
        var formatter = new HelpFormatter();
        // Default CommandLine Object
        var cmd = new CommandLine.Builder().build();
        try {
            cmd = parser.parse(options, args);
        } catch (Exception e) {
            // If any error occurs during parsing, print the help message and throw an exception
            // with the original error message
            formatter.printHelp(COMMAND_LINE_HELP_MESSAGE, options);
            throw new RuntimeException(String.format(COMMAND_LINE_ERROR_MESSAGE, e.getMessage()));
        }

        // If the help option was specified, print the help message and terminate
        if (cmd.hasOption(COMMAND_LINE_OPTION_LONG_HELP)) {
            formatter.printHelp(COMMAND_LINE_HELP_MESSAGE, options);
            System.exit(1);
        }
        // If the host option was specified, print the hostname and terminate
        if (cmd.hasOption(COMMAND_LINE_OPTION_LONG_HOST)) {
            try {
                InetAddress localHost = InetAddress.getLocalHost();
                System.out.println(String.format(COMMAND_LINE_HOSTNAME_MESSAGE, localHost.getHostName()));
            } catch (Exception e) {
                // If any error occurred,throw an exception with the original error message
                throw new RuntimeException(String.format(COMMAND_LINE_ERROR_MESSAGE, e.getMessage()));
            }
            System.exit(1);
        }

        return cmd;
    }

    /**
     * Prepares the app to parse the command line arguments according to specified options
     *
     * @return {@link Options}
     */
    private static Options getOptions() {
        var options = new Options();

        var searchOption = new Option(COMMAND_LINE_OPTION_SHORT_SEARCH, COMMAND_LINE_OPTION_LONG_SEARCH, true, COMMAND_LINE_OPTION_DESCRIPTION_SEARCH);
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
