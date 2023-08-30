package org.novatec;

import org.camunda.bpm.application.*;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

import java.util.logging.Level;
import java.util.logging.Logger;

@ProcessApplication("Dinner App DMN")
public class DinnerApplication extends ServletProcessApplication
{
    protected final static Logger LOGGER = Logger.getLogger(DinnerApplication.class.getName());

    @PostDeploy
    public void evaluateDecisionTable(ProcessEngine processEngine) {

        DecisionService decisionService = processEngine.getDecisionService();

        VariableMap variables = Variables.createVariables()
                .putValue("season", "Spring")
                .putValue("guestCount", 10);

        DmnDecisionTableResult dishDecisionResult = decisionService.evaluateDecisionTableByKey("dish", variables);
        String desiredDish = dishDecisionResult.getSingleEntry();

        LOGGER.log(Level.INFO, "\n\nDesired dish: {0}\n\n", desiredDish);
    }
}
