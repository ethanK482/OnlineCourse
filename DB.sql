CREATE TABLE "account"(
    "id" BIGINT NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    "isVerifyEmail" TINYINT default 0
);
ALTER TABLE
    "account" ADD CONSTRAINT "account_id_primary" PRIMARY KEY("id");
CREATE TABLE "review"(
    "id" BIGINT NOT NULL,
    "courseId" BIGINT NOT NULL,
    "content" VARCHAR(max) NOT NULL,
    "rate" INT NOT NULL
);
ALTER TABLE
    "review" ADD CONSTRAINT "review_id_primary" PRIMARY KEY("id");
CREATE TABLE "userCourse"(
    "courseID" BIGINT NOT NULL,
    "uid" BIGINT NOT NULL
);
CREATE TABLE "couseCategory"(
    "categoryId" BIGINT NOT NULL,
    "courseId" BIGINT NOT NULL
);
CREATE TABLE "user"(
    "uid" BIGINT NOT NULL,
    "accountID" BIGINT NOT NULL,
    "firstName" VARCHAR(255) NOT NULL,
    "lastName" VARCHAR(255) NOT NULL,
    "balance" DECIMAL(8, 2) NOT NULL,
    "role" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "user" ADD CONSTRAINT "user_uid_primary" PRIMARY KEY("uid");
CREATE TABLE "category"(
    "id" BIGINT NOT NULL,
    "name" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "category" ADD CONSTRAINT "category_id_primary" PRIMARY KEY("id");
CREATE TABLE "course"(
    "id" BIGINT NOT NULL,
    "uid" BIGINT NOT NULL,
    "title" VARCHAR(255) NOT NULL,
    "description" VARCHAR(max) NOT NULL
);
ALTER TABLE
    "course" ADD CONSTRAINT "course_id_primary" PRIMARY KEY("id");
CREATE TABLE "video"(
    "id" BIGINT NOT NULL,
    "courseID" BIGINT NOT NULL,
    "title" VARCHAR(255) NOT NULL,
    "description" VARCHAR(max) NOT NULL
);
ALTER TABLE
    "video" ADD CONSTRAINT "video_id_primary" PRIMARY KEY("id");
ALTER TABLE
    "user" ADD CONSTRAINT "user_accountid_foreign" FOREIGN KEY("accountID") REFERENCES "account"("id");
ALTER TABLE
    "couseCategory" ADD CONSTRAINT "cousecategory_categoryid_foreign" FOREIGN KEY("categoryId") REFERENCES "category"("id");
ALTER TABLE
    "userCourse" ADD CONSTRAINT "usercourse_courseid_foreign" FOREIGN KEY("courseID") REFERENCES "course"("id");
ALTER TABLE
    "review" ADD CONSTRAINT "review_id_foreign" FOREIGN KEY("id") REFERENCES "course"("id");
ALTER TABLE
    "couseCategory" ADD CONSTRAINT "cousecategory_courseid_foreign" FOREIGN KEY("courseId") REFERENCES "course"("id");
ALTER TABLE
    "video" ADD CONSTRAINT "video_courseid_foreign" FOREIGN KEY("courseID") REFERENCES "course"("id");
ALTER TABLE
    "course" ADD CONSTRAINT "course_uid_foreign" FOREIGN KEY("uid") REFERENCES "user"("uid");
ALTER TABLE
    "userCourse" ADD CONSTRAINT "usercourse_uid_foreign" FOREIGN KEY("uid") REFERENCES "user"("uid");