package ru.stqa.pft.addressbook.tests;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() throws Exception {
    //----------------------------------------------------
    //перейти на страницу создания контакта
    app.getNavigationHelper().gotoContactAddNew();
    //заполнить форму какими-то данными
    app.getContactHelper().fillContactForm(new ContactData("Sidor", "Sidorov", "89001112233", "sidor@mail.ru"));
    //подтвердить создание конткта
    app.getContactHelper().submitContactCreation();
    //вернуться на гавную страницу
    app.getContactHelper().returnToHomePage();
  }
}
