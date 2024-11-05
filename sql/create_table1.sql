-- 创建库
create database if not exists teaching_evaluation;
use teaching_evaluation;
--评论指标表
create table if not exists EvaluationIndicators(
    id  bigint auto_increment comment 'id' primary key,
    indicator varchar(100) comment '评价指标',
    isDelete tinyint default 0 not null comment '是否删除',
    createTime datetime not null comment '创建时间',
    updateTime datetime not null comment '更新时间'
) comment '评价指标表' collate = utf8mb4_unicode_ci;


--老师表
create table if not exists teacher(
    id  bigint auto_increment comment 'id' primary key,
    tnumber varchar(8) not null unique comment '老师工号',
    name varchar(50) not null comment '姓名',
    gender varchar(50) not null comment '性别',
    age int not null comment '年龄',
    phone varchar(50) null comment '电话号码',
    introduction varchar(100) null comment '一句话描述',
    isDelete tinyint default 0 not null comment '是否删除',
    createTime datetime not null comment '创建时间',
    updateTime datetime not null comment '更新时间',
    index idx_tnumber(tnumber)
) comment '老师表' collate = utf8mb4_unicode_ci;

-- 班级表
create table if not exists class(
    id  bigint auto_increment comment 'id' primary key,
    classId varchar(50) not null comment '班级号',
    headTeacher varchar(8) comment '班主任',
    ChineseTeacher varchar(8) comment '语文老师',
    MathTeacher varchar(8) comment '数学老师',
    EnglishTeacher varchar(8) comment '英语老师',
    isDelete tinyint default 0 not null comment '是否删除',
    createTime datetime not null comment '创建时间',
    updateTime datetime not null comment '更新时间',
    index idx_classId(classId),
    foreign key (headTeacher) references teacher(tnumber) on delete set null on update cascade,
    foreign key (ChineseTeacher) references teacher(tnumber) on delete set null on update cascade,
    foreign key (MathTeacher) references teacher(tnumber) on delete set null on update cascade,
    foreign key (EnglishTeacher) references teacher(tnumber) on delete set null on update cascade
    ) comment '班级表' collate = utf8mb4_unicode_ci;

--学生表
create table if not exists student(
    id  bigint auto_increment comment 'id' primary key,
    snumber varchar(8) not null unique comment '学号',
    name varchar(50) not null comment '姓名',
    gender varchar(50) not null comment '性别',
    age int not null comment '年龄',
    classId varchar(50)  comment '所属班级',
    password varchar(50) not null comment '密码',
    isDelete tinyint default 0 not null comment '是否删除',
    createTime datetime not null comment '创建时间',
    updateTime datetime not null comment '更新时间',
    index idx_snumber(snumber),
    foreign key (classId) references class(classId) on delete set null on update cascade
    ) comment '学生表' collate = utf8mb4_unicode_ci;
--管理员表
create table if not exists admin(
    id  bigint auto_increment comment 'id' primary key,
    snumber varchar(8) not null unique comment '账号',
    name varchar(50) not null comment '姓名',
    password varchar(50) not null comment '密码',
    gender varchar(50) not null comment '性别',
    age int not null comment '年龄',
    phone varchar(50) null comment '电话号码',
    isDelete tinyint default 0 not null comment'是否删除',
    createTime datetime not null comment '创建时间',
    updateTime datetime not null comment '更新时间',
    index idx_snumber(snumber)
    ) comment '管理员表' collate = utf8mb4_unicode_ci;
--评论结果表

create table if not exists EvaluationResults(
    id  bigint auto_increment comment 'id' primary key,
    snumber varchar(8) not null comment '学生学号',
    sname varchar(50) not null comment '学生姓名',
    tnumber varchar(8) not null comment '老师工号',
    tname varchar(50) not null comment '老师姓名',
    results varchar(512) comment '评价结果',
    indicatorId bigint not null comment '评价指标',
    isDelete tinyint default 0 not null comment '是否删除',
    createTime datetime not null comment '创建时间',
    updateTime datetime not null comment '更改时间',
    foreign key (tnumber) references teacher(tnumber) on delete cascade on update cascade,
    foreign key (indicatorId) references EvaluationIndicators(id) on delete cascade on update cascade,
    foreign key (snumber) references student(snumber) on delete cascade on update cascade
    ) comment '评价结果表' collate = utf8mb4_unicode_ci;




