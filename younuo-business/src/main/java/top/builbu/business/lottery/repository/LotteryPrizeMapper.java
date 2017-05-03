package top.builbu.business.lottery.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.builbu.business.lottery.dto.LotteryPrizeDTO;
import top.builbu.business.lottery.entity.LotteryPrize;

@Repository
public interface LotteryPrizeMapper {

    /**
     * 列表
     * @param dto
     * @return
     */
    List<LotteryPrizeDTO> selectByList(@Param("dto")LotteryPrizeDTO dto,@Param("offset")Integer offset,@Param("limit")Integer limit);
    
    /**
     *主键查询
     *
     */
    LotteryPrize selectByPrimaryKey(Integer id); 

   
    /**
     *主键查询
     *
     */
    int deleteByPrimaryKey(Integer id); 

      
    /**
     *插入
     *
     */
    int insert(LotteryPrize record); 
      
    /**
     * 修改
     *
     */  
    int updateByPrimaryKey(LotteryPrize record);  
    
    /**
     * 批量删除
     *
     */  
    int deleteByAll(@Param("delids")Integer[] delids);
    
    int selectByCount(@Param("dto")LotteryPrizeDTO dto);
    
    /**
     * openId查询抽奖情况
     * @param openId
     * @return
     */
    LotteryPrize selectByOpenId(@Param("openId")String openId);

}
