
@etsy
Feature: Etsy websitesi Ui test


  Scenario Outline: CrossBrowser test

    Given Kullanici etsy anasayfasinin farkli "<browser>" ile acilabildigini dogrular
    Examples:
      | browser |
      | edge    |
      | chrome  |

  Scenario: Login olma

    Given Kullanici "etsyUrl" anasayfasina giris yapar
    Then Kullanici login islemini tamamlar
    And Sepete urun ekler
    Then Odeme islemlerini yapar
    And Kart bilgilerini girer