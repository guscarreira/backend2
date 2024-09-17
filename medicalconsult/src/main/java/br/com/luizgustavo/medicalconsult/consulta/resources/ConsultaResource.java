package br.com.luizgustavo.medicalconsult.consulta.resources;

import br.com.luizgustavo.medicalconsult.consulta.domain.Consulta;
import br.com.luizgustavo.medicalconsult.consulta.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/consultas")
public class ConsultaResource {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<Consulta> cadastrarConsulta(@RequestBody Consulta consulta){
        Consulta novaConsulta = consultaService.cadastrarconsulta(consulta);
        URI url = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id").buildAndExpand(novaConsulta.getIdConsulta()).toUri();
        return ResponseEntity.created(url).body(novaConsulta);
    }
}
