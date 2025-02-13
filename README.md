# README

## Требования

Перед запуском тестов убедитесь, что у вас установлены следующие зависимости:

- **Java 22.02.2**
- **Maven 3.9.9**

## Установка зависимостей

1. Убедитесь, что у вас установлен **Maven 3.9.9**. Проверить можно командой:
   ```sh
   mvn -version
   ```
   Если Maven не установлен, скачайте его с [официального сайта](https://maven.apache.org/download.cgi) и настройте переменные среды.

   **Настройка переменных среды для Maven:**
   - Добавьте путь к Maven (пример: `"C:\Program Files\Apache\apache-maven-3.9.9\bin"`) в переменную `PATH`.
   - В Windows это делается через: **Пуск → Параметры → Система → О системе → Дополнительные параметры системы → Переменные среды**.
   - В разделе "Системные переменные" найдите переменную `Path` и нажмите "Изменить".
   

2. Убедитесь, что у вас установлена **Java 22.02.2**. Проверить можно командой:
   ```sh
   java -version
   ```
   Если версия не соответствует, установите нужную версию с [официального сайта Oracle](https://www.oracle.com/java/technologies/downloads/).

3. **Важно!** После установки Java и Maven **перезагрузите компьютер**, чтобы изменения переменных среды вступили в силу.


Затем обновите зависимости Maven:
```sh
mvn clean install
```

## Запуск тестов

Для запуска тестов выполните следующую команду в консоли в корне проекта:
```sh
mvn clean test
```

## Просмотр отчета Allure

После завершения тестирования для просмотра отчета выполните:

1. Генерация отчета:
   ```sh
   mvn allure:serve
   ```

После этого в браузере откроется Allure-отчет с результатами тестирования.

## Дополнительная информация

- Файл `TESTCASES.md` содержит тест-кейсы для проверки API.
- Файл `BUGS.md` содержит найденные баги и их описание.
- Файл `ScreenShotTestCases` содержит тест-кейсы по первому заданию.
- В директории `src/test/java` хранятся API автотесты, 1 класс - 1 ручка
- Вся информация о проекте и его структуре находится в `pom.xml` и исходных кодах тестов.

## Контакты
- В случае возникших вопросов:
- Email - `vladavgustinovich3800@gmail.com`
- Telegram - `https://t.me/octoberprime`
