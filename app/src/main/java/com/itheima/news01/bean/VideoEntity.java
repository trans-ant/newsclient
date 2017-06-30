package com.itheima.news01.bean;

import java.util.List;

/**
 * @author WJQ
 */

public class VideoEntity {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * sizeSHD : 19200
         * replyCount : 0
         * videosource : 新媒体
         * mp4Hd_url : http://flv2.bn.netease.com/videolib3/1706/27/VADzd4071/HD/VADzd4071-mobile.mp4
         * title : 切下的月季花没几天就蔫了？加点这个，半个月都开得美美的
         * cover : http://vimg3.ws.126.net/image/snapshot/2017/6/P/J/VDN1GVFPJ.jpg
         * videoTopic : {"alias":"专业的养花爱好者交流平台","tname":"养花大全","ename":"T1477545427791","tid":"T1477545427791","topic_icons":"http://dingyue.nosdn.127.net/3pVYF6pTIQaTSwDUKFP=07AxbjUfQIHAf9z1x1phiM=d91477545427180.jpg"}
         * description : 买来的鲜花，或者是家中的鲜切花，放花瓶里，装饰家居，可是没几天就凋谢了，美美的状态维持不了几天，其实加点儿啤酒就能轻松解决，花期能延长一星期都不是事儿！像家中种的月季、玫瑰、芍药、牡丹、绣球等鲜花，切下做成插花，插水里兑点啤酒，啤酒为鲜花提供养分，半月都不凋谢！
         * replyid : DN1GVFPI050835RB
         * length : 128
         * m3u8_url : http://flv.bn.netease.com/videolib3/1706/27/VADzd4071/SD/movie_index.m3u8
         * vid : VDN1GVFPI
         * topicName : 养花大全
         * votecount : 0
         * topicImg : http://vimg3.ws.126.net/image/snapshot/2017/3/S/9/VCE7JSTS9.jpg
         * topicDesc : 专业的养花爱好者交流平台，提供最全面的花卉知识、最专业的养花技巧、最实用的健康妙招。各种花卉知识在线解答，养花爱好者必备神器！
         * topicSid : VCE7JSTRS
         * replyBoard : video_bbs
         * playCount : 0
         * sectiontitle :
         * mp4_url : http://flv2.bn.netease.com/videolib3/1706/27/VADzd4071/SD/VADzd4071-mobile.mp4
         * playersize : 1
         * sizeSD : 9600
         * sizeHD : 12800
         * m3u8Hd_url : http://flv.bn.netease.com/videolib3/1706/27/VADzd4071/HD/movie_index.m3u8
         * ptime : 2017-06-27 19:51:26
         */

        private int sizeSHD;
        private int replyCount;
        private String videosource;
        private String mp4Hd_url;
        private String title;
        private String cover;
        private VideoTopicBean videoTopic;
        private String description;
        private String replyid;
        private int length;
        private String m3u8_url;
        private String vid;
        private String topicName;
        private int votecount;
        private String topicImg;
        private String topicDesc;
        private String topicSid;
        private String replyBoard;
        private int playCount;
        private String sectiontitle;
        private String mp4_url;
        private int playersize;
        private int sizeSD;
        private int sizeHD;
        private String m3u8Hd_url;
        private String ptime;

        public int getSizeSHD() {
            return sizeSHD;
        }

        public void setSizeSHD(int sizeSHD) {
            this.sizeSHD = sizeSHD;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public String getVideosource() {
            return videosource;
        }

        public void setVideosource(String videosource) {
            this.videosource = videosource;
        }

        public String getMp4Hd_url() {
            return mp4Hd_url;
        }

        public void setMp4Hd_url(String mp4Hd_url) {
            this.mp4Hd_url = mp4Hd_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public VideoTopicBean getVideoTopic() {
            return videoTopic;
        }

        public void setVideoTopic(VideoTopicBean videoTopic) {
            this.videoTopic = videoTopic;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getReplyid() {
            return replyid;
        }

        public void setReplyid(String replyid) {
            this.replyid = replyid;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public String getM3u8_url() {
            return m3u8_url;
        }

        public void setM3u8_url(String m3u8_url) {
            this.m3u8_url = m3u8_url;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public int getVotecount() {
            return votecount;
        }

        public void setVotecount(int votecount) {
            this.votecount = votecount;
        }

        public String getTopicImg() {
            return topicImg;
        }

        public void setTopicImg(String topicImg) {
            this.topicImg = topicImg;
        }

        public String getTopicDesc() {
            return topicDesc;
        }

        public void setTopicDesc(String topicDesc) {
            this.topicDesc = topicDesc;
        }

        public String getTopicSid() {
            return topicSid;
        }

        public void setTopicSid(String topicSid) {
            this.topicSid = topicSid;
        }

        public String getReplyBoard() {
            return replyBoard;
        }

        public void setReplyBoard(String replyBoard) {
            this.replyBoard = replyBoard;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public String getSectiontitle() {
            return sectiontitle;
        }

        public void setSectiontitle(String sectiontitle) {
            this.sectiontitle = sectiontitle;
        }

        public String getMp4_url() {
            return mp4_url;
        }

        public void setMp4_url(String mp4_url) {
            this.mp4_url = mp4_url;
        }

        public int getPlayersize() {
            return playersize;
        }

        public void setPlayersize(int playersize) {
            this.playersize = playersize;
        }

        public int getSizeSD() {
            return sizeSD;
        }

        public void setSizeSD(int sizeSD) {
            this.sizeSD = sizeSD;
        }

        public int getSizeHD() {
            return sizeHD;
        }

        public void setSizeHD(int sizeHD) {
            this.sizeHD = sizeHD;
        }

        public String getM3u8Hd_url() {
            return m3u8Hd_url;
        }

        public void setM3u8Hd_url(String m3u8Hd_url) {
            this.m3u8Hd_url = m3u8Hd_url;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public static class VideoTopicBean {
            /**
             * alias : 专业的养花爱好者交流平台
             * tname : 养花大全
             * ename : T1477545427791
             * tid : T1477545427791
             * topic_icons : http://dingyue.nosdn.127.net/3pVYF6pTIQaTSwDUKFP=07AxbjUfQIHAf9z1x1phiM=d91477545427180.jpg
             */

            private String alias;
            private String tname;
            private String ename;
            private String tid;
            private String topic_icons;

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public String getTname() {
                return tname;
            }

            public void setTname(String tname) {
                this.tname = tname;
            }

            public String getEname() {
                return ename;
            }

            public void setEname(String ename) {
                this.ename = ename;
            }

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public String getTopic_icons() {
                return topic_icons;
            }

            public void setTopic_icons(String topic_icons) {
                this.topic_icons = topic_icons;
            }
        }
    }
}
