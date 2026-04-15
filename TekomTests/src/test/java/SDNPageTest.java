import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.FormValidation;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Тесты страницы "SDN" (https://tecomgroup.ru/sdn)
 */
public class SDNPageTest extends BeforeAfter {

    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/sdn";
    }

    @DisplayName("Тест видимости элементов на странице SDN")
    @Test
    public void pageVisibility() {
        assertThat(p1.getByText("SDN-сеть для медиапроизводства и ТВ-вещания")).isVisible();

        assertThat(p1.getByText("SDN (программно определяемые сети) — система управления распределённой телекоммуникационной сетью")).isVisible();

        assertThat(p1.getByText("SDN‑сеть как основа надёжного вещания")).isVisible();

        assertThat(p1.getByText("Детерминированная сеть для прямых эфиров")).isVisible(); //Подпункт "Гарантированная доставка сигнала"

        assertThat(p1.getByText("SDN‑архитектура: контроль потоков и оборудования")).isVisible();
        assertThat(p1.getByText("Ключевые уровни")).isVisible();

        assertThat(p1.getByText("Масштабирование от пилотного сегмента до сотен коммутаторов и тысяч сервисов")).isVisible();

        assertThat(p1.getByText("Технологическая база")).isVisible();
        assertThat(p1.getByText("В систему встроены модули мониторинга, аналитики и отчётности, она интегрируется с системами учёта, сервис‑деском, CRM и SIEM и может расширяться за счёт партнёрских модулей, адаптируясь под задачи медиапроизводства")).isVisible();

        assertThat(p1.getByText("Оборудование, с которым работает система")).isVisible();
        assertThat(p1.getByText("Оборудование адаптации ТВ-сигналов")).isVisible();

        assertThat(p1.getByText("Импортозамещение")).isVisible();
        assertThat(p1.getByText("Система позволяет отказаться от зарубежных платформ Nevian VideoIPath, Lawo VSM, Magellan, Nimbra Vision, Dataminer без рисков и потери производительности инфраструктуры")).isVisible();

        assertThat(p1.getByText("Теком — надёжная основа вашей медиасети")).isVisible();
        assertThat(p1.getByText("Отечественная разработка, соответствующая требованиям импортозамещения")).isVisible();

        assertThat(p1.getByText("Подготовим индивидуальное предложение для вашего проекта")).isVisible();

        assertThat(p1.getByText("Всегда на связи и в эфире")).isVisible();
        // Иконки Telegram и VK
        assertThat(p1.locator("img[imgfield='tn_img_1771334765762']")).isVisible();
        assertThat(p1.locator("img[imgfield='tn_img_1771334765787']")).isVisible();
        assertThat(p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("+7 831 262 10 11").setExact(true))).isVisible();
        assertThat(p1.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("tecom.info@tecomgroup.ru").setExact(true))).isVisible();

        // Текст новости
        assertThat(p1.getByText("CSTB.PRO.MEDIA 2026: Теком представил проект IP-матрицы по управлению медиа-потоками")).isVisible();
    }

    @DisplayName("Тест кнопки 'Запросить проектирование'")
    @Test
    public void requestDesignButton() {
        assertThat(p1.getByText("Запросить проектирование")).isVisible();
        p1.getByText("Запросить проектирование").click();

        // Проверка открытия формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisibleNoСlosure();
    }

    @DisplayName("Тест раскрывающихся блоков")
    @Test
    public void dropDownBlocks() {
        assertThat(p1.getByText("Гарантированная доставка сигнала")).isVisible();
        assertThat(p1.locator("img[imgfield='tn_img_1771244634878']")).isVisible(); //Кнопка "галочка" для разворота
        p1.locator("img[imgfield='tn_img_1771244634878']").click(new Locator.ClickOptions().setForce(true));
        p1.waitForTimeout(3000);
        assertThat(p1.getByText("Резервирование маршрутов и устойчивость к отказам")).isVisible(); //подпункт
        p1.waitForTimeout(3000);

        // Блок "IP‑производство и переход на новые форматы"
        assertThat(p1.getByText("IP‑производство и переход на новые форматы")).isVisible();
        assertThat(p1.locator("img[imgfield='tn_img_1771244744636000002']")).isVisible(); //Кнопка "галочка" для разворота
        p1.locator("img[imgfield='tn_img_1771244744636000002']").click(new Locator.ClickOptions().setForce(true));
        assertThat(p1.getByText("Поддержка IP‑матриц для политических, спортивных и развлекательных трансляций")).isVisible(); //подпункт

        // Блок "Мониторинг и аналитика в реальном времени"
        assertThat(p1.getByText("Мониторинг и аналитика в реальном времени")).isVisible();
        assertThat(p1.locator("img[imgfield='tn_img_1771245128138000001']")).isVisible(); //Кнопка "галочка" для разворота
        p1.locator("img[imgfield='tn_img_1771245128138000001']").click(new Locator.ClickOptions().setForce(true));
        assertThat(p1.getByText("Быстрое выявление и устранение инцидентов")).isVisible(); //подпункт

        // Блок "Безопасность и изоляция контента"
        assertThat(p1.getByText("Безопасность и изоляция контента")).isVisible();
        assertThat(p1.locator("img[imgfield='tn_img_1771245139708000001']")).isVisible(); //Кнопка "галочка" для разворота
        p1.locator("img[imgfield='tn_img_1771245139708000001']").click(new Locator.ClickOptions().setForce(true));
        assertThat(p1.getByText("Интеграция с системами информационной безопасности")).isVisible(); //подпункт

        // Блок "Автоматизация рабочих процессов"
        assertThat(p1.getByText("Автоматизация рабочих процессов")).isVisible();
        assertThat(p1.locator("img[imgfield='tn_img_1771245144617000001']")).isVisible();//Кнопка "галочка" для разворота
        p1.locator("img[imgfield='tn_img_1771245144617000001']").click(new Locator.ClickOptions().setForce(true));
        assertThat(p1.getByText("Минимизация человеческого фактора")).isVisible(); //подпункт
    }

    @DisplayName("Тест кнопки 'Получить консультацию'")
    @Test
    public void getConsultationButton() {
        assertThat(p1.locator("#rec1923337601 .tn-atom__button-text:has-text('Получить консультацию')")).isVisible();
        p1.locator("#rec1923337601 .tn-atom__button-text:has-text('Получить консультацию')").click();

        // Проверка открытия формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisibleNoСlosure();
    }

    @DisplayName("Тест кнопки 'Обсудить технологии'")
    @Test
    public void discussTechnologiesButton() {
        assertThat(p1.locator("a:has-text('Обсудить технологии')")).isVisible();
        p1.locator("a:has-text('Обсудить технологии')").click();

        // Проверка открытия формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisibleNoСlosure();
    }
}