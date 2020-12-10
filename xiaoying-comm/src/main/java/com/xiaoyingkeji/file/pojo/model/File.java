package com.xiaoyingkeji.file.pojo.model;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 * @description:  Model
 * @author: lijiayu
 * @date: 2020-12-10 10:32:28
 */
@Data
@TableName("t_file")
public class File {

    /**
     *
     */
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    /**
     * 文件名
     */
    @TableField("f_name")
    private String name;

    /**
     * md5加密串
     */
    @TableField("f_md5")
    private String md5;

    /**
     * 文件路径
     */
    @TableField("f_path")
    private String path;

    /**
     * 更新时间
     */
    @TableField("f_update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @TableField("f_create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 是否被删除 0：未删除 1：已删除
     */
    @TableField("f_isdeleted")
    private Boolean isdeleted;


}
