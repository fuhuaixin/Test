package com.example.test0.bean;

import java.io.Serializable;
import java.util.List;

public class LetterBoxBean implements Serializable {


    private List<DateBean> date;

    public List<DateBean> getDate() {
        return date;
    }

    public void setDate(List<DateBean> date) {
        this.date = date;
    }

    public static class DateBean implements Serializable{
        /**
         * askMessage : 经一路北七号院是否纳入2020老旧小区改造？
         * dealMessage : 您好，感谢您的来信。经核实，金水区丰产路街道办事处经一路北七号院现已纳入2020年老旧小区改造项目。
         * dealTime : 金水区丰产路街道办事处 65836131 2020-5-29
         * gov : 金水区人民政府
         * progress : 处理完毕
         * sendTime : 2020-05-26
         * title : 经一路北七号院是否纳入2020老旧小区改造？
         * type : 咨询
         * url : 21/1.html
         */

        private String askMessage;
        private String dealMessage;
        private String dealTime;
        private String gov;
        private String progress;
        private String sendTime;
        private String title;
        private String type;
        private String url;

        public String getAskMessage() {
            return askMessage;
        }

        public void setAskMessage(String askMessage) {
            this.askMessage = askMessage;
        }

        public String getDealMessage() {
            return dealMessage;
        }

        public void setDealMessage(String dealMessage) {
            this.dealMessage = dealMessage;
        }

        public String getDealTime() {
            return dealTime;
        }

        public void setDealTime(String dealTime) {
            this.dealTime = dealTime;
        }

        public String getGov() {
            return gov;
        }

        public void setGov(String gov) {
            this.gov = gov;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
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
