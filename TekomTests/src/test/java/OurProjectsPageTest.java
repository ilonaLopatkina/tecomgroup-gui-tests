import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Тесты страницы "Наши проекты" (https://tecomgroup.ru/projects/)
 */
public class OurProjectsPageTest extends BeforeAfter {

    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/projects/";
    }

    @DisplayName("Тест видимости элементов на странице проектов")
    @Test
    public void pageVisibility() {
        // Заголовок "Наши проекты"
        assertThat(p1.locator("[field='tn_text_1655296994613']")).isVisible();
        assertThat(p1.getByText("Сегодня нашими постоянными заказчиками являются ведущие ТВ-компании и операторы связи")).isVisible();
        assertThat(p1.locator("body")).containsText("и многие другие российские и зарубежные компании...");
    }

    @DisplayName("Тест перехода в проект 'Окно-ТВ'")
    @Test
    public void oknoTvProjectButton() {
        // Скролл до карточки "Окно-ТВ"
        p1.locator("#cardtitle2_462722294").scrollIntoViewIfNeeded();
        assertThat(p1.locator("#cardtitle2_462722294")).isVisible();
        p1.locator("#cardtitle2_462722294").click();

        // Проверка, что открылась страница проекта "Окно-ТВ"
        assertThat(p1.locator("h1[field='tn_text_1654001567676']")).containsText("окно-ТВ");
    }
}