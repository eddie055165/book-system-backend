# Book System Back

這是一個基於 Spring Boot 的書籍管理系統後端應用程式。它提供了用戶管理、庫存管理和借閱記錄管理的 API。

## 功能

- 用戶註冊和登入
- 書籍的借閱與歸還

## 環境需求

- JDK 21
- Maven 4.0.0

## 安裝

1. 由於雲端資料庫已經架好了，請直接將本專案下載到local side並啟動即可，執行前台前，請確保後端api服務已順利啟動，謝謝您

2. Clone專案到本地端：

    ```bash
    git clone https://github.com/eddie055165/book-system-back.git
    cd book-system-back
    ```

3. 如果db要連本地的mysql，請確保本地的Mysql可以正常啟動，並修正application.properties
   ```bash
   spring.application.name=book-backend
   spring.jpa.hibernate.ddl-auto=update
   spring.datasource.url=jdbc:mysql://localhost:3306/${yourDatabase}
   spring.datasource.username=${yourUserName}
   spring.datasource.password=${yourPassord}
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   spring.jpa.defer-datasource-initialization=true
   spring.sql.init.mode=always
   ```

   例如
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/test0207
   spring.datasource.username=root
   spring.datasource.password=1qaz@WSX3edc
   ```
   
4. 最後請輸入以下sql語句來新增測資
   ```bash
   INSERT INTO user (phone_number, password, user_name, registration_time, last_login_time)
VALUES
    ('1234567890', 'password1', 'John Doe', '2023-01-01 10:00:00', '2023-01-10 12:00:00'),
    ('0987654321', 'password2', 'Jane Smith', '2023-01-05 15:00:00', '2023-01-15 16:00:00');
INSERT INTO book (isbn, name, author, introduction)
VALUES
    ('978-3-16-148410-0', 'Book One', 'Author One', 'Introduction to Book One'),
    ('978-1-234-56789-7', 'Book Two', 'Author Two', 'Introduction to Book Two');
INSERT INTO inventory (isbn, store_time, status)
VALUES
    ('978-3-16-148410-0', '2023-01-01 10:00:00', 'AVAILABLE'),
    ('978-1-234-56789-7', '2023-01-05 15:00:00', 'AVAILABLE');
INSERT INTO borrowing_record (user_id, inventory_id, borrowing_time, return_time)
VALUES
    (1, 1, '2023-01-10 12:00:00', '2023-01-20 12:00:00'),
    (2, 2, '2023-01-15 16:00:00', NULL);
   ```

## 運行應用程式

1. 使用 IntelliJ IDEA開啟本專案並啟動：

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
- **使用者登入**
    - **方法**: POST
    - **URL**: `/users/login`
    - **Body** (JSON):
        ```json
        {
              "phoneNumber": "1234567890",
              "password": "password1"
        }
        ```

- **取得所有用戶**
    - **方法**: GET
    - **URL**: `/api/users`

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
     
- **取得所有庫存**
    - **方法**: GET
    - **URL**: `/api/inventories`

## 授權

此專案使用 MIT 授權。詳情請參閱 LICENSE 文件。
