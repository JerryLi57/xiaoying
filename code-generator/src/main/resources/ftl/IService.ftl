package com.lolaage.crm.${ModuleName}.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lolaage.crm.${ModuleName}.dto.request.${ModelName}EditDto;
import com.lolaage.crm.${ModuleName}.dto.request.${ModelName}QueryDto;
import com.lolaage.crm.${ModuleName}.dto.response.${ModelName}ListDto;
import com.lolaage.crm.${ModuleName}.dto.response.${ModelName}DetailDto;
import com.lolaage.crm.${ModuleName}.model.${ModelName};

/**
 * @description: ${ModuleName}.${ModelName} 相关的服务接口类
 * @author: ${author}
 * @date: ${crateDate}
 **/
public interface I${ModelName}Service extends IBaseService<${ModelName}> {

    /**
     * 分页查询获取列表数据
     *
     * @param queryDto
     * @return
     */
    IPage<${ModelName}ListDto> listPageByDto(${ModelName}QueryDto queryDto);

    /**
     * 保存
     *
     * @param dto
     * @return
     */
    Long save(${ModelName}EditDto dto);

    /**
     * 编辑
     *
     * @param dto
     * @return
     */
    void edit(${ModelName}EditDto dto);

    /**
     * 删除
     *
     * @param id
     */
    void del(Long id);

    /**
     * 根据ID获取详情
     *
     * @param id
     * @return
     */
    ${ModelName}DetailDto getDetail(Long id);
}