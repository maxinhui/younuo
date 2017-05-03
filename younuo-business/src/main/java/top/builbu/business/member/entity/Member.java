package top.builbu.business.member.entity;  

import java.util.Date;      
  
public class Member {  
	    /**
	     *
	     *
	    **/
        private Long id;  
	    /**
	     *
	     *
	    **/
        private String openId;  
	    /**
	     *
	     *
	    **/
        private Date createTime;  
          
          
        
        public Long getId(){  
            return this.id;  
        }  
       
        public void setId(Long id){            
           
            this.id = id;
                    
        }  
          
                 
        
        public String getOpenId(){  
            return this.openId;  
        }  
       
        public void setOpenId(String openId){            
           
            this.openId = openId;
                    
        }  
          
                 
        
        public Date getCreateTime(){  
            return this.createTime;  
        }  
       
        public void setCreateTime(Date createTime){            
           
            this.createTime = createTime;
                    
        }  
          
                 
}  

