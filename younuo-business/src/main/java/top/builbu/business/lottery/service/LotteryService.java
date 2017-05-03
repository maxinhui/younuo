package top.builbu.business.lottery.service;

import java.text.ParseException;

import top.builbu.business.lottery.dto.LotteryPrizeDTO;
import top.builbu.common.dto.ResultDO;

public interface LotteryService {

	ResultDO<LotteryPrizeDTO> ly(Integer dsId, String openId)
			throws ParseException;

}
