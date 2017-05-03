package top.builbu.business.member.service;

import java.util.Map;

import top.builbu.business.member.dto.WxConfigDTO;
import top.builbu.business.member.entity.WxConfig;
import top.builbu.common.dto.PageDTO;
import top.builbu.common.dto.ResultDO;
import top.builbu.common.util.page.Pagination;

public interface WxConfigService{
     
     PageDTO<WxConfigDTO> selectByList(WxConfigDTO dto, Pagination page);
     
     ResultDO<WxConfig> selectById(Long id);
     
     ResultDO<?> save(WxConfigDTO dto);

     ResultDO<?> update(WxConfigDTO dto);
     
     ResultDO<?> deleteById(Long id);
     
     ResultDO<?> deleteByCheck(Long[] delids);

	ResultDO<Map<String, Object>> getTiekct(String url, String pramToken)
			throws Exception;

	ResultDO<WxConfig> getToken(String pramToken);
}
