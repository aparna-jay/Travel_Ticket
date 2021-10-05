package com.uee.travel_ticket;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
public class TestPasswordMatches {

    //Return true if passwords match
    @Test
    public void checkIfPasswordsMatch_returnsTrue(){
        assertTrue(RegistrationActivity.checkIfPasswordsMatch("12BC3", "12BC3"));
    }
    //Return false if passwords do not match
    @Test
    public void checkIfPasswordsMatch_returnsFalse(){
        assertFalse(RegistrationActivity.checkIfPasswordsMatch("123", "1AWD23"));
    }
    //Return false if password field is empty is entered for passwords
    @Test
    public void checkIfPasswordsMatch_passwordEmpty_returnsFalse(){
        assertFalse(RegistrationActivity.checkIfPasswordsMatch("", "12bC3"));
    }
    //Return false if confirm password field is empty is entered for passwords
    @Test
    public void checkIfPasswordsMatch_CPasswordEmpty_returnsFalse(){
        assertFalse(RegistrationActivity.checkIfPasswordsMatch("12bC3", ""));
    }

}
