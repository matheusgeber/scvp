package com.scvp.controller;

import com.scvp.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/login")
    public String form() {
        return "login";
    }

    @PostMapping("/login")
    public String autenticar(@RequestParam String email, @RequestParam String senha, HttpSession session, Model model) {

    	System.out.println("Email recebido: [" + email + "]");
        System.out.println("Senha recebida: [" + senha + "]");

        var usuarioOpt = usuarioRepository.findByEmailLogin(email);

        System.out.println("Usuário encontrado? " + usuarioOpt.isPresent());

        if (usuarioOpt.isPresent()) {
            System.out.println("Senha no banco: [" + usuarioOpt.get().getSenhaHash() + "]");
            System.out.println("Senhas iguais? " + usuarioOpt.get().getSenhaHash().equals(senha));
        }

        if (usuarioOpt.isEmpty() || !usuarioOpt.get().getSenhaHash().equals(senha)) {
            model.addAttribute("erro", "E-mail ou senha inválidos.");
            return "login";
        }
        
        var usuario = usuarioOpt.get();

        if (!Boolean.TRUE.equals(usuario.getAtivo())) {
            model.addAttribute("erro", "Usuário inativo. Contate o administrador.");
            return "login";
        }

        session.setAttribute("usuarioLogado", usuario.getEmailLogin());
        session.setAttribute("nomeUsuario",
                usuario.getFuncionario() != null ? usuario.getFuncionario().getNome() : "Usuário");
        session.setAttribute("perfil", usuario.getPerfil());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}