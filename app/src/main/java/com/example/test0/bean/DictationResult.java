package com.example.test0.bean;

import java.util.List;

/**
 * 解析 语音听写返回结果Json格式字符串 的模板类（多重嵌套Json）
 *
 * 语音识别结果Json数据格式（单条数据）：
 * {"sn":1,"ls":true,"bg":0,"ed":0,"ws":[
 * {"bg":0,"cw":[{"w":"今天","sc":0}]},
 * {"bg":0,"cw":[{"w":"的","sc":0}]},
 * {"bg":0,"cw":[{"w":"天气","sc":0}]},
 * {"bg":0,"cw":[{"w":"怎么样","sc":0}]},
 * {"bg":0,"cw":[{"w":"。","sc":0}]}
 * ]}
 *
 * sn  number :第几句
 * ls   boolean: 是否最后一句
 * bg  number :开始
 * ed  number :结束
 * ws  array :词
 * cw   array :中文分词
 * w  string :单字
 * sc  number :分数
 */
public class DictationResult {

    /**
     * sn : 1
     * ls : false
     * bg : 0
     * ed : 0
     * ws : [{"bg":29,"cw":[{"sc":0,"w":"我"}]},{"bg":53,"cw":[{"sc":0,"w":"要是"}]},{"bg":85,"cw":[{"sc":0,"w":"不说"}]},{"bg":125,"cw":[{"sc":0,"w":"有人"}]},{"bg":169,"cw":[{"sc":0,"w":"会"}]},{"bg":189,"cw":[{"sc":0,"w":"怎样"}]}]
     */

    private int sn;
    private boolean ls;
    private int bg;
    private int ed;
    private List<WsBean> ws;

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public boolean isLs() {
        return ls;
    }

    public void setLs(boolean ls) {
        this.ls = ls;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public int getEd() {
        return ed;
    }

    public void setEd(int ed) {
        this.ed = ed;
    }

    public List<WsBean> getWs() {
        return ws;
    }

    public void setWs(List<WsBean> ws) {
        this.ws = ws;
    }

    public static class WsBean {
        /**
         * bg : 29
         * cw : [{"sc":0,"w":"我"}]
         */

        private int bg;
        private List<CwBean> cw;

        public int getBg() {
            return bg;
        }

        public void setBg(int bg) {
            this.bg = bg;
        }

        public List<CwBean> getCw() {
            return cw;
        }

        public void setCw(List<CwBean> cw) {
            this.cw = cw;
        }

        public static class CwBean {
            /**
             * sc : 0.0
             * w : 我
             */

            private double sc;
            private String w;

            public double getSc() {
                return sc;
            }

            public void setSc(double sc) {
                this.sc = sc;
            }

            public String getW() {
                return w;
            }

            public void setW(String w) {
                this.w = w;
            }
        }
    }
}
