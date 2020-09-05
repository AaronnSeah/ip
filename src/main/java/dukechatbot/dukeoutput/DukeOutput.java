package dukechatbot.dukeoutput;

import java.util.Collections;
import java.util.List;

import dukechatbot.constant.DukeConstants;

/**
 * Represents the class to output the response of Duke.
 */
public class DukeOutput {

    /**
     * Prints the initial greeting message from Duke.
     */
    public static String  outputGreeting() {
        return "Hello from\n" + DukeConstants.LOGO;
    }

    /**
     * Formats and prints the response from Duke.
     *
     * @param response from Duke.
     */
    public static String output(String response) {
        String formattedResponse = DukeOutputFormatter.format(
                Collections.singletonList(response));
        return formattedResponse;
    }

    /**
     * Formats and prints the responses from Duke.
     *
     * @param response list of responses by Duke.
     */
    public static String output(List<String> response) {
        String formattedResponse = DukeOutputFormatter.format(response);
        return formattedResponse;
    }

    /**
     * Formats and prints the responses from Duke.
     * 
     * @param responses list of responses by Duke.
     * @param indentIndexes to indicate which response is formatted more.
     */
    public static String output(List<String> responses, List<Integer> indentIndexes) {
        String formattedResponse = DukeOutputFormatter.format(
                responses, indentIndexes);
        return formattedResponse;
    }
}
