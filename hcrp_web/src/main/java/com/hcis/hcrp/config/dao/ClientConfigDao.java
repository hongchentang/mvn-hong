package com.hcis.hcrp.config.dao;

import com.hcis.hcrp.config.entity.ClientConfig;
import com.hcis.hcrp.config.entity.ClientConfigExample;
import java.util.List;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author z
 * @date 2020/01/15
 */
@Repository("clientConfigDao")
public class ClientConfigDao extends MyBatisDao {

    int countByExample(ClientConfigExample example){
        return this.selectForInt(this.namespace + ".countByExample", example);
    }

    int deleteByExample(ClientConfigExample example){
        return this.delete(this.namespace + ".deleteByExample", example);
    }

    int deleteByPrimaryKey(String id){
        return this.delete(this.namespace + ".deleteByPrimaryKey", id);
    }

    int insert(ClientConfig record){
        return this.insert(this.namespace + ".insert", record);
    }

    int insertSelective(ClientConfig record){
        return this.insert(this.namespace + ".insertSelective", record);
    }

    List<ClientConfig> selectByExample(ClientConfigExample example){
        return this.selectForList(this.namespace + ".selectByExample", example);
    }


    int updateByExampleSelective(@Param("record") ClientConfig record, @Param("example") ClientConfigExample example){
        return 0;
    }

    int updateByExample(@Param("record") ClientConfig record, @Param("example") ClientConfigExample example){
        return 0;
    }

    int updateByPrimaryKeySelective(ClientConfig record){
        return this.update(this.namespace + ".updateByPrimaryKeySelective", record);
    }

    int updateByPrimaryKey(ClientConfig record){
        return this.update(this.namespace + ".updateByPrimaryKey", record);
    }

    public String validClient(String clientId) {
        return (String) this.selectOne(this.namespace + ".validClient", clientId);
    }

    public int maxSort() {
        return (int) this.selectOne(this.namespace + ".maxSort");
    }
}