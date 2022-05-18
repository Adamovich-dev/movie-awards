package com.adamovichdev.movieawards.service.omdb.mapper;

import com.adamovichdev.movieawards.service.movie.dto.MovieAwardInfoForUpdateDto;
import com.adamovichdev.movieawards.service.omdb.dto.OmdbInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OmdbDataToDataBaseDataMapperTest {

    private final OmdbDataToDataBaseDataMapper mapper = new OmdbDataToDataBaseDataMapper();

    @ParameterizedTest
    @MethodSource("getSourceAndExpectedValues")
    public void testMap(OmdbInfoDto omdbInfo, Double expectedImbRating, Long expectedBoxOffice) {
        final Long movieAwardId = 1L;
        MovieAwardInfoForUpdateDto result = mapper.map(movieAwardId, omdbInfo);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getMovieAwardId(), movieAwardId);
        Assertions.assertEquals(result.getBoxOffice(), expectedBoxOffice);
        Assertions.assertEquals(result.getImdbRating(), expectedImbRating);
    }

    private Object[][] getSourceAndExpectedValues() {
        OmdbInfoDto omdbInfo1 = new OmdbInfoDto();
        omdbInfo1.setImdbRating("2.0");
        omdbInfo1.setBoxOffice("$3,456");

        OmdbInfoDto omdbInfo2 = new OmdbInfoDto();
        omdbInfo2.setImdbRating("N/A");
        omdbInfo2.setBoxOffice("N/A");

        OmdbInfoDto omdbInfo3 = new OmdbInfoDto();
        omdbInfo3.setImdbRating("   ");
        omdbInfo3.setBoxOffice("   ");

        OmdbInfoDto omdbInfo4 = new OmdbInfoDto();

        OmdbInfoDto omdbInfo5 = new OmdbInfoDto();
        omdbInfo5.setImdbRating("dfg");
        omdbInfo5.setBoxOffice("$3,456dfgdfdgfd");

        return new Object[][] {
                {omdbInfo1, 2.0, 3456L},
                {omdbInfo2, null, null},
                {omdbInfo2, null, null},
                {omdbInfo3, null, null},
                {omdbInfo4, null, null},
                {omdbInfo5, null, null},
        };
    }
}
