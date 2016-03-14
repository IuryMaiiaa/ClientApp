package HuntApi.Model;

/**
 * Created by Iury on 3/14/2016.
 */
public class Etapa {

        private long id;

        private int posicao;

        private String descricao;

        private String dica;

        private String palavraChave;

        private QuestGeolocalizada questGeolocalizada;

        public int getPosicao() {
            return posicao;
        }

        public void setPosicao(int posicao) {
            this.posicao = posicao;
        }

        public QuestGeolocalizada getQuestGeolocalizada() {
            return questGeolocalizada;
        }

        public void setQuestGeolocalizada(QuestGeolocalizada questGeolocalizada) {
            this.questGeolocalizada = questGeolocalizada;
        }

        public boolean comparaPalavraChave(String chave) {
            if(chave.equals(palavraChave)) {
                return true;
            }
            return false;
        }

        public String getPalavraChave() {
            return palavraChave;
        }

        public void setPalavraChave(String palavraChave) {
            this.palavraChave = palavraChave;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public String getDica() {
            return dica;
        }

        public void setDica(String dica) {
            this.dica = dica;
        }

}
