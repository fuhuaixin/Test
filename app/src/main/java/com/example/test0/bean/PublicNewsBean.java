package com.example.test0.bean;

import java.util.List;

public class PublicNewsBean {

    private List<DynamicBean> dynamic;
    private List<NewsBean> news;

    public List<DynamicBean> getDynamic() {
        return dynamic;
    }

    public void setDynamic(List<DynamicBean> dynamic) {
        this.dynamic = dynamic;
    }

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public static class DynamicBean {
        /**
         * time : 2020-05-28
         * title : 市工信局一行到我区调研
         * url : 27/1.html
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
         * time : 2020-05-27
         * title : 金水区“票决”确定年度民生实事
         * url : 26/1.html
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
