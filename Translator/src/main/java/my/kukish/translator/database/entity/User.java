package my.kukish.translator.database.entity;

import jakarta.persistence.*;
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
@Table(name = "users")
public class User extends TimestampedEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    @Column(name = "password_hash")
    private String passwordHash;
    @Column(name = "created_at")
    private ZonedDateTime createdAt;
    private Role role;

    @Override
    protected void onCreate() {
        this.createdAt = ZonedDateTime.now();
    }




}
