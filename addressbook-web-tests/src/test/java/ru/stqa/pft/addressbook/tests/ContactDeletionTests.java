package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{


  @Test
  public void testContactDeletion() throws Exception {
    // переход на главную страницу
    app.getNavigationHelper().gotoContactHomePage();
    // проверка на наличее контакта и создание его в случаи отсутсвия
    if (! app.getContactHelper().isTherAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "89001112233", "ivan@mail.ru", "test1"), true);
    }
    // выбор контакта
    app.getContactHelper().selectedContact();
    // удаление контакта
    app.getContactHelper().deleteSelectedContact();
  }
}
