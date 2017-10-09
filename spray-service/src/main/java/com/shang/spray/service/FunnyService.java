package com.shang.spray.service;

import com.shang.spray.entity.Funny;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * info:
 * Created by hyr on 16/8/13.
 */
@Service
public class FunnyService extends BaseService<Funny> {

    public Page<Funny> findAllApi(Specification<Funny> specification, Pageable pageable) {
        return funnyRepository.findAll(specification, pageable);
    }

}
