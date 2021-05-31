package oxy.kshop.service;

import org.w3c.dom.stylesheets.LinkStyle;
import oxy.kshop.model.entity.ResourceDO;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IResourceService {
    /**
     * 根据用户id获取该用户拥有的资源id
     * @param userId 用户id
     * @return 权限id集合
     */
    Set<Long> getIdsByUserId(Long userId);

    /**
     * 批量增加接口类型的资源
     * @param resources 资源对象集合
     */
    void insertResource(Collection<ResourceDO> resources);

    /**
     * 根据用户id获取干用户所有用户资源对象
     * @param userId 用户id
     * @return 资源对象集合
     */
    List<ResourceDO> getResourceByUserId(Long userId);
}
