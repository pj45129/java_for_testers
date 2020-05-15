package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModification() {

        //
        // ПОЧЕМУ-ТО САЙТ НЕ ВНОСИТ ИЗМЕНЕНИЯ А УДАЛЯЕТ КОНТАКТ
        //

        // переход на главную страницу
        app.getNavigationHelper().gotoContactHomePage();
        // выбор контакта
        app.getContactHelper().selectedContact();
        // нажимаем на кнопку редактирования
        app.getContactHelper().initContactModification();
        // вносим данные
        app.getContactHelper().fillContactForm(new ContactData("Sidor", "Sidorov", "89001112233", "sidor@mail.ru"));
        // нажимаем добавить изменения
        app.getContactHelper().subContactModification();
    }
}
