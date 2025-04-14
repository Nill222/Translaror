package my.kukish.translator.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "movies")
public class Movie extends TimestampedEntity<Long> {
    @Id
    private Long id;
    private String title;
    private String description;
    @Column(name = "video_url")
    private String videoUrl;
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
    private ZonedDateTime uploadedAt;
    @Override
    protected void onCreate() {
        this.uploadedAt = ZonedDateTime.now();
    }

}
