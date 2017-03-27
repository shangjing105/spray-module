package com.shang.spray.service;

import com.shang.spray.entity.Type;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * info:
 * Created by shang on 2016/10/17.
 */
@Service
@CacheConfig(cacheNames = "typeService")
public class TypeService extends BaseService<Type> {

    @Cacheable(key = "'Type_findAllApi'+#pageable.pageNumber")
    public Page<Type> findAllApi(Specification<Type> specification, Pageable pageable) {
        return typeRepository.findAll(specification, pageable);
    }

}
