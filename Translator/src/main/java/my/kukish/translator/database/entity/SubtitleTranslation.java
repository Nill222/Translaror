package my.kukish.translator.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "subtitle_translations")
public class SubtitleTranslation extends TimestampedEntity<Integer> {
    @Id
    private Integer id;

    @Column(name = "selected_text")
    private String selectedText;

    @Column(name = "translated_text")
    private String translatedText;

    @Column(name = "source_lang")
    private String sourceLanguage;

    @Column(name = "target_lang")
    private String targetLanguage;

    @Column(name = "translated_at")
    private ZonedDateTime translatedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subtitle_id")
    private Subtitle subtitle;

    @Override
    protected void onCreate(){
        translatedAt = ZonedDateTime.now();
    }


}
