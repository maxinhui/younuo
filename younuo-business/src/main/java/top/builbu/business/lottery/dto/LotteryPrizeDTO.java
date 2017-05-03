package top.builbu.business.lottery.dto;  

import java.util.Date;      
  
public class LotteryPrizeDTO {  
	    /**
	     *
	     *
	    **/
        private Integer id;  
	    /**
	     *
	     *
	    **/
        private String prizeName;  
	    /**
	     *
	     *
	    **/
        private String openId;  
	    /**
	     *
	     *
	    **/
        private String lotteryName;  
	    /**
	     *
	     *
	    **/
        private Date takeTime;  
	    /**
	     *
	     *
	    **/
        private Integer disheId;  
          
          
        public Integer getId(){  
            return this.id;  
        }  
       
        public void setId(Integer id){  
            this.id = id;
        } 
         
        public String getPrizeName(){  
            return this.prizeName;  
        }  
       
        public void setPrizeName(String prizeName){  
            this.prizeName = prizeName == "" ? null : prizeName.trim();
        } 
         
        public String getOpenId(){  
            return this.openId;  
        }  
       
        public void setOpenId(String openId){  
            this.openId = openId == "" ? null : openId.trim();
        } 
         
        public String getLotteryName(){  
            return this.lotteryName;  
        }  
       
        public void setLotteryName(String lotteryName){  
            this.lotteryName = lotteryName == "" ? null : lotteryName.trim();
        } 
         
        public Date getTakeTime(){  
            return this.takeTime;  
        }  
       
        public void setTakeTime(Date takeTime){  
            this.takeTime = takeTime;
        } 
         
        public Integer getDisheId(){  
            return this.disheId;  
        }  
       
        public void setDisheId(Integer disheId){  
            this.disheId = disheId;
        } 
         
}  