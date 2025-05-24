package Miguelon.Mi_primer_proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

public class APIResponse {
    private List<DatosCarro> data;

    public List<DatosCarro> getData(){
        return data;
    }

}
