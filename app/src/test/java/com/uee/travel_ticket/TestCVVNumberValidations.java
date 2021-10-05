package com.uee.travel_ticket;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class TestCVVNumberValidations {

    //Return true if pattern ^[0-9]{3,4}$ is used
    @Test
    public void checkCVVValidations_returnsTrue(){
        assertTrue(PaymentInformationActivity.validateCVV("992"));
    }

    //Return false if less than 3 numbers inserted
    @Test
    public void checkCVVValidations_lessNumbers_returnsFalse(){
        assertFalse(RegistrationActivity.checkIfNICIsValid("99"));
    }
    //Return false if more than 4 numbers inserted
    @Test
    public void checkCVVValidations_extraNumbers_returnsFalse(){
        assertFalse(RegistrationActivity.checkIfNICIsValid("99234"));
    }
    //Return false if string is empty
    @Test
    public void checkCVVValidations_empty_returnsFalse(){
        assertFalse(RegistrationActivity.checkIfNICIsValid(""));
    }
}
