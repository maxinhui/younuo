package top.builbu.business.lottery.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import top.builbu.common.util.page.Pagination;
import top.builbu.common.dto.PageDTO;
import top.builbu.common.dto.BaseResultCode;
import top.builbu.common.dto.ResultDO;
import top.builbu.business.lottery.dto.LotteryPrizeDTO;
import top.builbu.business.lottery.entity.LotteryPrize;
import top.builbu.business.lottery.service.LotteryPrizeService;
import top.builbu.business.lottery.repository.LotteryPrizeMapper;
@Slf4j
@Service
public class LotteryPrizeServiceImpl implements LotteryPrizeService{
 
    @Autowired
	private LotteryPrizeMapper lotteryPrizeMapper;
	
	
	@Override
	public PageDTO<LotteryPrizeDTO> selectByList(LotteryPrizeDTO dto,Pagination page ){
	    PageDTO<LotteryPrizeDTO> pageDo = new PageDTO<LotteryPrizeDTO>();
	    List<LotteryPrizeDTO> result = lotteryPrizeMapper.selectByList(dto,page.getCurrentResult(),page.getPageSize());
		if(null!=result&&result.size()>0){
		    pageDo.setList(result);
		   
		}
		 pageDo.setPageCurrent(page.getPageCurrent());
		 pageDo.setPageSize(page.getPageSize());
		 pageDo.setTotal(lotteryPrizeMapper.selectByCount(dto));
		return pageDo;
	}
	
    @Override
	public ResultDO<LotteryPrize> selectById(Integer id){
	    ResultDO<LotteryPrize> resultDo = null;
	    if(null!=id&&!"".equals(id)){
	        LotteryPrize result = lotteryPrizeMapper.selectByPrimaryKey(id);
	        if(null!=result){
	            resultDo = new ResultDO<>(result);
	        }else{
	            resultDo = new ResultDO<>(BaseResultCode.COMMON_NO_DATA,Boolean.FALSE);
	        }
	    }else{
	        resultDo = new ResultDO<>(BaseResultCode.COMMON_WRONG_PARAMS,Boolean.FALSE);
	    }
	    return resultDo;
	}
	
    @Override
    public ResultDO<?> save(LotteryPrizeDTO dto){
    	ResultDO<?> result = null;
    	LotteryPrize record = new LotteryPrize(); 
    	BeanUtils.copyProperties(dto, record);
    	Integer rowId = lotteryPrizeMapper.insert(record);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    		result.setTabid("lotteryPrizeList");
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    		result.setCloseCurrent(Boolean.FALSE);
    	}
    	return result;
    }
    
    @Override
    public ResultDO<?> update(LotteryPrizeDTO dto){
    	ResultDO<?> result = null;
    	LotteryPrize record = new LotteryPrize();
    	BeanUtils.copyProperties(dto, record);
    	Integer rowId = lotteryPrizeMapper.updateByPrimaryKey(record);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    		result.setTabid("lotteryPrizeList");
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    		result.setCloseCurrent(Boolean.FALSE);
    	}
    	return result;
    }
    
    @Override
    public ResultDO<?> deleteById(Integer id){
    	ResultDO<?> result = null;
    	Integer rowId = lotteryPrizeMapper.deleteByPrimaryKey(id);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    	}
    	return result;
    }
    
    @Override
    public ResultDO<?> deleteByCheck(Integer[] delids){
    	ResultDO<?> result = null;
    	Integer rowId = lotteryPrizeMapper.deleteByAll(delids);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    		result.setCloseCurrent(Boolean.FALSE);
    	}
    	return result;
    }
    
    
    /**
     * 抽奖前查询
     * 一天只能抽一次
     * @return
     * @throws ParseException 
     */
    @Override
    public ResultDO<LotteryPrize> getByOpenId(String openId) throws ParseException{
    	ResultDO<LotteryPrize> result = new ResultDO<LotteryPrize>();
    	LotteryPrize prize = lotteryPrizeMapper.selectByOpenId(openId);
    	if(null != prize){
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String mat = sdf.format(prize.getTakeTime());//明细签到创建时间		    
		    Date beginTime = sdf1.parse(mat+" 00:00:00");
		    Date endTime = sdf1.parse(mat+" 23:59:59");
		    Long begin = beginTime.getTime();
		    Long end = endTime.getTime();
		    Long now = new Date().getTime();
		    log.debug(beginTime.toString());
		    log.debug(endTime.toString());
		    log.info("begin:"+begin+"==end:"+end+"==now:"+now);
		    log.debug((now >= begin)+"");
		    log.debug((now <= end)+"");
		    if(now >= begin && now <= end){
		    	result = new ResultDO<>(prize);
		    	result.setMessage(BaseResultCode.COMMON_NO_ONE);
		    	result.setSuccess(Boolean.FALSE);
		    }
    	}
    	log.debug(JSONObject.toJSONString(result));
    	return result;
    }
}
