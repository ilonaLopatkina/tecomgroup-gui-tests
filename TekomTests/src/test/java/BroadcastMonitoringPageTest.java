import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.FormValidation;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Тесты страницы "Мониторинг вещания" (https://tecomgroup.ru/services/uslugi-monitoringa/)
 */
public class BroadcastMonitoringPageTest extends BeforeAfter {

    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/services/uslugi-monitoringa/";
    }

    @DisplayName("Проверка видимости элементов на странице")
    @Test
    public void pageVisibility() {
        // Заголовок
        assertThat(p1.locator("[field='tn_text_1622986948166']")).isVisible();

        // Описание
        assertThat(p1.getByText("доставки сигнала могут осуществляться по")).isVisible();

        assertThat(p1.locator("#rec462739745 li:has-text('Эфирные сети')")).isVisible();

        // Кнопка "перейти на сайт"
        assertThat(p1.locator(".tn-elem__4627397451657262408041 .tn-atom")).isVisible();

        // Архитектура решения
        assertThat(p1.locator("#rec462795676 [field='tn_text_1656276685952']")).isVisible();

        // Описание компонентов архитектуры
        assertThat(p1.locator("#rec462795676 [field='tn_text_1656276685968']")).isVisible();

        // Сервер агрегации
        assertThat(p1.locator("#rec462795676 [field='tn_text_1656277673474']")).isVisible();

        assertThat(p1.locator("#rec462891908 .t-card__title:has-text('On-Premise')")).isVisible();
        assertThat(p1.locator("#rec462891908 .t-card__descr:has-text('Единовременная покупка лицензии')")).isVisible();

        // Доступные уровни мониторинга
        assertThat(p1.locator("#rec462868387 [field='tn_text_1656017906607']")).isVisible();

        // QoS - Качество обслуживания
        assertThat(p1.locator("#sbs-462868387-1656019340188 .tn-atom")).isVisible();

        // Физический уровень
        assertThat(p1.locator("#rec462868387 [field='tn_text_1656018704644']")).isVisible();

        // 2х-уровневый
        assertThat(p1.locator("#rec462868387 [field='tn_text_1656018704644']")).isVisible();

        // QoE - Качество восприятия
        assertThat(p1.locator("#sbs-462868387-1656019770761 .tn-atom")).isVisible();

        assertThat(p1.locator("#rec462868387 li:has-text('Уровень данных')")).isVisible();

        // 5-уровневый
        assertThat(p1.locator("#rec462868387 [field='tn_text_1656327681319']")).isVisible();
    }

    @DisplayName("Кнопка 'Обсудить проект'")
    @Test
    public void discussProjectButton() {
        assertThat(p1.locator("a[data-original*='bi_arrow-up-right.svg']")).isVisible();
        p1.locator("a[data-original*='bi_arrow-up-right.svg']").click();

        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();
    }

    @DisplayName("Кнопка перехода на сайт Qligent")
    @Test
    public void qligentWebsiteButton() {
        assertThat(p1.locator(".tn-elem__4627397451657262408094 .tn-atom")).isVisible();

        // Открывается новая вкладка
        Page newPage = p1.waitForPopup(() ->
                p1.locator(".tn-elem__4627397451657262408094 .tn-atom").click()
        );

        newPage.waitForLoadState();

        // Проверка элемента на открывшейся странице
        assertThat(newPage.locator(".descr:has-text('Вы создаете контент — мы фокусируемся на качестве его доставки')")).isVisible();
    }

    @DisplayName("Кнопка 'Подобрать модель'")
    @Test
    public void selectModelButton() {
        assertThat(p1.getByText("Подобрать модель")).isVisible();
        p1.getByText("Подобрать модель").click();

        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();
    }
}