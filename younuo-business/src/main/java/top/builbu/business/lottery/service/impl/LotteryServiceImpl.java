package top.builbu.business.lottery.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.builbu.business.dishe.dto.DisheDTO;
import top.builbu.business.dishe.service.DisheService;
import top.builbu.business.lottery.dto.LotteryPrizeDTO;
import top.builbu.business.lottery.dto.LotteryRuleDTO;
import top.builbu.business.lottery.entity.LotteryRule;
import top.builbu.business.lottery.service.LotteryPrizeService;
import top.builbu.business.lottery.service.LotteryRuleService;
import top.builbu.business.lottery.service.LotteryService;
import top.builbu.common.dto.BaseResultCode;
import top.builbu.common.dto.PageDTO;
import top.builbu.common.dto.ResultDO;
import top.builbu.common.util.StringUtil;
import top.builbu.common.util.page.Pagination;

@Slf4j
@Service
public class LotteryServiceImpl implements LotteryService {
    
	@Autowired
    private LotteryPrizeService lotteryPrizeService;
    
	@Autowired
    private LotteryRuleService lotteryRuleService;
    
	@Autowired
    private DisheService disheService;
	
	@Override
	public ResultDO<LotteryPrizeDTO> ly(Integer dsId,String openId) throws ParseException{
		ResultDO<LotteryPrizeDTO> resultDTO = null;
		ResultDO<?> result = null;
		log.debug(dsId+openId);
		if(null != dsId && !"".equals(openId) && null != dsId){
			
			result = lotteryPrizeService.getByOpenId(openId);//检查当天是否还可以抽奖
			if(result.isSuccess()){//true 开始抽奖
				LotteryRuleDTO ruleDTO = lyc();
				if(null != ruleDTO){//中奖
					LotteryPrizeDTO  prizeDTO = new LotteryPrizeDTO();
					prizeDTO.setOpenId(openId);
					prizeDTO.setTakeTime(new Date());
					prizeDTO.setPrizeName(ruleDTO.getPrizeName());
					prizeDTO.setLotteryName(ruleDTO.getPrizeCode());
					prizeDTO.setDisheId(dsId);
					result = lotteryPrizeService.save(prizeDTO);//保存会员中奖情况
					if(result.isSuccess()){
						DisheDTO sheDTO = new DisheDTO();
						sheDTO.setId((long)dsId);
						result = disheService.update(sheDTO);//修改菜品投票数
						if(result.isSuccess()){
							resultDTO = new ResultDO<LotteryPrizeDTO>(prizeDTO);//返回中奖情况
						}
					}
				}
				
				if(!result.isSuccess()){
					resultDTO = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
				}
			}else{
				LotteryPrizeDTO dto = new LotteryPrizeDTO();
				BeanUtils.copyProperties(result.getModule(),dto);				
				resultDTO = new ResultDO<>(dto);
			}
			
		}else{
			log.info("log");
			resultDTO = new ResultDO<>(BaseResultCode.COMMON_WRONG_PARAMS,Boolean.FALSE);
		}
		
		return resultDTO;
	}
	
	/**
	 * 抽奖过程
	 * @return
	 */
	LotteryRuleDTO lyc(){
		LotteryRuleDTO rule = new LotteryRuleDTO();
		rule.setValidFlag("Y");
		PageDTO<LotteryRuleDTO> ruleList = lotteryRuleService.selectByList(rule, new Pagination());
		double d = 0 - Math.random() * (0 - 100);//随机数
		double start = 0;
		double end = 0;
		for(LotteryRuleDTO r : ruleList.getList()){
			end = start + (double)r.getOdds()/10;
//			log.info("odd"+(double)r.getOdds()/10);
//			log.info("start:"+start);
//			log.info("end:"+end);
			if(d<=end&&d>start){//中奖
				return r;
			}
			start = end;
		}
		return null;
	}
	
	public static void main(String[] args) {
	    int x=100;
	    int y=0;
	    for(int i= 0 ;i<20;i++){
	    double d = y - Math.random() * (y - x);
	    System.out.print(d);
		System.out.println(d>0.5);
	    }
	}
}
