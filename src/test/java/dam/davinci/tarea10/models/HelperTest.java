package dam.davinci.tarea10.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

class HelperTest {

    String[] rightDniStrings;
    String[] wrongDniStrings;
    String[] rightUuidStrings;
    String[] wrongUuidStrings;

    @BeforeEach
    void init(){
        rightDniStrings  = new String[] {"91268284Y", "93619443J", "40271149N"};
        wrongDniStrings = new String[] {"91268284e", "", "e", "0"};
        rightUuidStrings = new String[] {"c84342fc-ebe1-4f09-8839-a587d3218881",
        "59f6adc4-d1e8-4c35-adac-4211a5af6020", "777b3128-d2d4-4993-a665-cd8d3ed07cb6"};
        wrongUuidStrings = new String[] {"","777b3128-d2d4-4993-a665-cd8d3ed07cg6"};
    }
    @Test
    void checkUUID() {
    }

    @Test
    void checkValidDni() {
        for (String dni : rightDniStrings) {
            Assertions.assertTrue(Helper.checkDni(dni));
        }
    }

    @Test
    void checkInvalidDni() {
        for (String dni : wrongDniStrings) {
            Assertions.assertFalse(Helper.checkDni(dni));
        }
    }

    @Test
    void checkValidUuid() {
        for (String uuid : rightUuidStrings) {
            Assertions.assertTrue(Helper.checkUUID(uuid));
        }
    }

    @Test
    void checkInvalidUuid() {
        for (String uuid : wrongUuidStrings) {
            Assertions.assertFalse(Helper.checkUUID(uuid));
        }
    }
}