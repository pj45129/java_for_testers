package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.testng.Assert.assertTrue;

public class  ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public void selectedContact() {
        click(By.name("selected[]"));
        acceptNextAlert = true;
    }

        // Этот кусок кода взятый из видео. Он заменяет код который закоментирован ниже.
    //------------------------------------------------------------------
    public void initContactCreation() { click(By.linkText("add new"));}

    public void submitContactCreation() {click(By.name("submit"));}

    public void initContactModification() {
        click(By.cssSelector("img[alt='Edit']"));
    }

    public void subContactModification() {
        click(By.name("update"));
    }
    //------------------------------------------------------------------

    // Этот кусок кода мой
    //public void submitContactCreation() {
    //    click(By.xpath("(//input[@name='submit'])[2]"));
    //}
    //    public void initContactModification() {
//        click(By.xpath("//img[@alt='Edit']"));
//    }

//    public void subContactModification() {
//        click(By.xpath("(//input[@name='update'])[2]"));
//    }

}
