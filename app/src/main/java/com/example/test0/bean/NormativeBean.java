package com.example.test0.bean;

import java.util.List;

public class NormativeBean {


    private List<DateBean> date;

    public List<DateBean> getDate() {
        return date;
    }

    public void setDate(List<DateBean> date) {
        this.date = date;
    }

    public static class DateBean {
        /**
         * publish : 金水区食品药品监督管理局
         * time : 2020-05-26
         * title : 关于1批次不合格食品核查处置情况的通告（2020年第24期）
         * url : 20/1.html
         */

        private String publish;
        private String time;
        private String title;
        private String url;

        public String getPublish() {
            return publish;
        }

        public void setPublish(String publish) {
            this.publish = publish;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
