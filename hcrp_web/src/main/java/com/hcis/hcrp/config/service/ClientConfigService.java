package com.hcis.hcrp.config.service;

import com.hcis.hcrp.config.entity.ClientConfig;
import com.hcis.ipanther.core.service.IBaseService;

/**
 * @author zhw
 * @date 2020/1/15
 **/
public interface ClientConfigService extends IBaseService<ClientConfig> {

    /**
     * 验证
     * @param clientId
     * @return
     */
    String validClient(String clientId);

    /**
     * 删除
     * @param clientId
     * @param updateBy
     * @return
     */
    int delete(String clientId, String updateBy);

    /**
     *
     * @return
     */
    int maxSort();
}
