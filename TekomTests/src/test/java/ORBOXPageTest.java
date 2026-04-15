import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.FormValidation;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Тесты страницы "ORBOX" (https://tecomgroup.ru/products/orbox/)
 */
public class ORBOXPageTest extends BeforeAfter {

    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/products/orbox/";
    }

    @DisplayName("Тест видимости элементов на странице ORBOX")
    @Test
    public void pageVisibility() {
        // Открытие меню продуктов
        assertThat(p1.locator("a[href='#submenu:products']")).isVisible();
        p1.locator("a[href='#submenu:products']").hover();

        // Клик по ORBOX в меню
        assertThat(p1.locator("a[href='/products/orbox/']:visible")).isVisible();
        p1.locator("a[href='/products/orbox/']:visible").click();

        // Заголовок ORBOX
        assertThat(p1.locator("h1[field='tn_text_1470209944682']")).isVisible();
        assertThat(p1.getByText("автоматизировать проверку качества технических параметров медиафайлов")).isVisible();

        // Цифры 01 и 10
        assertThat(p1.locator("#rec463177215 [field='tn_text_1654701397989']")).isVisible();
        assertThat(p1.locator("#rec463177215 [field='tn_text_1656357177014']")).isVisible();

        // Блоки "Кому необходим ORBOX" и "Что вы получите"
        assertThat(p1.locator("#rec463199879 [field='tn_text_1656362912337']")).isVisible();
        assertThat(p1.locator("#rec466561170 [field='tn_text_1656362912337']")).isVisible();

        assertThat(p1.getByText("Модели использования")).isVisible();
        assertThat(p1.getByText("На сервере заказчика")).isVisible();
        assertThat(p1.getByText("Единовременная покупка лицензии, которая устанавливается на сервер заказчика")).isVisible();

        assertThat(p1.getByText("Интересны наши продукты или услуги? Напишите нам и мы обязательно подберем пакет услуг под ваши нужды!")).isVisible();
    }

    @DisplayName("Тест кнопки 'Обсудить проект'")
    @Test
    public void discussProjectButton() {
        // Клик по кнопке "Обсудить проект"
        assertThat(p1.locator("a.t-bgimg.loaded[href='#popup-form'][data-original='https://static.tildacdn.com/tild3363-6566-4937-b230-343564653936/bi_arrow-up-right.svg']")).isVisible();
        p1.locator("a.t-bgimg.loaded[href='#popup-form'][data-original='https://static.tildacdn.com/tild3363-6566-4937-b230-343564653936/bi_arrow-up-right.svg']").click();

        // Проверка открытия формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();
    }

    @DisplayName("Тест кнопки перехода на сайт ORBOX")
    @Test
    public void orboxWebsiteButton() {
        // Клик по кнопке перехода на сайт
        p1.locator("div[data-elem-id='1755780580206'] a").isVisible();
        p1.locator("div[data-elem-id='1755780580206'] a").click();

        // Проверка загрузки сайта ORBOX
        assertThat(p1.locator("a.tn-atom[href='/']")).isVisible();
    }

    @DisplayName("Тест кнопки 'Матч ТВ'")
    @Test
    public void matchTvButton() {
        assertThat(p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Матч ТВ"))).isVisible();
        p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Матч ТВ")).click();

        // Проверка, что открылась страница с кейсом
        assertThat(p1.locator("span:has-text('Матч ТВ')")).isVisible();
        assertThat(p1.getByText("Разработка и внедрение системы контроля качества медиафайлов для оптимизации процесса проверки контента федерального канала")).isVisible();
    }

    @DisplayName("Тест кнопки 'Больше кейсов'")
    @Test
    public void moreCasesButton() {
        assertThat(p1.locator("a.tn-atom >> text=Больше кейсов")).isVisible();
        Page newPage = p1.context().waitForPage(() -> p1.locator("a.tn-atom >> text=Больше кейсов").click());
        // Проверка заголовка на новой вкладке
        assertThat(newPage.locator("h1:has-text('Наши проекты')")).isVisible();
    }

    @DisplayName("Тест кнопки 'Написать'")
    @Test
    public void writeButton() {
        assertThat(p1.locator(".tn-atom__button-text:has-text('Написать')")).isVisible();
        p1.locator(".tn-atom__button-text:has-text('Написать')").click();

        // Проверка открытия формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();
    }
}