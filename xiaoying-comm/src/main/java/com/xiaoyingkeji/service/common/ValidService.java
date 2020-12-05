package com.xiaoyingkeji.service.common;

import com.xiaoyingkeji.pojo.comm.ValidParamsDto;

/**
 * @description: ValidService 定义调用自定义的校验的接口
 * @author: lijiayu
 * @date: 2020-03-11 20:37
 **/
public interface ValidService {

    /**
     * 校验方法
     * @return
     * @throws Exception
     */
    ValidParamsDto verify() throws Exception;

}
