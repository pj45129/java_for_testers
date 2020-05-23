package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{


  @Test //(enabled = false)
  public void testContactCreation() throws Exception {
      // переход на главную страницу
      app.goTo().contactHomePage();
      // подсчитываем колличество элементов до создания
      // строчка ниже содержит список элементов ContactData
      Contacts before = app.contact().all();
      // подсчитываем колличество контактов до создания нового контакта
      // создаем локальную переменную с данными, которые будем вносить при модификации.
      ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Ivanov").withMobilePhone("89001112233").withEmail("ivan@mail.ru").withGroup("test1");
      app.contact().create(contact, true);
      // подсчитываем колличество контактов после создания нового элемента -
      Contacts after = app.contact().all();
      // - и сравниваем размер списка с ДО с ПОСЛЕ создания элемента
      assertThat(after.size(), equalTo(before.size() + 1));
      // Сравниваем элементы без их порядка. Преобразовываем списки в множества и сравниваем их.
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream()
                      .mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
