import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.FormValidation;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Тесты страницы "ProFinder" (https://tecomgroup.ru/products/profinder/)
 */
public class ProFinderPageTest extends BeforeAfter {

    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/products/profinder/";
    }

    @DisplayName("Тест видимости элементов на странице ProFinder")
    @Test
    public void pageVisibility() {
        // Заголовок "Обнаружение иноагентов и ненормативной лексики в медиаконтенте"
        assertThat(p1.locator("[field='tn_text_1744108209054']")).isVisible();

        assertThat(p1.getByText("Почему это важно?")).isVisible();
        assertThat(p1.getByText("Необходимо регулярно отслеживать изменения и актуальные списки иноагентов.")).isVisible();
        assertThat(p1.getByText("Словарь поиска легко адаптируется под ваши задачи, мы добавим нужные вам лексические единицы без ограничений.")).isVisible();

        assertThat(p1.getByText("Для кого будет полезно?")).isVisible();
        assertThat(p1.getByText("Продакшн-студии и контент-хабы")).isVisible();
        assertThat(p1.getByText("Получите персональную консультацию по вашему проекту")).isVisible();

        assertThat(p1.getByText("Ключевые возможности ProFinder")).isVisible();
        assertThat(p1.locator("[field='tn_text_1744108209054']")).isVisible();
        assertThat(p1.getByText("Определяет упоминания лиц из реестра Минюста РФ в аудиодорожке и титрах*")).isVisible();
        assertThat(p1.getByText("* в разработке")).isVisible();

        assertThat(p1.getByText("Поддерживаемые форматы файлов")).isVisible();
        // Изображение с форматами
        assertThat(p1.locator("img[imgfield='tn_img_1749039368866']")).isVisible();

        assertThat(p1.getByText("Как это работает?")).isVisible();
        assertThat(p1.getByText("Задайте параметры поиска: нецензурная лексика, иноагенты, упоминание запрещенных веществ")).isVisible();

        assertThat(p1.getByText("Предложим оптимальное решение для реализации ваших целей")).isVisible();
    }

    @DisplayName("Тест кнопки 'Запросить демо'")
    @Test
    public void requestDemoButton() {
        assertThat(p1.locator("#rec1235113916 .tn-atom__button-text:has-text('Запросить демо')")).isVisible();
        p1.locator("#rec1235113916 .tn-atom__button-text:has-text('Запросить демо')").click();

        // Проверка открытия формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();
    }

    @DisplayName("Тест кнопки 'Обсудить проект'")
    @Test
    public void discussProjectButton() {
        assertThat(p1.locator(".tn-atom__button-text:has-text('Обсудить проект')")).isVisible();
        p1.locator(".tn-atom__button-text:has-text('Обсудить проект')").click();

        // Проверка открытия формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();
    }

    @DisplayName("Тест кнопки 'Все новости'")
    @Test
    public void allNewsButton() {
        assertThat(p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Все новости"))).isVisible();
        p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Все новости")).click();

        // Проверка перехода на страницу новостей. Заголовок "Новости и события"
        assertThat(p1.locator("h1[field='tn_text_1655296994613']")).isVisible();
    }
}