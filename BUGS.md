
# Баги на ручку POST /api/1/item

## Баг 1: Ошибка при нулевом значении `sellerID` (200 OK вместо 400 Bad Request)

**Цель:** Проверить обработку ошибки при `sellerID = 0`.

**Предусловия:**
- Значение поля `sellerID` установлено в 0.

**Шаги:**
1. Отправить POST-запрос на endpoint `/api/1/item` с данными:
    ```json
    {
      "sellerID": 0,
      "name": "Test Ad",
      "price": 1000,
      "statistics": {
        "contacts": 10,
        "likes": 5,
        "viewCount": 100
      }
    }
    ```
2. Проверить, что ответ имеет статус 400.
3. Проверить, что в теле ответа присутствует поле `result`, содержащее объект с полем `message`, например, `"message": "Invalid sellerID"`.

**Ожидаемый результат:**
- Статус ответа 400.
- В теле ответа объект `result` с полем `message`, например: `"message": "Invalid sellerID"`.

**Фактический результат:**
- Ответ от API имеет статус 200 OK, несмотря на неправильное значение `sellerID = 0`.


## Баг 2: Ошибка при отрицательном значении `sellerID` (200 OK вместо 400 Bad Request)

**Цель:** Проверить обработку ошибки при `sellerID = -1`.

**Предусловия:**
- Значение поля `sellerID` установлено в -1.

**Шаги:**
1. Отправить POST-запрос на endpoint `/api/1/item` с данными:
    ```json
    {
      "sellerID": -1,
      "name": "Test Ad",
      "price": 1000,
      "statistics": {
        "contacts": 10,
        "likes": 5,
        "viewCount": 100
      }
    }
    ```
2. Проверить, что ответ имеет статус 400.
3. Проверить, что в теле ответа присутствует поле `result`, содержащее объект с полем `message`, например, `"message": "Invalid sellerID"`.

**Ожидаемый результат:**
- Статус ответа 400.
- В теле ответа объект `result` с полем `message`, например: `"message": "Invalid sellerID"`.

**Фактический результат:**
- Ответ от API имеет статус 200 OK, несмотря на неправильное значение `sellerID = -1`.

## Баг 3: Ошибка при отсутствии поля `sellerID` (200 OK вместо 400 Bad Request)

**Цель:** Проверить обработку ошибки при отсутствии поля `sellerID`.

**Предусловия:**
- Поле `sellerID` отсутствует в запросе.

**Шаги:**
1. Отправить POST-запрос на endpoint `/api/1/item` с данными:
    ```json
    {
      "name": "Test Ad",
      "price": 1000,
      "statistics": {
        "contacts": 10,
        "likes": 5,
        "viewCount": 100
      }
    }
    ```
2. Проверить, что ответ имеет статус 400.
3. Проверить, что в теле ответа присутствует поле `result`, содержащее объект с полем `message`, например, `"message": "sellerID is required"`.

**Ожидаемый результат:**
- Статус ответа 400.
- В теле ответа объект `result` с полем `message`, например: `"message": "sellerID is required"`.

**Фактический результат:**
- Ответ от API имеет статус 200 OK, несмотря на отсутствие поля `sellerID`.

## Баг 4: Ошибка при значении `sellerID` больше 999999 (200 OK вместо 400 Bad Request)

**Цель:** Проверить обработку ошибки при значении `sellerID`, превышающем максимальное допустимое значение (999999).

**Предусловия:**
- Значение поля `sellerID` установлено больше 999999.

**Шаги:**
1. Отправить POST-запрос на endpoint `/api/1/item` с данными:
    ```json
    {
      "sellerID": 1000000,
      "name": "Test Ad",
      "price": 1000,
      "statistics": {
        "contacts": 10,
        "likes": 5,
        "viewCount": 100
      }
    }
    ```
2. Проверить, что ответ имеет статус 400.
3. Проверить, что в теле ответа присутствует поле `result`, содержащее объект с полем `message`, например, `"message": "sellerID exceeds maximum value"`.

