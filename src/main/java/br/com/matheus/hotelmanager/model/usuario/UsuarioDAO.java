package br.com.matheus.hotelmanager.model.usuario;

import java.sql.ResultSet;

public interface UsuarioDAO {

    public boolean cadastraUsr(
            String nome_completo,
            String nome_usuario,
            String email,
            String senha
    );
    public boolean login(
            String nome_usuario,
            String senha
    );

    ResultSet selectUsuarios();
}
