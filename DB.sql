USE master

GO

CREATE DATABASE CoffeeShop

GO

USE CoffeeShop

GO

CREATE TABLE Users
(
    id         int primary key identity,
    [name]     nvarchar(100)       not null,
    email      varchar(100) unique not null,
    [password] varchar(100)        not null,
    [role]     tinyint default (0),
    [status]   bit     default (1)
)

GO

CREATE PROC sp_insertUser(@_name nvarchar(100),
                          @_email varchar(100),
                          @_password varchar(100),
                          @_role tinyint = 0,
                          @_status bit = 1,
                          @_outStt bit = 1 output,
                          @_outMsg nvarchar(200) = '' output)
AS
BEGIN TRY
    IF EXISTS(SELECT email FROM Users WHERE email = @_email)
        BEGIN
            SET @_outStt = 0;
            SET @_outMsg = N'Email đã tồn tại, vui lòng nhập lại';
        END
    ELSE
        BEGIN
            INSERT INTO Users([name], email, [password], [role], [status])
            VALUES (@_name, @_email, @_password, @_role, @_status)
            SET @_outStt = 1;
            SET @_outMsg = N'Thêm người dùng thành công';
        END
END TRY
BEGIN CATCH
    BEGIN
        SET @_outStt = 0;
        SET @_outMsg = N'Thêm không thành công: ' + ERROR_MESSAGE()
    END
END CATCH

GO

-- Them user admin
EXEC sp_insertUser @_name = N'Nguyễn Mạnh Tuấn Anh', @_email = 'xdorro@gmail.com', @_password = '1230123', @_role = 1,
     @_outMsg = 'OK'

GO

CREATE PROC sp_checkUser(
	@_email varchar(100),
    @_password varchar(100)
)
AS
BEGIN
	SELECT TOP 1 * FROM Users WHERE email = @_email AND [password] = @_password
END

GO

EXEC sp_checkUser 'xdorro@gmail.com', '1230123'

GO

CREATE TABLE Categories
(
    id       int primary key identity,
    [name]   nvarchar(100) unique not null,
    [status] bit default (1)
)

GO

CREATE PROC sp_insertCategory(@_name nvarchar(100),
                              @_status bit = 1,
                              @_outStt bit = 1 output,
                              @_outMsg nvarchar(200) = '' output)
AS
BEGIN TRY
    IF EXISTS(SELECT [name] FROM Categories WHERE [name] = @_name)
        BEGIN
            SET @_outStt = 0;
            SET @_outMsg = N'Tên danh mục đã tồn tại, vui lòng nhập lại'
        END
    ELSE
        BEGIN
            INSERT INTO Categories([name], [status]) VALUES (@_name, @_status)
            SET @_outStt = 1;
            SET @_outMsg = N'Thêm thành công'
        END
END TRY
BEGIN CATCH
    SET @_outStt = 0;
    SET @_outMsg = N'Thêm không thành công: ' + ERROR_MESSAGE()
END CATCH

GO

-- Thêm danh mục
EXEC sp_insertCategory @_name = N'Cà Phê Việt'

GO

EXEC sp_insertCategory @_name = N'Cà Phê Tây'

GO

EXEC sp_insertCategory @_name = N'Sinh Tố'

GO

EXEC sp_insertCategory @_name = N'Trà'

GO

EXEC sp_insertCategory @_name = N'Trái Cây'

GO

EXEC sp_insertCategory @_name = N'Sữa Chua'

GO

EXEC sp_insertCategory @_name = N'Khác'

GO

CREATE TABLE Products
(
    id          int primary key identity,
    category_id int foreign key references Categories (id),
    [name]      nvarchar(100) unique not null,
    price       float default (0),
    [status]    bit   default (1)
)

GO

CREATE PROC sp_insertProduct(@_category_id int,
                             @_name nvarchar(100),
                             @_price float,
                             @_status bit = 1,
                             @_outStt bit = 1 output,
                             @_outMsg nvarchar(200) = '' output)
AS
BEGIN TRY
    IF NOT EXISTS(SELECT id FROM Categories WHERE id = @_category_id)
        BEGIN
            SET @_outStt = 0;
            SET @_outMsg = N'Mã danh mục không tồn tại, vui lòng nhập lại'
        END
    ELSE
        IF EXISTS(SELECT [name] FROM Products WHERE [name] = @_name)
            BEGIN
                SET @_outStt = 0;
                SET @_outMsg = N'Tên sản phẩm đã tồn tại, vui lòng nhập lại'
            END
        ELSE
            BEGIN
                INSERT INTO Products(category_id, [name], price, [status])
                VALUES (@_category_id, @_name, @_price, @_status)
                SET @_outStt = 1;
                SET @_outMsg = N'Thêm danh mục thành công';
            END
END TRY
BEGIN CATCH
    SET @_outStt = 0;
    SET @_outMsg = N'Thêm không thành công: ' + ERROR_MESSAGE()
END CATCH

GO

CREATE TABLE Areas
(
    id       int primary key identity,
    [name]   nvarchar(100) unique not null,
    [status] bit default (1)
)

GO

CREATE PROC sp_insertArea(@_name nvarchar(100),
                          @_status bit = 1,
                          @_outStt bit = 1 output,
                          @_outMsg nvarchar(200) = '' output)
AS
BEGIN TRY
    IF EXISTS(SELECT [name] FROM Areas WHERE [name] = @_name)
        BEGIN
            SET @_outStt = 0;
            SET @_outMsg = N'Tên khu vực đã tồn tại, vui lòng nhập lại'
        END
    ELSE
        BEGIN
            INSERT INTO Areas([name], [status]) VALUES (@_name, @_status)
            SET @_outStt = 1;
            SET @_outMsg = N'Thêm khu vực thành công';
        END
