package com.uee.travel_ticket;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetTotalCreditsTest {
    @Test
    public void GetTotalCreditsIsCorrect(){
        int actual = PaymentInformationActivity.getTotalCredits(300, 200);
        int expected = 500;
        assertEquals(expected, actual);
    }
}
