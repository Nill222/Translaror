package my.kukish.translator.database.repository;

import my.kukish.translator.database.entity.SubtitleTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtitleTranslationRepository extends JpaRepository<SubtitleTranslation, Long> {
}
