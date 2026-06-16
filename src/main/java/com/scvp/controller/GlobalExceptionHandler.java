package com.scvp.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(IllegalArgumentException e, Model model) {
        model.addAttribute("erro", e.getMessage());
        return "erro";
    }

    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalState(IllegalStateException e, Model model) {
        model.addAttribute("erro", e.getMessage());
        return "erro";
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrity(DataIntegrityViolationException e, Model model) {
        String msg = e.getMessage() != null ? e.getMessage() : "";
        String mensagem;
        if (msg.contains("cpf")) mensagem = "CPF já cadastrado.";
        else if (msg.contains("email")) mensagem = "E-mail já cadastrado.";
        else if (msg.contains("cnpj")) mensagem = "CNPJ já cadastrado.";
        else mensagem = "Dado duplicado — verifique os campos únicos.";

        model.addAttribute("erro", mensagem);
        return "erro";
    }
}