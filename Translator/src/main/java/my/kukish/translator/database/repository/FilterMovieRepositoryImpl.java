//package my.kukish.translator.database.repository;
//
//import com.querydsl.jpa.impl.JPAQuery;
//import jakarta.persistence.EntityManager;
//import lombok.RequiredArgsConstructor;
//import my.kukish.translator.database.entity.Movie;
//import my.kukish.translator.dto.MovieFilter;
//import my.kukish.translator.dto.QPredicates;
//import my.kukish.translator.database.entity.QMovie;
//
//
//import java.util.List;
//@RequiredArgsConstructor
//public class FilterMovieRepositoryImpl implements FilterMovieRepository {
//    private final EntityManager em;
//    @Override
//    public List<Movie> findAllByFilter(MovieFilter filter) {
//        var predicate = QPredicates.builder()
//                .add(filter.title(), movie.title::containsIgnoreCase)
//                .add(filter.description(), movie.description::containsIgnoreCase)
//                .build();
//        return new JPAQuery<Movie>(em)
//                .select(movie)
//                .from(movie)
//                .where(predicate)
//                .fetch();
//    }
//}
