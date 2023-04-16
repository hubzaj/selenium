package org.housecallpro.extension;

import lombok.extern.slf4j.Slf4j;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

@Slf4j
public class LoggerListener implements TestExecutionListener {

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        log.info("Starting execution of test suite [{}]", testIdentifier.getDisplayName());
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        log.info("Finishing execution of test suite [{}]", testIdentifier.getDisplayName());
    }

}
