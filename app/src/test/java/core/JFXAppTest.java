package core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JFXAppTest {
    @Test public void testAppHasAGreeting() {
        JFXApp classUnderTest = new JFXApp();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }

}