**Ожидаемый результат:**
- Статус ответа 400.
- В теле ответа объект `result` с полем `message`, например: `"message": "sellerID exceeds maximum value"`.

**Фактический результат:**
- Ответ от API имеет статус 200 OK, несмотря на значение поля `sellerID` больше максимального.

## Баг 5: Ошибка при отрицательном значении `price` (200 OK вместо 400 Bad Request)

**Цель:** Проверить обработку ошибки при отрицательном значении поля `price`.

**Предусловия:**
- Значение поля `price` установлено в отрицательное число.

**Шаги:**
1. Отправить POST-запрос на endpoint `/api/1/item` с данными:
    ```json
    {
      "sellerID": 148888,
      "name": "Test Ad",
      "price": -1000,
      "statistics": {
        "contacts": 10,
        "likes": 5,
        "viewCount": 100
      }
    }
    ```
2. Проверить, что ответ имеет статус 400.
3. Проверить, что в теле ответа присутствует поле `result`, содержащее объект с полем `message`, например, `"message": "Invalid price value"`.

**Ожидаемый результат:**
- Статус ответа 400.
- В теле ответа объект `result` с полем `message`, например: `"message": "Invalid price value"`.

**Фактический результат:**
- Ответ от API имеет статус 200 OK, несмотря на отрицательное значение поля `price`.

## Баг 6: Ошибка при отрицательных значениях в статистике (200 OK вместо 400 Bad Request)

**Цель:** Проверить обработку ошибки при отрицательных значениях в полях статистики.

**Предусловия:**
- Одно или несколько значений в объекте `statistics` установлены в отрицательные числа.

**Шаги:**
1. Отправить POST-запрос на endpoint `/api/1/item` с данными:
    ```json
    {
      "sellerID": 12345,
      "name": "Test Ad",
      "price": 1000,
      "statistics": {
        "contacts": -10,
        "likes": -5,
        "viewCount": -100
      }
    }
    ```
2. Проверить, что ответ имеет статус 400.
3. Проверить, что в теле ответа присутствует поле `result`, содержащее объект с полем `message`, например, `"message": "Invalid statistics values"`.

**Ожидаемый результат:**
- Статус ответа 400.
- В теле ответа объект `result` с полем `message`, например: `"message": "Invalid statistics values"`.

**Фактический результат:**
- Ответ от API имеет статус 200 OK, несмотря на отрицательные значения в статистике.

## Баг 7: Ошибка при отсутствии поля `name` (200 OK вместо 400 Bad Request)

**Цель:** Проверить обработку ошибки при отсутствии поля `name` в запросе.

**Предусловия:**
- Поле `name` отсутствует в отправляемых данных.

**Шаги:**
1. Отправить POST-запрос на endpoint `/api/1/item` с данными:
    ```json
    {
      "sellerID": 148888,
      "price": 1000,
      "statistics": {
        "contacts": 10,
        "likes": 5,
        "viewCount": 100
      }
    }
    ```
2. Проверить, что ответ имеет статус 400.
3. Проверить, что в теле ответа присутствует поле `result`, содержащее объект с полем `message`, например, `"message": "Missing field: name"`.

**Ожидаемый результат:**
- Статус ответа 400.
- В теле ответа объект `result` с полем `message`, например: `"message": "Missing field: name"`.

**Фактический результат:**
- Ответ от API имеет статус 200 OK, несмотря на отсутствие поля `name`.

-----------------------------------------------------------------------------------------------------------------------


# Баги на ручку GET /api/1/item/{ID}

## Баг: Неверное расположение поля `createdAt` в ответе (200 OK)

**Цель:** Проверить, что при запросе на получение объявления по `id` поле `createdAt` расположено в правильном месте.

**Предусловия:**
- Объявление с указанным `id` существует в базе данных.

**Шаги:**
1. Отправить GET-запрос на endpoint `/api/1/item/{id}` с действительным `id`, например: `/api/1/item/c5c19b38-cc97-4ffa-a121-7387c82878c4`.
2. Проверить, что ответ имеет статус 200.
3. Проверить, что в теле ответа поле `createdAt` находится на правильной позиции в объекте (должно быть в конце, после всех других полей).

