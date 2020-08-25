package clinone;

import browserConfiguration.BaseBrowserInit;
import clinone.components.CookiePopUp;
import com.automation.remarks.junit.VideoRule;
import com.automation.remarks.video.annotations.Video;
import com.automation.remarks.video.enums.RecordingMode;
import com.automation.remarks.video.enums.VideoSaveMode;
import com.automation.remarks.video.recorder.VideoRecorder;
import com.codeborne.selenide.Selenide;
import helpers.UserForTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class ClinoneTest extends BaseBrowserInit {

//    @Rule
//    public VideoRule videoRule = new VideoRule();

    @Rule
    public TestName name = new TestName();

    private UserForTest user;
    private Header header;
    private RegisterAndLoginAccount registerAndLoginAccount;

    @Before
    public void setData() {
        user = new UserForTest();
        header = new Header();
        registerAndLoginAccount = new RegisterAndLoginAccount();
    }

//    @Test
//    public void captchaDoesNotLetTheUserSignUp() {
//        header.openCreateAccountTab();
//        registerAndLoginAccount.isLoadedSignUp()
//                .doRegistration(user);
//    }


    @Test
//    @Video
    public void signInAsValidUser() {
        Header.UserHeader userHeader = new Header.UserHeader();
        Header.UserHeader.DropDownMenu dropDownMenu = new Header.UserHeader.DropDownMenu();
        registerAndLoginAccount
                .isLoadedSignIn()
                .doLogin(user);
        new CookiePopUp().acceptCookiePopUp();
        userHeader.clickOnUserMenu();
        dropDownMenu.isLoaded().checkMenuItems().clickApprovalsMenuItem();
        System.out.println("!!!!!!!!!!!!!!");
    }


}
