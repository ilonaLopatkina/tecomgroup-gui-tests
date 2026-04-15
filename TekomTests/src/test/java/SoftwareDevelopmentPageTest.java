import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.FormValidation;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Тесты страницы "Разработка ПО на заказ" (https://tecomgroup.ru/services/razrabotka-na-zakaz/)
 */
public class SoftwareDevelopmentPageTest extends BeforeAfter {

    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/services/razrabotka-na-zakaz/";
    }

    @DisplayName("Тест видимости элементов на странице разработки ПО")
    @Test
    public void pageVisibility() {
        // Заголовок "Разработка ПО на заказ"
        assertThat(p1.locator("h1[field='tn_text_1622986948166']")).isVisible();

        // "Как мы работаем?"
        assertThat(p1.locator("#rec465336517 [field='tn_text_1470209944682']")).isVisible();
        // "Вы"
        assertThat(p1.locator("#rec465336517 [field='tn_text_1656928152043']")).isVisible();
        // "Расскажите нам о своем проекте через форму обратной связи на сайте"
        assertThat(p1.locator("#rec465336517 [field='tn_text_1470210011265']")).isVisible();
        // "Мы"
        assertThat(p1.locator("#rec465336517 [field='tn_text_1656928257891']")).isVisible();
        assertThat(p1.locator("#rec465336517 li:has-text('Будем сдавать работу поэтапно')")).isVisible();
        // "В течение 6 месяцев после сдачи продукта мы будем всегда на связи для бесплатной технической поддержки"
        assertThat(p1.locator("#rec465336517 [field='tn_text_1656928613865']")).isVisible();

        // "Модели взаимодействия"
        assertThat(p1.locator("#rec465369769 [field='tn_text_1470209944682']")).isVisible();
        assertThat(p1.locator("#rec465366535 .t-heading:has-text('Fixed Price')")).isVisible();
        assertThat(p1.locator("#rec465366535 .t-descr:has-text('Бюджет на разработку')")).isVisible();

        // "У нас большой опыт"
        assertThat(p1.locator("#rec465384018 [field='tn_text_1470209944682']")).isVisible();
        // "Наш опыт и компетентность подтверждаются наличием сертификата о соответствии деятельности компании стандарту ISO 9001"
        assertThat(p1.locator("#rec465384018 [field='tn_text_1656935935401']")).isVisible();
        assertThat(p1.locator("#rec465390005 li[data-list='bullet']:has-text('Контрольно-измерительное оборудование')")).isVisible();
        assertThat(p1.locator("#rec465390005 li:has-text('Кодирование и передача видео')").last()).isVisible();

        assertThat(p1.locator("#rec466703121 span:has-text('Кто уже c нами работает')")).isVisible();

        // Карточка Pike Media Lab. "Разработка и внедрение системы контроля качества субтитров"
        assertThat(p1.locator("#rec466699841 [field='li_descr__1656254548287']")).isVisible();
    }

    @DisplayName("Тест кнопки 'Обсудить проект'")
    @Test
    public void discussProjectButton() {
        assertThat(p1.locator("#sbs-465325549-1657229006295 .tn-atom")).isVisible();
        // Клик по кнопке через JavaScript
        p1.evaluate("() => { document.querySelector('#sbs-465325549-1657229006295 .tn-atom').click(); }");
        p1.waitForTimeout(3000);

        // Проверка открытия формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();
    }

    @DisplayName("Тест кнопки 'Рассказать про проект'")
    @Test
    public void tellAboutProjectButton() {
        assertThat(p1.locator("Рассказать про проект")).isVisible();
        p1.locator("Рассказать про проект").click();
        p1.waitForTimeout(3000);

        // Проверка открытия формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();
    }

    @DisplayName("Тест кнопки перехода в проект Pike Media Lab")
    @Test
    public void pikeMediaLabButton() {
        //"Разработка и внедрение системы контроля качества субтитров"
        assertThat(p1.locator("#rec466699841 [field='li_descr__1656254548287']")).isVisible();
        p1.locator("#rec466699841 [field='li_descr__1656254548287']").click();

        // Проверка, что открылась страница проекта
        assertThat(p1.getByText("«Теком» разработал систему проверки субтитров для Pike Media Lab")).isVisible();
    }

    @DisplayName("Тест кнопки 'Больше кейсов'")
    @Test
    public void moreCasesButton() {
        assertThat(p1.getByText("Больше кейсов")).isVisible();
        p1.getByText("Больше кейсов").click();

        // Проверка перехода на страницу проектов. "Наши проекты"
        assertThat(p1.locator("[field='tn_text_1655296994613']")).isVisible();
    }
}