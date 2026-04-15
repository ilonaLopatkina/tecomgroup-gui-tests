import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.FormValidation;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Тесты главной страницы (https://tecomgroup.ru/)
 */
public class HomePageTest extends BeforeAfter {

    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/";
    }

    @DisplayName("Тест видимости элементов на главной странице")
    @Test
    public void pageLoadTest() {
        // Логотип
        assertThat(p1.locator("img[src='https://static.tildacdn.com/tild3336-3962-4165-b037-353765653939/logo.svg']")).isVisible();

        // Меню навигации
        assertThat(p1.locator("header nav a:has-text('Продукты')")).isVisible();
        assertThat(p1.locator("header nav a:has-text('Контакты')")).isVisible();
        assertThat(p1.locator("header nav a:has-text('Услуги')")).isVisible();
        assertThat(p1.locator("span.tn-atom__button-text:has-text('Связаться с нами')")).isVisible();

        // Блок "Инновационные проекты"
        assertThat(p1.locator("[field='tn_text_1747988462874']")).isVisible();
        assertThat(p1.locator("a.tn-atom[href='#rec1175174316']").first()).isVisible();

        assertThat(p1.getByText("Наши продукты внесены в реестр отечественного ПО Минцифры РФ")).isVisible();
        assertThat(p1.getByText("30+")).isVisible();
        assertThat(p1.getByText("лет на рынке")).isVisible();

        assertThat(p1.getByText("Технологии, которые работают каждый день")).isVisible();
        assertThat(p1.locator("a[href='https://tecomgroup.ru/products/profinder/']").first()).isVisible(); //Кнопка ProFinder
        assertThat(p1.getByText("Получите персональную консультацию наших специалистов")).isVisible();
        assertThat(p1.locator("a[href='#popup-expert']")).isVisible(); //Кнопка "Связаться с экспертом"

        assertThat(p1.getByText("Наши проекты").nth(2)).isVisible();
        assertThat(p1.locator("a[href='https://tecomgroup.ru/projects/rostelekom-rossiya/']").first()).isVisible(); //Блок "Ростелеком"
        assertThat(p1.getByText("Больше проектов")).isVisible();
        assertThat(p1.locator("img[imgfield='tn_img_1751376526547']")).isVisible(); //Стрелка около надписи "Больше проектов"

        assertThat(p1.getByText("Партнеры")).isVisible();
        assertThat(p1.locator("img[imgfield='tn_img_1752668613687']")).isVisible(); //Логотип "DNK"
        assertThat(p1.getByText("Обсудить проект")).isVisible();

        assertThat(p1.getByText("Будьте в курсе наших новостей, подписывайтесь и оставайтесь с нами")).isVisible();
        assertThat(p1.getByPlaceholder("Ваш e-mail")).isVisible();
        assertThat(p1.locator(".t-checkbox__indicator")).isVisible(); //Чекбокс
        assertThat(p1.locator("span.t-checkbox__labeltext")).isVisible(); //Надпись "Я даю согласие на получение..."
        assertThat(p1.getByText("Подписаться")).isVisible();


        assertThat(p1.locator("img[imgfield='tn_img_1744189573181']")).isVisible(); //Лого ВК
        assertThat(p1.getByText("Тел.: +7 831 262 10 11")).isVisible();
        assertThat(p1.getByText("Эл.адрес: tecom.info@tecomgroup.ru")).isVisible();
        assertThat(p1.locator("#rec455856425")).isVisible();


        assertThat(p1.getByText("Политика конфиденциальности")).isVisible();
        assertThat(p1.locator("a[href='https://tecomgroup.ru/privacypolicy']").first()).isVisible(); //Согласие на обработку персональных данных
    }

    @DisplayName("Тест выпадающих меню навигации")
    @Test
    public void menuTest() {
        // Продукты
        assertThat(p1.locator("a[href='#submenu:products']")).isVisible();
        p1.locator("a[href='#submenu:products']").hover();
        assertThat(p1.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Subtitle Checker"))).isVisible();

        // Решения
        assertThat(p1.locator("a[href='#submenu:solutions']")).isVisible();
        p1.locator("a[href='#submenu:solutions']").hover();
        assertThat(p1.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("SDN"))).isVisible();

        // Услуги
        assertThat(p1.locator("a[href='#submenu:service']")).isVisible();
        p1.locator("a[href='#submenu:service']").hover();
        assertThat(p1.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Разработка ПО на заказ"))).isVisible();

        // Компания
        assertThat(p1.locator("a[href='#submenu:company']")).isVisible();
        p1.locator("a[href='#submenu:company']").hover();
        assertThat(p1.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Наши проекты"))).isVisible();

        // Новости
        assertThat(p1.locator("a[href='#submenu:news']")).isVisible();
        p1.locator("a[href='#submenu:news']").hover();
        assertThat(p1.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("2026"))).isVisible();
    }

    @DisplayName("Тест формы 'Связаться с нами' - корректные данные")
    @Test
    public void contactUsFormValid() {
        p1.locator("span.tn-atom__button-text:has-text('Связаться с нами')").click();
        p1.waitForTimeout(1000);

        // Проверка видимости формы
        FormValidation contactUsForm = new FormValidation(p1);
        contactUsForm.assertVisible();

        // Заполнение полей
        p1.locator("input[name='name']:visible").fill("Иванов Иван Иванович");
        p1.locator("input.b24-form-control:visible").nth(1).fill("Тестовые данные"); //Поле "Компания или сайт"
        p1.locator("input[name='email']:visible").first().fill("23ist42@gmail.com");
        p1.locator("input[name='phone']:visible").fill("88005353535");
        p1.locator("input.b24-form-control:visible").nth(4).fill("Тестовый запрос"); //Поле "Ваш запрос"

        // Чекбоксы
        p1.locator("input[type='checkbox']:visible").nth(0).click();
        p1.waitForTimeout(500);
        p1.locator("input[type='checkbox']:visible").nth(1).click();
        p1.waitForTimeout(500);

        // Отправка
        p1.locator("button[type='submit']:visible").click(); //Кнопка "Отправить"
        p1.waitForTimeout(3000);

        // Проверка успешной отправки
        assertThat(p1.getByText("Форма отправлена успешно")).isVisible();
    }

    @DisplayName("Тест формы 'Связаться с нами' - некорректный email")
    @Test
    public void contactUsFormInvalid() {
        p1.locator("span.tn-atom__button-text:has-text('Связаться с нами')").click();
        p1.waitForTimeout(1000);

        // Проверка видимости формы
        FormValidation contactUsForm = new FormValidation(p1);
        contactUsForm.assertVisible();

        // Заполнение полей
        p1.locator("input[name='name']:visible").fill("Иванов Иван Иванович");
        p1.waitForTimeout(500);
        p1.locator("input.b24-form-control:visible").nth(1).fill("Тестовые данные"); //Поле "Компания или сайт"
        p1.waitForTimeout(500);
        p1.locator("input[name='email']:visible").first().fill("23ist42@@gmail.com");
        p1.waitForTimeout(500);
        p1.locator("input[name='phone']:visible").fill("88005353535");
        p1.waitForTimeout(500);
        p1.locator("input.b24-form-control:visible").nth(4).fill("Тестовый запрос"); //Поле "Ваш запрос"
        p1.waitForTimeout(500);

        // Чекбоксы
        p1.locator("input[type='checkbox']:visible").nth(0).click();
        p1.waitForTimeout(500);
        p1.locator("input[type='checkbox']:visible").nth(1).click();
        p1.waitForTimeout(500);

        // Отправка
        p1.locator("button[type='submit']:visible").click();
        p1.waitForTimeout(3000);

        // Проверка успешной отправки
        assertThat(p1.getByText("Форма отправлена успешно")).isVisible();
    }

    @DisplayName("Тест поиска - существующий запрос")
    @Test
    public void searchExistingQuery() {
        assertThat(p1.locator("img[imgfield='tn_img_1684324342323']")).isVisible(); //Иконка поиска
        p1.locator("img[imgfield='tn_img_1684324342323']").click();

        assertThat(p1.getByPlaceholder("Я ищу")).isVisible();
        p1.getByPlaceholder("Я ищу").pressSequentially("java", new Locator.PressSequentiallyOptions().setDelay(100)); //Ввод запроса

        assertThat(p1.getByText("4 результата по запросу: java")).isVisible();
    }

    @DisplayName("Тест поиска - пустой запрос")
    @Test
    public void searchEmptyQuery() {
        assertThat(p1.locator("img[imgfield='tn_img_1684324342323']")).isVisible(); //Иконка поиска
        p1.locator("img[imgfield='tn_img_1684324342323']").click();

        // Поиск с пробелами
        assertThat(p1.getByPlaceholder("Я ищу")).isVisible();
        p1.getByPlaceholder("Я ищу").pressSequentially("  ", new Locator.PressSequentiallyOptions().setDelay(100));

        // Проверка ожидаемого сообщения
        assertThat(p1.getByText("Ничего не найдено")).isVisible();
    }

    @DisplayName("Тест видимости формы 'Связаться с экспертом'")
    @Test
    public void contactAnExpertVisibility() {
        p1.locator("span.tn-atom__button-text:has-text('Связаться с экспертом')").scrollIntoViewIfNeeded();
        p1.locator("span.tn-atom__button-text:has-text('Связаться с экспертом')").click();
        p1.waitForTimeout(1000);

        // Проверка видимости формы
        FormValidation contactExpert = new FormValidation(p1);
        contactExpert.assertVisible();

        // Закрытие
        p1.locator("button[aria-label='Закрыть диалоговое окно']:visible").click();
        p1.waitForTimeout(1000);
    }

    @DisplayName("Тест видимости формы 'Обсудить проект'")
    @Test
    public void discussTheProjectVisibility() {
        // Открытие формы
        p1.locator("span.tn-atom__button-text:has-text('Обсудить проект')").scrollIntoViewIfNeeded();
        p1.locator("span.tn-atom__button-text:has-text('Обсудить проект')").click();
        p1.waitForTimeout(1000);

        // Проверка видимости формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();

        // Закрытие
        p1.locator("button[aria-label='Закрыть диалоговое окно']:visible").click();
        p1.waitForTimeout(1000);
    }

    @DisplayName("Тест кнопки 'Управление и мониторинг' (ЦИСУСС)")
    @Test
    public void controlMonitoringButton() {
        assertThat(p1.locator("a[aria-label='ЦИСУСС']")).isVisible(); //Кнопка "ЦИИСУСС"
        p1.locator("a[aria-label='ЦИСУСС']").click();
        assertThat(p1.getByText("Российская система зонтичного мониторинга")).isVisible();
    }

    @DisplayName("Тест кнопки 'ИИ-поиск запрещенного контента' (Profinder)")
    @Test
    public void aiPoweredSearchButton() {
        assertThat(p1.locator("a[aria-label='Profinder']")).isVisible(); //Кнопка "ProFinder"
        p1.locator("a[aria-label='Profinder']").click();
        assertThat(p1.getByText("Обнаружение иноагентов и ненормативной лексики в медиаконтенте")).isVisible();
    }

    @DisplayName("Тест видимости кнопок в блоке 'Технологии, которые работают'")
    @Test
    public void technologySectionButtons() {
        assertThat(p1.locator("a[aria-label='Qligent Vision']")).isVisible();
        assertThat(p1.locator("a[aria-label='ORBOX']")).isVisible();
        assertThat(p1.locator("a[aria-label='Loudness Analyzer']")).isVisible();
        assertThat(p1.locator("a[aria-label='SRT agent']")).isVisible();
        assertThat(p1.locator("a[aria-label='Subtitle Checker']")).isVisible();
    }

    @DisplayName("Тест кнопки 'Больше проектов'")
    @Test
    public void moreProjectsButton() {
        assertThat(p1.getByText("Больше проектов")).isVisible();
        p1.getByText("Больше проектов").click();

        assertThat(p1.locator("h1[field='tn_text_1655296994613']")).isVisible(); //Наши проектв
        assertThat(p1.locator("#cardtitle1_462722294")).isVisible(); //МТС
        assertThat(p1.locator("#rec462722294 div[field='li_descr__1656254443027']")).isVisible(); //Подпись под мтс
        assertThat(p1.locator("#cardtitle7_466319573")).isVisible(); //РЕТ ТВ
        assertThat(p1.locator("#rec466319573 div[field='li_descr__1671181085994']")).isVisible(); //Подпись по рен тв
    }

    @DisplayName("Тест подписки на новости - корректный email")
    @Test
    void newsSubscriptionValidEmail() {
        p1.getByPlaceholder("Ваш e-mail").fill("poctatestovaa9@gmail.com");
        p1.locator(".t-checkbox__indicator").check();
        p1.getByText("Подписаться").click();
        p1.waitForTimeout(30000);

        // Проверка успешной подписки
        assertThat(p1.locator("#tildaformsuccesspopuptext")).isVisible();
    }

    @DisplayName("Тест подписки на новости - некорректный email (кириллица)")
    @Test
    void newsSubscriptionInvalidEmail() {
        p1.getByPlaceholder("Ваш e-mail").fill("pооctatestovaa9@gmail.com");
        p1.locator(".t-checkbox__indicator").check();
        p1.getByText("Подписаться").click();

        // Проверка сообщения об ошибке
        assertThat(p1.locator(".t-form__errorbox-item:has-text('Укажите, пожалуйста, корректный email')")).isVisible();
    }
}