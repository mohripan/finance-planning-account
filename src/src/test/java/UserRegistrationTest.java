import static org.junit.jupiter.api.Assertions.*;

import org.financePlanning.models.User;
import org.financePlanning.services.UserRegistrationService;
import org.junit.jupiter.api.Test;

public class UserRegistrationTest {

    @Test
    public void testSuccessfulRegistration() {
        User user = new User("nonExistingUsername", "password");
        UserRegistrationService registrationService = new UserRegistrationService();

        boolean result = registrationService.registerUser(user);

        assertTrue(result, "User should be successfully registered");
    }

    @Test
    public void testRegistrationWithExistingUsername() {
        User user = new User("username", "password123");
        UserRegistrationService registrationService = new UserRegistrationService();

        boolean result = registrationService.registerUser(user);

        assertFalse(result, "Registration should fail for existing username");
    }
}
