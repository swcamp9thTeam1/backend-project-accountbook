# backend-project-accountbook

---
## DDL
```sql
-- ---------------------
-- DROP TABLE DDL
-- 자식 테이블 삭제 -> 부모 테이블 삭제
-- ---------------------
DROP TABLE IF EXISTS community_post_scrap;
DROP TABLE IF EXISTS community_post_comment;
DROP TABLE IF EXISTS community_post_file;
DROP TABLE IF EXISTS community_post;
DROP TABLE IF EXISTS acc_comment;
DROP TABLE IF EXISTS accbook;
DROP TABLE IF EXISTS regular_expense;
DROP TABLE IF EXISTS asset;
DROP TABLE IF EXISTS acc_category;
DROP TABLE IF EXISTS store_review_file;
DROP TABLE IF EXISTS store_review;
DROP TABLE IF EXISTS store;
DROP TABLE IF EXISTS acc_group_post_comment;
DROP TABLE IF EXISTS acc_group_post_file;
DROP TABLE IF EXISTS acc_group_post;
DROP TABLE IF EXISTS acc_group_member;
DROP TABLE IF EXISTS acc_group;
DROP TABLE IF EXISTS member;
-- ----------------------
-- CREATE TABLE DDL
-- 부모 테이블 생성 -> 자식 테이블 생성
-- ----------------------
    
-- member(회원)
CREATE TABLE member (
  code INT PRIMARY KEY AUTO_INCREMENT,
  id VARCHAR(255) NOT NULL,
  nickname VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  is_admin CHAR(1) NOT NULL DEFAULT 'N',
  monthly_budget BIGINT NOT NULL DEFAULT 0,
  CHECK (is_admin IN ('Y', 'N'))
) ENGINE = INNODB;

-- acc_group(그룹)
CREATE TABLE acc_group (
  code INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  intro VARCHAR(255) NOT NULL
) ENGINE = INNODB;

-- acc_group_member(그룹 멤버)
CREATE TABLE acc_group_member (
  member_code INT,
  acc_group_code INT,
  role VARCHAR(255) NOT NULL,
  PRIMARY KEY (member_code, acc_group_code),
  FOREIGN KEY (member_code) REFERENCES member(code),
  FOREIGN KEY (acc_group_code) REFERENCES acc_group(code),
  CHECK (role IN ('ROLE_LEADER', 'ROLE_FOLLOWER', 'ROLE_UNALLOWED'))
) ENGINE = INNODB;

-- acc_group_post(그룹 게시글)
CREATE TABLE acc_group_post (
  code INT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL,
  title VARCHAR(255) NOT NULL,
  detail TEXT NOT NULL,
  member_code INT NOT NULL,
  acc_group_code INT NOT NULL,
  FOREIGN KEY (member_code, acc_group_code) REFERENCES acc_group_member(member_code, acc_group_code)
) ENGINE = INNODB;

-- acc_group_post_file(그룹 게시글 첨부파일)
CREATE TABLE acc_group_post_file (
  code INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  path VARCHAR(255) NOT NULL,
  acc_group_post_code INT NOT NULL,
  FOREIGN KEY (acc_group_post_code) REFERENCES acc_group_post(code)
) ENGINE = INNODB;

-- acc_group_post_comment(그룹 게시글 댓글)
CREATE TABLE acc_group_post_comment (
  code INT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL,
  detail TEXT NOT NULL,
  parent_code INT,
  acc_group_post_code INT NOT NULL,
  member_code INT NOT NULL,
  acc_group_code INT NOT NULL,
  FOREIGN KEY (parent_code) REFERENCES acc_group_post_comment(code),
  FOREIGN KEY (acc_group_post_code) REFERENCES acc_group_post(code),
  FOREIGN KEY (member_code, acc_group_code) REFERENCES acc_group_member(member_code, acc_group_code)
) ENGINE = INNODB;

-- store(가게)
CREATE TABLE store (
  code INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  latitude VARCHAR(255) NOT NULL,
  longitude VARCHAR(255) NOT NULL,
  is_good CHAR(1) NOT NULL DEFAULT 'N',
  good_menu_name VARCHAR(255),
  good_menu_price INT,
  CHECK (is_good IN ('Y', 'N'))
) ENGINE = INNODB;

-- store_review(가게 리뷰)
CREATE TABLE store_review (
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

-- store_review_file(가게 리뷰 첨부파일)
CREATE TABLE store_review_file (
  code INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  path VARCHAR(255) NOT NULL,
  store_review_code INT NOT NULL,
  FOREIGN KEY (store_review_code) REFERENCES store_review(code)
)ENGINE=INNODB;

-- acc_category(가계부 카테고리)
CREATE TABLE acc_category (
  code INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  finance_type CHAR(1) NOT NULL,
  visibility CHAR(1) NOT NULL DEFAULT 'Y',
  is_deleted CHAR(1) NOT NULL DEFAULT 'N',
  member_code INT NOT NULL,
  parent_code INT,
  FOREIGN KEY (member_code) REFERENCES member(code),
  FOREIGN KEY (parent_code) REFERENCES acc_category(code),
  CHECK (finance_type IN ('I', 'O')),
  CHECK (visibility IN ('Y', 'N')),
  CHECK (is_deleted IN ('Y', 'N'))
) ENGINE = INNODB;

-- asset(자산)
CREATE TABLE asset (
  code INT PRIMARY KEY AUTO_INCREMENT,
  category CHAR(2) NOT NULL,
  name VARCHAR(255) NOT NULL,
  balance BIGINT NOT NULL,
  payment_date INT,
  is_deleted CHAR(1) NOT NULL DEFAULT 'N',
  member_code INT NOT NULL,
  FOREIGN KEY (member_code) REFERENCES member(code),
  CHECK (category IN ('M', 'B', 'C', 'CC', 'S', 'L')),
  CHECK (is_deleted IN ('Y', 'N'))
)ENGINE = INNODB;

-- regualr_expense(고정지출)
CREATE TABLE regular_expense (
  code INT PRIMARY KEY AUTO_INCREMENT,
  expense_date TINYINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  amount BIGINT NOT NULL,
  member_code INT NOT NULL,
  asset_code INT NOT NULL,
  acc_category_code INT NOT NULL,
  FOREIGN KEY (member_code) REFERENCES member(code),
  FOREIGN KEY (asset_code) REFERENCES asset(code),
  FOREIGN KEY (acc_category_code) REFERENCES acc_category(code)
)ENGINE = INNODB;

-- accbook(가계부내역)
CREATE TABLE accbook (
  code INT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL,
  title VARCHAR(255) NOT NULL,
  amount BIGINT NOT NULL,
  is_regular CHAR(1) NOT NULL DEFAULT 'N',
  member_code INT NOT NULL,
  acc_category_code INT NOT NULL,
  store_code INT,
  asset_code INT NOT NULL,
  FOREIGN KEY (member_code) REFERENCES member(code),
  FOREIGN KEY (acc_category_code) REFERENCES acc_category(code),
  FOREIGN KEY (store_code) REFERENCES store(code) ON DELETE SET NULL,
  FOREIGN KEY (asset_code) REFERENCES asset(code),
  CHECK (is_regular IN ('Y', 'N'))
)ENGINE=INNODB;

-- acc_comment(가계부 댓글)
CREATE TABLE acc_comment (
  code INT PRIMARY KEY AUTO_INCREMENT,
  detail VARCHAR(255) NOT NULL,
  created_at DATETIME NOT NULL,
  parent_code INT,
  accbook_code INT NOT NULL,
  member_code INT NOT NULL,
  FOREIGN KEY (parent_code) REFERENCES acc_comment(code),
  FOREIGN KEY (accbook_code) REFERENCES accbook(code),
  FOREIGN KEY (member_code) REFERENCES member(code)
) ENGINE = INNODB;

-- community_post(커뮤니티 게시글)
CREATE TABLE community_post (
  code INT PRIMARY KEY AUTO_INCREMENT,
  create_at DATETIME NOT NULL,
  title VARCHAR(255) NOT NULL,
  detail TEXT NOT NULL,
  member_code INT NOT NULL,
  FOREIGN KEY (member_code) REFERENCES member(code)
) ENGINE = INNODB;

-- community_post_file(커뮤니티 첨부파일)
CREATE TABLE community_post_file (
  code INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  path VARCHAR(255) NOT NULL,
  community_post_code INT NOT NULL,
  FOREIGN KEY (community_post_code) REFERENCES community_post(code)
) ENGINE = INNODB;

-- community_post_comment(커뮤니티 게시글 댓글)
CREATE TABLE community_post_comment (
  code INT PRIMARY KEY AUTO_INCREMENT,
  created_at DATETIME NOT NULL,
  detail VARCHAR(255) NOT NULL,
  community_post_code INT NOT NULL,
  member_code INT NOT NULL,
  parent_code INT,
  FOREIGN KEY (community_post_code) REFERENCES community_post(code),
  FOREIGN KEY (member_code) REFERENCES member(code),
  FOREIGN KEY (parent_code) REFERENCES community_post_comment(code)
) ENGINE = INNODB;

-- community_post_scrap(커뮤니티 게시글 스크랩)
CREATE TABLE community_post_scrap (
  member_code INT,
  community_post_code INT,
  PRIMARY KEY (member_code, community_post_code),
  FOREIGN KEY (member_code) REFERENCES member(code),
  FOREIGN KEY (community_post_code) REFERENCES community_post(code)
) ENGINE = INNODB;
