import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.FormValidation;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Тесты страницы "Проектирование и монтаж" (https://tecomgroup.ru/montazhnye-raboty)
 */
public class ConstructionInstallationWorksPageTest extends BeforeAfter {

    @Override
    protected String getPageUrl() {
        return "https://tecomgroup.ru/montazhnye-raboty";
    }

    @DisplayName("Тест видимости элементов на странице")
    @Test
    public void pageVisibility() {
        // Заголовок
        assertThat(p1.getByText("Проектирование и монтаж оборудования связи и кабельных систем")).isVisible();

        // Описание
        assertThat(p1.getByText("Мы выполняем полный цикл работ по монтажу оборудования связи, кабельных систем и подготовке объектов к вводу в эксплуатацию")).isVisible();

        assertThat(p1.getByText("Комплекс работ для надёжной инфраструктуры")).isVisible();
        // Монтаж оборудования связи
        assertThat(p1.locator("[field='tn_text_1773298728954000002']")).isVisible();
        assertThat(p1.getByText("Маркировка кабельных линий согласно требованиям нормативной документации и эксплуатирующей организации")).isVisible();

        assertThat(p1.getByText("Компании и отрасли, с которыми мы работаем")).isVisible();
        assertThat(p1.getByText("Нефтегазовые компании")).isVisible();
        assertThat(p1.getByText("Добыча, транспортировка, переработка")).isVisible();

        assertThat(p1.getByText("Успешно реализованные проекты на объектах ПАО «Газпром»")).isVisible();

        assertThat(p1.getByText("Квалификация инженерной команды")).isVisible();
        assertThat(p1.getByText("Сборка и подключение систем электропитания и электропитающих устройств")).isVisible();

        assertThat(p1.getByText("Допуски для работы на объектах ТЭК")).isVisible();
        assertThat(p1.getByText("Мы гарантируем соблюдение всех процедур допуска и инструктажей на ОПО Нефтегазовой промышленности РФ")).isVisible();
        assertThat(p1.getByText("Все сотрудники прошли обучение и аттестованы по:")).isVisible();
        assertThat(p1.getByText("Промышленной безопасности (категория А1)")).isVisible();

        assertThat(p1.getByText("Технологический партнёр с глубокой экспертизой в ТЭК")).isVisible();
        assertThat(p1.getByText("5+")).isVisible();
        assertThat(p1.getByText("лет работы на объектах повышенной ответственности")).isVisible();

        assertThat(p1.getByText("Готовы подключиться к вашему проекту")).isVisible();
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

    @DisplayName("Тест кнопки 'Обсудить проект'")
    @Test
    public void discussProjectButton() {
        assertThat(p1.getByText("Обсудить проект")).isVisible();
        p1.getByText("Обсудить проект").click();
        // Проверка открытия формы
        FormValidation discussProject = new FormValidation(p1);
        discussProject.assertVisibleNoСlosure();
    }
}