import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.FormValidation;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты страницы "Loudness Analyzer" (https://tecomgroup.ru/products/loudnessanalyzer)
 */
public class LoudnessAnalyzerPageTest extends BeforeAfter {

    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/products/loudnessanalyzer";
    }

    @DisplayName("Тест видимости элементов на странице")
    @Test
    public void pageVisibility() {
        // Заголовок "Анализ громкости/ Loudness Analyzer"
        assertThat(p1.locator("h1[field='tn_text_1622986948166']")).isVisible();

        // Продукт в реестре отечественного ПО
        assertThat(p1.locator("#rec465496096 .tn-atom[field='tn_text_1656508518620']")).isVisible();

        // Семейство профессиональных приложений для контроля и нормализации уровня громкости
        assertThat(p1.locator("#rec465496096 .tn-atom[field='tn_text_1470209944682']")).isVisible();

        assertThat(p1.getByText("Федеральный закон о рекламе").first()).isVisible();
        assertThat(p1.getByText("Допустимая громкость звука рекламных роликов и анонсов не более 1,5 децибела").first()).isVisible();

        assertThat(p1.getByText("Проверка и нормализация громкости медиафайлов")).isVisible();
        assertThat(p1.getByText("Соответствие методике ФАС России")).isVisible();

        assertThat(p1.getByText("Оперативное выявление отклонений громкости эфира")).isVisible();
        assertThat(p1.getByText("Соответствие международным стандартам по громкости")).isVisible();

        assertThat(p1.getByText("Технические требования и стоимость")).isVisible();
        assertThat(p1.getByText("Стоимость приложения Loudness Analyzer")).isVisible();

        // Текст новости
        assertThat(p1.getByText("CSTB.PRO.MEDIA 2026: Теком представил проект IP-матрицы по управлению медиа-потоками")).isVisible();
    }

    @DisplayName("Тест кнопки 'Обсудить проект' (первая)")
    @Test
    public void discussProjectFirstButton() {
        // Клик по кнопке "Обсудить проект" в первом блоке
        assertThat(p1.locator("#sbs-465496016-1657264660359 a[data-original*='Frame_111.svg']")).isVisible();
        p1.locator("#sbs-465496016-1657264660359 a[data-original*='Frame_111.svg']").click(new Locator.ClickOptions().setForce(true));

        // Проверка открытия формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();
    }

    @DisplayName("Тест кнопки 'Обсудить проект' (вторая)")
    @Test
    public void discussProjectSecondButton() {
        // Клик по кнопке "Обсудить проект" во втором блоке
        assertThat(p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Обсудить проект"))).isVisible();
        p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Обсудить проект")).click();

        // Проверка открытия формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();
    }

    @DisplayName("Тест кнопки 'Руководство пользователя'")
    @Test
    public void userManualButton() {
        assertThat(p1.getByText("Руководство пользователя")).isVisible();

        // Ожидание открытия PDF в новой вкладке
        String pdfPage = p1.waitForPopup(() -> p1.getByText("Руководство пользователя").click()).url();

        // Проверка, что открылся PDF-файл в директории /docs/
        assertTrue(pdfPage.endsWith(".pdf"));
        assertTrue(pdfPage.contains("/docs/"));
    }

    @DisplayName("Тест кнопки 'Все новости'")
    @Test
    public void allNewsButton() {
        assertThat(p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Все новости"))).isVisible();
        p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Все новости")).click();

        // Заголовок "Новости и события"
        assertThat(p1.locator("h1[field='tn_text_1655296994613']")).isVisible();
    }
}