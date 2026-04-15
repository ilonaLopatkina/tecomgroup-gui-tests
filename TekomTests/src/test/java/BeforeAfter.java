import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

/**
 * Абстрактный базовый класс для всех тестовых классов
 * Обеспечивает настройку браузера, создание страницы и корректное закрытие ресурсов.
 */
public abstract class BeforeAfter {

    protected Playwright pl1;
    protected Page p1;
    protected Browser br1;

    /**
     * Возвращает URL страницы, которая будет открыта перед каждым тестом.
     *
     * @return строка с полным URL адресом страницы для тестирования
     */
    protected abstract String getPageUrl();

    @BeforeEach
    public void beforeTest() throws IOException {
        pl1 = Playwright.create();
        br1 = pl1.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        p1 = br1.newPage();
        p1.navigate(getPageUrl());
    }

    @AfterEach
    public void afterTest() {
        p1.close();
        br1.close();
        pl1.close();
    }
}