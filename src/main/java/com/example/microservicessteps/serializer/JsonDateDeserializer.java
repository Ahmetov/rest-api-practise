package com.example.microservicessteps.serializer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class JsonDateDeserializer extends StdDeserializer<Date> {

    protected JsonDateDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String value = jsonParser.readValueAs(String.class);
        try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(value);
        } catch (DateTimeParseException | ParseException e) {
            throw new JsonParseException(jsonParser, "Неверный формат даты. Дата должна быть MM/dd/yyyy");
        }
    }
}
