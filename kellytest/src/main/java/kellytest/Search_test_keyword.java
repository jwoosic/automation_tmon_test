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




public class Search_test_keyword {
   public static AppiumDriver<AndroidElement> driver;
   public static DesiredCapabilities capabilities = new DesiredCapabilities();
    public static final String TMON_ID = "woosikjeon001";
    public static final String TMON_PW = "tmon123!";
    public static final String TMON_WrongPW = "tmn143!";
    
    static AndroidDriver<MobileElement> wd;

   @BeforeClass
   public void setUp() throws Exception {
       capabilities.setCapability("appium-version", "{1.18.2}");
       capabilities.setCapability("automationName", "UiAutomator2");
       //aapt 명령어를 통해 입력 할 예정(테스트 할 앱의 패키지 명)
      capabilities.setCapability("appPackage", "com.tmon.working");
   
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
   
   
    //검색화면 이동
    private static void moveToSearch() {
        driver.findElement(By.id("com.tmon.working:id/tabbar_search_layout")).click();
        System.out.println("검색창 이동 성공");
    }
     
 
    //검색 키워드 입력 (검색 결과 있음)
    private static void searchKeywordY() {
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='search_input']")).sendKeys("마스크");
        System.out.println("검색 키워드 입력 성공");
    }
     
    //검색 키워드 입력 (검색 결과 없음)
    private static void searchKeywordN() {
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='search_input']")).sendKeys("ㅁㅇㄴㄹㅈㄷㄱ");
        System.out.println("검색 키워드 입력 성공");
    }
     
    //검색어 지우기
    private static void searchReset() {
        driver.findElement(By.xpath("//android.widget.Button[@text='검색어 지우기']")).click();
        System.out.println("검색어 삭제 성공");
    }
     
    //검색 버튼 선택
    private static void searchbtn() {
        driver.findElement(By.xpath("//android.widget.Button[@text='검색']")).click();
    }
 
    @Test      
    // 검색 결과 있는 경우
    public void Test_001() throws Exception {
        moveToSearch();
        searchKeywordY();
        searchbtn();
        Thread.sleep(10000);
        System.out.println("검색 결과 진입 성공");
        String result = driver.findElement(By.xpath("//android.view.View[contains(@text, '연관')]")).getText();
        Assert.assertEquals(result, "연관");
        System.out.println("검색 결과 있는 경우 확인");
    }  
     
    @Test
    // 검색 결과 없는 경우
    public void Test_002() throws Exception {
        searchReset();
        Thread.sleep(5000);
        searchKeywordN();
        searchbtn();
        Thread.sleep(5000);
        String result = driver.findElement(By.xpath("//android.view.View[contains(@text, '0개')]")).getText();
        Assert.assertEquals(result, "0개");
        System.out.println("검색 결과 없는 경우 확인");
    }  
   
   @AfterClass
   public void end() throws Exception {
      driver.quit();
   }

}