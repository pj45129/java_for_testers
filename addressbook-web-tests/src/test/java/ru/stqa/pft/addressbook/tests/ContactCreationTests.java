package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() throws Exception {
    // переход на главную страницу
    app.getNavigationHelper().gotoContactHomePage();
    app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "89001112233", "ivan@mail.ru", "test4"), true);
  }
}
