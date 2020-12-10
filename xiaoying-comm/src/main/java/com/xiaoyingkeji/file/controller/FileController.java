package com.xiaoyingkeji.file.controller;

import com.xiaoyingkeji.comm.controller.BaseController;
import com.xiaoyingkeji.comm.pojo.DataResult;
import com.xiaoyingkeji.comm.valid.group.AddGroup;
import com.xiaoyingkeji.comm.valid.group.UpdateGroup;
import com.xiaoyingkeji.file.pojo.dto.FileEditDto;
import com.xiaoyingkeji.file.pojo.dto.FileQueryDto;
import com.xiaoyingkeji.file.pojo.vo.FileDetailVo;
import com.xiaoyingkeji.file.pojo.vo.FileListVo;
import com.xiaoyingkeji.file.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 模块相关API接口
 * @author: lijiayu
 * @date: 2020-12-10 11:30:56
 **/
@Api(tags = "模块相关API接口 author:lijiayu")
@RestController
@RequestMapping("/file")
public class FileController extends BaseController {

    @Autowired
    private IFileService fileService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "分页查询列表信息 author:lijiayu", notes = "分页查询列表信息")
    public DataResult<List<FileListVo>> listByPage(FileQueryDto queryDto) {
        return resultDataPagePackage(() -> fileService.listPageByDto(queryDto));
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "添加信息 author:lijiayu", notes = "添加信息")
    public DataResult<Long> save(@RequestBody @Validated(AddGroup.class) FileEditDto editDto, BindingResult bindingResult) {
        return resultDataCommon(() -> fileService.save(editDto), bindingResult);
    }

    @PostMapping(value = "/edit")
    @ApiOperation(value = "编辑信息 author:lijiayu", notes = "编辑信息")
    public DataResult<Long> edit(@RequestBody @Validated(UpdateGroup.class) FileEditDto editDto, BindingResult bindingResult) {
        return resultDataCommon(() -> fileService.edit(editDto), bindingResult);
    }

    @GetMapping(value = "/getDetail")
    @ApiOperation(value = "获取详细信息 author:lijiayu", notes = "获取详细信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "应急救援设备信息Id", dataType = "Long", required = true)
    )
    public DataResult<FileDetailVo> getDetail(@RequestParam(name = "id") Long id) {
        return resultDataCommon(() -> fileService.getDetail(id));
    }

    @GetMapping(value = "/del")
    @ApiOperation(value = "删除信息 author:lijiayu", notes = "删除信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "应急救援设备信息Id", dataType = "Long", required = true)
    )
    public DataResult del(@RequestParam(name = "id") Long id) {
        return resultDataCommon(() -> fileService.del(id));
    }

}