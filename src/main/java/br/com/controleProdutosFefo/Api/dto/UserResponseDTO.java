package br.com.controleProdutosFefo.Api.dto;

public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String tipoUsuario;

    public UserResponseDTO(Long id, String username, String email, String tipoUsuario) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }
}
