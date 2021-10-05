package com.uee.travel_ticket;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestNICValidations {
    //Return true if pattern ^[0-9]{9}[vVxX]$ is used
    @Test
    public void checkNICValidations_pattern1_returnsTrue(){
        assertTrue(RegistrationActivity.checkIfNICIsValid("992344567V"));
    }
    //Return true if pattern ^[0-9]{12}$ is used
    @Test
    public void checkNICValidations_pattern2_returnsTrue(){
        assertTrue(RegistrationActivity.checkIfNICIsValid("992344567123"));
    }
    //Return false if only 9 numbers inserted without V
    @Test
    public void checkNICValidations_withoutV_returnsFalse(){
        assertFalse(RegistrationActivity.checkIfNICIsValid("992344566"));
    }
    //Return false if more than 12 numbers inserted
    @Test
    public void checkNICValidations_extraNumbers_returnsFalse(){
        assertFalse(RegistrationActivity.checkIfNICIsValid("992344567123123"));
    }
    //Return false if less numbers inserted
    @Test
    public void checkNICValidations_lessNumbers_returnsFalse(){
        assertFalse(RegistrationActivity.checkIfNICIsValid("99234"));
    }
    //Return false if string is empty
    @Test
    public void checkNICValidations_empty_returnsFalse(){
        assertFalse(RegistrationActivity.checkIfNICIsValid(""));
    }

}
