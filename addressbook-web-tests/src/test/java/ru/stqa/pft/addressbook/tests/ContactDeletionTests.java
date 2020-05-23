package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        // переход на главную страницу
        app.goTo().contactHomePage();
        // проверка на наличее контакта и создание его в случаи отсутсвия
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Ivan").withFirstname("Ivanov").withMobilePhone("89001112233").withEmail("ivan@mail.ru").withGroup("test1"), true);
        }
    }

    @Test //(enabled = false)
    public void testContactDeletion() throws Exception {
        // строчка ниже содержит список элементов ContactData
        Contacts before = app.contact().all();
        ContactData deletedContact =before.iterator().next();
        app.contact().delete(deletedContact);
        // подсчитываем колличество контактов после создания нового элемента -
        Contacts after = app.contact().all();
        // - и сравниваем размер списка с ДО с ПОСЛЕ создания элемента
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));

    }
}
