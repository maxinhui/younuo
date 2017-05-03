package top.builbu.website.wechat.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONObject;
import top.builbu.business.lottery.dto.LotteryPrizeDTO;
import top.builbu.business.lottery.service.LotteryService;
import top.builbu.common.dto.ResultDO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/lottery")
public class LotteryController {

	@Autowired
	private LotteryService lotteryService;
			
	@RequestMapping("ly")
	public String ly(HttpSession session,Integer dsId){
		ResultDO<LotteryPrizeDTO> prize = null;
		String openId = (String) session.getAttribute("yn_open_id");
		if(null != openId && !"".equals(openId)){
			log.debug(dsId+openId);			
			try {
				prize = lotteryService.ly(dsId, openId);
			} catch (Exception e) {
				log.error(e.getStackTrace().toString());
				return "/wechat/error";
			}
		}
		log.debug(JSONObject.toJSONString(prize));
		if(null != prize && prize.isSuccess()){
			return prize.getModule().getLotteryName();
		}else{
			return "/wechat/error";
		}		
	}
	
    
}