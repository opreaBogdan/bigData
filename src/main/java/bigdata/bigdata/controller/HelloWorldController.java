package bigdata.bigdata.controller;

import bigdata.algorithms.*;
import bigdata.entities.TimeSeriesInputEntity;
import bigdata.entities.UserEntity;
import bigdata.repositories.TimeSeriesInputEntityRepository;
import bigdata.repositories.UserEntityRepository;
import bigdata.utils.Constants;
import bigdata.utils.Utils;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
class HelloController {

    @Autowired
    TimeSeriesInputEntityRepository timeSeriesInputEntityRepository;
    @Autowired
    UserEntityRepository userEntityRepository;
    UserEntity user;

    @RequestMapping("/random")
    public LinkedList<Double> randomPrediction() {
        LinkedList<Double> ll = new LinkedList<>();

        ll.add(1.1);
        ll.add(1.2);
        ll.add(1.3);
        ll.add(1.4);
        ll.add(1.5);

        ll.add (RandomPrediction.predict(ll));
        return ll;
    }

    @RequestMapping("/homeostatic")
    public double homeostaticPrediction() {
        LinkedList<Double> ll = new LinkedList<>();

        ll.add(1.1);
        ll.add(1.2);
        ll.add(1.3);
        ll.add(1.4);
        ll.add(1.5);

        return Homeostatic.predict(ll);
    }

    @RequestMapping("/backpropagation")
    public LinkedList<Double> backpropagationPrediction() {
        LinkedList<Double> ll = new LinkedList<>();
        ll.add(1.7);
        ll.add(1.4);
        ll.add(1.5);
        ll.add(1.2);
        ll.add(4.2);
        BackPropagation.init();
      //  BackPropagation bp = new BackPropagation(ll);
        ll.add(BackPropagation.predict(ll));


        return ll;
    }


    @RequestMapping("/backpropagationV")
    public LinkedList<Double> backpropagationPredictionRealValues() {
        LinkedList<Double> ll = new LinkedList<>();
        ll.add(1.7);
        ll.add(1.4);
        ll.add(1.5);
        ll.add(1.2);
        ll.add(4.2);
        //BackPropagation bp = new BackPropagation(ll);
        ll.add(BackPropagation.predict(ll));


        return ll;
    }

    @RequestMapping("/cascor")
    public LinkedList<Double> cascorPrediction() {
        LinkedList<Double> ll = new LinkedList<>();

        ll.add(1.1);
        ll.add(1.2);
        ll.add(1.3);
        ll.add(1.4);
        ll.add(1.5);

        CasCor.init(ll);
        ll.add(CasCor.predict(ll));
        return ll;
    }

    @RequestMapping("/ema")
    public double emaPrediction() {
        LinkedList<Double> ll = new LinkedList<>();

        ll.add(1.1);
        ll.add(1.2);
        ll.add(1.3);
        ll.add(1.4);
        ll.add(1.5);

        return EMA.predict(ll);
    }

    @RequestMapping("/tendency")
    public double tendencyPrediction() {
        LinkedList<Double> ll = new LinkedList<>();

        ll.add(1.1);
        ll.add(1.2);
        ll.add(1.3);
        ll.add(1.4);
        ll.add(1.5);

        return Tendency_based.predict(ll);
    }

    @RequestMapping("/unix")
    public double unixPrediction() {
        LinkedList<Double> ll = new LinkedList<>();

        ll.add(1.1);
        ll.add(1.2);
        ll.add(1.3);
        ll.add(1.4);
        ll.add(1.5);

        return UnixCPULoadPrediction.predict(ll);
    }

    @RequestMapping("/wma")
    public double wmaPrediction() {
        LinkedList<Double> ll = new LinkedList<>();

        ll.add(1.1);
        ll.add(1.2);
        ll.add(1.3);
        ll.add(1.4);
        ll.add(1.5);

        return WMA.predict(ll);
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