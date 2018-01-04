package bigdata.utils;

import bigdata.entities.TimeSeriesInputEntity;
import bigdata.repositories.TimeSeriesInputEntityRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Utils {

    public static String generateToken(TimeSeriesInputEntityRepository timeSeriesInputEntityRepository) {
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

    public static void writeResult(String token, String algorithm, String content, TimeSeriesInputEntityRepository timeSeriesInputEntityRepository) {
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
