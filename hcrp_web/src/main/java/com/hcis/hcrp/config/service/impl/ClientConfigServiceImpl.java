package com.hcis.hcrp.config.service.impl;

import com.hcis.hcrp.config.dao.ClientConfigDao;
import com.hcis.hcrp.config.entity.ClientConfig;
import com.hcis.hcrp.config.service.ClientConfigService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhw
 * @date 2020/1/15
 **/
@Service("clientConfigService")
public class ClientConfigServiceImpl extends BaseServiceImpl<ClientConfig> implements ClientConfigService {

    @Autowired
    private ClientConfigDao clientConfigDao;

    @Override
    public MyBatisDao getBaseDao() {
        return clientConfigDao;
    }

    @Override
    public String validClient(String clientId) {
        return clientConfigDao.validClient(clientId);
    }

    @Override
    public int delete(String clientId, String updateBy) {
        return clientConfigDao.deleteByPrimaryKey(clientId);
    }

    @Override
    public int maxSort() {
        return clientConfigDao.maxSort();
    }
}
