package com.adamovichdev.movieawards.service.omdb.converter;

import com.adamovichdev.movieawards.service.movie.dto.MovieAwardInfoForUpdateDto;
import com.adamovichdev.movieawards.service.omdb.dto.OmdbInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.adamovichdev.movieawards.Util.LoggerUtil.*;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Service
public class OmdbDataToDataBaseDataMaper {

    public static final Logger logger = LoggerFactory.getLogger(OmdbDataToDataBaseDataMaper.class);

    private static final String EMPTY_IMDB_BOX_OFFICE = "N/A";
    private static final String REG_EX = "[$ .,]";

    public MovieAwardInfoForUpdateDto map(Long movieAwardId, OmdbInfoDto omdbInfo) {
        final String methodName = "map";
        logRequest(logger, methodName, movieAwardId, omdbInfo);

        final MovieAwardInfoForUpdateDto infoForUpdate = new MovieAwardInfoForUpdateDto();
        infoForUpdate.setMovieAwardId(movieAwardId);
        infoForUpdate.setImdbRating(omdbInfo.getImdbRating());
        setBoxOffice(infoForUpdate, omdbInfo);

        logResponse(logger, methodName, omdbInfo);
        return infoForUpdate;
    }

    private void setBoxOffice(MovieAwardInfoForUpdateDto infoForUpdate, OmdbInfoDto omdbInfo) {
        final String methodName = "setBoxOffice";
        logRequest(logger, methodName, infoForUpdate, omdbInfo);

        final String boxOffice = omdbInfo.getBoxOffice();
        if (isNotBlank(boxOffice) && !EMPTY_IMDB_BOX_OFFICE.equals(boxOffice)) {
            try {
                Long movieAwardBoxOffice = Long.valueOf(boxOffice.replaceAll(REG_EX, ""));
                infoForUpdate.setBoxOffice(movieAwardBoxOffice);
            } catch (NumberFormatException e) {
                logException(logger, methodName, e);
            }
        }
    }
}
