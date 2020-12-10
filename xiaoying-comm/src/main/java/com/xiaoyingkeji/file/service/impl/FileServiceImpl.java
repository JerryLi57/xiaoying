package com.xiaoyingkeji.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoyingkeji.comm.constant.ErrorEnum;
import com.xiaoyingkeji.comm.exception.ServiceException;
import com.xiaoyingkeji.comm.service.impl.BaseServiceImpl;
import com.xiaoyingkeji.comm.utils.SimpleBeanUtils;
import com.xiaoyingkeji.file.dao.mapper.FileMapper;
import com.xiaoyingkeji.file.pojo.dto.FileEditDto;
import com.xiaoyingkeji.file.pojo.dto.FileQueryDto;
import com.xiaoyingkeji.file.pojo.model.File;
import com.xiaoyingkeji.file.pojo.vo.FileDetailVo;
import com.xiaoyingkeji.file.pojo.vo.FileListVo;
import com.xiaoyingkeji.file.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: file.File 相关的服务实现类
 * @author: lijiayu
 * @date: 2020-12-10 10:37:07
 **/
@Service
public class FileServiceImpl extends BaseServiceImpl<FileMapper, File> implements IFileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public IPage<FileListVo> listPageByDto(FileQueryDto queryDto) {
        Page<File> page = new Page(queryDto.getCurrentPage(), queryDto.getPageSize(), true);
        return fileMapper.listPageByDto(page, queryDto);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Long save(FileEditDto dto) {
        File entity = SimpleBeanUtils.autoFill(dto, File.class);
        fileMapper.insert(entity);
        return entity.getId();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void edit(FileEditDto dto) {
        File entity = SimpleBeanUtils.autoFill(dto, File.class);
        fileMapper.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void del(Long id) {
        LambdaUpdateWrapper<File> luw = new LambdaUpdateWrapper<>();
        luw.set(File::getIsdeleted, true).eq(File::getId, id);
        fileMapper.update(null, luw);
    }

    @Override
    public FileDetailVo getDetail(Long id) {
        LambdaQueryWrapper<File> lqw = new LambdaQueryWrapper<>();
        lqw.eq(File::getId, id).eq(File::getIsdeleted, false);
        File entity = fileMapper.selectOne(lqw);
        if (null == entity) {
            throw new ServiceException(ErrorEnum.DATA_NOT_EXISTS);
        }
        FileDetailVo dto = SimpleBeanUtils.autoFill(entity, FileDetailVo.class);
        return dto;
    }
}