package com.gslandtreter.dnstracer.server.controller;

import com.gslandtreter.dnstracer.common.beam.ExecutionStatistics;
import com.gslandtreter.dnstracer.common.entity.VersionInfoEntity;
import com.gslandtreter.dnstracer.server.repository.StatisticsRepository;
import com.gslandtreter.dnstracer.server.repository.VersionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VersionInfoController {

    @Autowired
    VersionInfoRepository versionInfoRepository;

    @Autowired
    StatisticsRepository statisticsRepository;

    @GetMapping("/versionInfo")
    public VersionInfoEntity getDnssEntityByVersion(@RequestParam(value = "region") String region,
                                                    HttpServletRequest request) {

        VersionInfoEntity versionInfoEntity = versionInfoRepository.findUnfinishedVersion(region);

        // Unfinished execution found. Return it.
        if(versionInfoEntity != null)
            return versionInfoEntity;

        // Create new execution
        versionInfoEntity = new VersionInfoEntity();
        versionInfoEntity.setRegion(region);
        versionInfoEntity.setWorkerIp(request.getRemoteAddr());
        versionInfoEntity.setStartDate(new Timestamp(System.currentTimeMillis()));

        versionInfoEntity = versionInfoRepository.save(versionInfoEntity);
        statisticsRepository.onNewExecutionStarted(versionInfoEntity);

        return versionInfoEntity;
    }

    @PostMapping("/versionInfo/{id}/finish")
    public VersionInfoEntity finishExecution(@PathVariable("id") int versionInfoId) {

        VersionInfoEntity versionInfoEntity = versionInfoRepository.findById(versionInfoId)
                .orElseThrow(() -> new RuntimeException(String.format("versionInfo %d not found.", versionInfoId)));

        versionInfoEntity.setEndDate(new Timestamp(System.currentTimeMillis()));

        versionInfoEntity = versionInfoRepository.save(versionInfoEntity);
        statisticsRepository.onExecutionFinished(versionInfoEntity);

        return versionInfoEntity;
    }

    @GetMapping("/allAvailableRuns")
    public List<VersionInfoEntity> getAllFinishedRuns() {
        return versionInfoRepository.getFinishedExecutions();
    }

}
