package br.kotlin.consultacepservice.commons;

import lombok.Getter;

import java.util.Optional;

public record Cachorro(
        String pedigre,
        String nome,
        String idade,
        String cor,
        String raca
){
    String acao(AcaoCachoroEnum tipo){
        return Optional.of(tipo)
                .filter(val -> AcaoCachoroEnum.valueOf(tipo.name()).equals(val))
                .map(AcaoCachoroEnum::getSomAcao)
                .orElse("sem som");
    }

    @Override
    public String pedigre() {
        return "Meu pedigre é ".concat(pedigre);
    }

    @Override
    public String nome() {
        return "Meu nome é ".concat(nome);
    }

    @Override
    public String idade() {
        return "Eu tenho idade ".concat(idade).concat(" anos de idade");
    }

    @Override
    public String cor() {
        return "Eu sou ".concat(cor);
    }

    @Override
    public String raca() {
        return "Minha raça é ".concat(raca);
    }

    @Getter
    public enum AcaoCachoroEnum{
        LATIR("AU AU"),
        CORRER("PLUT PLUT"),
        ANDAR("PU PU"),
        COMER("RUR RUR"),
        DORMIR("RHURHURHU"),
        BRINCAR("HIHIHIHI");

        private String somAcao;

        private AcaoCachoroEnum(String somAcao){
            this.somAcao = somAcao;
        }

    }

}