**Ожидаемый результат:**
- Статус ответа 200.
- Поле `createdAt` должно располагаться в конце объекта, после всех других полей, как указано в спецификации.
```json
[
    {
        "id": "c5c19b38-cc97-4ffa-a121-7387c82878c4",
        "name": "dsdsd",
        "price": 1703,
        "sellerId": 148888,
        "statistics": {
            "contacts": 0,
            "likes": 0,
            "viewCount": 0
        },
       "createdAt": "2025-02-12 16:04:21.978269 +0300 +0300"
    }
]
```
**Фактический результат:**
- Ответ содержит поле `createdAt` в начале объекта, до полей `id`, `name`, `price` и т.д.
```json
[
    {
        "createdAt": "2025-02-12 16:04:21.978269 +0300 +0300",
        "id": "c5c19b38-cc97-4ffa-a121-7387c82878c4",
        "name": "dsdsd",
        "price": 1703,
        "sellerId": 148888,
        "statistics": {
            "contacts": 0,
            "likes": 0,
            "viewCount": 0
        }
    }
]
```
-------------------------------------------------------------------------------------------------------------------
# Баги на ручку GET /api/1/{sellerID}/item

## Баг 1: Неверное расположение поля `createdAt` в ответе (200 OK)

**Цель:** Проверить, что при запросе на получение элементов по `sellerID` поле `createdAt` расположено в правильном месте.

**Предусловия:**
- Существующий `sellerID` с товарами.

**Шаги:**
1. Отправить GET-запрос на endpoint `/api/1/{sellerID}/item` с существующим `sellerID`, например: `/api/1/148888/item`.
2. Проверить, что ответ имеет статус 200.
3. Проверить, что в теле ответа поле `createdAt` находится на правильной позиции в объекте (должно быть в конце, после всех других полей).

**Ожидаемый результат:**
- Статус ответа 200.
- Поле `createdAt` должно располагаться в конце объекта, после всех других полей, как указано в спецификации.
```json
[
    {
        "id": "c5c19b38-cc97-4ffa-a121-7387c82878c4",
        "name": "dsdsd",
        "price": 1703,
        "sellerId": 148888,
        "statistics": {
            "contacts": 0,
            "likes": 0,
            "viewCount": 0
        },
       "createdAt": "2025-02-12 16:04:21.978269 +0300 +0300"
    }
]
```
**Фактический результат:**
- Ответ содержит поле `createdAt` в начале объекта, до полей `id`, `name`, `price` и т.д.
```json
[
    {
        "createdAt": "2025-02-12 16:04:21.978269 +0300 +0300",
        "id": "c5c19b38-cc97-4ffa-a121-7387c82878c4",
        "name": "dsdsd",
        "price": 1703,
        "sellerId": 148888,
        "statistics": {
            "contacts": 0,
            "likes": 0,
            "viewCount": 0
        }
    }
]
```
## Баг 2: Неверный код ответа для несуществующего sellerID (200 OK вместо 400 Bad Request)

**Цель:** Проверить, что при запросе на получение элементов по несуществующему `sellerID` возвращается статус 400 и корректная структура ошибки.

**Предусловия:**
- Несуществующий `sellerID`.

**Шаги:**
1. Отправить GET-запрос на endpoint `/api/1/{sellerID}/item` с несуществующим `sellerID`, например: `/api/1/150/item`.
2. Проверить, что ответ имеет статус 400.
3. Проверить, что в теле ответа присутствует структура с полем `result`, которое содержит сообщение об ошибке.

**Ожидаемый результат:**
- Статус ответа 400.
- В теле ответа должна быть следующая структура:
```json
{
   "result": {
      "messages": {
         "nostrudffb": "<string>",
         "Ut__": "<string>"
      },
      "message": "<string>"
   },
   "status": "<string>"
}
```
**Фактический результат:**
- Статус ответа 200 ОК
- В теле ответа: []

