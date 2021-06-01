package oxy.kshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import oxy.kshop.mapper.ResourceRepository;
import oxy.kshop.model.entity.ResourceDO;
import oxy.kshop.service.IResourceService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl implements IResourceService {
    @Autowired
    ResourceRepository resourceRepository;

    @Override
    public Set<Long> getIdsByUserId(Long userId) {
        return resourceRepository.selectIdsByUserId(userId);
    }

    @Override
    public void insertResource(Collection<ResourceDO> resources) {

    }

    @Override
    public List<ResourceDO> getResourceByUserId(Long userId) {
        return null;
    }
}
