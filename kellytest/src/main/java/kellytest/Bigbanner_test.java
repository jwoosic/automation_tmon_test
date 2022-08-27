package kellytest;


import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;



public class Bigbanner_test {
   public static AppiumDriver<AndroidElement> driver;
   public static DesiredCapabilities capabilities = new DesiredCapabilities();
    
    static AndroidDriver<MobileElement> wd;

   @BeforeClass
   public void setUp() throws Exception {
       capabilities.setCapability("appium-version", "{1.18.2}");
       capabilities.setCapability("automationName", "UiAutomator2");
       //aapt 명령어를 통해 입력 할 예정(테스트 할 앱의 패키지 명)
      capabilities.setCapability("appPackage", "com.tmon");
   
        //aapt 명령어를 통해 입력 할 예정(테스트 할 앱의 launchable-activity 정보)
      capabilities.setCapability("appActivity", "com.tmon.splash.SplashActivity"); 
        
        //자신의 디바이스 명을 설정. 아무 이름이나 적어도 괜찮습니다.
      capabilities.setCapability("deviceName", "unauthorized");
        
        //adb 명령어를 통해 입력 할 예정(자기 디바이스의 udid)
      capabilities.setCapability("udid", "R3CMB0NH2XY");
        
        // 키보드
      capabilities.setCapability("unicodeKeyboard", true);
      
      //리셋에 대한 내용
      capabilities.setCapability("noReset", true);
      capabilities.setCapability("autoDismissAlerts", true);
      capabilities.setCapability("resetKeyboard", true);
      driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   }
   
   
    //빅배너 노출 체크
    private static void bigbannerCheck() {
        //driver.findElement(By.xpath("//android.widget.ImageView[@NAF='[true]'")).isDisplayed();
        //driver.findElementByAccessibilityId("//android.widget.FrameLayout[@index='[1]'").isDisplayed();
        String result = driver.findElement(By.xpath("//android.widget.FrameLayout")).getText();
        Assert.assertNotNull(result);
        System.out.println("빅배너 노출 확인");
    }
     
 
    //빅배너 더보기 진입
    private static void bigbannerDetail() {
        driver.findElement(By.id("com.tmon:id/container_go_banner_detail")).click();
        System.out.println("빅배너 더보기 진입");
    }
     
    //첫 번째 빅배너 진입
    private static void bigbannerSelect() {
        driver.findElement(By.xpath("//android.view.ViewGroup[@bounds='[39,407][1041,938]']")).click();
        System.out.println("빅배너 진입 성공");
    }
 
    @Test      
    // 빅배너 노출 여부 확인
    public void bigbanner_test_001() throws Exception {
    	bigbannerCheck();
    }  
     
    @Test
    // 빅배너 진입 확인
    public void bigbanner_test_002() throws Exception {
    	bigbannerDetail();
    	bigbannerSelect();
        String result = driver.findElement(By.xpath("//android.widget.TextView")).getText();
        Assert.assertNotNull(result);
        System.out.println("빅배너 진입 확인");
    }  
   
   @AfterClass
   public void end() throws Exception {
      driver.quit();
   }

}