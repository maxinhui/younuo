package top.builbu.business.lottery.service;

import java.text.ParseException;

import top.builbu.business.lottery.dto.LotteryPrizeDTO;
import top.builbu.business.lottery.entity.LotteryPrize;
import top.builbu.common.dto.PageDTO;
import top.builbu.common.dto.ResultDO;
import top.builbu.common.util.page.Pagination;

public interface LotteryPrizeService{
     
     PageDTO<LotteryPrizeDTO> selectByList(LotteryPrizeDTO dto, Pagination page);
     
     ResultDO<LotteryPrize> selectById(Integer id);
     
     ResultDO<?> save(LotteryPrizeDTO dto);

     ResultDO<?> update(LotteryPrizeDTO dto);
     
     ResultDO<?> deleteById(Integer id);
     
     ResultDO<?> deleteByCheck(Integer[] delids);

     /**
      * 检查当天抽奖次数
      * @param openId
      * @return
      * @throws ParseException
      */
	 ResultDO<LotteryPrize> getByOpenId(String openId) throws ParseException;
}
