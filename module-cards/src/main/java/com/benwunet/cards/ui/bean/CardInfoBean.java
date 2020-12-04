package com.benwunet.cards.ui.bean;

import java.io.Serializable;
import java.util.List;

public class CardInfoBean implements Serializable {


    /**
     * words_result : {"PC":[""],"TEL":[""],"TITLE":["研发总监"],"EMAIL":["ong.xiang@benwunet.cn"],"FAX":[""],"URL":["ewww.benwunet.cn"],"ADDR":["重庆市沙坪坝区西园北街6号附10号5楼"],"COMPANY":["本无网络科技有限公司"],"MOBILE":["13910578825"],"NAME":["向开洪"]}
     * log_id : 1334769828904304640
     * words_result_num : 10
     */
    private String path;
    private WordsResultBean words_result;
    private long log_id;
    private int words_result_num;

    public WordsResultBean getWords_result() {
        return words_result;
    }

    public void setWords_result(WordsResultBean words_result) {
        this.words_result = words_result;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public int getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(int words_result_num) {
        this.words_result_num = words_result_num;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static class WordsResultBean implements Serializable{
        private List<String> PC;
        private List<String> TEL;
        private List<String> TITLE;
        private List<String> EMAIL;
        private List<String> FAX;
        private List<String> URL;
        private List<String> ADDR;
        private List<String> COMPANY;
        private List<String> MOBILE;
        private List<String> NAME;

        public List<String> getPC() {
            return PC;
        }

        public void setPC(List<String> PC) {
            this.PC = PC;
        }

        public List<String> getTEL() {
            return TEL;
        }

        public void setTEL(List<String> TEL) {
            this.TEL = TEL;
        }

        public List<String> getTITLE() {
            return TITLE;
        }

        public void setTITLE(List<String> TITLE) {
            this.TITLE = TITLE;
        }

        public List<String> getEMAIL() {
            return EMAIL;
        }

        public void setEMAIL(List<String> EMAIL) {
            this.EMAIL = EMAIL;
        }

        public List<String> getFAX() {
            return FAX;
        }

        public void setFAX(List<String> FAX) {
            this.FAX = FAX;
        }

        public List<String> getURL() {
            return URL;
        }

        public void setURL(List<String> URL) {
            this.URL = URL;
        }

        public List<String> getADDR() {
            return ADDR;
        }

        public void setADDR(List<String> ADDR) {
            this.ADDR = ADDR;
        }

        public List<String> getCOMPANY() {
            return COMPANY;
        }

        public void setCOMPANY(List<String> COMPANY) {
            this.COMPANY = COMPANY;
        }

        public List<String> getMOBILE() {
            return MOBILE;
        }

        public void setMOBILE(List<String> MOBILE) {
            this.MOBILE = MOBILE;
        }

        public List<String> getNAME() {
            return NAME;
        }

        public void setNAME(List<String> NAME) {
            this.NAME = NAME;
        }
    }
}
