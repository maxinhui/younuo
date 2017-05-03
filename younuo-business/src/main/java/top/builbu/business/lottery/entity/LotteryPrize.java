package top.builbu.business.lottery.entity;  

import java.util.Date;      
  
public class LotteryPrize {  
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
           
            this.prizeName = prizeName;
                    
        }  
          
                 
        
        public String getOpenId(){  
            return this.openId;  
        }  
       
        public void setOpenId(String openId){            
           
            this.openId = openId;
                    
        }  
          
                 
        
        public String getLotteryName(){  
            return this.lotteryName;  
        }  
       
        public void setLotteryName(String lotteryName){            
           
            this.lotteryName = lotteryName;
                    
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

