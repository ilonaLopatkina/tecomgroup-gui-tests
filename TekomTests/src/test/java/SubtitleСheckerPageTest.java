import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.FormValidation;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Тесты страницы "Subtitle Checker" (https://tecomgroup.ru/products/subtitle-checker/)
 */
public class SubtitleСheckerPageTest extends BeforeAfter {

    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/products/subtitle-checker/";
    }

    @DisplayName("Тест видимости элементов на странице Subtitle Checker")
    @Test
    public void pageVisibility() {
        assertThat(p1.locator("h1:has-text('Проверка субтитров/ Subtitle checker')")).isVisible();
        assertThat(p1.locator("#rec463875230 span:has-text('Основной функционал Subtitle Checker')")).isVisible();

        // Пункты списка
        assertThat(p1.locator("#rec463875230 li:has-text('Работа с файлами формата STL')")).isVisible();
        assertThat(p1.locator("#rec463875230 li:has-text('Проверка скорости чтения символов в минуту')")).isVisible();
        assertThat(p1.locator("#rec463875230 li:has-text('Просмотр файлов субтитров')")).isVisible();

        // Заголовок "Новости"
        assertThat(p1.locator("[field='tn_text_1744103725046']:has-text('Новости')")).isVisible();

        // Ссылка на новость
        assertThat(p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Теком на Пиринговом форуме 2024"))).isVisible();
    }

    @DisplayName("Тест кнопки 'Обсудить проект'")
    @Test
    public void discussProjectButton() {
        // Кнопка "Обсудить проект"
        assertThat(p1.locator("#sbs-463860815-1657198868949 a")).isVisible();
        p1.locator("#sbs-463860815-1657198868949 a").click(new Locator.ClickOptions().setForce(true));

        // Проверка открытия формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();
    }
}