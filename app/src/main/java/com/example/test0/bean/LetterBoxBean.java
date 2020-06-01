package com.example.test0.bean;

import java.util.List;

public class LetterBoxBean {


    private List<DateBean> date;

    public List<DateBean> getDate() {
        return date;
    }

    public void setDate(List<DateBean> date) {
        this.date = date;
    }

    public static class DateBean {
        /**
         * progress : 处理完毕
         * time : 2020-05-28
         * title : 经一路北七号院是否纳入2020老旧小区改造？
         * type : 咨询
         * url : 21/1.html
         */

        private String progress;
        private String time;
        private String title;
        private String type;
        private String url;

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
