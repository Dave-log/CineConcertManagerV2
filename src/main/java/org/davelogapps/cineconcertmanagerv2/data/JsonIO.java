package org.davelogapps.cineconcertmanagerv2.data;

import org.davelogapps.cineconcertmanagerv2.model.Sequence;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonIO {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static Sequence readSequenceFromJson(String path) {
        try {
            return objectMapper.readValue(new File(path), Sequence.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void writeSequenceToJson(Sequence sequence, String path) {
        try {
            objectMapper.writeValue(new File(path), sequence);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
