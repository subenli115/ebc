package com.benwunet.home.ui.bean;

public class CodeBean {

    /**
     * url : /pages/cardClip/cardDetails
     * data : {"card_id":"res.data.cardId","requestSources":2}
     * date : new Date().getTime()
     */

    private String url;
    private DataBean data;
    private String date;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static class DataBean {
        /**
         * card_id : res.data.cardId
         * requestSources : 2
         */

        private String card_id;
        private int requestSources;

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }

        public int getRequestSources() {
            return requestSources;
        }

        public void setRequestSources(int requestSources) {
            this.requestSources = requestSources;
        }
    }
}
