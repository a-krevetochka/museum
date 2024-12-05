package com.mgtu.museum.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class BackupService {
    private final TaskPublisher taskPublisher;

    private static final long TIMEOUT_MILLIS = 60 * 1000;
    private static final String POSTGRES_CONTAINER_NAME = "docker-postgres-1";

    @Value("${backup.path:/default/path/to/backup/directory}")
    private String backupDir = null;

    @Value("${spring.datasource.username:default_username}")
    private String dbUser = null;

    @Value("${backup.dbName:default_db_name}")
    private String dbName = null;

    public CompletableFuture<String> backup() {
        String timestamp = LocalDate.now().toString();
        String backupFileName = "backup_" + timestamp + ".dump";
        String backupPath = Paths.get(backupDir, backupFileName).toString();

        return CompletableFuture.runAsync(() -> {
            try {
                Files.createDirectories(Paths.get(backupDir));
                createDump();
                copyBackupFromContainer(backupPath);
            } catch (Exception e) {
            }
        }).thenApply(v -> backupFileName);
    }

    private void createDump() throws Exception {
        String command = String.format("docker exec %s pg_dump -U %s -d %s -F c -b -v -f /tmp/backup.dump",
                POSTGRES_CONTAINER_NAME, dbUser, dbName);
        taskPublisher.submit(command, TIMEOUT_MILLIS);
    }

    private void copyBackupFromContainer(String backupPath) throws Exception {
        String copyCommand = String.format("docker cp %s:/tmp/backup.dump %s", POSTGRES_CONTAINER_NAME, backupPath);
        taskPublisher.submit(copyCommand, TIMEOUT_MILLIS);
    }

    private void applyDump() throws Exception {
        String restoreCommand = String.format("docker exec -t %s pg_restore -U %s -d %s -v /tmp/backup.dump",
                POSTGRES_CONTAINER_NAME, dbUser, dbName);
        taskPublisher.submit(restoreCommand, TIMEOUT_MILLIS);
    }
}
