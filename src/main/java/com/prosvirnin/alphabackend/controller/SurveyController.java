package com.prosvirnin.alphabackend.controller;

import com.prosvirnin.alphabackend.model.survey.Survey;
import com.prosvirnin.alphabackend.model.survey.SurveyRequest;
import com.prosvirnin.alphabackend.service.SurveyService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/survey")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    public @ResponseBody ResponseEntity<?> createSurvey(
            @RequestParam(name = "survey") String survey,
            @RequestParam(name = "picture", required = false) MultipartFile file) throws IOException {
        System.out.println(survey);
        ObjectMapper objectMapper = new ObjectMapper();
        SurveyRequest surveyRequest = objectMapper.readValue(survey, SurveyRequest.class);
        if(surveyService.save(surveyRequest, file))
            return ResponseEntity.ok(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    @PostMapping("/answer/{survey_id}")
    public ResponseEntity<?> takeSurvey(
            @PathVariable("survey_id") Long surveyId,
            @RequestBody String answers){
        if(surveyService.takeSurvey(surveyId, answers))
            return ResponseEntity.ok(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping("/{id}")
    public Survey getSurvey(@PathVariable("id") Long id){
        return surveyService.findById(id);
    }
}
