package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{


  @Test (enabled = false)
  public void testContactCreation() throws Exception {
    // переход на главную страницу
    app.goTo().gotoContactHomePage();
    // подсчитываем колличество элементов до создания
    // строчка ниже содержит список элементов ContactData
    List<ContactData> before = app.getContactHelper().getContactList();
    // подсчитываем колличество контактов до создания нового контакта
    // создаем локальную переменную с данными, которые будем вносить при модификации.
    ContactData contact = new ContactData("Ivan", "Ivanov", "89001112233", "ivan@mail.ru", "test1");
    app.getContactHelper().createContact(contact, true);
    // подсчитываем колличество контактов после создания нового элемента -
    List<ContactData> after = app.getContactHelper().getContactList();
    // - и сравниваем размер списка с ДО с ПОСЛЕ создания элемента
    Assert.assertEquals(after.size(), before.size() + 1);


    // создаем список с элементов, который появится после модификации.
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    // Сравниваем элементы без их порядка. Преобразовываем списки в множества и сравниваем их.
    Assert.assertEquals(before, after);
  }
}
