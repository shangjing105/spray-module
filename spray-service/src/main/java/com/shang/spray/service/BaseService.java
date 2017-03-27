package com.shang.spray.service;

import com.shang.spray.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * info:
 * Created by shang on 16/7/8.
 */
@Transactional
public class BaseService<T> {

    @Autowired
    protected BaseRepository<T> baseRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected NewsRepository newsRepository;
    @Autowired
    protected TypeRepository typeRepository;
    @Autowired
    protected BeautifulRepository beautifulRepository;
    @Autowired
    protected FunnyRepository funnyRepository;
    @Autowired
    protected SourcesRepository sourcesRepository;


    public boolean exists(Integer id) {
        return baseRepository.exists(id);
    }

    public long count() {
        return baseRepository.count();
    }

    public long count(Specification<T> spec) {
        return baseRepository.count(spec);
    }

    public T save(T entity) {
        return baseRepository.save(entity);
    }

    public List<T> save(Iterable<T> entities) {
        return baseRepository.save(entities);
    }

    public T saveAndFlush(T entity) {
        return baseRepository.saveAndFlush(entity);
    }

    public void flush() {
        baseRepository.flush();
    }

    public void delete(Integer id) {
        baseRepository.delete(id);
    }

    public void delete(T entity) {
        baseRepository.delete(entity);
    }

    public void delete(Iterable<? extends T> entities) {
        baseRepository.delete(entities);
    }

    public void deleteAll() {
        baseRepository.deleteAll();
    }

    public void deleteInBatch(Iterable<T> entities) {
        baseRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch() {
        baseRepository.deleteAllInBatch();
    }

    public T findOne(Integer id) {
        return baseRepository.findOne(id);
    }

    public T findOne(Specification<T> spec) {
        return baseRepository.findOne(spec);
    }

    public List<T> findAll() {
        return baseRepository.findAll();
    }

    public List<T> findAll(Sort sort) {
        return baseRepository.findAll(sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    public List<T> findAll(Iterable<Integer> ids) {
        return baseRepository.findAll(ids);
    }

    public List<T> findAll(Specification<T> spec) {
        return baseRepository.findAll(spec);
    }

    public Page<T> findAll(Specification<T> specification, Pageable pageable) {
        return baseRepository.findAll(specification, pageable);
    }

    public List<T> findAll(Specification<T> spec, Sort sort) {
        return baseRepository.findAll(spec, sort);
    }

}
