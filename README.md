# backend-project-accountbook

## DDD
---
### 회원, 자산, 고정지출 Context
![image](https://github.com/user-attachments/assets/03d2772f-02ea-4679-8afc-de16827e99dc)

### 가계부내역, 가계부카테고리, 가계부댓글 Context
![image](https://github.com/user-attachments/assets/b9efce50-3e4b-4e8c-b9f4-1c4fedd8e6a1)

###  


## 개념 모델링
![image](https://github.com/user-attachments/assets/31ecba32-3a0e-4591-a62f-481c805a88ab)

<br>

## 논리 모델링
![공유가계부_논리모델링](https://github.com/user-attachments/assets/82e6e139-fae1-4988-917c-31661c5befb7)

<br>

## 물리 모델링
![공유가계부_물리모델링](https://github.com/user-attachments/assets/af109269-49c2-450d-b174-f5c36e75ddda)



---
## DDL
```sql
-----------------------
-- DROP TABLE DDL
-- 자식 테이블 삭제 -> 부모 테이블 삭제
-----------------------
DROP TABLE IF EXISTS acc_comment;
DROP TABLE IF EXISTS accbook;
DROP TABLE IF EXISTS asset;
DROP TABLE IF EXISTS acc_category;
DROP TABLE IF EXISTS review_file;
DROP TABLE IF EXISTS store_review;
DROP TABLE IF EXISTS store;
DROP TABLE IF EXISTS member;
------------------------
-- CREATE TABLE DDL
-- 부모 테이블 생성 -> 자식 테이블 생성
------------------------
    
-- member(회원)
CREATE TABLE IF NOT EXISTS member (
    code INT PRIMARY KEY AUTO_INCREMENT,
    id VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_admin CHAR(1) NOT NULL CHECK (is_admin IN ('Y', 'N')),
    monthly_budget BIGINT NOT NULL
	) ENGINE = INNODB;

-- asset(자산)
CREATE TABLE if NOT EXISTS asset
(
    code INT AUTO_INCREMENT
  , category VARCHAR(255) NOT NULL
  , name VARCHAR(255) NOT NULL
  , balance BIGINT NOT NULL
  , payment_date INT
  , is_deleted CHAR(1) NOT NULL
  , member_code INT NOT NULL
  , PRIMARY KEY(code)
  , FOREIGN KEY(member_code) REFERENCES member(code)
  , CHECK(category IN ('M', 'B', 'C', 'CC', 'S', 'L'))
  , CHECK(is_deleted IN ('Y', 'N'))
)
ENGINE=INNODB;
    
-- store(가게)
CREATE TABLE IF NOT EXISTS store (
    code INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    latitude VARCHAR(255) NOT NULL,
    longitude VARCHAR(255) NOT NULL,
    is_good CHAR(1) NOT NULL,
    good_menu_name VARCHAR(255),
    good_menu_price INT,
    CHECK (is_good IN ('Y', 'N'))
    ) ENGINE = INNODB;

-- acc_group(그룹)
    
-- acc_group_member(그룹 멤버)
    
-- acc_group_post(그룹 게시글)

-- acc_category(가계부 카테고리)
CREATE TABLE if NOT EXISTS acc_category
(
  code INT AUTO_INCREMENT
  , name VARCHAR(255) NOT NULL
  , finance_type CHAR(1) NOT NULL
  , visibility CHAR(1) NOT NULL DEFAULT 'Y'
  , is_deleted CHAR(1) NOT NULL
  , member_code INT NOT NULL
  , parent_code INT
  , PRIMARY KEY(code)
  , FOREIGN KEY(member_code) REFERENCES member(code)
  , FOREIGN KEY(parent_code) REFERENCES acc_category(code)
  , CHECK(finance_type IN ('I', 'O'))
  , CHECK(visibility IN ('Y', 'N'))
  , CHECK(is_deleted IN ('Y', 'N'))
)
ENGINE=INNODB;
    
-- accbook(가계부내역)
CREATE TABLE IF NOT EXISTS accbook (
    code INT PRIMARY KEY AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    amount BIGINT NOT NULL,
    is_regular VARCHAR(1) NOT NULL,
    title VARCHAR(255) NOT NULL,
    member_code INT NOT NULL,
    acc_category_code INT NOT NULL,
    store_code INT NULL,
    asset_code INT NOT NULL,
    FOREIGN KEY (member_code) REFERENCES member(code),
    FOREIGN KEY (acc_category_code) REFERENCES acc_category(code),
    FOREIGN KEY (store_code) REFERENCES store(code),
    FOREIGN KEY (asset_code) REFERENCES asset(code),
    CHECK ( is_regular IN ('Y', 'N') )
) ENGINE=INNODB;

-- acc_comment(가계부 댓글)
CREATE TABLE IF NOT EXISTS acc_comment (
    code INT PRIMARY KEY AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    detail VARCHAR(255) NOT NULL,
    parent_code INT DEFAULT NULL,
    accbook_code INT NOT NULL,
    member_code INT NULL,
    FOREIGN KEY (parent_code) REFERENCES acc_comment(code) ON DELETE CASCADE,
    FOREIGN KEY (accbook_code) REFERENCES accbook(code),
    FOREIGN KEY (member_code) REFERENCES member(code)
) ENGINE=INNODB;

-- regualr_expense(고정지출)

-- store_review(가게 리뷰)
CREATE TABLE store_review(
    code INT PRIMARY KEY AUTO_INCREMENT,      
    created_at DATETIME NOT NULL,          
    visitors INT NOT NULL,                 
    total_expense BIGINT NOT NULL,          
    one_line_review VARCHAR(255) NOT NULL,
    member_code INT NOT NULL,              
    store_code INT NOT NULL,                                   
    FOREIGN KEY (member_code) REFERENCES member(code),  
    FOREIGN KEY (store_code) REFERENCES store(code)     
   )ENGINE=INNODB;
    
-- community_post(커뮤니티 게시글)

-- community_post_scrap(커뮤니티 게시글 스크랩)
    
-- community_post_comment(커뮤니티 게시글 댓글)
    
-- acc_group_post_comment(그룹 게시글 댓글)

-- review_file(리뷰 첨부파일)
CREATE TABLE review_file(
  code INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  path VARCHAR(255) NOT NULL,
  store_review_code INT NOT NULL, 
  FOREIGN KEY (store_review_code) REFERENCES store_review(code)
) ENGINE=INNODB;

-- community_file(커뮤니티 첨부파일)

-- acc_group_post_file(그룹 게시글 첨부파일)

```
