/**
 DECLARATION LIST
 login    - Login object used in each test case
 firstName - Test user's first name ("Zarah")
 lastName  - Test user's last name ("Mocke")
 username  - Valid test username ("ZRM_1")
 password  - Valid test password ("Ch&&sec@ke99!")
 cellPhoneNumber - Valid SA test phone number ("+27838968976")
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    private Login createValidUser() {
        return new Login("Zarah", "Mocke", "ZRM_1", "Ch&&sec@ke99!", "+27838968976");
    }

    @Test
    public void testCheckUserName_Valid() {
        Login login = createValidUser();
        assertTrue(login.checkUserName());
    }

    @Test
    public void testCheckUserName_Invalid() {
        Login login = new Login("Zarah", "Mocke", "Cassiem", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.checkUserName());
    }

    @Test
    public void testCheckPasswordComplexity_Valid() {
        Login login = createValidUser();
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexity_Invalid() {
        Login login = new Login("Zarah", "Mocke", "ZRM_1", "password", "+27838968976");
        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    public void testCheckCellPhoneNumber_Valid() {
        Login login = createValidUser();
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testCheckCellPhoneNumber_Invalid() {
        Login login = new Login("Zarah", "Mocke", "ZRM_1", "Ch&&sec@ke99!", "08966553");
        assertFalse(login.checkCellPhoneNumber());
    }

    @Test
    public void testRegisterUser_UsernameCorrect() {
        Login login = createValidUser();
        assertEquals(
            "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.",
            login.registerUser()
        );
    }

    @Test
    public void testRegisterUser_UsernameIncorrect() {
        Login login = new Login("Zarah", "Mocke", "Cassiem", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(
            "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
            login.registerUser()
        );
    }

    @Test
    public void testRegisterUser_PasswordFails() {
        Login login = new Login("Zarah", "Mocke", "ZRM_1", "password", "+27838968976");
        assertEquals(
            "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
            login.registerUser()
        );
    }

    @Test
    public void testRegisterUser_CellPhoneFails() {
        Login login = new Login("Zarah", "Mocke", "ZRM_1", "Ch&&sec@ke99!", "08966553");
        assertEquals(
            "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.",
            login.registerUser()
        );
    }

    @Test
    public void testLoginUser_Success() {
        Login login = createValidUser();
        assertTrue(login.loginUser("ZRM_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUser_Fail() {
        Login login = createValidUser();
        assertFalse(login.loginUser("wrongUser", "wrongPass"));
    }

    @Test
    public void testReturnLoginStatus_Success() {
        Login login = createValidUser();
        assertEquals(
            "Welcome Zarah Mocke it is great to see you again.",
            login.returnLoginStatus(true)
        );
    }

    @Test
    public void testReturnLoginStatus_Fail() {
        Login login = createValidUser();
        assertEquals(
            "Username or password incorrect, please try again.",
            login.returnLoginStatus(false)
        );
    }
}
