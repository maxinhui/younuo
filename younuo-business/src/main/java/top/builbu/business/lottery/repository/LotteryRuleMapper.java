package top.builbu.business.lottery.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.builbu.business.lottery.dto.LotteryRuleDTO;
import top.builbu.business.lottery.entity.LotteryRule;

@Repository
public interface LotteryRuleMapper {

    /**
     * 列表
     * @param dto
     * @return
     */
    List<LotteryRuleDTO> selectByList(@Param("dto")LotteryRuleDTO dto,@Param("offset")Integer offset,@Param("limit")Integer limit);
    
    /**
     *主键查询
     *
     */
    LotteryRule selectByPrimaryKey(Integer id); 

   
    /**
     *主键查询
     *
     */
    int deleteByPrimaryKey(Integer id); 

      
    /**
     *插入
     *
     */
    int insert(LotteryRule record); 
      
    /**
     * 修改
     *
     */  
    int updateByPrimaryKey(LotteryRule record);  
    
    /**
     * 批量删除
     *
     */  
    int deleteByAll(@Param("delids")Integer[] delids);
    
    int selectByCount(@Param("dto")LotteryRuleDTO dto);

}
