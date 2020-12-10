package com.xiaoyingkeji.file.pojo.dto;

import com.xiaoyingkeji.comm.valid.group.AddGroup;
import com.xiaoyingkeji.comm.valid.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @description:  Dto
 * @author: lijiayu
 * @date: 2020-12-10 10:32:28
 */
@Data
@ApiModel(value = "FileEditDto", description = "")
public class FileEditDto {

    @Null(message = "主键必须为空", groups = AddGroup.class)
    @NotNull(message = "主键不能为空", groups = UpdateGroup.class)
    @ApiModelProperty(value = "", name = "id")
    private Long id;

    @ApiModelProperty(value = "文件名", name = "name")
    private String name;

    @ApiModelProperty(value = "md5加密串", name = "md5")
    private String md5;

    @ApiModelProperty(value = "文件路径", name = "path")
    private String path;

}