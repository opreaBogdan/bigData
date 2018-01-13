package bigdata.bigdata.controller;

import bigdata.algorithms.*;
import bigdata.entities.TimeSeriesInputEntity;
import bigdata.entities.UserEntity;
import bigdata.repositories.TimeSeriesInputEntityRepository;
import bigdata.repositories.UserEntityRepository;
import bigdata.utils.Constants;
import bigdata.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
class HelloController {

    @Autowired
    TimeSeriesInputEntityRepository timeSeriesInputEntityRepository;
    @Autowired
    UserEntityRepository userEntityRepository;
    UserEntity user;

    private List<Double> aux = Arrays.asList(1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.0, 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.9);
    private LinkedList<Double> values = new LinkedList<>();
    private int predict_start = 5;
    private int prediction_time = 5;

    public boolean sanityCheck() {
        if (values.isEmpty())
            return false;
        return true;
    }

    @RequestMapping("/random")
    public LinkedList<Double> randomPrediction() {
        if(!sanityCheck()) {
            return null;
        }

        LinkedList<Double>  result = new LinkedList<>();
        LinkedList<Double>  to_predict = new LinkedList<>();
        int real_size = values.size();

        // add the number of real values
        result.add((double)real_size);

        // add real values
        result.addAll(values);

        // add initial values for prediction
        for (int i = 0; i < predict_start; i++) {
            result.add(values.get(i));
            to_predict.add(values.get(i));
        }

        for (int i = predict_start; i < real_size; i++) {
            double predicted_value = RandomPrediction.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(values.get(i));
        }

        for (int i = 0; i < prediction_time; i++) {
            double predicted_value = RandomPrediction.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(predicted_value);
        }

        return result;
    }

    @RequestMapping("/homeostatic")
    public LinkedList<Double> homeostaticPrediction() {
        if(!sanityCheck()) {
            return null;
        }

        LinkedList<Double>  result = new LinkedList<>();
        LinkedList<Double>  to_predict = new LinkedList<>();
        int real_size = values.size();

        // add the number of real values
        result.add((double)real_size);

        // add real values
        result.addAll(values);

        // add initial values for prediction
        for (int i = 0; i < predict_start; i++) {
            result.add(values.get(i));
            to_predict.add(values.get(i));
        }

        for (int i = predict_start; i < real_size; i++) {
            double predicted_value = Homeostatic.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(values.get(i));
        }

        for (int i = 0; i < prediction_time; i++) {
            double predicted_value = Homeostatic.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(predicted_value);
        }

        return result;
    }

    @RequestMapping("/backpropagation")
    public LinkedList backpropagationPrediction() {
        if(!sanityCheck()) {
            return null;
        }

        BackPropagation.init();

        LinkedList<Double>  result = new LinkedList<>();
        LinkedList<Double>  to_predict = new LinkedList<>();
        int real_size = values.size();

        // add the number of real values
        result.add((double)real_size);

        // add real values
        result.addAll(values);

        // add initial values for prediction
        for (int i = 0; i < predict_start; i++) {
            result.add(values.get(i));
            to_predict.add(values.get(i));
        }

        for (int i = predict_start; i < real_size; i++) {
            double predicted_value = BackPropagation.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(values.get(i));
        }

        for (int i = 0; i < prediction_time; i++) {
            double predicted_value = BackPropagation.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(predicted_value);
        }

        return result;
    }

    @RequestMapping("/cascor")
    public LinkedList<Double> cascorPrediction() {
        if(!sanityCheck()) {
            return null;
        }

        LinkedList<Double>  result = new LinkedList<>();
        LinkedList<Double>  to_predict = new LinkedList<>();
        int real_size = values.size();

        // add the number of real values
        result.add((double)real_size);

        // add real values
        result.addAll(values);

        // add initial values for prediction
        for (int i = 0; i < predict_start; i++) {
            result.add(values.get(i));
            to_predict.add(values.get(i));
        }

        for (int i = predict_start; i < real_size; i++) {
            CasCor.init(to_predict);
            double predicted_value = CasCor.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(values.get(i));
        }

        for (int i = 0; i < prediction_time; i++) {
            CasCor.init(to_predict);
            double predicted_value = CasCor.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(predicted_value);
        }

        return result;
    }

    @RequestMapping("/ema")
    public LinkedList<Double> emaPrediction() {
        if(!sanityCheck()) {
            return null;
        }

        LinkedList<Double>  result = new LinkedList<>();
        LinkedList<Double>  to_predict = new LinkedList<>();
        int real_size = values.size();

        // add the number of real values
        result.add((double)real_size);

        // add real values
        result.addAll(values);

        // add initial values for prediction
        for (int i = 0; i < predict_start; i++) {
            result.add(values.get(i));
            to_predict.add(values.get(i));
        }

        for (int i = predict_start; i < real_size; i++) {
            double predicted_value = EMA.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(values.get(i));
        }

        for (int i = 0; i < prediction_time; i++) {
            double predicted_value = EMA.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(predicted_value);
        }

        return result;
    }

