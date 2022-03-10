package com.example.javafxgame.connection;

import com.example.javafxgame.data.EstatJoc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonClass {

    public String getJSON(EstatJoc estatJoc) {
        String resposta="No s'ha processat el json";
        try {
            resposta= new ObjectMapper().writeValueAsString(estatJoc);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resposta;
    }
}
