package core.entities;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntityUtilityTest {

    private final static String[] SPACESHIP_STRING_ARRAY_CONFIGURATION = {
            "-+-",
            "+-+",
            "+-+",
            "-+-",
    };
    private final static String SPACESHIP_STRING_CONFIGURATION = "-+-\n+-+\n+-+\n-+-";

    private final static boolean[][] EXPECTED_SPACESHIP_CONFIGURATION = {
            {false, true, false},
            {true, false, true},
            {true, false, true},
            {false, true, false},
    };

    @BeforeClass
    public static void beforeAllTestMethods() {
    }

    @Test
    public void testCanConvertStringArrayConfigurationToBooleanArrayEntity() {
        assertArrayEquals(EntityUtility.loadEntityFromString(SPACESHIP_STRING_ARRAY_CONFIGURATION), EXPECTED_SPACESHIP_CONFIGURATION);
    }

    @Test
    public void testCanConvertStringConfigurationToBooleanArrayEntity() {
        assertArrayEquals(EntityUtility.loadEntityFromString(SPACESHIP_STRING_CONFIGURATION), EXPECTED_SPACESHIP_CONFIGURATION);
    }

}
