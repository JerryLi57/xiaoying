package com.lolaage.crm.${ModuleName}.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lolaage.crm.service.aspect.UpdateCheck;
import com.lolaage.crm.service.constant.ErrorEnum;
import com.lolaage.crm.service.exception.ServiceException;
import com.lolaage.crm.${ModuleName}.dao.mapper.${ModelName}Mapper;
import com.lolaage.crm.${ModuleName}.dto.request.${ModelName}EditDto;
import com.lolaage.crm.${ModuleName}.dto.request.${ModelName}QueryDto;
import com.lolaage.crm.${ModuleName}.dto.response.${ModelName}DetailDto;
import com.lolaage.crm.${ModuleName}.dto.response.${ModelName}ListDto;
import com.lolaage.crm.${ModuleName}.model.${ModelName};
import com.lolaage.crm.${ModuleName}.service.I${ModelName}Service;
import com.lolaage.crm.service.utils.SimpleBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: ${ModuleName}.${ModelName} 相关的服务实现类
 * @author: ${author}
 * @date: ${crateDate}
 **/
@Service
public class ${ModelName}ServiceImpl extends BaseServiceImpl<${ModelName}Mapper, ${ModelName}> implements I${ModelName}Service {

    @Autowired
    private ${ModelName}Mapper ${ModelNameLower}Mapper;

    @Override
    public IPage<${ModelName}ListDto> listPageByDto(${ModelName}QueryDto queryDto) {
        Page<${ModelName}> page = new Page(queryDto.getCurrentPage(), queryDto.getPageSize(), true);
        return ${ModelNameLower}Mapper.listPageByDto(page, queryDto);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Long save(${ModelName}EditDto dto) {
        ${ModelName} entity = SimpleBeanUtils.autoFill(dto, ${ModelName}.class);
        ${ModelNameLower}Mapper.insert(entity);
        return entity.getId();
    }

    @Override
    @UpdateCheck(clasz = "com.lolaage.crm.${ModuleName}.model.${ModelName}")
    @Transactional(rollbackFor = {Exception.class})
    public void edit(${ModelName}EditDto dto) {
        ${ModelName} entity = SimpleBeanUtils.autoFill(dto, ${ModelName}.class);
        ${ModelNameLower}Mapper.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void del(Long id) {
        LambdaUpdateWrapper<${ModelName}> luw = new LambdaUpdateWrapper<>();
        luw.set(${ModelName}::getIsdeleted, true).eq(${ModelName}::getId, id);
        ${ModelNameLower}Mapper.update(null, luw);
    }

    @Override
    public ${ModelName}DetailDto getDetail(Long id) {
        LambdaQueryWrapper<${ModelName}> lqw = new LambdaQueryWrapper<>();
        lqw.eq(${ModelName}::getId, id).eq(${ModelName}::getIsdeleted, false);
        ${ModelName} entity = ${ModelNameLower}Mapper.selectOne(lqw);
        if (null == entity) {
            throw new ServiceException(ErrorEnum.DATA_NOT_EXISTS);
        }
        ${ModelName}DetailDto dto = SimpleBeanUtils.autoFill(entity, ${ModelName}DetailDto.class);
        return dto;
    }
}