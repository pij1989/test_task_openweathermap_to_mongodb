package com.pozharsky.dmitri.pojo;

import org.bson.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@org.springframework.data.mongodb.core.mapping.Document(collection = "openweathermap")
public class OpenWeatherMap {

    @Id
    private String id;

    @Indexed
    @Field("response_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime responseDate;


    @Field("response_status")
    private Integer responseStatus;

    @Field("response_body")
    private Document responseBody;

    public LocalDateTime getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(LocalDateTime responseDate) {
        this.responseDate = responseDate;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Document getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Document responseBody) {
        this.responseBody = responseBody;
    }
}
