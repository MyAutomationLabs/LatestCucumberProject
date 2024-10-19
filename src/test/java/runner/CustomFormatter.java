package runner;

import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.Plugin;

public class CustomFormatter implements Plugin {


    public void setEventPublisher(EventPublisher publisher) {
        // Register handlers for the relevant events
        publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
    }

    private void handleTestCaseStarted(TestCaseStarted event) {
        // Print the scenario name when it starts
        System.out.println("Starting Scenario: " + event.getTestCase().getName());
    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        // Print the scenario name when it finishes
        System.out.println("Finished Scenario: " + event.getTestCase().getName());
    }
}
