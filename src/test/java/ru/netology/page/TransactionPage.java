package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransactionPage {
    private final SelenideElement transactionButton = $("[data-test-id=action-transfer]");
    private final SelenideElement amountInput = $("[data-test-id=amount] input");
    private final SelenideElement fromInput = $("[data-test-id=from] input");
    private final SelenideElement transactionHead = $(byText("Пополнение карты"));
    private final SelenideElement errorMessage = $("[data-test-id=error-message]");

    public TransactionPage() {
        transactionHead.shouldBe(visible);
    }

    public DashboardPage makeValidTransaction(String amountToTransaction, DataHelper.CardInfo cardInfo) {
        makeTransaction(amountToTransaction, cardInfo);
        return new DashboardPage();
    }

    public void makeTransaction(String amountToTransaction, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransaction);
        fromInput.setValue(cardInfo.getCardNumber());
        transactionButton.click();
    }

    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(10)).shouldBe(visible);
    }
}