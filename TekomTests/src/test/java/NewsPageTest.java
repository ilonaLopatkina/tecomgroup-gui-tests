import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты страницы новостей (https://tecomgroup.ru/news/2025)
 */
public class NewsPageTest extends BeforeAfter {

    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/news/2025";
    }

    @DisplayName("Тест видимости элементов на странице новостей")
    @Test
    public void pageVisibility() {
        // Заголовок страницы "Новости и события"
        assertThat(p1.locator("h1[field='tn_text_1655296994613']")).isVisible();

        // Фильтр "Все"
        assertThat(p1.locator("a", new Page.LocatorOptions().setHasText("Все"))).isVisible();

        // Фильтр "ORBOX"
        assertThat(p1.locator("a[href='#!/tfeeds/237586738201/c/ORBOX']")).isVisible();

        // Фильтр "ТЕКОМ: СОБЫТИЯ"
        assertThat(p1.locator("a", new Page.LocatorOptions().setHasText("ТЕКОМ: СОБЫТИЯ"))).isVisible();

        // Фильтр "ProFinder"
        assertThat(p1.locator("a[href='#!/tfeeds/237586738201/c/ProFinder']")).isVisible();

        // Фильтр "ЦИСУСС"
        assertThat(p1.locator("a[href='#!/tfeeds/237586738201/c/ЦИСУСС']")).isVisible();

        // Проверка видимости новости
        assertThat(p1.locator("a.t-feed__link", new Page.LocatorOptions().setHasText("ГК «Теком» запускает программу поддержки внедрения отечественного ПО"))).isVisible();
        assertThat(p1.locator("span.js-feed-post-date", new Page.LocatorOptions().setHasText("12.11.2025"))).isVisible();

        // Блок с годами
        assertThat(p1.locator("div.t830__panel-text", new Page.LocatorOptions().setHasText("Новости и события (2017-2026)"))).isVisible();
    }

    @DisplayName("Тест поиска по слову 'программное' - первая новость")
    @Test
    public void searchByKeywordFirstResult() {
        // Открытие бокового меню
        assertThat(p1.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Навигационное меню"))).isVisible();
        p1.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Навигационное меню")).hover();
        assertThat(p1.locator("div.t830__logo__text", new Page.LocatorOptions().setHasText("Новости и события"))).isVisible();

        // Поиск
        assertThat(p1.getByPlaceholder("Поиск")).isVisible();
        p1.getByPlaceholder("Поиск").fill("программное");
        p1.waitForTimeout(4000);

        // Проверка первого результата
        assertThat(p1.locator("div.t-site-search-dm__result__title", new Page.LocatorOptions().setHasText("«Теком» примет участие в семинаре «Актуальные технологии: ТВ 2019»"))).isVisible();

        // Проверка, что результат содержит слово "программное"
        assertTrue(Boolean.parseBoolean(p1.locator("div.t-site-search-dm__result__title", new Page.LocatorOptions().setHasText("«Теком» примет участие в семинаре «Актуальные технологии: ТВ 2019»")).evaluate("el => el.textContent.toLowerCase().includes('программное')").toString()), "Текст не содержит слово 'программное'");
    }

    @DisplayName("Тест поиска по слову 'программное' - вторая новость")
    @Test
    public void searchByKeywordSecondResult() {
        // Открытие бокового меню
        assertThat(p1.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Навигационное меню"))).isVisible();
        p1.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Навигационное меню")).hover();
        assertThat(p1.locator("div.t830__logo__text", new Page.LocatorOptions().setHasText("Новости и события"))).isVisible();

        // Поиск
        assertThat(p1.getByPlaceholder("Поиск")).isVisible();
        p1.getByPlaceholder("Поиск").fill("программное");
        p1.waitForTimeout(4000);

        // Проверка второго результата
        assertThat(p1.locator("div.t-site-search-dm__result__body", new Page.LocatorOptions().setHasText("Программное решение PowerSMS включает в себя"))).isVisible();

        // Проверка, что результат содержит слово "программное"
        assertTrue(Boolean.parseBoolean(p1.locator("div.t-site-search-dm__result__body", new Page.LocatorOptions().setHasText("Программное решение PowerSMS включает в себя")).evaluate("el => el.textContent.toLowerCase().includes('программное')").toString()), "Текст не содержит слово 'программное'");
    }

    @DisplayName("Тест перехода по новости PowerSMS")
    @Test
    public void powerSmsNewsButton() {
        // Открытие бокового меню
        assertThat(p1.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Навигационное меню"))).isVisible();
        p1.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Навигационное меню")).hover();
        assertThat(p1.locator("div.t830__logo__text", new Page.LocatorOptions().setHasText("Новости и события"))).isVisible();

        // Поиск
        assertThat(p1.getByPlaceholder("Поиск")).isVisible();
        p1.getByPlaceholder("Поиск").fill("программное");
        p1.waitForTimeout(4000);

        // Проверка результата
        assertThat(p1.locator("div.t-site-search-dm__result__body", new Page.LocatorOptions().setHasText("Программное решение PowerSMS включает в себя"))).isVisible();

        // Клик по результату, ожидание открытия новой вкладки
        Page newPage = p1.waitForPopup(() -> {
            p1.locator("div.t-site-search-dm__result__body", new Page.LocatorOptions().setHasText("Программное решение PowerSMS включает в себя")).click();
        });

        newPage.waitForLoadState();

        // Проверка содержимого на новой странице
        assertThat(newPage.locator("div.tn-atom", new Page.LocatorOptions().setHasText("Телеканал PowerTurk использует систему ORAD"))).isVisible();
    }

    @DisplayName("Тест фильтра новостей по категории ORBOX")
    @Test
    public void orboxNewsFilter() {
        // Клик по фильтру ORBOX
        assertThat(p1.locator("a[href='#!/tfeeds/237586738201/c/ORBOX']")).isVisible();
        p1.locator("a[href='#!/tfeeds/237586738201/c/ORBOX']").click();
        p1.waitForTimeout(3000);

        // Проверка отображения новости
        assertThat(p1.locator("a.t-feed__link.js-feed-post-link:has-text('ИИ-контроль контента: как избежать штрафов и защитить репутацию')")).isVisible();
    }

    @DisplayName("Тест фильтра новостей по категории ТЕКОМ: СОБЫТИЯ")
    @Test
    public void tecomEventsNewsFilter() {
        // Клик по фильтру ТЕКОМ: СОБЫТИЯ
        assertThat(p1.locator("a[href='#!/tfeeds/237586738201/c/ТЕКОМ: СОБЫТИЯ']")).isVisible();
        p1.locator("a[href='#!/tfeeds/237586738201/c/ТЕКОМ: СОБЫТИЯ']").click();

        // Проверка отображения новости
        assertThat(p1.locator("a.t-feed__link.js-feed-post-link:has-text('ГК «Теком» запускает программу поддержки внедрения отечественного ПО')")).isVisible();
    }

    @DisplayName("Тест фильтра новостей по категории ProFinder")
    @Test
    public void proFinderNewsFilter() {
        // Клик по фильтру ProFinder
        assertThat(p1.locator("a[href='#!/tfeeds/237586738201/c/ProFinder']")).isVisible();
        p1.locator("a[href='#!/tfeeds/237586738201/c/ProFinder']").click();

        // Проверка сообщения об отсутствии новостей
        assertThat(p1.locator("div.t-feed__error-msg")).hasText("Ничего не найдено");
    }

    @DisplayName("Тест фильтра новостей по категории ЦИСУСС")
    @Test
    public void cisussNewsFilter() {
        // Клик по фильтру ЦИСУСС
        assertThat(p1.locator("a[href='#!/tfeeds/237586738201/c/ЦИСУСС']")).isVisible();
        p1.locator("a[href='#!/tfeeds/237586738201/c/ЦИСУСС']").click();

        // Проверка отображения новости
        assertThat(p1.locator("a.t-feed__link.js-feed-post-link:has-text('«Теком» представил кейс перехода крупнейшей в мире SDN-медиасети под управление отечественной системы')")).isVisible();
    }
}