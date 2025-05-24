package Miguelon.Mi_segundo_proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosTeam(int idTeam,
                        String strTeam,
                        String strTeamAlternate,
                        String strLeague) {
}
