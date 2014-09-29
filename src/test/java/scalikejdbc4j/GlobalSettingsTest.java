package scalikejdbc4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scalikejdbc4j.globalsettings.IgnoredParamsValidation;
import scalikejdbc4j.globalsettings.LoggingSQLAndTimeSettings;

public class GlobalSettingsTest {

    private static final Logger logger = LoggerFactory.getLogger(GlobalSettingsTest.class);

    @Test
    public void loggingSQLAndTimeSettings() {
        LoggingSQLAndTimeSettings loggingSettings = new LoggingSQLAndTimeSettings();
        loggingSettings.setEnabled(true);
        loggingSettings.setSingleLineMode(true);
        GlobalSettings.setLoggingSQLAndTime(loggingSettings);
    }

    @Test
    public void queryExecutionListeners() throws Exception {
        GlobalSettings.setQueryFailureListener((statement, params, error) -> logger.error("Failed", error));
        GlobalSettings.setQueryCompletionListener((statement, params, millis) -> logger.info("--- END ---"));

        // do nothing
        GlobalSettings.setQueryFailureListener((statement, params, error) -> {
        });
        GlobalSettings.setQueryCompletionListener((statement, params, millis) -> {
        });
    }

    @Test
    public void nameBindingSQLValidator() {
        GlobalSettings.getNameBindingSQLValidator().setIgnoredParams(IgnoredParamsValidation.NoCheckForIgnoredParams);
        GlobalSettings.getNameBindingSQLValidator().setIgnoredParams(IgnoredParamsValidation.ExceptionForIgnoredParams);
    }

}
