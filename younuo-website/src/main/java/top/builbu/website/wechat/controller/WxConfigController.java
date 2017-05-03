package top.builbu.website.wechat.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import top.builbu.business.lottery.entity.LotteryPrize;
import top.builbu.business.lottery.service.LotteryPrizeService;
import top.builbu.business.member.dto.MemberDTO;
import top.builbu.business.member.dto.WxConfigDTO;
import top.builbu.business.member.entity.WxConfig;
import top.builbu.business.member.service.MemberService;
import top.builbu.business.member.service.WxConfigService;
import top.builbu.common.dto.PageDTO;
import top.builbu.common.dto.ResultDO;
import top.builbu.common.dto.ResultCode;
import top.builbu.common.dto.BaseResultCode;
import top.builbu.common.util.page.Pagination;

import org.springframework.web.multipart.MultipartFile;

import top.builbu.core.util.UploadUtil;
import top.builbu.core.wechat.entity.WXOpenIdResult;
import top.builbu.core.wechat.utils.Aouth2Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/wxConfig")
public class WxConfigController {

	@Autowired
	private WxConfigService wxConfigService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private LotteryPrizeService lotteryPrizeService;
	
	@RequestMapping("/add")
	public String add(){
		log.info("for ：wxConfigAdd");
		return "/member/wxConfigAdd";
	}
	
	
	@RequestMapping("/selectByList")
	public String selectByList(HttpServletRequest request,WxConfigDTO dto,Pagination page){
		log.info(JSONObject.toJSONString(page));
		PageDTO<WxConfigDTO> result = null;
		try{
		    result = wxConfigService.selectByList(dto,page);
		    request.setAttribute("pageDTO", result);
		    request.setAttribute("searchDTO", dto);
		    return "/member/wxConfigList";
	    } catch (Exception e) {
			log.info(ExceptionUtils.getStackTrace(e));
			return ResultCode.ERROR;
		}
		
	}
	
	
	
	@RequestMapping("/selectById")
	public String selectById(HttpServletRequest request,Long id){
	  ResultDO<WxConfig> result = null;
	    try{
		    result = wxConfigService.selectById(id);
		    if(result.isSuccess()){
		       request.setAttribute("module",result.getModule());
		       return "/member/wxConfigEdit";
		    }else{
		       return ResultCode.ERROR;
		    }
		} catch (Exception e) {
			log.info(ExceptionUtils.getStackTrace(e));
			return ResultCode.ERROR;
		}
		
	}
	
	
    @ResponseBody
	@RequestMapping("/save")
	public ResultDO<?> save(WxConfigDTO dto){
		ResultDO<?> result = null;
		 try{
			 result = wxConfigService.save(dto);
			} catch (Exception e) {
			 log.info(ExceptionUtils.getStackTrace(e));
			 result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
			 result.setCloseCurrent(Boolean.FALSE);
			}
		 return result;
	}
	
	
	@ResponseBody
	@RequestMapping("/update")
    public ResultDO<?> update(WxConfigDTO dto){
    	ResultDO<?> result = null;
    	 try{
			 result = wxConfigService.update(dto);
			} catch (Exception e) {
			 log.info(ExceptionUtils.getStackTrace(e));
			 result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
			 result.setCloseCurrent(Boolean.FALSE);
			}
		 return result;
    }
    
    @ResponseBody
	@RequestMapping("/deleteById")
    public ResultDO<?> deleteById(Long id){
    	ResultDO<?> result = null;
    	 try{
			 result = wxConfigService.deleteById(id);
			} catch (Exception e) {
			 log.info(ExceptionUtils.getStackTrace(e));
			 result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
			 result.setCloseCurrent(Boolean.FALSE);
			}
		 return result;
    }
    
    @ResponseBody
	@RequestMapping("/deleteByCheck")
    public ResultDO<?> deleteByCheck(Long[] delids){
    	ResultDO<?> result = null;
   	 try{
   		 result  =  wxConfigService.deleteByCheck(delids);
		} catch (Exception e) {
		 log.info(ExceptionUtils.getStackTrace(e));
		 result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
		 result.setCloseCurrent(Boolean.FALSE);
		}
		 return result;
    }
    //https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx35eed173d89a147d&redirect_uri=http://youruo.vbooline.com/younuo-website/wxConfig/auth&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect
    //https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx35eed173d89a147d&redirect_uri=http://www.builbu.top/younuo-website/wxConfig/auth&response_type=code&scope=https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect&state=STATE#wechat_redirect
    @RequestMapping("/auth")
	public String auth(HttpSession session,HttpServletRequest request,String code){
		//log.info("code:"+code+"--isTag:"+state);
		ResultDO<?> result = null;
		ResultDO<WxConfig> config = null;
		ResultDO<LotteryPrize> prize = null;
		WXOpenIdResult openIdResult = null;
		if(null != code && !"".equals(code)){
			try {
				config = wxConfigService.getToken("yn");
				if(config.isSuccess()){
					WxConfig wxConfig = config.getModule();
					openIdResult = Aouth2Utils.getOpenId(wxConfig.getCorpId(), wxConfig.getCorpSecret(), code);
					if(null != openIdResult.getOpenid() && !"".equals(openIdResult.getOpenid())){
						prize = lotteryPrizeService.getByOpenId(openIdResult.getOpenid());
						if(prize.isSuccess()){//每天一次抽奖
							result = memberService.save(openIdResult.getOpenid());//检查会员是否已存在，保存或已存在都返回true
						}else{//一天已抽过
							result = new ResultDO<>(BaseResultCode.COMMON_NO_ONE,Boolean.FALSE);
						}					
					}else{
						result = new ResultDO<>(BaseResultCode.COMMON_NO_USER,Boolean.FALSE);
					}
				}else{
					result = new ResultDO<>(BaseResultCode.COMMON_SALE_RETURN,Boolean.FALSE);
				}			
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
			}	
		}else{
			result = new ResultDO<>(BaseResultCode.COMMON_NO_USER,Boolean.FALSE);
		}
		
		if(result.isSuccess()){
			session.setAttribute("yn_open_id", openIdResult.getOpenid());
			return "/wechat/vote01";
		}else if(!result.isSuccess()&&null==prize){
			return "/wechat/error";
		}else{
			return prize.getModule().getLotteryName();
		}
	}
    
    @ResponseBody
	@RequestMapping(value="/token")
	public ResultDO<WxConfig> selectAccessToken(){
	    ResultDO<WxConfig> result = null;
		try {
			result = wxConfigService.getToken("lile");
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
		}
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping("/getTicket")
	public JSONObject getTicket(String url,HttpSession session){
		JSONObject json=new JSONObject();
		ResultDO<Map<String, Object>> result=new ResultDO<>(BaseResultCode.COMMON_DB_ERRORS,Boolean.FALSE);
		try {
			result=wxConfigService.getTiekct(url,"lile");
			json.put("data", result);
			//MemberDTO memberDTO =  (MemberDTO) session.getAttribute(Lile.USERKEY);
			
		} catch (Exception e) {
			log.error(e.getStackTrace().toString());
			return json;
		}
		return json;
	}
}