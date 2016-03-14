package HuntApi.Model;

import java.util.List;

/**
 * Created by Iury on 3/14/2016.
 */
public class QuestGeolocalizada {

    private int id;

    private String nome;

    private List<Etapa> etapas;

    private Usuario usuario;

    private CordenadaGeografica cordenada;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
    }

    public void addNovaEtapa(Etapa etapa) {
        etapas.add(etapa);
    }

    public void addEtapaPosicao(Etapa etapa, int posicao) {
        etapas.add(posicao, etapa);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Etapa> getEtapas() {
        return etapas;
    }

    public CordenadaGeografica getCordenada() {
        return cordenada;
    }

    public void setCordenada(CordenadaGeografica cordenada) {
        this.cordenada = cordenada;
    }

}
