@etsy
Feature: Etsy websitesi Ui test

  Scenario Outline: CrossBrowser test

    Given Kullanici etsy anasayfasinin farkli "<browser>" ile acilabildigini dogrular
    Examples:
      | browser |
      | chrome  |
      | firefox |
      | edge    |

  Scenario: Login olma

    Given Kullanici "etsyUrl" anasayfasina giris yapar
    Then Kullanici login islemini tamamlar
    And Sepete urun ekler