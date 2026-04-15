import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Тестовый класс для проверки страницы "О компании" (https://tecomgroup.ru/company/)
 * Содержит автотест для проверки видимости элементов интерфейса
 */
public class AboutСompanyPageTest extends BeforeAfter {

    /**
     * Проверяет видимость основных элементов на странице компании:
     * - Текстовые блоки с информацией о наградах
     * - Блоки с описанием преимуществ
     * - Блок руководства компании
     * - Информация о президенте
     * - Иконки и графические элементы
     */
    @DisplayName("Проверка видимости элементов на странице")
    @Test
    public void pageVisibility() {
        // Проверка видимости текстового элемента по полю tn_text
        assertThat(p1.locator("#rec463960722 [field='tn_text_1657112297006']")).isVisible();

        // Проверка видимости блока с информацией о премии им. В. Зворыкина
        assertThat(p1.getByText("Премия им. В. Зворыкина «За достижения в системной интеграции с использованием новейших технологий в области телерадиовещания»")).isVisible();

        // Проверка видимости блока с информацией о защите интеллектуальной собственности
        assertThat(p1.getByText("Защита интеллектуальной собственности заказчика")).isVisible();

        // Проверка видимости заголовка блока руководства компании
        assertThat(p1.getByText("Руководство компании")).isVisible();

        // Проверка видимости имени президента компании
        assertThat(p1.getByText("Евгений Щемелев")).isVisible();

        // Проверка видимости должности президента
        assertThat(p1.getByText("Президент группы компаний «Теком»")).isVisible();

        // Проверка видимости иконки с указанным графическим файлом
        assertThat(p1.locator("div[data-original*='Frame_2.svg']")).isVisible();
    }

    /**
     * Возвращает URL страницы для тестирования
     * @return строка с URL страницы "О компании"
     */
    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/company/";
    }
}