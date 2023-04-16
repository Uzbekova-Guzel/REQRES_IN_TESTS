<h1 >Проект автоматизации API для сайта <a href="https://reqres.in/ ">reqres.in</a></h1>

## Содержание

* <a href="#tools">Технологии и инструменты</a>
* <a href="#cases">Автоматизированные проверки</a>
* <a href="#console">Запуск тестов</a>
* <a href="#jenkins">Запуск тестов в Jenkins</a>
* <a href="#allure">Отчеты в Allure</a>
* <a href="#testops">Интеграция с Allure TestOps</a>
* <a href="#telegram">Уведомления в Telegram и Slack с использованием бота</a>

<a id="tools"></a>
## Технологии и инструменты

<div align="center">
<a href="https://www.jetbrains.com/idea/"><img alt="InteliJ IDEA" height="50" src="images/logo/IntelliJ_IDEA.png" width="50"/></a>
<a href="https://github.com/"><img alt="GitHub" height="50" src="images/logo/GitHub.png" width="50"/></a>  
<a href="https://www.java.com/"><img alt="Java" height="50" src="images/logo/Java_logo.png" width="50"/></a>
<a href="https://gradle.org/"><img alt="Gradle" height="50" src="images/logo/Gradle.png" width="50"/></a>  
<a href="https://junit.org/junit5/"><img alt="JUnit 5" height="50" src="images/logo/JUnit5.png" width="50"/></a>
<a href="https://selenide.org/"><img alt="Selenide" height="50" src="images/logo/Selenide.png" width="50"/></a>
<a href="https://rest-assured.io/"><img alt="Rest-assured" height="50" src="images/logo/RestAssured.png" width="50"/></a>
<a href="https://www.jenkins.io/"><img alt="Jenkins" height="50" src="images/logo/Jenkins.png" width="50"/></a>
<a href="https://github.com/allure-framework/"><img alt="Allure Report" height="50" src="images/logo/AllureReports.png" width="50"/></a>
<a href="https://qameta.io/"><img alt="Allure TestOps" height="50" src="images/logo/AllureTestOps.svg" width="50"/></a>
<a href="https://telegram.org/"><img alt="Telegram" height="50" src="images/logo/Telegram.png" width="50"/></a>
<a href="https://telegram.org/"><img alt="Telegram" height="50" src="images/logo/Slack.png" width="50"/></a>
</div>

Автотесты написаны на `Java` с использованием `JUnit 5` и `Gradle`.

Краткий список интересных фактов о проекте:
- [x] Использование `Lombok` для моделей в API тестах
- [x] Использование request/response спецификаций для API тестов
- [x] Custom Allure listener для API requests/responses логов
- [x] Cборка в `Jenkins`
- [x] Формирование Allure-отчета
- [x] Интеграция с `Allure TestOps`
- [x] Отправка уведомления с результатами в `Telegram` и `Slack`

<a id="cases"></a>
## Автоматизированные проверки
:heavy_check_mark: Check login successful

:heavy_check_mark: Check login unsuccessful

:heavy_check_mark: Check update user

:heavy_check_mark: Check delete user

:heavy_check_mark: Check that response body has email

<a id="console"></a>
##  Запуск тестов

```
gradle clean api_test 
```

<a id="jenkins"></a>
## Запуск тестов в <a target="_blank" href="https://jenkins.autotests.cloud/job/017_Guzel_Uz_api_reqresin_tests/"> Jenkins </a>

<p align="center">
<img src="images/screenshots/JenkinsJob.PNG"/>
</p>

<a id="allure"></a>
## Отчеты в <a target="_blank" href="https://jenkins.autotests.cloud/job/017_Guzel_Uz_api_reqresin_tests/2/allure/"> Allure report </a>

### Основное окно

<p align="center">
<img src="images/screenshots/AllureOverview.png">
</p>

### Тесты

<p align="center">
<img src="images/screenshots/AllureBehaviors.png">
</p>

<a id="testops"></a>
## Интеграция с <a target="_blank" href="https://allure.autotests.cloud/project/2181/dashboards"> Allure TestOps </a>

### Доска
<p align="center">
<img src="images/screenshots/AllureTestOpsDashboard.PNG">
</p>

<a id="telegram"></a>
## Уведомления в Telegram с использованием бота

<p>
<img src="images/screenshots/TelegramBot.PNG">
</p>

<a id="slack"></a>
## Уведомления в Slack с использованием бота

<p>
<img src="images/screenshots/SlackBot.PNG">
</p>
