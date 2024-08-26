# backend-project-accountbook

## DDL
```sql
-----------------------
-- DROP TABLE DDL
-- 자식 테이블 삭제 -> 부모 테이블 삭제
-----------------------
DROP TABLE IF EXISTS accbook;
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
    
-- accbook(가계부내역)
CREATE TABLE IF NOT EXISTS accbook
(
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
    FOREIGN KEY (asset_code) REFERENCES asset(code)
    ) ENGINE=INNODB;

-- acc_comment(가계부 댓글)

-- regualr_expense(고정지출)

-- store_review(가게 리뷰)
    
-- community_post(커뮤니티 게시글)

-- community_post_scrap(커뮤니티 게시글 스크랩)
    
-- community_post_comment(커뮤니티 게시글 댓글)
    
-- acc_group_post_comment(그룹 게시글 댓글)

-- review_file(리뷰 첨부파일)

-- community_file(커뮤니티 첨부파일)

-- acc_group_post_file(그룹 게시글 첨부파일)

```
