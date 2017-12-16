package bigdata.bigdata.controller;

import bigdata.algorithms.*;
import bigdata.entities.TimeSeriesInputEntity;
import bigdata.repositories.TimeSeriesInputEntityRepository;
import bigdata.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
class HelloController {

    @Autowired
    TimeSeriesInputEntityRepository timeSeriesInputEntityRepository;

    @RequestMapping("/random")
    public double randomPrediction() {
        LinkedList<Double> ll = new LinkedList<>();

        ll.add(1.1);
        ll.add(1.2);
        ll.add(1.3);
        ll.add(1.4);
        ll.add(1.5);

        return RandomPrediction.predict(ll);
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
    public double backpropagationPrediction() {
        LinkedList<Double> ll = new LinkedList<>();

        ll.add(1.1);
        ll.add(1.2);
        ll.add(1.3);
        ll.add(1.4);
        ll.add(1.5);

        BackPropagation.init();
        return BackPropagation.predict(ll);
    }

    @RequestMapping("/cascor")
    public double cascorPrediction() {
        LinkedList<Double> ll = new LinkedList<>();

        ll.add(1.1);
        ll.add(1.2);
        ll.add(1.3);
        ll.add(1.4);
        ll.add(1.5);

        CasCor.init(ll);
        return CasCor.predict(ll);
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
        LinkedList<Double> ll = new LinkedList<>();

        String result = "";

        result += "Random " + randomPrediction() + "</br>";
        result += "BackPropagation " + backpropagationPrediction() + "</br>";
        result += "CasCor " + cascorPrediction() + "</br>";
        result += "EMA " + emaPrediction() + "</br>";
        result += "Homeostatic " + homeostaticPrediction() + "</br>";
        result += "Tendency " + tendencyPrediction() + "</br>";
        result += "Unix " + unixPrediction() + "</br>";
        result += "WMA " + wmaPrediction() + "</br>";

        String token = generateToken();
        resultFile(token, result);

        return result;
    }

    @RequestMapping(value = "/get", params = "token", method = GET)
    public String getFromToken(@RequestParam("token") String token) {
        String result = "No data available for this token";
        List<TimeSeriesInputEntity> sql_result = timeSeriesInputEntityRepository.findAll();
        boolean ok = true;
        for(TimeSeriesInputEntity res : sql_result) {
            if (res.getToken().equals(token)) {
                ok = false;
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
                break;
            }
        }
        return result;
    }

    private String generateToken() {
        String result = "";
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVXYZWabcdefghijklmnopqrstuvxyzw0123456789";
        int len = alphanumeric.length();
        boolean done = false;

        while (!done) {
            for (int i = 0; i < Constants.tokenSize; i++) {
                char c = alphanumeric.charAt((int)(Math.random() * len));
                result += c;
            }

            List<TimeSeriesInputEntity> sql_result = timeSeriesInputEntityRepository.findAll();
            boolean ok = true;
            for(TimeSeriesInputEntity res : sql_result) {
                if (res.getToken().equals(result)) {
                    ok = false;
                    break;
                }
                break;
            }
            if (ok == true)
                done = true;
        }

        return result;
    }

    public void resultFile(String token, String content) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(Constants.serverBasePath + Constants.delimiter + token));
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TimeSeriesInputEntity output = new TimeSeriesInputEntity(token);
        timeSeriesInputEntityRepository.save(output);
    }
}