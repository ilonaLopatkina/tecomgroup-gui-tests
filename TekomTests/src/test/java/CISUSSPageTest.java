import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.FormValidation;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты страницы "ЦИСУСС" (https://tecomgroup.ru/products/cisuss/)
 */
public class CISUSSPageTest extends BeforeAfter {

    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/products/cisuss/";
    }

    @DisplayName("Тест видимости элементов на странице")
    @Test
    public void pageVisibility() {
        // Заголовок
        assertThat(p1.getByText("Российская система зонтичного мониторинга")).isVisible();
        assertThat(p1.getByText("Централизованный контроль оборудования, сервисов и ИТ-инфраструктуры")).isVisible();

        // Реестры и награды
        assertThat(p1.getByText("Продукт внесен в реестр")).isVisible();
        assertThat(p1.getByText("Премия В. Зворыкина «За лучший системный проект в области телерадиовещания», 2023")).isVisible();
        assertThat(p1.getByText("Сертификат соответствия от Astra Linux")).isVisible();

        // ЦИСУСС «NB XT EM»
        assertThat(p1.locator(".tn-atom[field='tn_text_1744103725046']:has-text('ЦИСУСС «NB XT EM»')")).isVisible();
        assertThat(p1.getByText("Совместимость c инфраструктурой на базе российских ОС (Astra Linux), СУБД (PostgreSQL) и ПО (Яндекс.Браузер, Kaspersky).")).isVisible();

        // Почему выбирают ЦИСУСС «NB XT EM»?
        assertThat(p1.getByText("Почему выбирают ЦИСУСС «NB XT EM»?")).isVisible();
        assertThat(p1.getByText("Доверие экспертов отрасли и заказчиков")).isVisible();
        assertThat(p1.getByText("Проекты по внедрению системы мониторинга ЦИСУСС отмечены престижными индустриальными наградами.")).isVisible();

        assertThat(p1.getByText("Как насчет комплексного подхода?")).isVisible();
        assertThat(p1.getByText("Узнайте, как мы обновили систему управления и мониторинга оборудования Телепорта для «Спутникового ТВ МТС»")).isVisible();

        // Модели использования
        assertThat(p1.locator("[field='tn_text_1744107332812']:has-text('Модели использования')")).isVisible();
        assertThat(p1.getByText("Стоимость системы ЦИСУСС зависит от выбранной модели использования и рассчитывается индивидуально. Заполните форму и мы свяжемся с вами для уточнения ваших задач.")).isVisible();
        assertThat(p1.getByText("На сервере заказчика")).isVisible();
        assertThat(p1.getByText("Минимальные требования*")).isVisible();
        assertThat(p1.getByText("*В случае установки всех модулей системы на 1 сервер")).isVisible();
        assertThat(p1.locator("img[alt='ОС']")).isVisible();

        assertThat(p1.getByText("ПО как услуга")).isVisible();

        // Новости
        assertThat(p1.locator("[field='tn_text_1744103725046']:has-text('Новости')")).isVisible();
        assertThat(p1.getByText("«Теком» представил кейс перехода крупнейшей в мире SDN-медиасети под управление отечественной системы")).isVisible();
    }

    @DisplayName("Тест кнопки 'Запросить демо'")
    @Test
    public void requestDemoButton() {
        assertThat(p1.locator(".tn-atom__button-text:has-text('Запросить демо')")).isVisible();
        p1.locator(".tn-atom__button-text:has-text('Запросить демо')").click();
        // Проверка открытия формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();
    }

    @DisplayName("Тест кнопки 'Презентация продукта'")
    @Test
    public void productPresentationButton() {
        assertThat(p1.getByText("Презентация продукта")).isVisible();
        String pdfPage = p1.waitForPopup(() -> p1.getByText("Презентация продукта").click()).url(); // Клик по кнопке, ожидание открытия PDF в новой вкладке, получение URL
        assertTrue(pdfPage.endsWith(".pdf")); // Проверка, что открылся PDF-файл
        assertTrue(pdfPage.contains("/docs/")); // Проверка, что файл находится в директории /docs/
    }

    @DisplayName("Тест кнопки 'Обсудить проект'")
    @Test
    public void discussProjectButton() {
        assertThat(p1.locator("#rec1216461136 .tn-atom__button-text:has-text('Обсудить проект')")).isVisible();
        p1.locator("#rec1216461136 .tn-atom__button-text:has-text('Обсудить проект')").click();
        // Проверка открытия формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();
    }

    @DisplayName("Тест кнопки 'Смотреть кейс'")
    @Test
    public void viewCaseButton() {
        assertThat(p1.locator(".tn-atom__button-text:has-text('Смотреть кейс')")).isVisible();
        Page newPage = p1.context().waitForPage(() -> p1.locator(".tn-atom__button-text:has-text('Смотреть кейс')").click());  // Клик по кнопке и ожидание открытие новой вкладки с кейсом
        assertThat(newPage.locator("h1:has-text('«Теком» реализовал систему автоматизированного переключения между телепортами Спутникового ТВ МТС')")).isVisible();
    }

    @DisplayName("Тест кнопки 'Руководство оператора'")
    @Test
    public void operatorManualButton() {
        assertThat(p1.getByText("Руководство оператора ЦИСУСС «NB XT EM»")).isVisible();
        String pdfPage = p1.waitForPopup(() -> p1.getByText("Руководство оператора ЦИСУСС «NB XT EM»").click()).url(); // Клик по кнопке, ожидание открытия PDF в новой вкладке, получение URL
        assertTrue(pdfPage.endsWith(".pdf")); // Проверка, что открылся PDF-файл
        assertTrue(pdfPage.contains("/docs/")); // Проверка, что файл находится в директории /docs/
    }
}