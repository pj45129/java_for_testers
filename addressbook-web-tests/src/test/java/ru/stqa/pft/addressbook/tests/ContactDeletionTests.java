package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {


    gotoContactHomePage();
    driver.findElement(By.id("1")).click();
    acceptNextAlert = true;
    driver.findElement(By.xpath("//input[@value='Delete']")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  private void gotoContactHomePage() {
    driver.findElement(By.linkText("home")).click();
  }

}
