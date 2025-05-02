package my.kukish.translator.database.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@Service
public class VideoService {
    @Value("${app.video.bucket}")
    private String bucket;

    @SneakyThrows
    public void upload(String videoPath, InputStream content) {
        Path fullVideoPath = Paths.get(bucket, videoPath);

        try(content) {
            Files.createDirectories(fullVideoPath.getParent());
            Files.write(fullVideoPath, content.readAllBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public Optional<byte[]> get(String videoPath) {
        Path fullVideoPath = Paths.get(bucket, videoPath);

        return Files.exists(fullVideoPath)
                ? Optional.of(Files.readAllBytes(fullVideoPath))
                : Optional.empty();
    }
}
