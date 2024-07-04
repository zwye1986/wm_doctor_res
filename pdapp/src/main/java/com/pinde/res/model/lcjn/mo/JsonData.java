package com.pinde.res.model.lcjn.mo;

import java.util.List;

/**
 * Created by Administrator on 2017/2/9.
 */

public class JsonData {

    /**
     * courseEvaList : [{"dictId":"1","dictName":"课程难度","evalScore":"3"},{"dictId":"2","dictName":"课堂气氛","evalScore":"2"}]
     * courseFlow : 201702081521456789
     * evalContent : 开发课程2
     */

    private CourseBean course;
    /**
     * course : {"courseEvaList":[{"dictId":"1","dictName":"课程难度","evalScore":"3"},{"dictId":"2","dictName":"课堂气氛","evalScore":"2"}],"courseFlow":"201702081521456789","evalContent":"开发课程2"}
     * teas : [{"evalContent":"开发课程2","teacherEvaList":[{"dictId":"1","dictName":"授课方式","evalScore":"2"},{"dictId":"2","dictName":"工作态度","evalScore":"2"}],"userFlow":"c4d093b6454b4854b8a02123f4566a8c","userName":"华永泉"},{"evalContent":"开发课程2","teacherEvaList":[{"dictId":"1","dictName":"授课方式","evalScore":"2"},{"dictId":"2","dictName":"工作态度","evalScore":"2"}],"userFlow":"5e936f19ede1453c8913c3928d3086ab","userName":"韩兰叶"},{"evalContent":"开发课程2","teacherEvaList":[{"dictId":"1","dictName":"授课方式","evalScore":"2"},{"dictId":"2","dictName":"工作态度","evalScore":"2"}],"userFlow":"4ecca8502942463091d5fc8771214557","userName":"王龙"},{"evalContent":"开发课程2","teacherEvaList":[{"dictId":"1","dictName":"授课方式","evalScore":"2"},{"dictId":"2","dictName":"工作态度","evalScore":"2"}],"userFlow":"12249ea4d23348678109a27779391ef7","userName":"研发经理"}]
     * userFlow : sdfsdf
     */

    private String userFlow;
    /**
     * evalContent : 开发课程2
     * teacherEvaList : [{"dictId":"1","dictName":"授课方式","evalScore":"2"},{"dictId":"2","dictName":"工作态度","evalScore":"2"}]
     * userFlow : c4d093b6454b4854b8a02123f4566a8c
     * userName : 华永泉
     */

    private List<TeasBean> teas;

    public CourseBean getCourse() {
        return course;
    }

    public void setCourse(CourseBean course) {
        this.course = course;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public List<TeasBean> getTeas() {
        return teas;
    }

    public void setTeas(List<TeasBean> teas) {
        this.teas = teas;
    }

    public static class CourseBean {
        private String courseFlow;
        private String evalContent;
        /**
         * dictId : 1
         * dictName : 课程难度
         * evalScore : 3
         */

        private List<CourseEvaListBean> courseEvaList;

        public String getCourseFlow() {
            return courseFlow;
        }

        public void setCourseFlow(String courseFlow) {
            this.courseFlow = courseFlow;
        }

        public String getEvalContent() {
            return evalContent;
        }

        public void setEvalContent(String evalContent) {
            this.evalContent = evalContent;
        }

        public List<CourseEvaListBean> getCourseEvaList() {
            return courseEvaList;
        }

        public void setCourseEvaList(List<CourseEvaListBean> courseEvaList) {
            this.courseEvaList = courseEvaList;
        }

        public static class CourseEvaListBean {
            private String dictId;
            private String dictName;
            private String evalScore;

            public String getDictId() {
                return dictId;
            }

            public void setDictId(String dictId) {
                this.dictId = dictId;
            }

            public String getDictName() {
                return dictName;
            }

            public void setDictName(String dictName) {
                this.dictName = dictName;
            }

            public String getEvalScore() {
                return evalScore;
            }

            public void setEvalScore(String evalScore) {
                this.evalScore = evalScore;
            }
        }
    }

    public static class TeasBean {
        private String evalContent;
        private String userFlow;
        private String userName;
        /**
         * dictId : 1
         * dictName : 授课方式
         * evalScore : 2
         */

        private List<TeacherEvaListBean> teacherEvaList;

        public String getEvalContent() {
            return evalContent;
        }

        public void setEvalContent(String evalContent) {
            this.evalContent = evalContent;
        }

        public String getUserFlow() {
            return userFlow;
        }

        public void setUserFlow(String userFlow) {
            this.userFlow = userFlow;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public List<TeacherEvaListBean> getTeacherEvaList() {
            return teacherEvaList;
        }

        public void setTeacherEvaList(List<TeacherEvaListBean> teacherEvaList) {
            this.teacherEvaList = teacherEvaList;
        }

        public static class TeacherEvaListBean {
            private String dictId;
            private String dictName;
            private String evalScore;

            public String getDictId() {
                return dictId;
            }

            public void setDictId(String dictId) {
                this.dictId = dictId;
            }

            public String getDictName() {
                return dictName;
            }

            public void setDictName(String dictName) {
                this.dictName = dictName;
            }

            public String getEvalScore() {
                return evalScore;
            }

            public void setEvalScore(String evalScore) {
                this.evalScore = evalScore;
            }
        }
    }
}
