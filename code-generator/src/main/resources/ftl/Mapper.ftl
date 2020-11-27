package com.lolaage.crm.${ModuleName}.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lolaage.crm.${ModuleName}.dto.request.${ModelName}QueryDto;
import com.lolaage.crm.${ModuleName}.dto.response.${ModelName}ListDto;
import com.lolaage.crm.${ModuleName}.model.${ModelName};
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

    IPage<${ModelName}ListDto> listPageByDto(IPage<${ModelName}> page, @Param("dto") ${ModelName}QueryDto ${ModelNameLower}Dto);

}