CREATE OR REPLACE PROCEDURE UPDATE_SZINFO AS
BEGIN
    -- 声明游标
    FOR emp_record IN (
            select A.USER_FLOW,A.USER_NAME,A.ORG_FLOW,A.ORG_NAME,case A.TEACHER_LEVEL when '一般师资' then 'GeneralFaculty' when '骨干师资' then 'KeyFaculty' end  as TEACHER_ID,
            A.TEACHER_LEVEL from SYS_USER A  LEFT JOIN RES_TEACHER_TRAINING B ON A.USER_FLOW = B.RECORD_FLOW
            where A.TEACHER_LEVEL IS NOT NULL AND A.RECORD_STATUS = 'Y' AND B.RECORD_FLOW IS NULL
        ) LOOP
        insert into RES_TEACHER_TRAINING (RECORD_FLOW,DOCTOR_NAME,ORG_FLOW,ORG_NAME,TEACHER_LEVEL_ID,TEACHER_LEVEL_NAME,RECORD_STATUS)
        values(emp_record.USER_FLOW,emp_record.USER_NAME,emp_record.ORG_FLOW,emp_record.ORG_NAME,emp_record.TEACHER_ID,emp_record.TEACHER_LEVEL,'Y');
    END LOOP;
    -- 提交事务
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- 发生异常时回滚
        ROLLBACK;
        -- 可以记录错误信息或抛出异常
        DBMS_OUTPUT.PUT_LINE('Error occurred: ' || SQLERRM);
END UPDATE_SZINFO;

begin
    PDSCI.UPDATE_SZINFO;
end;