END TRY
BEGIN CATCH
    SET @_outStt = 0;
    SET @_outMsg = N'Thêm không thành công: ' + ERROR_MESSAGE()
END CATCH

GO

EXEC sp_insertArea @_name = N'Tầng 1'

GO

EXEC sp_insertArea @_name = N'Tầng 2'

GO

CREATE TABLE [Tables]
(
    id       int primary key identity,
    area_id  int foreign key references Areas (id),
    [name]   nvarchar(100) unique not null,
    note     nvarchar(150),
    [status] bit default (1)
)

GO

CREATE PROC sp_insertTable(@_area_id int,
                           @_name nvarchar(100),
                           @_note nvarchar(150) = '',
                           @_status bit = 1,
                           @_outStt bit = 1 output,
                           @_outMsg nvarchar(200) = '' output)
AS
BEGIN TRY
    IF NOT EXISTS(SELECT id FROM Areas WHERE id = @_area_id)
        BEGIN
            SET @_outStt = 0;
            SET @_outMsg = N'Mã khu vực không tồn tại, vui lòng nhập lại'
        END
    ELSE
        IF EXISTS(SELECT [name] FROM [Tables] WHERE [name] = @_name)
            BEGIN
                SET @_outStt = 0;
                SET @_outMsg = N'Tên bàn đã tồn tại, vui lòng nhập lại'
            END
        ELSE
            BEGIN
                INSERT INTO Tables(area_id, [name], note, [status]) VALUES (@_area_id, @_name, @_note, @_status)
                SET @_outStt = 1;
                SET @_outMsg = N'Thêm bàn thành công'
            END
END TRY
BEGIN CATCH
    SET @_outStt = 0;
    SET @_outMsg = N'Thêm không thành công: ' + ERROR_MESSAGE()
END CATCH

GO

EXEC sp_insertTable @_area_id = 1, @_name = N'Bàn 1'

GO

EXEC sp_insertTable @_area_id = 1, @_name = N'Bàn 2'

GO

EXEC sp_insertTable @_area_id = 1, @_name = N'Bàn 3'

GO

CREATE TABLE Orders
(
    id          int primary key identity,
    user_id     int not null foreign key references Users (id),
    table_id    int not null foreign key references Tables (id),
    total_price float,
    discount    float    default (0),
    note        nvarchar(150),
    status      bit      default (1),
    created_at  datetime default (getdate())
)

GO

CREATE PROC sp_insertOrder(@_user_id int,
                           @_table_id int,
                           @_total_price float,
                           @_discount float = 0,
                           @_note nvarchar(150) = '',
                           @_status bit = 1,
                           @_outStt bit = 1 output,
                           @_outMsg nvarchar(200) = '' output)
AS
BEGIN TRY
    IF NOT EXISTS(SELECT id FROM Users WHERE id = @_user_id)
        BEGIN
            SET @_outStt = 0;
            SET @_outMsg = N'Mã người dùng không tồn tại, vui lòng nhập lại'
        END
    ELSE
        IF NOT EXISTS(SELECT id FROM Tables WHERE id = @_table_id)
            BEGIN
                SET @_outStt = 0;
                SET @_outMsg = N'Mã bàn không tồn tại, vui lòng nhập lại'
            END
        ELSE
            BEGIN
                INSERT INTO Orders(user_id, table_id, total_price, discount, note, status)
                VALUES (@_user_id, @_table_id, @_total_price, @_discount, @_note, @_status)
                BEGIN
                    SET @_outStt = 1;
                    SET @_outMsg = N'Thêm hoá đơn thành công'
                END
            END
END TRY
BEGIN CATCH
    SET @_outStt = 0;
    SET @_outMsg = N'Thêm không thành công: ' + ERROR_MESSAGE()
END CATCH

GO

CREATE TABLE OrderDetails
(
    order_id   int not null foreign key references Orders (id),
    product_id int not null foreign key references Products (id),
    amount     int default (1)
)

GO

CREATE PROC sp_insertOrderDetail(@_order_id int,
                                 @_product_id int,
                                 @_amount int,
                                 @_outStt bit = 1 output,
                                 @_outMsg nvarchar(200) = '' output)
AS
BEGIN TRY
    IF NOT EXISTS(SELECT id FROM Orders WHERE id = @_order_id)
        BEGIN
            SET @_outStt = 0;
            SET @_outMsg = N'Mã đơn hàng không tồn tại, vui lòng nhập lại'
        END
    ELSE
        IF NOT EXISTS(SELECT id
                      FROM Products
                      WHERE id = @_product_id)
            BEGIN
                SET @_outStt = 0;
                SET @_outMsg = N'Mã sản phẩm không tồn tại, vui lòng nhập lại'
            END
        ELSE
            IF @_amount < 1
                BEGIN
                    SET @_outStt = 0;
                    SET @_outMsg = N'Số lượng sản phẩm phải lớn hơn 0'
                END
            ELSE
                BEGIN
                    INSERT INTO OrderDetails(order_id, product_id, amount)
                    VALUES (@_order_id, @_product_id, @_amount)
                    BEGIN
                        SET @_outStt = 1;
                        SET @_outMsg = N'Thêm chi tiết hoá đơn thành công'
                    END
                END
END TRY
BEGIN CATCH
    SET @_outStt = 0;
    SET @_outMsg = N'Thêm không thành công: ' + ERROR_MESSAGE()
END CATCH