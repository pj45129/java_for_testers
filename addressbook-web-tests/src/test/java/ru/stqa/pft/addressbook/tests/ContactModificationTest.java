package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModification() {

        // переход на главную страницу
        app.getNavigationHelper().gotoContactHomePage();
        // проверка на наличее контакта и создание его в случаи отсутсвия
        if (! app.getContactHelper().isTherAContact()) {
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "89001112233", "ivan@mail.ru", "test1"), true);
        }

        // выбор контакта (можно обойтись без проставления галочки)
        //app.getContactHelper().selectedContact();


        // нажимаем на кнопку редактирования
        app.getContactHelper().initContactModification();
        // вносим данные
        app.getContactHelper().fillContactForm(new ContactData("Sidor", "Sidorov", "89001112233", "sidor@mail.ru", null), false);
        // нажимаем добавить изменения
        app.getContactHelper().subContactModification();
        //вернуться на гавную страницу
        app.getContactHelper().returnToHomePage();
    }
}
