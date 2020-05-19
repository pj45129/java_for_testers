package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() throws Exception {
    // переход на главную страницу
    app.getNavigationHelper().gotoContactHomePage();
    // подсчитываем колличество контактов до создания нового контакта
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "89001112233", "ivan@mail.ru", "test1"), true);
    // подсчитываем колличество контактов после создания контакта
    // и сравниваем колличество контактов с ДО с ПОСЛЕ
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }
}
