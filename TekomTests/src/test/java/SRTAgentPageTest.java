import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.FormValidation;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SRTAgentPageTest extends BeforeAfter {

    @DisplayName("Проверка видимости элементов на странице")
    @Test
    public void pageVisibility() {
        // Заголовок "SRT agent"
        assertThat(p1.locator("[field='tn_text_1665143242689']")).isVisible();

        // "О продукте"
        assertThat(p1.getByText("О продукте")).isVisible();

// Описание SRT agent
        assertThat(p1.getByText("SRT agent – приложение для конвертации протоколов, их репликации, а также сбора статистики по входному SRT потоку и её передачи в системы мониторинга по протоколу SNMP")).isVisible();

// "Функциональность"
        assertThat(p1.getByText("Функциональность")).isVisible();

// Пункт списка про ретрансляцию
        assertThat(p1.getByText("Ретрансляция контента с новой схемой маршрутизации по нескольким IP-путям для обеспечения надежной доставки потока даже в случае отказа одного из сетевых каналов")).isVisible();

// "Интеграция с системой мониторинга"
        assertThat(p1.getByText("Интеграция с системой мониторинга")).isVisible();

// "04"
        assertThat(p1.locator("[field='tn_text_1693246561626']")).isVisible();

// "Мониторинг качества передачи контента и предупреждение аварийных ситуаций"
        assertThat(p1.getByText("Мониторинг качества передачи контента и предупреждение аварийных ситуаций")).isVisible();

// "Интеграция с Qligent vision"
        assertThat(p1.getByText("Интеграция с Qligent vision")).isVisible();

// Описание про Qligent vision
        assertThat(p1.getByText("SRT agent конвертирует видео, передаваемое с помощью протокола SRT, в протокол UDP. Это позволяет отображать видеопоток SRT в системе мониторинга вещания Qligent Vision.")).isVisible();

// "потеря пакетов (lost packets)"
        assertThat(p1.getByText("потеря пакетов (lost packets)")).isVisible();

// "Минимальные системные требования"
        assertThat(p1.getByText("Минимальные системные требования")).isVisible();

// "SRT agent"
        assertThat(p1.locator("[field='tn_text_1662561995754']")).isVisible();

// "Сбор статистики"
        assertThat(p1.locator("[field='tn_text_1662562035693']")).isVisible();

// Пункт про процессор
        assertThat(p1.getByText("Процессор - Intel Core i3 4 поколения и выше, 2 ядра")).isVisible();

// Текст про стоимость
        assertThat(p1.getByText("Стоимость приложения SRT agent зависит от выбранной модели использования и рассчитывается индивидуально. Заполните форму и мы свяжемся с вами для уточнения ваших задач.")).isVisible();
//Текст новости
        assertThat(p1.getByText("CSTB.PRO.MEDIA 2026: Теком представил проект IP-матрицы по управлению медиа-потоками")).isVisible();

    }

    @DisplayName("Кнопка СВЯЗАТЬСЯ")
    @Test
    public void discussProject() {
        assertThat(p1.locator("a[data-original*='bi_arrow-up-right.svg']")).isVisible();
        p1.locator("a[data-original*='bi_arrow-up-right.svg']").click();
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();

    }

    @DisplayName("Посмотреть брошюру")
    @Test
    public void viewBrochure() {
        assertThat(p1.getByText("Посмотреть брошюру")).isVisible();
        String pdfPage = p1.waitForPopup(() -> p1.getByText("Посмотреть брошюру").click()).url();
        assertTrue(pdfPage.endsWith(".pdf"));
        assertTrue(pdfPage.contains("/docs/"));
    }

   @DisplayName("Кнопка СВЯЗАТЬСЯ")
    @Test
    public void contactButton() {
        assertThat(p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Связаться").setExact(true))).isVisible();
        p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Связаться").setExact(true)).click();
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisible();
    }

    @DisplayName("Новости")
    @Test
    public void NewsButton() {
        assertThat(p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Все новости"))).isVisible();
        p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Все новости")).click();
        assertThat(p1.locator("h1[field='tn_text_1655296994613']")).isVisible();
    }

    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/srtagent";
    }
}
