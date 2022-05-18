package com.adamovichdev.movieawards.service.omdb;

import com.adamovichdev.movieawards.service.movie.dto.MovieAwardInfoForUpdateDto;
import com.adamovichdev.movieawards.service.omdb.converter.OmdbDataToDataBaseDataMaper;
import com.adamovichdev.movieawards.service.omdb.dto.OmdbInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OmdbService {

    private final OmdbDataToDataBaseDataMaper omdbMapper;

    @Autowired
    public OmdbService(OmdbDataToDataBaseDataMaper omdbMapper) {
        this.omdbMapper = omdbMapper;
    }

    @Value("${omdb.api.key}")
    private String apiKey;
    @Value("${omdb.api.uri}")
    private String omdbUri;

    public List<MovieAwardInfoForUpdateDto> getOmdbDataForUpdateMovieAward(final Map<Long, String> movieAwardsIdNomineeMap) {
        List<MovieAwardInfoForUpdateDto> omdbInfoForUpdate = new ArrayList<>(movieAwardsIdNomineeMap.size() / 4 *3);
        RestTemplate restTemplate = new RestTemplate();
        for (Map.Entry<Long, String> entry : movieAwardsIdNomineeMap.entrySet()) {
            final String title = entry.getValue();
            final Long movieAwardId = entry.getKey();
            ResponseEntity<OmdbInfoDto> omdbResponseEntity;
            try {
                omdbResponseEntity = restTemplate.getForEntity(omdbUri, OmdbInfoDto.class, apiKey, title);
            } catch (Exception e) {
                System.out.println("Ошибка при поиске = " + title);
                continue;
            }

            OmdbInfoDto omdbInfo = omdbResponseEntity.getBody();
            //it's need, because if we look up "Cries and Whispers" movie, omdb will return "Solace: Video Essay for Cries and Whispers"
            if (omdbInfo != null && title.equalsIgnoreCase(omdbInfo.getTitle())) {
                MovieAwardInfoForUpdateDto infoForUpdate = omdbMapper.map(movieAwardId, omdbInfo);
                omdbInfoForUpdate.add(infoForUpdate);
            } else {
                //todo logger - notFound
            }

        }

        return omdbInfoForUpdate;
    }
}
