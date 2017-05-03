package top.builbu.business.member.service.impl;

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
import top.builbu.business.member.dto.MemberDTO;
import top.builbu.business.member.entity.Member;
import top.builbu.business.member.service.MemberService;
import top.builbu.business.member.repository.MemberMapper;
@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
 
    @Autowired
	private MemberMapper memberMapper;
	
	
	@Override
	public PageDTO<MemberDTO> selectByList(MemberDTO dto,Pagination page ){
	    PageDTO<MemberDTO> pageDo = new PageDTO<MemberDTO>();
	    List<MemberDTO> result = memberMapper.selectByList(dto,page.getCurrentResult(),page.getPageSize());
		if(null!=result&&result.size()>0){
		    pageDo.setList(result);
		   
		}
		 pageDo.setPageCurrent(page.getPageCurrent());
		 pageDo.setPageSize(page.getPageSize());
		 pageDo.setTotal(memberMapper.selectByCount(dto));
		return pageDo;
	}
	
    @Override
	public ResultDO<Member> selectById(Long id){
	    ResultDO<Member> resultDo = null;
	    if(null!=id&&!"".equals(id)){
	        Member result = memberMapper.selectByPrimaryKey(id);
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
    public ResultDO<?> save(MemberDTO dto){
    	ResultDO<?> result = null;
    	Member record = new Member(); 
    	BeanUtils.copyProperties(dto, record);
    	Integer rowId = memberMapper.insert(record);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    		result.setTabid("memberList");
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    		result.setCloseCurrent(Boolean.FALSE);
    	}
    	return result;
    }
    
    @Override
    public ResultDO<?> update(MemberDTO dto){
    	ResultDO<?> result = null;
    	Member record = new Member();
    	BeanUtils.copyProperties(dto, record);
    	Integer rowId = memberMapper.updateByPrimaryKey(record);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    		result.setTabid("memberList");
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    		result.setCloseCurrent(Boolean.FALSE);
    	}
    	return result;
    }
    
    @Override
    public ResultDO<?> deleteById(Long id){
    	ResultDO<?> result = null;
    	Integer rowId = memberMapper.deleteByPrimaryKey(id);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    	}
    	return result;
    }
    
    @Override
    public ResultDO<?> deleteByCheck(Long[] delids){
    	ResultDO<?> result = null;
    	Integer rowId = memberMapper.deleteByAll(delids);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    		result.setCloseCurrent(Boolean.FALSE);
    	}
    	return result;
    }
    
    
    /**
     * 保存会员
     * 已存在不保存
     * @param openId
     * @return
     */
    @Override
    public ResultDO<?> save(String openId){
        ResultDO<?> result = null;
        Member member = memberMapper.selectByOpenId(openId);
        if(null != member){//已有会员返回ture
        	result = new ResultDO<>(BaseResultCode.TRUE,Boolean.TRUE);
        }else{//没有会员，创建会员
        	member = new Member();
        	member.setOpenId(openId);
        	member.setCreateTime(new Date());
        	Integer rowId = memberMapper.insert(member);
        	if(null != rowId && rowId >0){
        		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
        	}else{
        		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
        	}
        }
        return result;
    }
}
