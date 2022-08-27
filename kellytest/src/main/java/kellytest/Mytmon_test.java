package kellytest;


import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;




public class Mytmon_test {
   public static AppiumDriver<AndroidElement> driver;
   public static DesiredCapabilities capabilities = new DesiredCapabilities();
    public static final String TMON_ID = "fhwm21";
    public static final String TMON_PW = "abc123";
    public static final String TMON_WrongPW = "tmn123!";
    
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
      capabilities.setCapability("udid", "18281FDF60053U");
        
        // 키보드
      capabilities.setCapability("unicodeKeyboard", true);
      
      //리셋에 대한 내용
      capabilities.setCapability("noReset", true);
      capabilities.setCapability("autoDismissAlerts", true);
      capabilities.setCapability("resetKeyboard", true);
      driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   }
   
   
   // 마이티몬으로 이동
    private static void moveToMyTmon() {
       driver.findElement(By.id("com.tmon:id/tabbar_mytmon_layout")).click();   
        System.out.println("move to mytmon.");
    }    
    
    
    //로그인하기
    private static void logIn() {
       //ID 입력
        driver.findElement(By.id("com.tmon:id/username")).setValue(TMON_ID);
        //PW 입력
        driver.findElement(By.id("com.tmon:id/password")).setValue(TMON_PW);
        //로그인 버튼 클릭
        driver.findElement(By.id("com.tmon:id/btn_login")).click();
    }
    
   
   // TC1_로그인 실패
   @Test      
   public void TC1() throws Exception {
      //마이티몬으로 이동
      moveToMyTmon();
      //로그인 버튼 클릭
      driver.findElement(By.id("com.tmon:id/btnLogin")).click();
      //자동로그인 해제 
      driver.findElement(By.id("com.tmon:id/btn_autologin")).click();
      //ID 입력
      driver.findElement(By.id("com.tmon:id/username")).setValue(TMON_ID);
      //PW 입력(잘못된 값)
      driver.findElement(By.id("com.tmon:id/password")).setValue(TMON_WrongPW);
      //로그인 버튼 클릭
      driver.findElement(By.id("com.tmon:id/btn_login")).click();
      
      //TC 1 성공 메시지 출력
      System.out.println("TC_1. PW 잘못 입력 하여 login 실패 케이스 실행");
   }
   
   
   // 로그인 성공
   @Test
   public void TC2() throws Exception {
      
      logIn();
      Thread.sleep(2000);
      
      //TC2 성공 메시지 출력
      System.out.println("TC_2.login 성공 케이스 실행");
   }
   
   // 로그아웃
   @Test
   public void TC3() throws Exception{
      //설정 페이지
      driver.findElement(By.id("com.tmon:id/slimNavibarSetting")).click();
      //로그인 페이지로 
      driver.findElement(By.id("com.tmon:id/username")).click();
      //로그 아웃 버튼
      driver.findElement(By.id("com.tmon:id/btn_logout")).click();
      
      Thread.sleep(2000);
      
      //TC2 성공 메시지 출력
      System.out.println("TC_3.logout 케이스 실행");
      
      
   }
   
   @AfterClass
   public void end() throws Exception {
      driver.quit();
   }

}