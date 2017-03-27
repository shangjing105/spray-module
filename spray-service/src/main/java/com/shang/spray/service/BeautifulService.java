package com.shang.spray.service;

import com.shang.spray.entity.Beautiful;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * info:
 * Created by shang on 16/7/21.
 */
@Service
@CacheConfig(cacheNames = "beautifulService")
public class BeautifulService extends BaseService<Beautiful>{

    @Cacheable(key = "'Beautiful_findAllApi'+#pageable.pageNumber")
    public Page<Beautiful> findAllApi(Specification<Beautiful> specification, Pageable pageable) {
        return beautifulRepository.findAll(specification, pageable);
    }

}
