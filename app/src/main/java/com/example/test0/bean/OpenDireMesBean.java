package com.example.test0.bean;

public class OpenDireMesBean {
    public OpenDireMesBean(String publish, String sendDate, String title,String url) {
        this.publish = publish;
        this.sendDate = sendDate;
        this.title = title;
        this.url = url;
    }

    public OpenDireMesBean(String sendDate, String title, String url) {
        this.sendDate = sendDate;
        this.title = title;
        this.url = url;
    }

    /**
         * abolitionDate :
         * effectiveDate :
         * endDate : 2020-04-23
         * genre :
         * indexNumber : 005255985/2020-00009
         * keyword : 普法
         * publish : 金水区司法局
         * refereNumber :
         * sendDate : 2020-04-23
         * theme : 公安、司法/司法/业务信息/法制宣传
         * title : 2020年“4·15”全民国家安全教育日挂图
         * url : 19/1.html
         */


        private String abolitionDate;
        private String effectiveDate;
        private String endDate;
        private String genre;
        private String indexNumber;
        private String keyword;
        private String publish;
        private String refereNumber;
        private String sendDate;
        private String theme;
        private String title;
        private String url;

        public String getAbolitionDate() {
            return abolitionDate;
        }

        public void setAbolitionDate(String abolitionDate) {
            this.abolitionDate = abolitionDate;
        }

        public String getEffectiveDate() {
            return effectiveDate;
        }

        public void setEffectiveDate(String effectiveDate) {
            this.effectiveDate = effectiveDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getIndexNumber() {
            return indexNumber;
        }

        public void setIndexNumber(String indexNumber) {
            this.indexNumber = indexNumber;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getPublish() {
            return publish;
        }

        public void setPublish(String publish) {
            this.publish = publish;
        }

        public String getRefereNumber() {
            return refereNumber;
        }

        public void setRefereNumber(String refereNumber) {
            this.refereNumber = refereNumber;
        }

        public String getSendDate() {
            return sendDate;
        }

        public void setSendDate(String sendDate) {
            this.sendDate = sendDate;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
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
