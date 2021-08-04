package com.example.microservicessteps.model;

import com.example.microservicessteps.serializer.JsonDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    private Integer id;

    @Size(min = 2, max = 30)
    private String name;

    @Past
    //@JsonDeserialize(using = JsonDateDeserializer.class)
    private Date birthDate;
}
