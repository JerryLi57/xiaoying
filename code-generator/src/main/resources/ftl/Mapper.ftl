package com.xiaoyingkeji.${ModuleName}.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoyingkeji.comm.dao.mapper.SuperMapper;
import com.xiaoyingkeji.${ModuleName}.pojo.dto.${ModelName}QueryDto;
import com.xiaoyingkeji.${ModuleName}.pojo.vo.${ModelName}ListVo;
import com.xiaoyingkeji.${ModuleName}.pojo.model.${ModelName};
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @description: ${tableComment} Model
 * @author: ${author}
 * @date: ${crateDate}
 */
@Mapper
@Component("${ModelNameLower}Mapper")
public interface ${ModelName}Mapper extends SuperMapper<${ModelName}> {

    IPage<${ModelName}ListVo> listPageByDto(IPage<${ModelName}> page, @Param("dto") ${ModelName}QueryDto ${ModelNameLower}Dto);

}