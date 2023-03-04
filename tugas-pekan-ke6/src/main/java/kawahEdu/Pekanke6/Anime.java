package kawahEdu.Pekanke6;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
// @Table(name = "Anime")
public class Anime extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // @Column(name = "id", nullable = false)
    public Long id;

    @JsonGetter
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public String judul;
    public String synopsis;
    public String studio;
    public String genre;
    public Integer episode;
}
// ================contoh penulisan diPOSTMAN=============================
// {
// "judul": "string",
// "synopsis": "string",
// "studio": "string",
// "genre": "string",
// "episode": 0
// }

// ===========================================