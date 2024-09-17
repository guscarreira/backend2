package br.com.luizgustavo.medicalconsult.consulta.services;

import br.com.luizgustavo.medicalconsult.consulta.domain.Consulta;
import br.com.luizgustavo.medicalconsult.consulta.repositories.ConsultaRepository;
import br.com.luizgustavo.medicalconsult.usuario.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta cadastrarconsulta(Consulta consulta){
        consulta.setIdConsulta(null);
        return consultaRepository.save(consulta);
    }


}
