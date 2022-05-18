package com.adamovichdev.movieawards.service.omdb;

import com.adamovichdev.movieawards.service.movie.dto.MovieAwardInfoForUpdateDto;
import com.adamovichdev.movieawards.service.omdb.dto.OmdbInfoDto;
import com.adamovichdev.movieawards.service.omdb.mapper.OmdbDataToDataBaseDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.adamovichdev.movieawards.Util.LoggerUtil.logException;
import static com.adamovichdev.movieawards.Util.LoggerUtil.logRequest;

@Service
public class OmdbService {

    private static final Logger logger = LoggerFactory.getLogger(OmdbService.class);

    private final OmdbDataToDataBaseDataMapper omdbMapper;
    private final RestTemplate restTemplate;

    @Autowired
    public OmdbService(OmdbDataToDataBaseDataMapper omdbMapper, RestTemplate restTemplate) {
        this.omdbMapper = omdbMapper;
        this.restTemplate = restTemplate;
    }

    @Value("${omdb.api.key}")
    private String apiKey;
    @Value("${omdb.api.uri}")
    private String omdbUri;

    public List<MovieAwardInfoForUpdateDto> getOmdbDataForUpdateMovieAward(final Map<Long, String> movieAwardsIdNomineeMap) {
        final String methodName = "getOmdbDataForUpdateMovieAward";
        logRequest(logger, methodName, movieAwardsIdNomineeMap);

        final List<MovieAwardInfoForUpdateDto> omdbInfoForUpdate = new ArrayList<>(getCapacityForArrayFromMap(movieAwardsIdNomineeMap.size()));
        for (Map.Entry<Long, String> entry : movieAwardsIdNomineeMap.entrySet()) {
            final String title = entry.getValue();
            final Long movieAwardId = entry.getKey();
            ResponseEntity<OmdbInfoDto> omdbResponseEntity;
            try {
                omdbResponseEntity = restTemplate.getForEntity(omdbUri, OmdbInfoDto.class, apiKey, title);
            } catch (Exception e) {
                logException(logger, methodName, e);
                continue;
            }

            final OmdbInfoDto omdbInfo = omdbResponseEntity.getBody();
            if (omdbInfo != null) {
                final String omdbTitle = omdbInfo.getTitle();
                //it's need, because if we look up "Cries and Whispers" - 1973 year movie, omdb will return "Solace: Video Essay for Cries and Whispers" - 2015 year
                if (title.equalsIgnoreCase(omdbTitle)) {
                    final MovieAwardInfoForUpdateDto infoForUpdate = omdbMapper.map(movieAwardId, omdbInfo);
                    omdbInfoForUpdate.add(infoForUpdate);
                } else {
                    logger.error(String.format("OmdbService return movie with another title, search was by: %s, return: %s", title, omdbTitle));
                }
            } else {
                logger.error(String.format("OmdbService return response without body, http status: %s\n" +
                        "search by title: %s", omdbResponseEntity.getStatusCode(), title));
            }
        }

        return omdbInfoForUpdate;
    }

    private int getCapacityForArrayFromMap(int mapCapacity) {
        return mapCapacity - (mapCapacity / 4);
    }
}
