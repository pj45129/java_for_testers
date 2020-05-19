package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{


  @Test
  public void testContactDeletion() throws Exception {
    // переход на главную страницу
    app.getNavigationHelper().gotoContactHomePage();
    // проверка на наличее контакта и создание его в случаи отсутсвия
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "89001112233", "ivan@mail.ru", "test1"), true);
    }
    // строчка ниже содержит список элементов ContactData
    List<ContactData> before = app.getContactHelper().getContactList();
    // выбор контакта
    app.getContactHelper().selectedContact(before.size() - 1);
    // удаление контакта
    app.getContactHelper().deleteSelectedContact();
    // возвращаемся на главную страницу

    // подсчитываем колличество контактов после создания нового элемента -
    List<ContactData> after = app.getContactHelper().getContactList();
    // - и сравниваем размер списка с ДО с ПОСЛЕ создания элемента
    Assert.assertEquals(after.size(), before.size() - 1);
  }
}
