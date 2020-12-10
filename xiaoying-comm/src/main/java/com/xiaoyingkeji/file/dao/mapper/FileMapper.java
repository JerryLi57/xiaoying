package com.xiaoyingkeji.file.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoyingkeji.comm.dao.mapper.SuperMapper;
import com.xiaoyingkeji.file.pojo.dto.FileQueryDto;
import com.xiaoyingkeji.file.pojo.vo.FileListVo;
import com.xiaoyingkeji.file.pojo.model.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @description:  Model
 * @author: lijiayu
 * @date: 2020-12-10 10:32:28
 */
@Mapper
@Component("fileMapper")
public interface FileMapper extends SuperMapper<File> {

    IPage<FileListVo> listPageByDto(IPage<File> page, @Param("dto") FileQueryDto fileDto);

}