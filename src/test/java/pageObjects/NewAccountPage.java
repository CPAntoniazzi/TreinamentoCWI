package pageObjects;

import elementMapper.NewAccountPageElementMapper;
import org.openqa.selenium.support.PageFactory;
import utils.Browser;

public class NewAccountPage extends NewAccountPageElementMapper {
    public NewAccountPage(){
        PageFactory.initElements(Browser.getCurrentDriver(), this);
    }
}
