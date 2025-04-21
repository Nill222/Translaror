package my.kukish.translator.database.entity;

import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@QueryEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "movies")
public class Movie extends TimestampedEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
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
