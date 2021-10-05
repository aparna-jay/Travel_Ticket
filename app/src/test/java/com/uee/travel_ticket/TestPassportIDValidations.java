package com.uee.travel_ticket;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class TestPassportIDValidations {
    //Return true if pattern ^(?!^0+$)[a-zA-Z0-9]{3,20}$ is used
    @Test
    public void checkPassportValidations_returnsTrue(){
        assertTrue(ForeignRegisterActivity.validatePassportID("12345678AB"));
    }
    //Return false if less than 3 numbers inserted
    @Test
    public void checkPassportValidations_LessCharacters_returnsFalse(){
        assertFalse(ForeignRegisterActivity.validatePassportID("1C"));
    }
    //Return false if more than 20 characters inserted
    @Test
    public void checkPassportValidations_ExtraCharacters_returnsFalse(){
        assertFalse(ForeignRegisterActivity.validatePassportID("11223344AD3355678934NN"));
    }
    //Return false if string is empty
    @Test
    public void checkPassportValidations_empty_returnsFalse(){
        assertFalse(ForeignRegisterActivity.validatePassportID(""));
    }
}