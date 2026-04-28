CREATE DATABASE IF NOT EXISTS homevalue
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE homevalue;

-- Optional: create a dedicated application user instead of using root.
-- Replace 'your_password_here' before running this section.
-- CREATE USER IF NOT EXISTS 'homevalue_user'@'%' IDENTIFIED BY 'your_password_here';
-- GRANT ALL PRIVILEGES ON homevalue.* TO 'homevalue_user'@'%';
-- FLUSH PRIVILEGES;
