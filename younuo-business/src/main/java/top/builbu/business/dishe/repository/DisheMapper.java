package top.builbu.business.dishe.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.builbu.business.dishe.dto.DisheDTO;
import top.builbu.business.dishe.entity.Dishe;

@Repository
public interface DisheMapper {

    /**
     * 列表
     * @param dto
     * @return
     */
    List<DisheDTO> selectByList(@Param("dto")DisheDTO dto,@Param("offset")Integer offset,@Param("limit")Integer limit);
    
    /**
     *主键查询
     *
     */
    Dishe selectByPrimaryKey(Long id); 

   
    /**
     *主键查询
     *
     */
    int deleteByPrimaryKey(Long id); 

      
    /**
     *插入
     *
     */
    int insert(Dishe record); 
      
    /**
     * 修改
     *
     */  
    int updateByPrimaryKey(Dishe record);  
    
    /**
     * 批量删除
     *
     */  
    int deleteByAll(@Param("delids")Long[] delids);
    
    int selectByCount(@Param("dto")DisheDTO dto);

}
