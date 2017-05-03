package top.builbu.business.dishe.service;

import top.builbu.business.dishe.dto.DisheDTO;
import top.builbu.business.dishe.entity.Dishe;
import top.builbu.common.dto.PageDTO;
import top.builbu.common.dto.ResultDO;
import top.builbu.common.util.page.Pagination;

public interface DisheService{
     
     PageDTO<DisheDTO> selectByList(DisheDTO dto, Pagination page);
     
     ResultDO<Dishe> selectById(Long id);
     
     ResultDO<?> save(DisheDTO dto);

     ResultDO<?> update(DisheDTO dto);
     
     ResultDO<?> deleteById(Long id);
     
     ResultDO<?> deleteByCheck(Long[] delids);
}
