import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.FormValidation;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты страницы "Контакты" (https://tecomgroup.ru/contacts/)
 */
public class ContactsPageTest extends BeforeAfter {

    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/contacts/";
    }

    @DisplayName("Тест видимости элементов на странице")
    @Test
    public void pageVisibility() {
        // Заголовок "Всегда на связи и в эфире"
        assertThat(p1.locator("h1.tn-atom[field='tn_text_1770806661885000001']")).isVisible();

        // Подзаголовок "Будем рады обсудить ваш проект"
        assertThat(p1.locator("div.tn-atom[field='tn_text_1771487505829000002']")).isVisible();

        assertThat(p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Эл.адрес: tecom.info@"))).isVisible();

        // "Всегда на связи и в эфире"
        assertThat(p1.locator("div.tn-atom[field='tn_text_1771489604748000004']")).isVisible();

        // Заголовок блока "Реквизиты компании"
        assertThat(p1.locator("div.tn-atom[field='tn_text_1470209944682']")).isVisible();
        // Подзаголовок "Полное наименование"
        assertThat(p1.locator("div.tn-atom[field='tn_text_1684236959113']")).isVisible();
        // Наименование компании
        assertThat(p1.locator("div.tn-atom[field='tn_text_1470210011265']")).isVisible();
        // Название банка "ПАО "Банк ПСБ""
        assertThat(p1.locator("div.tn-atom[field='tn_text_1684237287481']")).isVisible();
        // Филиал банка "Приволжский филиал ПАО "Банк ПСБ""
        assertThat(p1.locator("div.tn-atom[field='tn_text_1685440236476']")).isVisible();
    }

    @DisplayName("Тест формы обратной связи")
    @Test
    public void fillForm() {
        // Скролл до формы
        p1.locator(".b24-form:visible").scrollIntoViewIfNeeded();

        // Проверка заголовка формы
        assertThat(p1.locator(".b24-form:visible .b24-form-header-title")).isVisible();

        // Проверка видимости всех элементов формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisibleNoСlosure();
    }

    @DisplayName("Тест ссылки 'Скачать реквизиты PDF'")
    @Test
    public void downloadPdfButton() {
        // Проверка видимости ссылки
        assertThat(p1.locator("a").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Скачать PDF$")))).isVisible();

        // Клик по ссылке, ожидание открытия PDF в новой вкладке
        String pdfPage = p1.waitForPopup(() ->
                p1.locator("a").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Скачать PDF$"))).click()
        ).url();

        // Проверка, что открылся PDF-файл в директории /docs/
        assertTrue(pdfPage.endsWith(".pdf"));
        assertTrue(pdfPage.contains("/docs/"));
    }
}