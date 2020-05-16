package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void gotoGroupPage() {
        if (isElementPresent(By.tagName("h1"))
                && driver.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void gotoContactHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }


//    //-----------------------------------------------------------------
//    //Ниже код для перехода во вкладку добавления контакта.
//    //Он же дублирует метод public void initContactCreation() { click(By.linkText("add new"));} в классе ContactHelper
//    public void gotoContactAddNew() {
//        click(By.linkText("add new"));
//    }
//    //-----------------------------------------------------------------

}
