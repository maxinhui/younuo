package top.builbu.business.lottery.service.impl;

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
import top.builbu.business.lottery.dto.LotteryRuleDTO;
import top.builbu.business.lottery.entity.LotteryRule;
import top.builbu.business.lottery.service.LotteryRuleService;
import top.builbu.business.lottery.repository.LotteryRuleMapper;
@Slf4j
@Service
public class LotteryRuleServiceImpl implements LotteryRuleService{
 
    @Autowired
	private LotteryRuleMapper lotteryRuleMapper;
	
	
	@Override
	public PageDTO<LotteryRuleDTO> selectByList(LotteryRuleDTO dto,Pagination page ){
	    PageDTO<LotteryRuleDTO> pageDo = new PageDTO<LotteryRuleDTO>();
	    List<LotteryRuleDTO> result = lotteryRuleMapper.selectByList(dto,page.getCurrentResult(),page.getPageSize());
		if(null!=result&&result.size()>0){
		    pageDo.setList(result);
		   
		}
		 pageDo.setPageCurrent(page.getPageCurrent());
		 pageDo.setPageSize(page.getPageSize());
		return pageDo;
	}
	
    @Override
	public ResultDO<LotteryRule> selectById(Integer id){
	    ResultDO<LotteryRule> resultDo = null;
	    if(null!=id&&!"".equals(id)){
	        LotteryRule result = lotteryRuleMapper.selectByPrimaryKey(id);
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
    public ResultDO<?> save(LotteryRuleDTO dto){
    	ResultDO<?> result = null;
    	LotteryRule record = new LotteryRule(); 
    	BeanUtils.copyProperties(dto, record);
    	Integer rowId = lotteryRuleMapper.insert(record);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    		result.setTabid("lotteryRuleList");
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    		result.setCloseCurrent(Boolean.FALSE);
    	}
    	return result;
    }
    
    @Override
    public ResultDO<?> update(LotteryRuleDTO dto){
    	ResultDO<?> result = null;
    	LotteryRule record = new LotteryRule();
    	BeanUtils.copyProperties(dto, record);
    	Integer rowId = lotteryRuleMapper.updateByPrimaryKey(record);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    		result.setTabid("lotteryRuleList");
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    		result.setCloseCurrent(Boolean.FALSE);
    	}
    	return result;
    }
    
    @Override
    public ResultDO<?> deleteById(Integer id){
    	ResultDO<?> result = null;
    	Integer rowId = lotteryRuleMapper.deleteByPrimaryKey(id);
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
    	Integer rowId = lotteryRuleMapper.deleteByAll(delids);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    		result.setCloseCurrent(Boolean.FALSE);
    	}
    	return result;
    }
    

}
