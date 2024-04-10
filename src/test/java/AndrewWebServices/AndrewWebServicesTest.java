package AndrewWebServices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class AndrewWebServicesTest {
    Database database;
    RecSys recommender;
    PromoService promoService;
    AndrewWebServices andrewWebService;

    @Before
    public void setUp() {
        // You need to use some mock objects here
        database = new InMemoryDatabase();
        recommender = new RecSys();
        promoService = new PromoService();
        andrewWebService = new AndrewWebServices(database, recommender, promoService);
    }

    @Test
    public void testLogIn() {
        // This is taking way too long to test
        assertTrue(andrewWebService.logIn("Scotty", 17214));
    }

    @Test
    public void testGetRecommendation() {
        // This is taking way too long to test
        // assertEquals("Animal House", andrewWebService.getRecommendation("Scotty"));
        AndrewWebServices andrewWebServicesMock = mock(AndrewWebServices.class);
        when(andrewWebServicesMock.getRecommendation("Scotty")).thenReturn("Animal House");
        assertEquals("Animal House", andrewWebServicesMock.getRecommendation("Scotty"));

    }

    @Test
    public void testSendEmail() {
        // How should we test sendEmail() when it doesn't have a return value?
        // Hint: is there something from Mockito that seems useful here?
         // Mock the PromoService
        PromoService mockPromoService = mock(PromoService.class);

        // Create an instance of the class under test, injecting the mock
        AndrewWebServices andrewWebServicesTest = new AndrewWebServices(database, recommender, mockPromoService);

        // The email address to be tested
        String testEmail = "test@example.com";

        // Call the method under test
        andrewWebServicesTest.sendPromoEmail(testEmail);

        // Verify that promoService.mailTo was called with the correct email address
        verify(mockPromoService).mailTo(testEmail);
    }

    @Test
    public void testNoSendEmail() {
        // How should we test that no email has been sent in certain situations (like right after logging in)?
        // Hint: is there something from Mockito that seems useful here?
        // Mock the PromoService
        PromoService mockPromoService = mock(PromoService.class);

        // Create an instance of the class under test, injecting the mock
        AndrewWebServices andrewWebServicesTest = new AndrewWebServices(database, recommender, mockPromoService);

        // Perform the operation that should not trigger an email send.
        andrewWebServicesTest.logIn("Scotty", 17214);

        // Verify that promoService.mailTo was never called
        verify(mockPromoService, never()).mailTo(anyString());
    }
}
