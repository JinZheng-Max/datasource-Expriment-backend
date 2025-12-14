-- =====================================================
-- 社会实践和奖惩管理 - 学生申请/管理员审核 功能升级SQL
-- =====================================================

USE student_management;

-- =====================================================
-- 1. 修改 student_practice 表，增加审核相关字段
-- =====================================================
ALTER TABLE student_practice
    ADD COLUMN apply_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间' AFTER status,
    ADD COLUMN apply_remark TEXT COMMENT '申请备注/说明' AFTER apply_time,
    ADD COLUMN reviewer_id INT COMMENT '审核人ID' AFTER apply_remark,
    ADD COLUMN review_time DATETIME COMMENT '审核时间' AFTER reviewer_id,
    ADD COLUMN review_comment TEXT COMMENT '审核意见' AFTER review_time;

-- 添加外键约束
ALTER TABLE student_practice
    ADD CONSTRAINT fk_practice_reviewer FOREIGN KEY (reviewer_id) REFERENCES user_account(user_id);

-- =====================================================
-- 2. 修改 reward_punishment 表，增加审核流程字段
-- =====================================================
ALTER TABLE reward_punishment
    ADD COLUMN status ENUM('待审核', '已通过', '未通过') DEFAULT '待审核' COMMENT '审核状态' AFTER remark,
    ADD COLUMN apply_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间' AFTER status,
    ADD COLUMN apply_remark TEXT COMMENT '申请备注/说明' AFTER apply_time,
    ADD COLUMN reviewer_id INT COMMENT '审核人ID' AFTER apply_remark,
    ADD COLUMN review_time DATETIME COMMENT '审核时间' AFTER reviewer_id,
    ADD COLUMN review_comment TEXT COMMENT '审核意见' AFTER review_time;

-- 添加外键约束
ALTER TABLE reward_punishment
    ADD CONSTRAINT fk_rp_reviewer FOREIGN KEY (reviewer_id) REFERENCES user_account(user_id);

-- =====================================================
-- 3. 创建索引以提高查询性能
-- =====================================================
CREATE INDEX idx_practice_status ON student_practice(status);
CREATE INDEX idx_practice_apply_time ON student_practice(apply_time);
CREATE INDEX idx_rp_status ON reward_punishment(status);
CREATE INDEX idx_rp_apply_time ON reward_punishment(apply_time);

-- =====================================================
-- 4. 将现有数据的状态设置为已通过（历史数据兼容）
-- =====================================================
UPDATE student_practice SET status = '已通过' WHERE status IS NULL;
UPDATE reward_punishment SET status = '已通过' WHERE status IS NULL;
