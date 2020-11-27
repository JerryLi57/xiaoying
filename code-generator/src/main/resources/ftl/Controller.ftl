package com.lolaage.crm.${ModuleName}.controller;

import com.lolaage.crm.${ModuleName}.dto.request.${ModelName}EditDto;
import com.lolaage.crm.${ModuleName}.dto.request.${ModelName}QueryDto;
import com.lolaage.crm.${ModuleName}.dto.response.${ModelName}DetailDto;
import com.lolaage.crm.${ModuleName}.dto.response.${ModelName}ListDto;
import com.lolaage.crm.${ModuleName}.service.I${ModelName}Service;
import com.lolaage.crm.service.dto.DataResult;
import com.lolaage.crm.service.valid.group.AddGroup;
import com.lolaage.crm.service.valid.group.UpdateGroup;
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
 * @description: ${tableComment}模块相关API接口
 * @author: ${author}
 * @date: ${crateDate}
 **/
@Api(tags = "${tableComment}模块相关API接口 author:${author}")
@RestController
@RequestMapping("/${ModelNameLower}")
public class ${ModelName}Controller extends BaseController {

    @Autowired
    private I${ModelName}Service ${ModelNameLower}Service;

    @GetMapping(value = "/list")
    @ApiOperation(value = "分页查询${tableComment}列表信息 author:${author}", notes = "分页查询${tableComment}列表信息")
    public DataResult<List<${ModelName}ListDto>> listByPage(${ModelName}QueryDto queryDto) {
        return resultDataPagePackage(() -> ${ModelNameLower}Service.listPageByDto(queryDto));
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "添加${tableComment}信息 author:${author}", notes = "添加${tableComment}信息")
    public DataResult<Long> save(@RequestBody @Validated(AddGroup.class) ${ModelName}EditDto editDto, BindingResult bindingResult) {
        return resultDataCommon(() -> ${ModelNameLower}Service.save(editDto), bindingResult);
    }

    @PostMapping(value = "/edit")
    @ApiOperation(value = "编辑${tableComment}信息 author:${author}", notes = "编辑${tableComment}信息")
    public DataResult<Long> edit(@RequestBody @Validated(UpdateGroup.class) ${ModelName}EditDto editDto, BindingResult bindingResult) {
        return resultDataCommon(() -> ${ModelNameLower}Service.edit(editDto), bindingResult);
    }

    @GetMapping(value = "/getDetail")
    @ApiOperation(value = "获取${tableComment}详细信息 author:${author}", notes = "获取${tableComment}详细信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "应急救援设备信息Id", dataType = "Long", required = true)
    )
    public DataResult<${ModelName}DetailDto> getDetail(@RequestParam(name = "id") Long id) {
        return resultDataCommon(() -> ${ModelNameLower}Service.getDetail(id));
    }

    @GetMapping(value = "/del")
    @ApiOperation(value = "删除${tableComment}信息 author:${author}", notes = "删除${tableComment}信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "应急救援设备信息Id", dataType = "Long", required = true)
    )
    public DataResult del(@RequestParam(name = "id") Long id) {
        return resultDataCommon(() -> ${ModelNameLower}Service.del(id));
    }

}