package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;


import static org.testng.Assert.assertTrue;

public class  ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void returnToHomePage() {
        // переход на домашнюю страницу по нажатию на всплывающее окно.
//        click(By.linkText("home page"));

        // переход на главную страницу наимая на видемую ссылку
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobilePhone());
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

    public void selectedContactById(int id) {
        driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactCreation() { click(By.linkText("add new"));}

    public void submitContactCreation() {click(By.name("submit"));}

    public void initContactModification() {
        click(By.cssSelector("img[alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact, boolean b) {
        // прям с домашней страницы нажимаю на кнопку для добавления контакта
        initContactCreation();
        //заполнить форму какими-то данными
        fillContactForm(contact, true);
        //подтвердить создание конткта
        submitContactCreation();
        //вернуться на гавную страницу
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        // при выбор контакта (можно обойтись без проставления галочки)
        selectedContactById(contact.getId());
        // нажимаем на кнопку редактирования
        initContactModification();
        // вносим данные
        fillContactForm(contact, false);
        // нажимаем добавить изменения
        submitContactModification();
        //вернуться на гавную страницу
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        // выбор контакта
        selectedContactById(contact.getId());
        // удаление контакта
        deleteSelectedContact();
        /** возвращаемся на главную страницу.
         *!!!Не удивляйся что тест сдесь падает, потому что ранее я переходи на главную через класс в навигации.
         * * Потому что, я нажимаю на абсолютно другую кнопку contactHomePage()
         */
        returnToHomePage();
    }

    // проверка на наличее Контактов
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    // создаем список элементов ContactData
    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> rows = driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        driver.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = driver.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

        //Ниже несколько вариантов поска объкта на веб страние каждый из которых замещает 4-ре строчки сверху.
        // 1-й вариант
//        driver.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
        // 2-й вариант
//        driver.findElement(By.xpath(String.format("//tr[.input[@value='%s']]/td[8]/a", id))).click();
        // 3-й вариант
//        driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
}
