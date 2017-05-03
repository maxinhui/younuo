package top.builbu.business.dishe.dto;  

import java.util.Date;      
  
public class DisheDTO {  
	    /**
	     *
	     *
	    **/
        private Long id;  
	    /**
	     *
	     *
	    **/
        private Integer count;  
          
          
        public Long getId(){  
            return this.id;  
        }  
       
        public void setId(Long id){  
            this.id = id;
        } 
         
        public Integer getCount(){  
            return this.count;  
        }  
       
        public void setCount(Integer count){  
            this.count = count;
        } 
         
}  