package com.gslandtreter.dnstracer.server.repository;

import com.gslandtreter.dnstracer.common.entity.VersionInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionInfoRepository extends JpaRepository<VersionInfoEntity, Integer> {

    @Query("SELECT v FROM VersionInfoEntity v WHERE v.region = ?1 AND v.endDate = null")
    VersionInfoEntity findUnfinishedVersion(String region);

    @Query("SELECT v FROM VersionInfoEntity v WHERE v.endDate = null")
    List<VersionInfoEntity> getRunningExecutions();

    @Query("SELECT v FROM VersionInfoEntity v WHERE v.endDate is not null")
    List<VersionInfoEntity> getFinishedExecutions();
}
