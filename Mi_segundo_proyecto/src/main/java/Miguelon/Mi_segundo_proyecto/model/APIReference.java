package Miguelon.Mi_segundo_proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.crypto.Data;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class APIReference {

    private List<DatosTeam>teams;

    public List<DatosTeam> getTeams(){
        return teams;
    }
}
