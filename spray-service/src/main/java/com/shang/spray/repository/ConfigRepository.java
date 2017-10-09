package com.shang.spray.repository;

import com.shang.spray.entity.Config;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * info:
 * Created by shang on 16/8/30.
 */
public interface ConfigRepository extends BaseRepository<Config> {

    @Query(value = "SELECT QRTZ_JOB_DETAILS.JOB_NAME,QRTZ_JOB_DETAILS.JOB_GROUP,QRTZ_JOB_DETAILS.JOB_CLASS_NAME,QRTZ_TRIGGERS.TRIGGER_NAME,QRTZ_TRIGGERS.TRIGGER_STATE,QRTZ_TRIGGERS.TRIGGER_GROUP,QRTZ_CRON_TRIGGERS.CRON_EXPRESSION,QRTZ_CRON_TRIGGERS.TIME_ZONE_ID FROM QRTZ_JOB_DETAILS JOIN QRTZ_TRIGGERS JOIN QRTZ_CRON_TRIGGERS ON QRTZ_JOB_DETAILS.JOB_NAME = QRTZ_TRIGGERS.JOB_NAME AND QRTZ_TRIGGERS.TRIGGER_NAME = QRTZ_CRON_TRIGGERS.TRIGGER_NAME AND QRTZ_TRIGGERS.TRIGGER_GROUP = QRTZ_CRON_TRIGGERS.TRIGGER_GROUP",
            nativeQuery = true)
    List<Object[]> getJobAndTriggerDetails();
}
