package com.example.test0.bean;

import java.util.List;

public class GovernmentBean {


    private List<DocumentBean> document;
    private List<InformBean> inform;
    private List<NewsBean> news;
    private List<PoliciesBean> policies;

    public List<DocumentBean> getDocument() {
        return document;
    }

    public void setDocument(List<DocumentBean> document) {
        this.document = document;
    }

    public List<InformBean> getInform() {
        return inform;
    }

    public void setInform(List<InformBean> inform) {
        this.inform = inform;
    }

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public List<PoliciesBean> getPolicies() {
        return policies;
    }

    public void setPolicies(List<PoliciesBean> policies) {
        this.policies = policies;
    }

    public static class DocumentBean {
        /**
         * time : 2019-12-23
         * title : 郑州市金水区人民政府关于同意调整1个社区管辖范围及新设立3个社区的批复
         * url : 24/001.html
         */

        private String time;
        private String title;
        private String url;

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

    public static class InformBean {
        /**
         * time : 2020-03-31
         * title : 国家税务总局郑州市金水区税务局2019年法治政府建设情况报告
         * url : 22/TZ001.html
         */

        private String time;
        private String title;
        private String url;

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

    public static class NewsBean {
        /**
         * time : 2020-05-28
         * title : 金水区人武部举行党委第一书记任职会议
         * url : 25/zw001.html
         */

        private String time;
        private String title;
        private String url;

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

    public static class PoliciesBean {
        /**
         * time : 2020-04-14
         * title : 农作物病虫害防治条例
         * url : 23/001.html
         */

        private String time;
        private String title;
        private String url;

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
