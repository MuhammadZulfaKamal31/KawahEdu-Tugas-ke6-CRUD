package kawahEdu.Pekanke6;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.quarkus.panache.common.Sort;

@Path("anime")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResourceAnime {
    // ===================================================GET========================================================
    @GET
    // mendapatkan semua list
    public List<Anime> daftarAnime() {
        return Anime.listAll(Sort.ascending("id"));
    }

    @GET
    // meendapatkan total jumlah daftar yang ada di tabel
    @Path("total")
    public Long totalDaftar() {
        return Anime.count();
    }

    @GET
    // mendapatkan total jumlah genre yang sama
    @Path("jumlahGenre")
    public Long tatalGenre(@QueryParam("genre") String genre) {
        return Anime.count("genre", genre);
    }

    @GET
    // mendapatkan berdasarkan id tertentu
    @Path("{id}")
    public Anime idAnime(@PathParam("id") Long id) {
        return Anime.findById(id);
    }

    @GET
    // mencari judul
    @Path("{judul}")
    public Anime cariJudul(@QueryParam("judul") String judul) {
        return Anime.find("judul = ?1", judul).firstResult();
    }

    @GET
    // mendapatkan semua genre yang sama
    @Path("genre")
    public List<Anime> cariGenre(@QueryParam("genre") String genre) {
        return Anime.list("genre", genre);
    }

    // ================================================ENDGET======================================================

    // ===================================================POST========================================================
    @POST
    // menambah daftar
    @Transactional
    public List<Anime> buatDaftar(Anime anime) {
        anime.persist();
        return Anime.listAll(Sort.ascending("id"));
    }
    // ================================================ENDPOST======================================================

    // ================================================PUT==========================================================
    @PUT
    // update semua data
    @Path("{id}")
    @Transactional
    public List<Anime> editDaftar(@PathParam("id") Long id, Anime edit) {
        Anime anime = Anime.findById(id);
        anime.judul = edit.judul;
        anime.synopsis = edit.synopsis;
        anime.studio = edit.studio;
        anime.genre = edit.genre;
        anime.episode = edit.episode;
        return Anime.listAll(Sort.ascending("id"));
    }

    // ================================================ENDPUT==========================================================

    // ================================================DELETE==========================================================
    @DELETE
    // menghapus berdasarkan id
    @Path("{id}")
    @Transactional
    public HashMap<String, Object> hapusAnime(@PathParam("id") Long id) {
        HashMap<String, Object> hasil = new HashMap<>();
        if (Anime.deleteById(id)) {
            hasil.put("Message :", "id dengan nama anime tersebut berhasil dihapus");
        } else {
            hasil.put("message :", "id dengan nama anime tersebut tidak ada");
        }
        return hasil;
    }

    @DELETE
    // menghapus semua judul yang sama
    @Path("judul")
    @Transactional
    public Long deleteJudul(@QueryParam("judul") String judul) {
        return Anime.delete("judul", judul);
    }

    @DELETE
    // menghapus semua kolom
    @Path("semua")
    @Transactional
    public Long deleteSemua() {
        return Anime.deleteAll();
    }
    // ================================================ENDDELETE==========================================================

}
