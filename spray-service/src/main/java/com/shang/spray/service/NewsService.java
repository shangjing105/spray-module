package com.shang.spray.service;

import com.shang.spray.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * info:
 * Created by shang on 16/7/8.
 */
@Service
public class NewsService extends BaseService<News> {

    public Page<News> findAllApi(Specification<News> specification, Pageable pageable) {
        return newsRepository.findAll(specification, pageable);
    }

    public News findOneApi(Integer id) {
        return newsRepository.findOne(id);
    }
}
