package com.xiaoyingkeji.file.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoyingkeji.comm.service.IBaseService;
import com.xiaoyingkeji.file.pojo.dto.FileEditDto;
import com.xiaoyingkeji.file.pojo.dto.FileQueryDto;
import com.xiaoyingkeji.file.pojo.model.File;
import com.xiaoyingkeji.file.pojo.vo.FileDetailVo;
import com.xiaoyingkeji.file.pojo.vo.FileListVo;

/**
 * @description: file.File 相关的服务接口类
 * @author: lijiayu
 * @date: 2020-12-10 09:27:32
 **/
public interface IFileService extends IBaseService<File> {

    /**
     * 分页查询获取列表数据
     * @param queryDto
     * @return
     */
    IPage<FileListVo> listPageByDto(FileQueryDto queryDto);

    /**
     * 保存
     * @param dto
     * @return
     */
    Long save(FileEditDto dto);

    /**
     * 编辑
     * @param dto
     * @return
     */
    void edit(FileEditDto dto);

    /**
     * 删除
     * @param id
     */
    void del(Long id);

    /**
     * 根据ID获取详情
     * @param id
     * @return
     */
    FileDetailVo getDetail(Long id);
}