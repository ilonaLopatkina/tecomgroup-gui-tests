package page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class FormValidation {
    private final Page p1; // страница передаётся из теста

    public FormValidation(Page page) {
        this.p1 = page;
    }

    // Метод для проверки видимости всех элементов
    public void assertVisible() {
        Locator visibleForm = p1.locator(".b24-form:visible").first();

        // Заголовок формы
        assertThat(visibleForm.locator(".b24-form-header-title")).isVisible();

        // Подписи к полям (лейблы)
        assertThat(visibleForm.locator(".b24-form-control-label:has-text('ФИО')")).isVisible();
        assertThat(visibleForm.locator(".b24-form-control-label:has-text('E-mail')")).isVisible();
        assertThat(visibleForm.locator(".b24-form-control-label:has-text('Телефон')")).isVisible();
        assertThat(visibleForm.locator(".b24-form-control-label:has-text('Компания или сайт')")).isVisible();
        assertThat(visibleForm.locator(".b24-form-control-label:has-text('Ваш запрос')")).isVisible();

        // Поля ввода
        assertThat(visibleForm.locator("input[name='name']")).isVisible();
        assertThat(visibleForm.locator("input[name='email']")).isVisible();
        assertThat(visibleForm.locator("input[name='phone']")).isVisible();
        assertThat(visibleForm.locator("input.b24-form-control").nth(1)).isVisible(); // Компания
        assertThat(visibleForm.locator("input.b24-form-control").nth(4)).isVisible(); // Ваш запрос

        // Чекбоксы согласий
        assertThat(visibleForm.locator("input[type='checkbox']").nth(0)).isVisible();
        assertThat(visibleForm.locator("input[type='checkbox']").nth(1)).isVisible();

        // Кнопка "Отправить"
        assertThat(visibleForm.locator("button[type='submit']")).isVisible();

        // Крестик (закрыть диалог) — вне формы
        assertThat(p1.locator("button[aria-label='Закрыть диалоговое окно']:visible")).isVisible();
    }

    public void assertVisibleNoСlosure() {
        Locator visibleForm = p1.locator(".b24-form:visible").first();

        // Заголовок формы
        assertThat(visibleForm.locator(".b24-form-header-title")).isVisible();

        // Подписи к полям (лейблы)
        assertThat(visibleForm.locator(".b24-form-control-label:has-text('ФИО')")).isVisible();
        assertThat(visibleForm.locator(".b24-form-control-label:has-text('E-mail')")).isVisible();
        assertThat(visibleForm.locator(".b24-form-control-label:has-text('Телефон')")).isVisible();
        assertThat(visibleForm.locator(".b24-form-control-label:has-text('Компания или сайт')")).isVisible();
        assertThat(visibleForm.locator(".b24-form-control-label:has-text('Ваш запрос')")).isVisible();

        // Поля ввода
        assertThat(visibleForm.locator("input[name='name']")).isVisible();
        assertThat(visibleForm.locator("input[name='email']")).isVisible();
        assertThat(visibleForm.locator("input[name='phone']")).isVisible();
        assertThat(visibleForm.locator("input.b24-form-control").nth(1)).isVisible(); // Компания
        assertThat(visibleForm.locator("input.b24-form-control").nth(4)).isVisible(); // Ваш запрос

        // Чекбоксы согласий
        assertThat(visibleForm.locator("input[type='checkbox']").nth(0)).isVisible();
        assertThat(visibleForm.locator("input[type='checkbox']").nth(1)).isVisible();

        // Кнопка "Отправить"
        assertThat(visibleForm.locator("button[type='submit']")).isVisible();

    }


}