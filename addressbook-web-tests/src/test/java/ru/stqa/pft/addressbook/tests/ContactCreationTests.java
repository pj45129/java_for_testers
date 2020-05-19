package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() throws Exception {
    // переход на главную страницу
    app.getNavigationHelper().gotoContactHomePage();
    // подсчитываем колличество элементов до создания
    // строчка ниже содержит список элементов ContactData
    List<ContactData> before = app.getContactHelper().getContactList();
    // подсчитываем колличество контактов до создания нового контакта

    app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "89001112233", "ivan@mail.ru", "test1"), true);
    // подсчитываем колличество контактов после создания нового элемента -
    List<ContactData> after = app.getContactHelper().getContactList();
    // - и сравниваем размер списка с ДО с ПОСЛЕ создания элемента
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
