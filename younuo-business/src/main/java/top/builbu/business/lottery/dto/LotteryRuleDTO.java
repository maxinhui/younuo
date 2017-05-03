package top.builbu.business.lottery.dto;  

import java.util.Date;      
  
public class LotteryRuleDTO {  
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
        private Integer odds;  
	    /**
	     *
	     *
	    **/
        private String prizeCode;  
	    /**
	     *
	     *
	    **/
        private Date createDate;  
	    /**
	     *
	     *
	    **/
        private Date modifyDate;  
	    /**
	     *
	     *
	    **/
        private String validFlag;  
          
          
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
         
        public Integer getOdds(){  
            return this.odds;  
        }  
       
        public void setOdds(Integer odds){  
            this.odds = odds;
        } 
         
        public String getPrizeCode(){  
            return this.prizeCode;  
        }  
       
        public void setPrizeCode(String prizeCode){  
            this.prizeCode = prizeCode == "" ? null : prizeCode.trim();
        } 
         
        public Date getCreateDate(){  
            return this.createDate;  
        }  
       
        public void setCreateDate(Date createDate){  
            this.createDate = createDate;
        } 
         
        public Date getModifyDate(){  
            return this.modifyDate;  
        }  
       
        public void setModifyDate(Date modifyDate){  
            this.modifyDate = modifyDate;
        } 
         
        public String getValidFlag(){  
            return this.validFlag;  
        }  
       
        public void setValidFlag(String validFlag){  
            this.validFlag = validFlag == "" ? null : validFlag.trim();
        } 
         
}  