package HuntApi.Model;

import java.util.List;

/**
 * Created by Iury on 3/14/2016.
 */

public class Usuario {

    private int id;

    private String name;

    private String email;

    private String senha;

    private String endereco;

    private String nacionalidade;

    private String cep;

    private List<QuestGeolocalizada> minhasQuests;

    public void addQuest(QuestGeolocalizada quest) {
        minhasQuests.add(quest);
    }

    public void removeQuest(QuestGeolocalizada quest) {
        minhasQuests.remove(quest);
    }

    public List<QuestGeolocalizada> getMinhasQuests() {
        return minhasQuests;
    }

    public void setMinhasQuests(List<QuestGeolocalizada> minhasQuests) {
        this.minhasQuests = minhasQuests;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}