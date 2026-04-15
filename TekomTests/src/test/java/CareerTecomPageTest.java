import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Тесты страницы "Карьера в Теком" (https://tecomgroup.ru/career/)
 */
public class CareerTecomPageTest extends BeforeAfter {

    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/career/";
    }

    @DisplayName("Тест видимости элементов на странице")
    @Test
    public void pageVisibility() {
        // Заголовок
        assertThat(p1.getByText("Карьера в Теком")).isVisible();

        // Цифра "4"
        assertThat(p1.locator("[field='tn_text_1749464023618']")).isVisible();

        assertThat(p1.getByText("открытых вакансии")).isVisible();

        assertThat(p1.getByText("Что такое работа в Теком")).isVisible();

        assertThat(p1.getByText("Собираешься «войти в IT», но не знаешь, с чего начать? Попробуй свои силы в интернатуре Текома! Мы принимаем на стажировку студентов и выпускников профильных вузов и выращиваем из них настоящих инженеров. Ведь самое главное — твоя мотивация и интерес к работе в IT-сфере")).isVisible();

        // "Актуальные вакансии"
        assertThat(p1.locator("[field='tn_text_1768910053041000005']")).isVisible();

        assertThat(p1.getByText("Контент - менеджер")).isVisible();

        assertThat(p1.getByText("Работа в Текоме это:")).isVisible();

        assertThat(p1.getByText("ДМС со стоматологией")).isVisible();

        assertThat(p1.getByText("и сеансами массажа")).isVisible();

        assertThat(p1.getByText("Комфортный офис и гибкий гибридный график работы")).isVisible();

        assertThat(p1.getByText("А ещё мы...")).isVisible();

        assertThat(p1.getByText("и даже с ним на одной волне")).isVisible();

        assertThat(p1.getByText("Мы всегда рядом и готовы помочь")).isVisible();

        // Текст про HR
        assertThat(p1.locator("[field='tn_text_1655317936058']")).isVisible();

        assertThat(p1.getByText("Хочу работать в Текоме")).isVisible();
    }

    @DisplayName("Тест кнопки 'Написать нам'")
    @Test
    public void writeUsButton() {
        assertThat(p1.getByText("Написать нам")).isVisible();
        assertThat(p1.locator("a[href='mailto:HR@tecomgroup.ru']")).hasAttribute("href", "mailto:HR@tecomgroup.ru"); //Кнопка "Написать нам"
        p1.getByText("Написать нам").click();
    }

    @DisplayName("Тест кнопки 'К вакансиям'")
    @Test
    public void toVacanciesButton() {
        assertThat(p1.locator("a[href=\"#rec1809863731\"]")).isVisible(); //Локатор для кнопки "К вакансиям"
        p1.locator("a[href=\"#rec1809863731\"]").click();
        assertThat(p1.locator("[field=\"tn_text_1768910053041000005\"]")).isVisible(); //Надпись "Актуальные вакансии"
        assertThat(p1.locator("[field=\"tn_text_1768910053041000005\"]")).isInViewport();
    }

    @DisplayName("Тест кнопки 'Хочу в команду'")
    @Test
    public void joinTeamButton() {
        assertThat(p1.locator("a[href=\"#hr-form\"]")).isVisible(); //Локатор кнопки
        p1.locator("a[href=\"#hr-form\"]").click();
        p1.waitForTimeout(3000);
        assertThat(p1.locator("[field=\"tn_text_1763731212261000001\"]")).isVisible(); //Локатор "Хочу работать в Текоме"
        assertThat(p1.locator("[field=\"tn_text_1763731212261000001\"]")).isInViewport();
    }

    @DisplayName("Тест вакансии Контент-менеджер")
    @Test
    public void contentManagerVacancyButton() {
        assertThat(p1.locator("span.t585__title:has-text('Контент - менеджер')")).isVisible(); //Вакансия
        assertThat(p1.locator("button.t585__trigger-button:has(span.t585__title:has-text('Контент - менеджер')) span.t585__icon:not(.t585__icon-hover) svg")).isVisible(); //Локатор икноки "+" рядом с вакансией
        p1.locator("button.t585__trigger-button:has(span.t585__title:has-text('Контент - менеджер')) span.t585__icon:not(.t585__icon-hover) svg").click();
        assertThat(p1.locator("a[href='https://nn.hh.ru/vacancy/131004555']")).isVisible(); //Надпись "Смотреть вакансию"
        p1.locator("a[href='https://nn.hh.ru/vacancy/131004555']").click();
        p1.waitForTimeout(5000);
    }

    @DisplayName("Тест формы 'Хочу работать в Теком' - успешная отправка")
    @Test
    public void workAtTekomFormSuccess() {
        assertThat(p1.getByPlaceholder("Имя")).isVisible();
        p1.getByPlaceholder("Имя").fill("Иван");
        p1.waitForTimeout(500);

        assertThat(p1.getByPlaceholder("Фамилия")).isVisible();
        p1.getByPlaceholder("Фамилия").fill("Иванов");
        p1.waitForTimeout(500);

        assertThat(p1.getByPlaceholder("Специальность")).isVisible();
        p1.getByPlaceholder("Специальность").fill("Тестовая специальность");
        p1.waitForTimeout(500);

        assertThat(p1.getByPlaceholder("Email")).isVisible();
        p1.getByPlaceholder("Email").fill("pactatestovaa@gmail.com");
        p1.waitForTimeout(500);

        assertThat(p1.getByPlaceholder("(000) 000-00-00")).isVisible();
        p1.getByPlaceholder("(000) 000-00-00").fill("8005353535");
        p1.waitForTimeout(500);

        assertThat(p1.getByPlaceholder("Пара слов о себе")).isVisible();
        p1.getByPlaceholder("Пара слов о себе").fill("Тестовые слова о себе");
        p1.waitForTimeout(500);

        assertThat(p1.getByPlaceholder("Ссылка на резюме")).isVisible();
        p1.getByPlaceholder("Ссылка на резюме").fill("https://tecomgroup.ru/career/");
        p1.waitForTimeout(500);

        // Чекбокс согласия
        assertThat(p1.locator("#form1593137551").getByText("Я даю согласие на обработку персональных данных в соответствии с Федеральным зак")).isVisible();
        assertThat(p1.locator("label:has(span.t-checkbox__labeltext:has-text('Я даю')) .t-checkbox__indicator").first()).isVisible();
        p1.locator("label:has(span.t-checkbox__labeltext:has-text('Я даю')) .t-checkbox__indicator").first().click();

        assertThat(p1.locator("button.t-submit[type='submit']")).isVisible(); //Локатор кнопки "Отправить"
        p1.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Отправить")).click();
        p1.waitForTimeout(15000);

        assertThat(p1.locator("#tildaformsuccesspopuptext")).isVisible(); //Всплывающее окно с информацией об успешной отправке
    }

    @DisplayName("Тест формы 'Хочу работать в Теком' - некорректные данные")
    @Test
    public void workAtTekomFormInvalidData() {
        assertThat(p1.getByPlaceholder("Имя")).isVisible();
        p1.getByPlaceholder("Имя").fill("Иван");
        p1.waitForTimeout(500);

        assertThat(p1.getByPlaceholder("Фамилия")).isVisible(); //Смешивание русских и английских букв
        p1.getByPlaceholder("Фамилия").fill("Ivаноv");
        p1.waitForTimeout(500);

        assertThat(p1.getByPlaceholder("Специальность")).isVisible();
        p1.getByPlaceholder("Специальность").fill("Тестовая специальность");
        p1.waitForTimeout(500);

        assertThat(p1.getByPlaceholder("Email")).isVisible();
        p1.getByPlaceholder("Email").fill("pactatestovaa@gmail.com");
        p1.waitForTimeout(500);

        assertThat(p1.getByPlaceholder("(000) 000-00-00")).isVisible();
        p1.getByPlaceholder("(000) 000-00-00").fill("8005353535");
        p1.waitForTimeout(500);

        assertThat(p1.getByPlaceholder("Пара слов о себе")).isVisible();
        p1.getByPlaceholder("Пара слов о себе").fill("Тестовые слова о себе");
        p1.waitForTimeout(500);

        assertThat(p1.getByPlaceholder("Ссылка на резюме")).isVisible();
        p1.getByPlaceholder("Ссылка на резюме").fill("https://tecomgroup.ru/career/");
        p1.waitForTimeout(500);

        // Чекбокс согласия
        assertThat(p1.locator("#form1593137551").getByText("Я даю согласие на обработку персональных данных в соответствии с Федеральным зак")).isVisible();
        assertThat(p1.locator("label:has(span.t-checkbox__labeltext:has-text('Я даю')) .t-checkbox__indicator").first()).isVisible();
        p1.locator("label:has(span.t-checkbox__labeltext:has-text('Я даю')) .t-checkbox__indicator").first().click();

        assertThat(p1.locator("button.t-submit[type='submit']")).isVisible(); //Локатор кнопки "Отправить"
        p1.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Отправить")).click();
        p1.waitForTimeout(15000);

        // Проверка: форма не отправилась
        assertThat(p1.locator("#tildaformsuccesspopuptext")).not().isVisible();
    }
}