    @RequestMapping("/tendency")
    public LinkedList<Double> tendencyPrediction() {
        if(!sanityCheck()) {
            return null;
        }

        LinkedList<Double>  result = new LinkedList<>();
        LinkedList<Double>  to_predict = new LinkedList<>();
        int real_size = values.size();

        // add the number of real values
        result.add((double)real_size);

        // add real values
        result.addAll(values);

        // add initial values for prediction
        for (int i = 0; i < predict_start; i++) {
            result.add(values.get(i));
            to_predict.add(values.get(i));
        }

        for (int i = predict_start; i < real_size; i++) {
            double predicted_value = Tendency_based.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(values.get(i));
        }

        for (int i = 0; i < prediction_time; i++) {
            double predicted_value = Tendency_based.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(predicted_value);
        }

        return result;
    }

    @RequestMapping("/unix")
    public LinkedList<Double> unixPrediction() {
        if(!sanityCheck()) {
            return null;
        }

        LinkedList<Double>  result = new LinkedList<>();
        LinkedList<Double>  to_predict = new LinkedList<>();
        int real_size = values.size();

        // add the number of real values
        result.add((double)real_size);

        // add real values
        result.addAll(values);

        // add initial values for prediction
        for (int i = 0; i < predict_start; i++) {
            result.add(values.get(i));
            to_predict.add(values.get(i));
        }

        for (int i = predict_start; i < real_size; i++) {
            double predicted_value = UnixCPULoadPrediction.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(values.get(i));
        }

        for (int i = 0; i < prediction_time; i++) {
            double predicted_value = UnixCPULoadPrediction.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(predicted_value);
        }

        return result;
    }

    @RequestMapping("/wma")
    public LinkedList<Double> wmaPrediction() {
        if(!sanityCheck()) {
            return null;
        }

        LinkedList<Double>  result = new LinkedList<>();
        LinkedList<Double>  to_predict = new LinkedList<>();
        int real_size = values.size();

        // add the number of real values
        result.add((double)real_size);

        // add real values
        result.addAll(values);

        // add initial values for prediction
        for (int i = 0; i < predict_start; i++) {
            result.add(values.get(i));
            to_predict.add(values.get(i));
        }

        for (int i = predict_start; i < real_size; i++) {
            double predicted_value = WMA.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(values.get(i));
        }

        for (int i = 0; i < prediction_time; i++) {
            double predicted_value = WMA.predict(to_predict);
            result.add(predicted_value);
            to_predict.add(predicted_value);
        }

        return result;
    }

    @RequestMapping("/all")
    public String allPredictions() {
        if (user == null)
            return "Please login to start simulating";

        LinkedList<Double> ll = new LinkedList<>();
        String algorithm = "TODO";
        String result = "";

        result += "Random " + randomPrediction() + "</br>";
        result += "BackPropagation " + backpropagationPrediction() + "</br>";
        result += "CasCor " + cascorPrediction() + "</br>";
        result += "EMA " + emaPrediction() + "</br>";
        result += "Homeostatic " + homeostaticPrediction() + "</br>";
        result += "Tendency " + tendencyPrediction() + "</br>";
        result += "Unix " + unixPrediction() + "</br>";
        result += "WMA " + wmaPrediction() + "</br>";

        String token = Utils.generateToken(timeSeriesInputEntityRepository);
        Utils.writeResult(token, algorithm, result, timeSeriesInputEntityRepository);

        return result;
    }

    @RequestMapping(value = "/get", params = "token", method = GET)
    public String getFromToken(@RequestParam("token") String token) {
        List<TimeSeriesInputEntity> queryResult = timeSeriesInputEntityRepository.findByToken(token);
        String result = "No data available for this token";
        if (queryResult == null || queryResult.size() == 0) {
            return result;
        }

        TimeSeriesInputEntity res = queryResult.remove(0);
        try {
            result = "";
            BufferedReader br = new BufferedReader(new FileReader(Constants.serverBasePath + Constants.delimiter + token));
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateAlgorithm(@RequestParam Map<String,String> requestParams) throws Exception{
        if (user == null)
            return "Please login first";

        String token = requestParams.get("token");
        String description = requestParams.get("description");

        //perform DB operations
        timeSeriesInputEntityRepository.updateAlgorithm(description, token);
        return "Updated";
    }


    @ResponseBody
    @RequestMapping(value = "/uploadingFile", method = RequestMethod.POST ,consumes = {"application/x-www-form-urlencoded","multipart/form-data"})
    public String uploadFile(@ModelAttribute Receive response) throws Exception {
        if(response.getFile() == null) {
            throw new IllegalArgumentException("File not found");
        }
        BufferedReader readFIle = new BufferedReader(new InputStreamReader(response.getFile().getInputStream(), "UTF-8"));

        return "File has been uploaded";
    }

    @ResponseBody
    @RequestMapping(value = "/loginJava", method = RequestMethod.POST)
    public String login(@RequestParam Map<String,String> requestParams) throws Exception {
        return "Welcome "/* + user.getUsername()*/;
//        String username = requestParams.get("username");
//        String password = requestParams.get("password");
//        List<UserEntity> userEntities = userEntityRepository.findUser(username, password);
//        if (userEntities == null || userEntities.size() == 0) {
//            return "No user with those credentials";
//        }
//
//        user = userEntities.remove(0);
    }
}