package bigdata.entities;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: radu
 * Date: 2/8/16
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="prediction_results")
public class TimeSeriesInputEntity {

    @Id
    @Column(name = "token", nullable = false)
    private String token;

    public TimeSeriesInputEntity() {}

    public TimeSeriesInputEntity(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
