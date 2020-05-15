package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{


  @Test
  public void testContactDeletion() throws Exception {
    // переход на главную страницу
    app.getNavigationHelper().gotoContactHomePage();
    // выбор контакта
    app.getContactHelper().selectedContact();
    // удаление контакта
    app.getContactHelper().deleteSelectedContact();
  }
}
