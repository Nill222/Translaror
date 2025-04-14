package my.kukish.translator.database.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class TimestampedEntity<T extends Serializable> implements BaseEntity<T> {



    @PrePersist
    protected void onCreate() {
    }


}
