package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() throws Exception {
    // переход на главную страницу
    app.getNavigationHelper().gotoContactHomePage();

//    //перейти на страницу создания контакта. Это тоже самое действие что и app.getContactHelper().initContactCreation();
//    app.getNavigationHelper().gotoContactAddNew();

    // прям с домашней страницы нажимаю на кнопку для добавления контакта
    app.getContactHelper().initContactCreation();
    //заполнить форму какими-то данными
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "89001112233", "ivan@mail.ru", "test4"), true);
    //подтвердить создание конткта
    app.getContactHelper().submitContactCreation();
    //вернуться на гавную страницу
    app.getContactHelper().returnToHomePage();
  }
}
