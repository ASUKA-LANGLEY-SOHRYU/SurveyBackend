package com.prosvirnin.alphabackend.service;

import com.prosvirnin.alphabackend.model.company.Company;
import com.prosvirnin.alphabackend.model.survey.Answers;
import com.prosvirnin.alphabackend.model.survey.Survey;
import com.prosvirnin.alphabackend.model.survey.SurveyRequest;
import com.prosvirnin.alphabackend.model.user.User;
import com.prosvirnin.alphabackend.repository.AnswersRepository;
import com.prosvirnin.alphabackend.repository.CompanyRepository;
import com.prosvirnin.alphabackend.repository.SurveyRepository;
import com.prosvirnin.alphabackend.repository.UserRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class SurveyService {

    private final List<String> extensions;
    private final SurveyRepository surveyRepository;
    private final AnswersRepository answersRepository;

    private final CompanyRepository companyRepository;

    private final UserRepository userRepository;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository, AnswersRepository answersRepository, CompanyRepository companyRepository, UserRepository userRepository) {
        this.surveyRepository = surveyRepository;
        this.answersRepository = answersRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        extensions = List.of("bmp", "jpg", "jpeg", "png");
    }

    public Survey findById(Long id){
        return surveyRepository.findById(id).get();
    }

    @Transactional
    public boolean saveAnswers(Long id, String answers){
        if(!surveyRepository.existsById(id))
            return false;
        surveyRepository.findById(id).ifPresent(survey -> {
            Answers answersObj = new Answers(survey, answers);
            survey.addAnswers(answersObj);
            answersRepository.save(answersObj);
        });
        return true;
    }

    public List<Answers> getAllAnswers(Long surveyId){
        if(!surveyRepository.existsById(surveyId))
            return List.of();
        return surveyRepository.findById(surveyId).get().getAnswers();
    }

    private String savePicture(MultipartFile picture) throws IOException {
        if (picture == null)
            return null;
        String extension = FilenameUtils.getExtension(picture.getOriginalFilename());
        if (!extensions.contains(extension))
            return null;
        String name = UUID.randomUUID().toString() + "." + extension;
        byte[] bytes = picture.getBytes();

        //create dir
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "external_uploads");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        //create file
        File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();

        return name;
    }

    @Transactional
    public boolean save(SurveyRequest surveyRequest, MultipartFile picture) throws IOException {
        if(!companyRepository.existsById(surveyRequest.getCompanyId()))
            return false;
        String pictureName = savePicture(picture);
        Company company = companyRepository.getReferenceById(surveyRequest.getCompanyId());
        Survey survey = new Survey(surveyRequest.getText(), pictureName, surveyRequest.getQuestions(), company);
        company.addSurvey(survey);
        surveyRepository.save(survey);
        return true;
    }

    @Transactional
    public boolean takeSurvey(Long surveyId, String answers){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userRequest = (User) authentication.getPrincipal();

        Survey survey = surveyRepository.getReferenceById(surveyId);
        if (survey.getId() == 0) return false;

        Answers answersObj = new Answers(survey, answers);
        answersObj.setUser(userRequest);
        survey.addAnswers(answersObj);
        userRequest.addAnswersList(answersObj);
        answersRepository.save(answersObj);
        return true;
    }
}
