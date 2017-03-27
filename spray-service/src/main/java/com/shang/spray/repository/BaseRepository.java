package com.shang.spray.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * info:
 * Created by shang on 16/7/8.
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T,Integer>,JpaSpecificationExecutor<T> {
}
