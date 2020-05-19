package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class  ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void deleteSelectedContact() {
        acceptNextAlert = true;
        click(By.xpath("//input[@value='Delete']"));
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public void selectedContact(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
        //click(By.name("selected[]"));
        //acceptNextAlert = true;
    }

        // Этот кусок кода взятый из видео. Он заменяет код который закоментирован ниже.
    //------------------------------------------------------------------
    public void initContactCreation() { click(By.linkText("add new"));}

    public void submitContactCreation() {click(By.name("submit"));}

    public void initContactModification() {
        click(By.cssSelector("img[alt='Edit']"));
    }

    public void subContactModification() {
        click(By.name("update"));
    }

    public void createContact(ContactData contact, boolean b) {
        // прям с домашней страницы нажимаю на кнопку для добавления контакта
        initContactCreation();
        //заполнить форму какими-то данными
        fillContactForm(contact, true);
        //подтвердить создание конткта
        submitContactCreation();
        //вернуться на гавную страницу
        returnToHomePage();
    }

    // проверка на наличее Контактов
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    // создаем список элементов ContactData
    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        // выбрал By.name("entry") потому что By.cssSelector("tr") не подходит (захватывает лишние элементы)
        List<WebElement> elements = driver.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.getText();
            ContactData contact = new ContactData(name, name, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
