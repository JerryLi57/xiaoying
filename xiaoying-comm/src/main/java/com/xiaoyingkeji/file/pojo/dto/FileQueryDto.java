package com.xiaoyingkeji.file.pojo.dto;

import com.xiaoyingkeji.comm.pojo.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @description:  Dto
 * @author: lijiayu
 * @date: 2020-12-10 10:32:28
 */
@Data
@ApiModel(value = "FileQueryDto", description = " QueryDto")
public class FileQueryDto extends PageDto {

    @ApiModelProperty(value = "", name = "id")
    private Long id;

    @ApiModelProperty(value = "文件名", name = "name")
    private String name;

    @ApiModelProperty(value = "md5加密串", name = "md5")
    private String md5;

    @ApiModelProperty(value = "文件路径", name = "path")
    private String path;

}