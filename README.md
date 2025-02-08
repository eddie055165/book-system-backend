# Book System Back

這是一個基於 Spring Boot 的書籍管理系統後端應用程式。它提供了用戶管理、書籍管理和借閱記錄管理的 API。

## 功能

- 用戶註冊和管理
- 書籍新增、查詢、更新和刪除
- 借閱記錄的新增和查詢

## 環境需求

- JDK 21
- Maven 4.0.0
- MySQL 資料庫

## 安裝

1. 由於雲端資料庫已經架好了，請直接將本專案下載到local side並啟動即可，執行前台前，請確保後端api服務已順利啟動，謝謝您

2. Clone專案到本地端：

    ```bash
    git clone [https://github.com/yourusername/book-system-back.git]
    cd book-system-back
    ```

3. 建立 MySQL 資料庫：

    ```sql
    CREATE DATABASE booksystem;
    ```

4. 修改 [application.properties] 文件，配置資料庫連線資訊：

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/booksystem
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    spring.sql.init.mode=always
    ```

5. 使用 Maven 編譯專案：

    ```bash
    mvn clean install
    ```

## 運行應用程式

1. 使用 Maven 運行應用程式：

    ```bash
    mvn spring-boot:run
    ```

2. 應用程式將在 `http://localhost:8080` 運行。

## API 文件

### 用戶管理

- **註冊新用戶**
    - **方法**: POST
    - **URL**: `/api/users/register`
    - **Body** (JSON):
        ```json
        {
            "phoneNumber": "1234567890",
            "password": "password1",
            "name": "John Doe"
        }
        ```

- **取得所有用戶**
    - **方法**: GET
    - **URL**: `/api/users`

- **更新用戶**
    - **方法**: PUT
    - **URL**: `/api/users/{id}`
    - **Body** (JSON):
        ```json
        {
            "phoneNumber": "0987654321",
            "password": "newpassword",
            "name": "Jane Doe"
        }
        ```

- **刪除用戶**
    - **方法**: DELETE
    - **URL**: `/api/users/{id}`

### 書籍管理

- **新增書籍**
    - **方法**: POST
    - **URL**: `/api/books`
    - **Body** (JSON):
        ```json
        {
            "isbn": "978-3-16-148410-0",
            "name": "Book One",
            "author": "Author One",
            "introduction": "Introduction to Book One"
        }
        ```

- **根據 ISBN 取得書籍**
    - **方法**: GET
    - **URL**: `/api/books/{isbn}`

- **取得所有書籍**
    - **方法**: GET
    - **URL**: `/api/books`

- **更新書籍**
    - **方法**: PUT
    - **URL**: `/api/books/{isbn}`
    - **Body** (JSON):
        ```json
        {
            "name": "Updated Book Name",
            "author": "Updated Author",
            "introduction": "Updated Introduction"
        }
        ```

- **刪除書籍**
    - **方法**: DELETE
    - **URL**: `/api/books/{isbn}`

### 借閱記錄管理

- **借書**
    - **方法**: POST
    - **URL**: `/api/borrowings/borrow`
    - **參數**: 
        - `userId` (請求參數): 用戶的 ID
        - `inventoryId` (請求參數): 書籍的庫存 ID

- **還書**
    - **方法**: POST
    - **URL**: `/api/borrowings/return`
    - **參數**: 
        - `userId` (請求參數): 用戶的 ID
        - `inventoryId` (請求參數): 書籍的庫存 ID

## 授權

此專案使用 MIT 授權。詳情請參閱 LICENSE 文件。
