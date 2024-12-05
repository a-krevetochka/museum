package com.mgtu.museum.controller.Backup;

import com.mgtu.museum.service.BackupService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Log
@RestController("api/v1/backup/")
@AllArgsConstructor
public class BackupController {

    private final BackupService backupService;

    @PostMapping("create")
    public CompletableFuture<ResponseEntity<String>> createBackup() {
        return backupService.backup()
                .thenApply(backupFileName -> ResponseEntity.ok(backupFileName));

    }

}
