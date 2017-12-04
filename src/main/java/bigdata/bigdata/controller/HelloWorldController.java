package bigdata.bigdata.controller;

import bigdata.algorithms.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
class HelloController {

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

        return result;
    }
}