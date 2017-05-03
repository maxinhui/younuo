package top.builbu.business.lottery.service;

import top.builbu.business.lottery.dto.LotteryRuleDTO;
import top.builbu.business.lottery.entity.LotteryRule;
import top.builbu.common.dto.PageDTO;
import top.builbu.common.dto.ResultDO;
import top.builbu.common.util.page.Pagination;

public interface LotteryRuleService{
     
     PageDTO<LotteryRuleDTO> selectByList(LotteryRuleDTO dto, Pagination page);
     
     ResultDO<LotteryRule> selectById(Integer id);
     
     ResultDO<?> save(LotteryRuleDTO dto);

     ResultDO<?> update(LotteryRuleDTO dto);
     
     ResultDO<?> deleteById(Integer id);
     
     ResultDO<?> deleteByCheck(Integer[] delids);
}
