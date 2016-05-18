import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RailwayReservation {
	static WebDriver wd;
	public static void main(String arg[]) {
		wd= new FirefoxDriver();
		wd.get("https://www.irctc.co.in/eticketing/loginHome.jsf");
		JOptionPane.showMessageDialog(null, "Enter Captcha shown on the page");
		String captchaData = JOptionPane.showInputDialog(null, "Enter Captcha shown on the page");
		WebElement username = wd.findElement(By.className("loginUserId"));
		username.sendKeys("avigogetit");
		WebElement password = wd.findElement(By.className("loginPassword"));
		password.sendKeys("aww111");
		WebElement captcha = wd.findElement(By.className("loginCaptcha"));
		captcha.sendKeys(captchaData);
		WebElement login = wd.findElement(By.id("loginbutton"));
		login.click();

		WebElement stationFrom = wd.findElement(By.id("jpform:fromStation"));
		stationFrom.sendKeys("PUNE JN - PUNE");
		WebElement stationTo = wd.findElement(By.id("jpform:toStation"));
		stationTo.sendKeys("LUCKNOW NE - LJN");
		String dateData = JOptionPane.showInputDialog(null, "Enter Date in dd-mm-yyyy format");
		WebElement stationDate = wd.findElement(By.id("jpform:journeyDateInputDate"));
		stationDate.sendKeys(dateData);

		WebElement submit = wd.findElement(By.id("jpform:jpsubmit"));
		submit.click();
		WebElement slLink = wd.findElement(By.id("cllink-12103-SL-3"));
		slLink.click();
		try { 
			//form[@id='avlAndFareForm']//div[contains(@id,'avlAndFareForm') and contains(@id,'_body')]//table//a[contains(@id,'-0') and text()='Book Now']
			//WebElement dateLink = wd.findElement(By.id("12103-SL-GN-0"));
			//WebElement dateLink = wd.findElement(By.xpath("//a[contains(SL-GN-0)]"));
			wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			WebElement dateLink =wd.findElement(By.xpath("//form[@id='avlAndFareForm']//div[contains(@id,'avlAndFareForm') and contains(@id,'_body')]//table//a[contains(@id,'-0') and text()='Book Now']"));
			dateLink.click();
			//Filling form details
			fillForm();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			
			//JOptionPane.showMessageDialog(null, "Tickets not available directing towards tatkal page");			
			//tatkalTicket();
		}		
	}
	public static void tatkalTicket(){
		try{
		WebElement premiumTatkal = wd.findElement(By.cssSelector("input[value='PT']"));
		premiumTatkal.click();
		WebElement slPTLink = wd.findElement(By.id("cllink-12103-SL-3"));
		slPTLink.click();
		fillForm();
		//JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Tatkal tickets still not available");
		}
	}
	public static void fillForm(){
		
		WebElement name=wd.findElement(By.xpath("//td[contains(@id,'addPassengerForm:psdetail:0')]//input[contains(@class,'psgn-name')]"));
		name.sendKeys("Avaneesh Tripathi");
		WebElement age=wd.findElement(By.id("addPassengerForm:psdetail:0:psgnAge"));
		age.sendKeys("21");
		WebElement gender=wd.findElement(By.id("addPassengerForm:psdetail:0:psgnGender"));
		gender.sendKeys("Male");
		WebElement captcha=wd.findElement(By.id("j_captcha"));
		String captchaData = JOptionPane.showInputDialog(null, "Enter Captcha displayed on the page below");
		captcha.sendKeys(captchaData);
		WebElement validate=wd.findElement(By.id("validate"));
		validate.click();
	}
}
